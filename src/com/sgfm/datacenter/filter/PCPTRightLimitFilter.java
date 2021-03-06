package com.sgfm.datacenter.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sgfm.base.util.PropsLoader;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.action.IndexAction;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.login.model.AuthorizedInfo;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.product.ProductService;
import com.sgfm.datacenter.service.user.UserService;
import com.sgfm.datacenter.util.AppContext;
import com.sgfm.datacenter.util.SpringContext;
import com.sgfm.datacenter.util.SysUtils;
import com.sgfm.datacenter.util.Ip.IPSeeker;
import com.sgfm.datacenter.util.Ip.IpCommtool;

import oracle.sql.DATE;

/**
 * 权限验证过滤器
 * 
 * @概述：
 * 
 * @author cliu
 * @时间：2015-5-3 上午10:56:32
 */
public class PCPTRightLimitFilter implements Filter {
	private Log log = LogFactory.getLog(this.getClass());
	WebApplicationContext applicationContext = null;

	public void init(FilterConfig filterConfig) {
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
	}

	public boolean checkCookInfo(HttpServletRequest httpservletrequest, HttpServletResponse response) {
		try {

			String requrl = httpservletrequest.getRequestURI();
			Enumeration head_names = httpservletrequest.getHeaderNames();
			String head_str = "";
			while (head_names.hasMoreElements()) {// 以此取出头信息
				String headerName = (String) head_names.nextElement();
				String headerValue = httpservletrequest.getHeader(headerName);// 取出头信息内容
				head_str += headerName + ":" + headerValue + "\n";

			}
			// log.info("head_names："+head_str+"
			// ip="+httpservletrequest.getRemoteAddr()+"\n"+"时间:"+(new
			// Date().toLocaleString()));

			// 如果用户进入这些页面均设置cookie,防止直接调用短信发送,因为这些是进入短信获取验证码时候的必须经过页面
			if (requrl.indexOf("toRegPage") > 0 || requrl.indexOf("toChangePhone") > 0
					|| requrl.indexOf("toShoppingcart") > 0) {

				// System.out.println("((((((((((((((((");
				Cookie cookie = new Cookie("yk_newpc_cook_flag", "666888");
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			// sendSecurityCodeToFindPwd：忘记密码
			// 。checkAccountOrRegister下单登录注册。直接注册：getAchCaptchas。
			if (requrl.indexOf("getAchCaptchas") > 0 || requrl.indexOf("checkAccountOrRegister") > 0) {
				if (head_str.indexOf("yk_newpc_cook_flag") > 0) {
					log.info("Y手机短信获取验证码有cookie" + "\n");
					return true;
				} else {
					log.info("N手机短信获取验证码没有cookie" + "\n");
					return false;
				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return true;

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		long t1 = System.currentTimeMillis();

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		// beg 短信攻击处理
		// if(!checkCookInfo(servletRequest,servletResponse)){
		// return;
		// }

		// end 短信攻击处理
		// 获得页面地址，如:/DataCenter/contentRoot/login.do
		String url = servletRequest.getRequestURI();
		log.info(url + ",商家后台请求原始地址：" + "\n");
		String webapppath = servletRequest.getContextPath();
		
		/*
		 * 这里将会从session中获得用户对象，并判断是否为空，如果为空，则证明该用户没有正常登录，程序将会跳转到错误页面提示用户登录
		 */
		//AuthorizedInfo authorizedUser = null;
		TMember authorizedUser = (TMember) session.getAttribute(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
		// session.getAttribute(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
		if (authorizedUser == null) {
			log.info("没有登录session:---------" + new Date().toLocaleString());
			if(checkUrl(webapppath, url, "request.url.reload.openid")){
				ApplicationContext app = AppContext.getAppContext();
				UserService userService = (UserService) app.getBean("userServiceImpl");
				
				log.info("没有登录session并且属于外部.do直接发起请求没有经过首页过滤器,所以用openid查询member表记录:---------" + new Date().toLocaleString());
				log.info("链接参数openid:---------" + request.getParameter("openId") + "----------" + new Date().toLocaleString());
				
				if(StringUtil.isBlank(request.getParameter("openId"))){
					dispatcherUrl(servletRequest, servletResponse);
				}
				TMember tMember = new TMember();
				tMember.setOpenid(request.getParameter("openId"));
				TMember openidMember = userService.findMemByOpenidOrMobile(tMember);
				if(!SysUtils.isEmpty(openidMember)){
					servletRequest.getSession().setAttribute(SysConstant.OPENID, request.getParameter("openId"));
					servletRequest.getSession().setAttribute(SysConstant.SYS_MEMBER_LOG_SUCC_INFO, openidMember);
					String cityInfo = (String) servletRequest.getSession().getAttribute(SysConstant.SYS_CITYID);
					if (cityInfo == null)
						setSessionCityInfo(servletRequest, servletResponse);
					
					filterChain.doFilter(request, response);
					return;
				}else{
					//TODO 跳转错误页面
					servletResponse.sendRedirect(webapppath + "/");
					return;
				}
			}
			
			
			log.info("没有登录session但不是外部.do请求:---------" + new Date().toLocaleString());
			if (checkUrl(webapppath, url, "request.url.no.intercept")) {
				
				if(StringUtil.isBlank(servletRequest.getSession().getAttribute(SysConstant.IS_MEMBER))){
					findMemberByOpenid(servletRequest, servletResponse);
				}
				
				// beg： 如果session过期后,再根据用户request的ip信息设置城市信息session
				String cityInfo = (String) servletRequest.getSession().getAttribute(SysConstant.SYS_CITYID);
				if (cityInfo == null)
					setSessionCityInfo(servletRequest, servletResponse);
				//// end： 如果session过期后,再根据用户request的ip信息
				filterChain.doFilter(request, response);
				return;
			} else {
				servletResponse.sendRedirect(webapppath + "/");
				return;
			}

		} else {

		}

		// 如果用户为注销动作,直接跳转到登录页面
		if (url.equalsIgnoreCase(webapppath + "/contentRoot/loginOut.do")) {
			// 不管哪边退出都清空所有ssocookie begin
			// CookieUtil.delSSOCookie((HttpServletRequest)request,
			// (HttpServletResponse)response, "ssoRevptLoginInfo");
			// CookieUtil.delSSOCookie((HttpServletRequest)request,
			// (HttpServletResponse)response, "ssoYkpfLoginInfo");
			// end
			session.invalidate();// 清空session
			authorizedUser = null;
			servletResponse.sendRedirect(webapppath + "/");
			return;
		}
		filterChain.doFilter(request, response);

		log.info(url + ",本次请求花费：" + (System.currentTimeMillis() - t1));
	}

	/**
	 * 检查当前请求URL是否不需要拦截.
	 * 
	 * @param webapppath
	 *            项目部署目录
	 * @param requestUrl
	 *            当前的请求URL
	 * @return true表示不需要拦截
	 */
	private boolean checkUrl(String webapppath, String requestUrl, String propertyName) {
		PropsLoader loader = (PropsLoader) SpringContext.getBean("propsLoader");
		String urlStr = loader.props.getProperty(propertyName);
		String[] urls = urlStr.split(";");
		for (String url : urls) {
			if ((webapppath + url).trim().equalsIgnoreCase(requestUrl)) {
				return true;
			}
		}
		if (requestUrl.indexOf("salearea") != -1) {
			return true;
		}
		return false;
	}

	/**
	 * Function: 验证该用户是否可以访问此URL
	 * 
	 * @param auth
	 *            保存授权信息的对象
	 * @param authURL
	 *            当前要访问道页面
	 * @return
	 */
	private boolean verifyPageAuth(AuthorizedInfo authInfo, String authURL) {
		boolean result = false;
		if (authInfo != null) {

			Map mapUrl = authInfo.getUrlMap();
			if (mapUrl.containsKey(authURL)) {
				return true;
			}
			Map map = (Map) applicationContext.getServletContext().getAttribute("");
			// Map map=authInfo.getAllMap();
			if (!map.containsKey(authURL)) {
				return true;
			}

		}
		return result;
	}

	public void setSessionCityInfo(HttpServletRequest request, HttpServletResponse response) {

		try {
			String cityInfo = (String) request.getSession().getAttribute(SysConstant.SYS_CITYID);
			// 如果用户是第一次返回我们的首页，session里面没有值
			if (cityInfo == null) {
				IPSeeker ipSeeker = IPSeeker.getInstance();
				String ip = ipSeeker.getIp(request);
				// String ip="219.137.150.255";
				ApplicationContext app = AppContext.getAppContext();
				CacheHashMap cacheMap = (CacheHashMap) app.getBean("cacheHashMap");
				String country = ipSeeker.getCountry(ip);
				List<HashMap<String, Object>> CityList = cacheMap.getValidCity(MemcachedConstant.TW_VALIDCITY);
				boolean flag = false;
				for (int i = 0; i < CityList.size(); i++) {
					// area =
					// userService.findAreaById(Integer.parseInt(String.valueOf(CityList.get(i).get("ID"))));
					String cityTitle = CityList.get(i).get("TITLE").toString();
					String cityId = CityList.get(i).get("ID").toString();
					if (country.indexOf(cityTitle) > -1) {
						// 获取首页城市
						request.setAttribute("cityname", cityTitle);
						// city = String.valueOf(cityId);
						request.getSession().setAttribute(SysConstant.SYS_CITYID, cityId);
						request.getSession().setAttribute(SysConstant.SYS_CITYNAME, cityTitle);
						String cityArea = IpCommtool.getMapByCityId(cityId);
						request.getSession().setAttribute(SysConstant.SYS_CITYAREANO, cityArea);
						flag = true;
						break;
					}
				}
				if (!flag) {
					request.setAttribute("cityname", "深圳");
					// city = "10312";
					request.getSession().setAttribute(SysConstant.SYS_CITYID, "10312");
					request.getSession().setAttribute(SysConstant.SYS_CITYNAME, "深圳");
					request.getSession().setAttribute(SysConstant.SYS_CITYAREANO, "0755");

				}
			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void findMemberByOpenid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		log.info("进入会员信息查询方法------" + new Date().toLocaleString());
		ApplicationContext app = AppContext.getAppContext();
		UserService userService = (UserService) app.getBean("userServiceImpl");
		
		String openid = request.getParameter("openId");
		//String openid = "oV3cDv0WSNZoJSks3HVGJbcZrbC8";
		System.out.println("过滤器获取openid-----------" + openid + "-------" +  new Date().toLocaleString());
		
		if(StringUtil.isBlank(openid)){
			dispatcherUrl(request, response);
		}
		TMember tMember = new TMember();
		tMember.setOpenid(openid);
		TMember openidMember = userService.findMemByOpenidOrMobile(tMember);
		
		if(SysUtils.isEmpty(openidMember)){
			//没有openid记录
			log.info("没有openid记录------" + new Date().toLocaleString());
			request.getSession().setAttribute(SysConstant.IS_MEMBER, 1);
		}else{
			if(StringUtil.isBlank(openidMember.getMobile())){
				//有openid记录但是是用身份证登录的团检
				log.info("有openid记录但是是用身份证登录的团检------" + new Date().toLocaleString());
				request.getSession().setAttribute(SysConstant.IS_MEMBER, 2);
			}else{
				//有openid记录并且是用手机登录的团检
				log.info("有openid记录并且是用手机登录的团检, 进行登录------" + new Date().toLocaleString());
				request.getSession().setAttribute(SysConstant.IS_MEMBER, 3);
				request.getSession().setAttribute(SysConstant.SYS_MEMBER_LOG_SUCC_INFO, openidMember);
			}
		}
		log.info("session存放openid------" + openid + "------------" + new Date().toLocaleString());
		request.getSession().setAttribute(SysConstant.OPENID, openid);
	}
	
	public void dispatcherUrl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("没有获取到openid,重定向到页面重新请求微信-----------" + new Date().toLocaleString());
		String domain = SysUtils.getRemoteDomain(request.getServerPort());
		request.setAttribute("url", domain + request.getRequestURI());
		request.getRequestDispatcher("/WEB-INF/content/error/lossOpenid.jsp").forward(request, response);
	}

	public void destroy() {

	}

}
