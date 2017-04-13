package com.sgfm.datacenter.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.MsgConstant;
import com.sgfm.datacenter.SysConstant;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

import net.sf.json.JSONObject;

public class TbMsgUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(TbMsgUtil.class);
	
	private TbMsgUtil(){}
	
	/**
	 * 发送员工预约体检后通知短信
	 * @param params   模板参数 K-V 对
	 * @param templateName 模板名称
	 * @param mobile 手机号码
	 * @return true 短信发送成功
	 * 		   false  短信发送失败
	 * @throws ApiException 
	 */
	public static Boolean sendMessage(Map<String, String> params, String templateName, String mobile) throws ApiException{
		if (params== null || params.isEmpty()) {
			throw new IllegalArgumentException("模板参数集合不能为空!");
		} if (StringUtil.isBlank(templateName)) {
			throw new IllegalArgumentException("请传入模板名称参数!");
		} if (StringUtil.isBlank(mobile)) {
			throw new IllegalArgumentException("请传入手机号码参数!");
		}
		TaobaoClient client = new DefaultTaobaoClient(SysConstant.SERVER_URL, SysConstant.APP_KEY, SysConstant.APP_SECRET);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		DateFormat df = new SimpleDateFormat("yyyy-MM-ss hh:mm:ss:SSS");
		LOGGER.info("--------------->发送短信：封装参数开始..."+ df.format(new Date(System.currentTimeMillis())));
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName(MsgConstant.SIGN_TIANWEN_YILIAO);
		req.setSmsTemplateCode(templateName);
		req.setRecNum(mobile);
		JSONObject json = new JSONObject();
		json.putAll(params);
		req.setSmsParamString(json.toString());
		LOGGER.info("--------------->开始发送短信:"+ df.format(new Date(System.currentTimeMillis())));
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		LOGGER.info("--------------->短信发送返回码:"+df.format(new Date(System.currentTimeMillis()))+",err_code:"+rsp.getErrorCode());
		LOGGER.info(rsp.getBody());
		return rsp.isSuccess();
	}
	
	
	public static void main(String[] args) throws ApiException {
		// TODO Auto-generated method stub
		/*
		Map<String,String> params = new HashMap<>();
		params.put("name","陈先森");
		params.put("shopname", "一号店");
		String mobile = "15826820233";
		System.out.println(TbMsgUtil.sendMessage(params, MsgConstant.TMPL_RESERVEVATION_REPORT, mobile));
		*/
		/*TaobaoClient client = new DefaultTaobaoClient(SysConstant.SERVER_URL, SysConstant.APP_KEY, SysConstant.APP_SECRET);
		AlibabaAliqinFcSmsNumQueryRequest req = new AlibabaAliqinFcSmsNumQueryRequest();
		//req.setRecNum("18826549576");
		req.setQueryDate("20170301");
		req.setCurrentPage(1L);
		req.setPageSize(50L);
		AlibabaAliqinFcSmsNumQueryResponse rsp = client.execute(req);
		System.out.println((rsp.getBody()));
		System.out.println(rsp.getTotalCount());*/
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		System.out.println(df.format(new Date()));
	}

}
