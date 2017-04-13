package com.sgfm.datacenter.action.user;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.base.util.DateUtil;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.entity.TUserMsgrecord;
import com.sgfm.datacenter.exception.AppException;
import com.sgfm.datacenter.mobile.NewHttpYKMsg;
import com.sgfm.datacenter.service.common.CommonService;
import com.sgfm.datacenter.service.order.OrderService;
import com.sgfm.datacenter.service.reservation.ReservationService;
import com.sgfm.datacenter.service.user.UserService;
import com.sgfm.datacenter.util.DateTool;
import com.sgfm.datacenter.util.JsonResponseResult;
import com.sgfm.datacenter.util.SysUtils;
import com.sgfm.datacenter.util.Ip.IPSeeker;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserService userService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private CommonService commonService;

	public TMember tMember = new TMember();
	public TUserMsgrecord msgrecord = new TUserMsgrecord();

	/**
	 * 跳转至个人中心
	 * 
	 * @return
	 * @author JingZeKuan
	 */
	public String toPerCenPage() {
		try {
			tMember = (TMember) getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			if (tMember == null) {
				return super.toContentView("user", "register");
			}
			HashMap<String, Object> infoMap = userService.getPerCenterInfo(tMember);
			super.getRequest().setAttribute("tMember", tMember);
			super.getRequest().setAttribute("infoMap", infoMap);
			super.getRequest().setAttribute("title", "个人中心");
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			e.printStackTrace();
			this.logger.error(e);
		}
		return super.toContentView("user", "perCen");
	}

	/**
	 * 我的订单页面
	 * 
	 * @return
	 */
	public String findUserOrder() {
		try {
			tMember = (TMember) getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			String mid = String.valueOf(tMember.getMid());
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("mid", mid);
			Map<String, Object> returnMap = orderService.findUserOrder(map);
			super.getRequest().setAttribute("orderList", returnMap.get("list"));
			super.getRequest().setAttribute("title", "我的订单");
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("user", "usercenter_order");
	}

	/**
	 * 取消订单
	 * 
	 * @return
	 */
	public String changeOrderIsValid() {
		JsonResponseResult result = JsonResponseResult.createSuccess();
		try {
			String oid = super.getRequest().getParameter("oid");
			userService.changeOrderIsValid(oid);
			result.setReturncode(0);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return BaseAction.JSON;
	}

	/**
	 * 我的卡密页面
	 * 
	 * @return
	 */
	public String findUserCard() {
		try {
			logger.info("findUserCard->进入:---------------");
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			String mid = String.valueOf(tMember.getMid());
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("mid", mid);
			HashMap<String, Object> data = orderService.findUserCardByMid(map);
			super.getRequest().setAttribute("cardList", data.get("list"));
			super.getRequest().setAttribute("title", "我的卡密");
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return super.toContentView("user", "usercenter_cardpwd");
	}

	/**
	 * 跳转到我的优惠卷页面
	 * 
	 * @return
	 */
	public String toCouponPage() {
		try {
			tMember = (TMember) getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);

			int mid = tMember.getMid();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("mid", mid);

			// List<Map> list = userService.findUserCoupon(map);
			// super.getRequest().setAttribute("couponLogs", list);
			//
			// double balance = userService.findCouponBalanceByMid(mid);
			// balance = new BigDecimal(balance).setScale(2,
			// BigDecimal.ROUND_HALF_UP).doubleValue();
			//
			// super.getRequest().setAttribute("balance", balance);
			// List<Map> list2 = userService.findUserIntegration(map);
			// super.getRequest().setAttribute("integrationLogs", list2);
			//
			// Integer integration = userService.findIntegrationByMid(mid);
			// super.getRequest().setAttribute("totalIntegration", integration);

			super.getRequest().setAttribute("title", "我的优惠卷");
		} catch (final AppException app) {
			logger.error(app);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return toContentView("user", "coupon");
	}

	/**
	 * 卡密详情页
	 * 
	 * @return
	 */
	public String toCardInfo() {
		Map<String, Object> param = new HashMap<String, Object>(8);
		HttpServletRequest request = getRequest();
		try {
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			// 测试人员id
			// param.put("mid",15852);
			param.put("mid", tMember.getMid());
			param.put("oid", request.getParameter("oid"));
			param.put("card", request.getParameter("card"));
			param.put("password", request.getParameter("pwd"));
			HashMap<String, Object> card = orderService.findCardInfo(param);
			request.setAttribute("card", card);
			request.setAttribute("title", "卡密详情");
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return toContentView("user", "usercenter_cardInfo");
	}

	/**
	 * 我的预约页面
	 * 
	 * @return
	 */
	public String findUserReservation() {
		HttpServletRequest request = getRequest();
		HashMap<String, Object> param = new HashMap<String, Object>();
		try {
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			param.put("mid", tMember.getMid());
			param.put("mobile", tMember.getMobile());
			HashMap<String, Object> data = reservationService.findReservationInfoByMid(param);
			request.setAttribute("reservationList", data.get("list"));
			request.setAttribute("title", "我的预约");
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return toContentView("user", "usercenter_reservation");
	}

	/**
	 * 预约单详情页
	 * 
	 * @return
	 */
	public String toReservationInfo() {
		Map<String, Object> param = new HashMap<String, Object>();
		HashMap<String, Object> data = null;
		try {
			super.getRequest().setAttribute("title", "预约单详情");
			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			param.put("mid", tMember.getMid());
			param.put("rid", super.getRequest().getParameter("rid"));
			int rtype = Integer.valueOf(super.getRequest().getParameter("rtype"));
			data = reservationService.findReservationInfo(param);
			data.put("rtype", rtype);
			super.getRequest().setAttribute("reservation", data);
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return toContentView("user", "usercenter_reservationInfo");
	}

	// 修改体检卡预约信息
	public String toUpdateOnlinePayReservation() {
		Map<String, Object> param = new HashMap<String, Object>();
		HttpServletRequest request = getRequest();
		try {
			 request.setAttribute("title", "修改预约单");
			 tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
			 param.put("mid", tMember.getMid());
			 param.put("rid", request.getParameter("rid"));
			 param.put("pid", request.getParameter("pid"));
			 Map<String,Object> data = reservationService.getReservationInfoForUpdate(param);
			 //体检卡预约信息
			 HashMap<String, Object> reservation =reservationService.findReservationInfo(param);
			 request.setAttribute("re", reservation);
			 request.setAttribute("cityMap", data.get("areaMap"));
			 request.setAttribute("hospitalCity",data.get("temp"));
			 request.setAttribute("hospitalName",data.get("medicals"));
			 request.setAttribute("esidAndAddress",data.get("address"));
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return toContentView("user", "usercenter_updateCardReservation");
	}

//	// TODO 修改到店付款预约单
//	public String toUpdateShopReservation() {
//		Map<String, Object> param = new HashMap<String, Object>();
//		HttpServletRequest request = getRequest();
//		try {
//			request.setAttribute("title", "修改预约单");
//			String proSex = request.getParameter("pro_sex");
//			// tProductAttr.setProSex(Integer.parseInt(proSex));
//			// tProductAttr.setPid(Integer.valueOf(request.getParameter("pid")));
//			tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
//			param.put("mid", tMember.getMid());
//			param.put("rid", request.getParameter("rid"));
//			// Map<String,Object> data =
//			// reservationService.getReservationInfoForUpdate(tProductAttr);
//			// //到店预约信息
//			// HashMap<String, Object> reservation =
//			// reservationService.findShopPayReservationInfo(param);
//			//
//			// request.setAttribute("re", reservation);
//			// request.setAttribute("cityMap", data.get("cityMap"));
//			// request.setAttribute("hospitalCity",
//			// JSONArray.fromObject(data.get("hospitalCity")).toString());
//			// request.setAttribute("hospitalName",
//			// JSONArray.fromObject(data.get("hospitalName")).toString());
//			// request.setAttribute("esidAndAddress",
//			// JSONArray.fromObject(data.get("esidAndAddress")).toString());
//		} catch (final AppException app) {
//			this.logger.error(app);
//		} catch (final Exception e) {
//			this.logger.error(e);
//		}
//		return toContentView("user", "usercenter_updateShopReservation");
//	}
	
	/**
	 * 跳转到账号安全页面
	 * @return
	 */
	public String toAccountSafe()
	{
		try
		{
			 tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);	
			 super.getRequest().setAttribute("mobile", tMember.getMobile());
		} catch (final AppException app){
			logger.error(app);
		} catch (final Exception e){
			e.printStackTrace();	
		}
	   super.getRequest().setAttribute("title", "账号安全");
       return toContentView("user","accountSafe");				
	}

	// public String findRegisterByAccount(){
	// JsonResponseResult result = null;
	// try {
	// tMember = userService.findMemberByAccountOrMobile(tMember); //查询是否有这个账户
	// if (null !=tMember){
	// result = JsonResponseResult.createFalied("该账号已被注册过！");
	// } else{
	// result = JsonResponseResult.createSuccess();
	// }
	// } catch (final AppException app) {
	// this.logger.error(app);
	// } catch (final Exception e) {
	// this.logger.error(e);
	// }
	// this.logger.debug("action:" + this.jsonResult);
	// super.jsonResult = JSONObject.fromObject(result).toString();
	// return BaseAction.JSON;
	// }

	public String doRegister() {
		JsonResponseResult result = null;
		try {
			String pageYzm = super.getRequest().getParameter("captchas");
			String yzm = super.getFromSession("captcha").toString();
			if (!pageYzm.equals(yzm)) {
				result = JsonResponseResult.createFalied("验证码输入错误!");
				super.jsonResult = JSONObject.fromObject(result).toString();
				return BaseAction.JSON;
			}
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("id", super.getRequest().getParameter("mark"));
			param.put("mobile", tMember.getMobile());
			HashMap<String, Object> reMap = userService.findMsgCodeByIdAndMobile(param);
			String code = reMap.get("CODE").toString();
			if(StringUtil.isBlank(reMap) || !code.equals(super.getRequest().getParameter("veriCode"))){
				result = JsonResponseResult.createFalied("手机验证码输入错误!");
				super.jsonResult = JSONObject.fromObject(result).toString();
				return BaseAction.JSON;
			}
			String time = reMap.get("CREATE_DATE").toString();
			Date date1 = new Date(); //现在时间
			Date temp = DateUtil.parseDate(time, "yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(temp);
			calendar.add(Calendar.MINUTE, 5); //数据库时间+5分钟
			Date date2 = calendar.getTime();
			if(!DateUtil.getDaysEqual(date1, date2)){
				result = JsonResponseResult.createFalied("手机验证码已过期!");
				super.jsonResult = JSONObject.fromObject(result).toString();
				return BaseAction.JSON;
			}
			
			// 防止重复注册
			Integer isMember = (Integer) super.getFromSession(SysConstant.IS_MEMBER);
			String openid = (String) super.getFromSession(SysConstant.OPENID);
			TMember openidMember = userService.findMemByOpenidOrMobile(tMember);
			System.out.println("session的ismember:-----------" + isMember);
			System.out.println("session的openid:-----------" + openid);
			//System.out.println("查询到的会员账号:-----------" + openidMember.getAccount() + "----会员手机---" + openidMember.getMobile());
			if (!SysUtils.isEmpty(openidMember)) {
				result = JsonResponseResult.createFalied("该账号已经注册过!");
				super.jsonResult = JSONObject.fromObject(result).toString();
				return BaseAction.JSON;
			}else{
				if(isMember == 1){
					//没有openid记录
					tMember.setOpenid(openid);
					tMember.setMType(1);
					tMember.setRegisterDate(SysUtils.getTime());
					tMember.setLastLogin(SysUtils.getTime());
					int mid = userService.doRegisterNewMember(tMember);
					tMember.setMid(mid);
				}else{
					//有openid没有手机
					tMember.setOpenid(openid);
					tMember.setMType(3);
					tMember.setLastLogin(SysUtils.getTime());
					userService.updMemberByOpenid(tMember);
				}
			}
			super.setToSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO, tMember);
			result = JsonResponseResult.createSuccess();
			super.jsonResult = JSONObject.fromObject(result).toString();

		} catch (final AppException app) {
			logger.error(app);
			final String msg = getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			final String msg = getText("操作失败,请联系管理员！");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
	
	/**
	 * 手机登录-获取验证码
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String getMobileAchCaptchas(){
		JsonResponseResult result = null;
		try {
			String mobile = super.getRequest().getParameter("mobile");
			String code = RandomStringUtils.randomNumeric(4);
			msgrecord.setMobile(mobile);
			msgrecord.setCode(code);
			msgrecord.setBussinessType(4);
			msgrecord.setUserId(0);
			msgrecord.setCreateDate(SysUtils.getTime());
			int i = userService.addSecuritycode(msgrecord);
			logger.info("验证码:-----" + code + "-----ID:-----" + i);
			if(i > 0){
				logger.info("短信发送开始");
				Map<String, String> msgParam = new HashMap<String, String>();
				msgParam.put("code", code);
				Map<String, Object> reMap = commonService.sendMessage(msgParam, "SMS_57415059", mobile);
				boolean flag = (boolean) reMap.get("success");
				//boolean flag = true;
				if(flag){
					logger.info("短信发送成功"+mobile);
					result = JsonResponseResult.createSuccess();
					result.addData(i);
				}else{
					logger.info("短信发送失败"+mobile);
					result = JsonResponseResult.createFalied("短信发送失败!请稍后重试!");
				}
				super.jsonResult = JSONObject.fromObject(result).toString();
			}		
		} catch (final AppException app) {
			logger.error(app);
			final String msg = getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			e.printStackTrace();
			logger.error(e);
			final String msg = getText("操作失败,请联系管理员！");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	public String updPassWord() {
		JsonResponseResult result = null;
		try {
			String mobile = super.getRequest().getParameter("mobile");
			String password = super.getRequest().getParameter("pwd");
			// tMember = this.loginService.findForget_passwordByMobile(mobile);
			// tMember.setPwd(SysUtils.MD5(password)); //给密码赋值
			// loginService.updPwdByMid(tMember); //修改密码
			result = JsonResponseResult.createSuccess();
		} catch (final AppException app) {
			this.logger.error(app);
			String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("操作失败，请稍后再试！");
			result = JsonResponseResult.createFalied(msg);
		}
		this.jsonResult = JSONObject.fromObject(result).toString();
		return BaseAction.JSON;
	}
}
