package com.sgfm.datacenter.action.reservation;


import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sgfm.base.action.BaseAction;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.entity.SOrderLine;
import com.sgfm.datacenter.entity.SReservation;
import com.sgfm.datacenter.entity.TMember;
import com.sgfm.datacenter.exception.AppException;
import com.sgfm.datacenter.service.order.OrderService;
import com.sgfm.datacenter.service.reservation.ReservationService;
import com.sgfm.datacenter.util.JsonResponseResult;
import com.sgfm.datacenter.util.SysUtils;
import com.sgfm.datacenter.util.token.TokenProcessor;

@Scope("prototype")
@Controller
public class ReservationAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	
	public SReservation sReservation = new SReservation();
	
	public TMember tMember = new TMember();
	
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private OrderService orderService;
	
	//TODO修改预约单要修改handle_status 字段
	
//	public String updateShopReservation() {
//		JsonResponseResult result = null;
//		try {
//			Map<String,Object> ret = new HashMap<String,Object>();
//			//修改预约单为未审核状态
//			sReservation.setHandleStatus(0);
//			sReservation.setStatus(0);
//			sReservation.setPostDate(SysUtils.getTime());
//			int results = reservationService.updateSreservation(sReservation);
//
//			ret.put("results", results);
//			super.jsonResult = JSONObject.fromObject(ret).toString();
//		} catch (final AppException app) {
//			this.logger.error(app);
//			final String msg = this.getText(app.getMessage());
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		} catch (final Exception e) {
//			this.logger.error(e);
//			final String msg = this.getText("com.sgfm.datacenter.action.user.UserAction.exception");
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		}
//		this.logger.debug("action:" + this.jsonResult);
//		return BaseAction.JSON;
//	}
//	
	public String updateCardReservation() {
		JsonResponseResult result = null;
		try {
			HashMap<String,Object> ret = new HashMap<String,Object>();
			//修改预约单,将预约单重新置为未审核
			sReservation.setStatus(0);
			sReservation.setPostDate(SysUtils.getTime());
			int results = reservationService.updateCardReservation(sReservation);
			ret.put("results", results);
			super.jsonResult = JSONObject.fromObject(ret).toString();
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.user.UserAction.exception");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
//	
//	public String updateQDReservation() {
//		JsonResponseResult result = null;
//		try {
//			Map<String,Object> ret = new HashMap<String,Object>();
//			//修改预约单,将预约单重新置为未审核
//			gReservation.setHandleStatus("0");
//			gReservation.setStatus("0");
//			gReservation.setPostDate(SysUtils.getTime());
//			int results = reservationService.updateGroupbuyReservation(gReservation);
//			ret.put("results", results);
//			super.jsonResult = JSONObject.fromObject(ret).toString();
//			
//		} catch (final AppException app) {
//			this.logger.error(app);
//			final String msg = this.getText(app.getMessage());
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		} catch (final Exception e) {
//			this.logger.error(e);
//			final String msg = this.getText("com.sgfm.datacenter.action.user.UserAction.exception");
//			result = JsonResponseResult.createFalied(msg);
//			super.jsonResult = JSONObject.fromObject(result).toString();
//		}
//		return BaseAction.JSON;
//	}
//	
	
	public String doLoginRs(){
		JsonResponseResult result = null;
		try {
			
			//tReservation.setCsid(Integer.parseInt(csid));
			String results = reservationService.checkCard(sReservation);
			if (results.indexOf("0") == -1) {
				String msg = "";
				switch (Integer.parseInt(results)) {
				case 1:
					msg = "未找到卡信息，请仔细核对卡号卡密和使用商家";
					break;
				case 2:
					msg = "此卡未激活，请联系发卡机构";
					break;
				case 3:
					msg = "此卡已使用，请联系发卡机构";
					break;
				case 4:
					msg = "此卡绑定商品已下架，请联系发卡机构";
					break;
				case 5:
					msg = "此卡没有可预约门店，请联系发卡机构";
					break;
				}
				result = JsonResponseResult.createFalied(msg);
			} else {
				TokenProcessor tokenProcessor = TokenProcessor.getInstance();
				String token = tokenProcessor.generateToken(super.getRequest());
				super.getRequest().getSession().setAttribute(SysConstant.TW_TOKEN, token);
				result = JsonResponseResult.createSuccess();
				result.addData(sReservation.getCard());
				result.addData(sReservation.getPassword());
				result.addData(results.substring(1, results.length()));
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
			final String msg = getText("com.sgfm.datacenter.action.order.OrderAction.exception");
			result = JsonResponseResult.createFalied(msg);
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		this.logger.debug("action:" + this.jsonResult);
		return BaseAction.JSON;
	}
	
	public String submitOnlineTreservation() {
		try {
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("reservation", sReservation);
			if(StringUtil.isBlank(sReservation.getPid()) || StringUtil.isBlank(sReservation.getCard()) || StringUtil.isBlank(sReservation.getPassword())) 
				return super.toContentView("reservation", "rs_login");
			
			SOrderLine orderLine = reservationService.findCardInfoByCard(sReservation);
			HashMap<String, Object> infoMap = reservationService.toReFromUsercenter(map);
			
			super.getRequest().setAttribute("infoMap", infoMap);
			super.getRequest().setAttribute("card", sReservation.getCard());
			super.getRequest().setAttribute("password", sReservation.getPassword());
			super.getRequest().setAttribute("olid", orderLine.getOlid());
			super.getRequest().setAttribute("token", super.getRequest().getParameter("TK"));
			
		} catch (final AppException app) {
			this.logger.error(app);
		} catch (final Exception e) {
			this.logger.error(e);
		}
		return toContentView("reservation","medicalcard_rs");
	}
	
	public String doCreateNewReservation(){
		JsonResponseResult result = null;
		try {
			
			TokenProcessor tokenProcessor = TokenProcessor.getInstance();
			if (tokenProcessor.isTokenValid(super.getRequest(), super.getRequest().getParameter("plat_token"))) {
				tMember = (TMember) super.getFromSession(SysConstant.SYS_MEMBER_LOG_SUCC_INFO);
				if(!SysUtils.isEmpty(tMember)) sReservation.setMid(tMember.getMid());
				boolean flag = reservationService.doCreateNewReservation(sReservation);
				
				if(flag){
					result = JsonResponseResult.createSuccess();
					//发送短息 & 微信推送消息
					HashMap<String, Object> wxMap=new HashMap<String, Object>();
					String address=reservationService.getAddressByEsidNoProvince(sReservation.getEsid().toString());
					wxMap.put("address", address);
					wxMap.put("openId", tMember.getOpenid());
					//commonService.sendWechatTemplate(wxMap);
					wxMap.put("type", "2");
					wxMap.put("mid", tMember.getMid());
					wxMap.put("sReservation", sReservation);
					orderService.sendMessage(wxMap);
				}else{
					JsonResponseResult.createFalied("分院可约人数超过上限,请选择其他分院");
				}
				
			}else{
				result = JsonResponseResult.createFalied("当前页面已失效,请返回重新登录!");
			}
			
			tokenProcessor.resetToken(super.getRequest());
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final AppException app) {
			this.logger.error(app);
			final String msg = this.getText(app.getMessage());
			result = JsonResponseResult.createFalied("预约单提交失败!请联系客服!");
			super.jsonResult = JSONObject.fromObject(result).toString();
		} catch (final Exception e) {
			this.logger.error(e);
			final String msg = this.getText("com.sgfm.datacenter.action.ReservationAction.doNewCardReservationOnLine.exception");
			result = JsonResponseResult.createFalied("预约单提交失败!请联系客服!");
			super.jsonResult = JSONObject.fromObject(result).toString();
		}
		return BaseAction.JSON;
	}
}
