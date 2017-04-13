package com.sgfm.datacenter.action.order;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.entity.CgVariable;
import com.sgfm.datacenter.entity.SConsigneeAddress;
import com.sgfm.datacenter.entity.SOrder;
import com.sgfm.datacenter.entity.SReservation;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.exception.AppException;
import com.sgfm.datacenter.memcached.CacheHashMap;
import com.sgfm.datacenter.memcached.MemcachedConstant;
import com.sgfm.datacenter.service.order.OrderService;
import com.sgfm.datacenter.service.product.ProductService;
import com.sgfm.datacenter.util.CookieUtil;
import com.sgfm.datacenter.util.JsonResponseResult;
import com.sgfm.datacenter.util.SysUtils;
import com.sgfm.datacenter.util.Ip.IPSeeker;
import com.sgfm.datacenter.util.token.TokenProcessor;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class OrderAction extends BaseAction {
	private Log logger = LogFactory.getLog(OrderAction.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CacheHashMap cacheHashMap;
	
	public CgVariable cgVariable = new CgVariable();
	public TMember tMember = new TMember();
	public SReservation rs = new SReservation();
	public SOrder order = new SOrder();
	public SConsigneeAddress add = new SConsigneeAddress();
	
	public String addCardToCookie(){
		JsonResponseResult result = null;
		try {
			
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			if(SysUtils.isEmpty(tMember)){
				result = JsonResponseResult.createFalied("");
				String url = super.getRequest().getServletPath();
				String pid = super.getRequest().getParameter("pid");
				String cardType = super.getRequest().getParameter("cardType");
				String quantity = super.getRequest().getParameter("quantity");
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("pid", pid);
				jsonObject.put("cardType", cardType);
				jsonObject.put("quantity", quantity);
				result.addData(url);
				result.addData(jsonObject.toString());
			}else{
				TokenProcessor tokenProcessor = TokenProcessor.getInstance();
				String token = tokenProcessor.generateToken(super.getRequest());
				super.getRequest().getSession().setAttribute(SysConstant.TW_TOKEN, token);
				CookieUtil.addCookie(super.getResponse(), "M_MALL_BUYNOW", super.getRequest().getParameter("pid") + "," + super.getRequest().getParameter("cardType") + "," + super.getRequest().getParameter("quantity"), SysConstant.COOKIE_LIFRCYCLE_NOWADAY);
				result = JsonResponseResult.createSuccess();
				result.addData(token);
			}
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
			result = JsonResponseResult.createFalied(msg);

			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}

	public String toCardConfirm() {
		try {

			HashMap<String, Object> map = new HashMap<String, Object>();

			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			map.put("tMember", tMember);
			
			boolean flag = false;
			Cookie[] cookies = super.getRequest().getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("M_MALL_BUYNOW")) {
					map.put("cookie", cookie.getValue());
					flag = true;
					break;
				}
			}

			if (!flag) {
				return super.toAction("index", "indexPage2");
			}
			
			String cityNo = (String) super.getFromSession(SysConstant.SYS_CITYID);
			map.put("city", cityNo);
			
			HashMap<String, Object> reMap = orderService.doHandleCardCookie(map);
			super.getRequest().setAttribute("map", reMap);
			super.getRequest().setAttribute("token", super.getRequest().getParameter("TK"));

		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("order", "card_confirm");
	}
	
	
	
	public String addRsToCookie(){
		JsonResponseResult result = null;
		try {
			
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			if(SysUtils.isEmpty(tMember)){
				result = JsonResponseResult.createFalied("");
			}else{
				TokenProcessor tokenProcessor = TokenProcessor.getInstance();
				String token = tokenProcessor.generateToken(super.getRequest());
				super.getRequest().getSession().setAttribute(SysConstant.TW_TOKEN, token);
				CookieUtil.addCookie(super.getResponse(), "M_PLAT_BUYNOW", super.getRequest().getParameter("pid") + "," + super.getRequest().getParameter("quantity"), SysConstant.COOKIE_LIFRCYCLE_NOWADAY);
				result = JsonResponseResult.createSuccess();
				result.addData(token);
			}
			
			super.jsonResult = JSONObject.fromObject(result).toString();
			
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
			result = JsonResponseResult.createFalied(msg);

			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
	
	public String toRsConfirm() {
		try {

			HashMap<String, Object> map = new HashMap<String, Object>();

			boolean flag = false;
			Cookie[] cookies = super.getRequest().getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("M_PLAT_BUYNOW")) {
					map.put("cookie", cookie.getValue());
					flag = true;
					break;
				}
			}

			if (!flag) {
				return super.toAction("index", "indexPage2");
			}
			
			String cityNo = (String) super.getFromSession(SysConstant.SYS_CITYID);
			map.put("city", cityNo);
			
			HashMap<String, Object> reMap = orderService.doHandleRsCookie(map);
			super.getRequest().setAttribute("map", reMap);
			super.getRequest().setAttribute("title", "提交订单");
			super.getRequest().setAttribute("token", super.getRequest().getParameter("TK"));

		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("order", "rs_confirm");
	}
	
	public String doCreateReservation() {
		JsonResponseResult result = null;
		try {
			
			HashMap<String, Object> pro = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT + rs.getPid());
			if(SysUtils.isEmpty(pro)){
				result = JsonResponseResult.createFalied("");
				result.addData(0);
			}else{
				TokenProcessor tokenProcessor = TokenProcessor.getInstance();
				if (tokenProcessor.isTokenValid(super.getRequest(), super.getRequest().getParameter("plat_token"))) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("name", java.util.Arrays.asList(super.getRequest().getParameterValues("names")));
					map.put("sex", java.util.Arrays.asList(super.getRequest().getParameterValues("sexs")));
					map.put("id", java.util.Arrays.asList(super.getRequest().getParameterValues("ids")));
					map.put("age", java.util.Arrays.asList(super.getRequest().getParameterValues("ages")));
					map.put("mar", java.util.Arrays.asList(super.getRequest().getParameterValues("marrias")));
					map.put("mobile", java.util.Arrays.asList(super.getRequest().getParameterValues("mobiles")));
					
					map.put("rs", rs);
					map.put("quantity", super.getRequest().getParameter("quantity"));
					map.put("coupon", super.getRequest().getParameter("coupon"));

					tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
					IPSeeker ipSeeker = IPSeeker.getInstance();
					map.put("ip", ipSeeker.getIp(super.getRequest()));
					map.put("sumPrice", super.getRequest().getParameter("sumPrice"));
					map.put("mid", tMember.getMid());
					System.out.println("openid-----" + super.getFromSession(SysConstant.OPENID));
					System.out.println("mid-----" + tMember.getMid());
					
					/*boolean flag = false;
					Cookie[] cookies = super.getRequest().getCookies();
					for (Cookie cookie : cookies) {
						if(cookie.getName().equals("PLAT_CHECKED")){
							map.put("cookie", java.util.Arrays.asList(cookie.getValue().split("M")));
							map.put("flag", true);
							flag = true;
						}
					}
					if(!flag) map.put("flag", false);*/

					String oid = orderService.doCreateReservation(map);
					
					//到店付款预约 发送 短信和微信 通知信息
					if(!super.getRequest().getParameter("coupon").toString().equals("1")){
						map.put("type", "1");
						map.put("mid", tMember.getMid());
						map.put("openId", super.getFromSession(SysConstant.OPENID));
						orderService.sendMessage(map);
					}
					
					if(StringUtil.isBlank(oid) || oid.indexOf("YY") > -1){
						CookieUtil.addCookie(super.getResponse(), "M_PLAT_BUYNOW", "", SysConstant.COOKIE_LIFRCYCLE_DELETE);
						//CookieUtil.addCookie(super.getResponse(), "PLAT_CHECKED", "", SysConstant.COOKIE_LIFRCYCLE_DELETE);

						result = JsonResponseResult.createSuccess();
						result.addData(oid);
						tokenProcessor.resetToken(super.getRequest());
					}else{
						result = JsonResponseResult.createFalied(oid);
						result.addData(2);
					}
				} else {
					result = JsonResponseResult.createFalied("");
					result.addData(1);
				}
			}
			
			super.jsonResult = JSONObject.fromObject(result).toString();

		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.product.ProductAction.exception");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
	
	public String doCreateOrder(){
		JsonResponseResult result = null;
		try {
			
			HashMap<String, Object> pro = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT+super.getRequest().getParameter("pid"));
			if(SysUtils.isEmpty(pro)){
				result = JsonResponseResult.createFalied("");
				result.addData(0);
			}else{
				TokenProcessor tokenProcessor = TokenProcessor.getInstance();
				logger.info("页面传过来的token:-------" + super.getRequest().getParameter("plat_token") + "------" + new Date().toLocaleString());
				logger.info("session中的token:-------" + super.getFromSession(SysConstant.TW_TOKEN) + "------" + new Date().toLocaleString());
				if (tokenProcessor.isTokenValid(super.getRequest(), super.getRequest().getParameter("plat_token"))) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					
					Cookie[] cookies = super.getRequest().getCookies();
					for (Cookie cookie : cookies) {
						if (cookie.getName().equals("M_MALL_BUYNOW")) {
							map.put("cookie", cookie.getValue());
							break;
						}
					}
					
					tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
					IPSeeker ipSeeker = IPSeeker.getInstance();
					
					map.put("ip", ipSeeker.getIp(super.getRequest()));
					map.put("tMember", tMember);
					map.put("caid", super.getRequest().getParameter("caid") == null ? "0" : super.getRequest().getParameter("caid"));
					map.put("sumPrice", super.getRequest().getParameter("sumPrice"));
					map.put("integral", super.getRequest().getParameter("itg"));
					map.put("coupon", super.getRequest().getParameter("cp"));
					
					String oid = orderService.doCreateOrder(map);
					
					result = JsonResponseResult.createSuccess();
					result.addData(oid);
					tokenProcessor.resetToken(super.getRequest());
				} else {
					result = JsonResponseResult.createFalied("");
					result.addData(1);
				}
			}
			
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
			result = JsonResponseResult.createFalied(msg);

			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
	
	public String addMemberAddress(){
		JsonResponseResult result = null;
		try {
			
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			add.setMid(tMember.getMid());
			int count = orderService.addMemberAddress(add);
			if(count > 0){
				result = JsonResponseResult.createSuccess();
				result.addData(count);
			}else{
				result = JsonResponseResult.createFalied("请勿新增重复地址");
			}
			
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
			result = JsonResponseResult.createFalied(msg);

			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
	
	public String editAddress(){
		JsonResponseResult result = null;
		try {
			
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			add.setMid(tMember.getMid());
			orderService.updateMemberAddress(add);
			result = JsonResponseResult.createSuccess();
			
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
			result = JsonResponseResult.createFalied(msg);

			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
//	
//
//	public String finishOrderToPay() {
//		try {
//
//			HashMap<String, Object> map = orderService.finishOrderToPay(order);
//			if (SysUtils.isEmpty(map) || map.size() <= 0)
//				return super.toContentView("error", "lossProduct");
//			this.logger.debug("finishOrderToPay-》map:" + map);
//			super.getRequest().setAttribute("map", map);
//
//		} catch (final AppException app) {
//			this.logger.error(app);
//		} catch (final Exception e) {
//			this.logger.error(e);
//		}
//		return super.toContentView("order", "finish_order");
//	}
//
//	public String findOrderByOid() {
//		JsonResponseResult result = null;
//		try {
//			RPorder order = this.orderService.findOrderByOid(String.valueOf(this.order.getOid()));
//			super.jsonResult = JSONObject.fromObject(order).toString();
//			System.out.println(order.getKdNumber());
//
//		} catch (final AppException app) {
//			this.logger.error(app);
//			final String msg = this.getText(app.getMessage());
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		} catch (final Exception e) {
//			this.logger.error(e);
//			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
//			result = JsonResponseResult.createFalied(msg);
//
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		}
//		this.logger.debug("action:" + this.jsonResult);
//		return BaseAction.JSON;
//	}
//	
	/**
	 * 订单中心跳转付款页面
	 */
	public String toPayMent() {
		try {
			List<HashMap<String, Object>> list = orderService.findPidsByOid(String.valueOf(order.getOid()));
			if(list==null||list.size()==0) return super.toContentView("error", "lossProduct");
    		
			for(int i=0;i<list.size();i++){
    		HashMap<String, Object> pro = cacheHashMap.getProductByPid(MemcachedConstant.TW_PRODUCT+list.get(i).get("PID").toString());
			if(SysUtils.isEmpty(pro)) return super.toContentView("error", "lossProduct");
			}
			order = orderService.findOrderByOid(String.valueOf(order.getOid()));
			super.getRequest().setAttribute("order", order);

		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("payment", "payment");
	}
	
//	public String toMallPayMent() {
//		try {
//			List<HashMap<String, Object>> list = orderService.findProductByOid(String.valueOf(torder.getOid()));
//			if(list==null||list.size()==0) return super.toContentView("error", "lossProduct");
//    		
//			for(int i=0;i<list.size();i++){
//    		HashMap<String, Object> pro = CommHashMap.getProductHashMaps(MemcachedConstant.YY_PRODUCT+list.get(i).get("PID").toString());
//			if(pro.get("ALIVE").toString().equals("0")) return super.toContentView("error", "lossProduct");
//			}
//			torder = orderService.findMallOrderByOid(String.valueOf(torder.getOid()));
//			super.getRequest().setAttribute("order", torder);
//
//		} catch (final AppException app) {
//			this.logger.error(app);
//		} catch (final Exception e) {
//			this.logger.error(e);
//		}
//		return super.toContentView("payMent", "mallPayment");
//	}
//	
	/**
	 * 订单详情页
	 * @return
	 */
	public String toOrderDetail(){
		try {
			String oid=super.getRequest().getParameter("oid");
			HashMap<String, Object> orderInfo=orderService.getOrderInfoByOid(oid);
			super.getRequest().setAttribute("orderInfo", orderInfo);
			super.getRequest().setAttribute("title", "订单详情");
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("order", "orderDetail");			
	}
//	
//	//模拟支付
//	public String orderSimulation() {
//		JsonResponseResult result = null;
//		try {
//
//			Map returnMap = orderService.orderOverdueSimulation(super.getRequest().getParameter("oid"));
//			result = JsonResponseResult.createSuccess();
//			result.addData(returnMap);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//
//		} catch (final AppException app) {
//			this.logger.error(app);
//			final String msg = this.getText(app.getMessage());
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		} catch (final Exception e) {
//			this.logger.error(e);
//			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		}
//		return BaseAction.JSON;
//	}
//	
//	public String checkProductIsEmpty(){
//		JsonResponseResult result = null;
//		try {
//			
//			boolean bl = orderService.doCompareOrderDate(super.getRequest().getParameter("oid"));
//			boolean flag = orderService.checkProductIsEmpty(super.getRequest().getParameter("oid"));
//			result = JsonResponseResult.createSuccess();
//			result.addData(flag);
//			if(super.getRequest().getParameter("oid").indexOf("YY") > -1){
//				result.addData(1);
//			}else{
//				result.addData(0);
//			}
//			result.addData(super.getRequest().getParameter("oid"));
//			result.addData(bl);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//			
//		} catch (final AppException app) {
//			this.logger.error(app);
//			final String msg = this.getText(app.getMessage());
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		} catch (final Exception e) {
//			this.logger.error(e);
//			final String msg = this.getText("com.sgfm.datacenter.action.order.OrderAction.exception");
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		}
//		return BaseAction.JSON;
//	}
}
