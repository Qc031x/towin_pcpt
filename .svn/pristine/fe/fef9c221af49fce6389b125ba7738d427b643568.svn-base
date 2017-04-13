package com.sgfm.datacenter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.sgfm.datacenter.action.IndexAction;
import com.sgfm.datacenter.util.AppContext;
import com.sgfm.datacenter.util.Ip.IpCommtool;

/**
 * 数据中心权限验证过滤器
 * 
 * @概述：
 * 
 * @authorcliu @时间：2015-5-20 上午10:56:32
 */
public class IndexPageFilter implements Filter {
	private Log log = LogFactory.getLog(this.getClass());
	WebApplicationContext applicationContext = null;

	public void init(FilterConfig filterConfig) {
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		long t1 = System.currentTimeMillis();
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();

		/*
		 * String source= request.getParameter("source"); if(source!=null){
		 * servletRequest.getSession().setAttribute("source", source); }
		 */

		//
		// 获得页面地址，如:/DataCenter/contentRoot/login.do
		// String ur2l = servletRequest.getScheme()+"://"+
		// servletRequest.getServerName()+servletRequest.getRequestURI();
		String url = servletRequest.getRequestURI();
		String webapppath = servletRequest.getContextPath();
		if (url.equals("/")) {
			// response.setContentType("text/html; charset=UTF-8");
			// request.setAttribute("abc", "搞ff");
			ApplicationContext app = AppContext.getAppContext();
			IndexAction action = (IndexAction) app.getBean("indexAction");
			action.indexPage(servletRequest, servletResponse);
			log.info(url + ",本次首页请求花费：" + (System.currentTimeMillis() - t1));
			// servletResponse.sendRedirect("/0755");
			// request.getRequestDispatcher("http://127.0.0.1:8082/0755").forward(request,response);
			return;
		} else if (url.length() < 6 && url.indexOf(".") < 0) {
			/* cxj 404修改 */
			// 首先这一步过滤掉 非纯数字的以根开头的url 如 /abc 或 /12abc 或 /abc12 直接跳到404
			if (url.matches("^\\/([^0-9])+$")) {
				servletResponse.sendError(404);
				return;
			} else {
				// 如果是 数字的以根开头的 url 如 /123 判断cityMap里面有没有这个区号，若没有，跳到404
				if (url.indexOf("/") >= 0) {
					String urlWithoutRoot = url.substring(url.indexOf("/") + 1);
					if (null == IpCommtool.getMapByareaNo(urlWithoutRoot)) {
						servletResponse.sendError(404);
						return;
					}
				}
			}
			String cityId = url.substring(1, url.length());
			ApplicationContext app = AppContext.getAppContext();
			IndexAction action = (IndexAction) app.getBean("indexAction");
			action.indexPage2(servletRequest, servletResponse, cityId);
			log.info(url + ",本次首页请求花费：" + (System.currentTimeMillis() - t1));
			return;

		}

		filterChain.doFilter(request, response);

	}


	public void destroy() {

	}

}
