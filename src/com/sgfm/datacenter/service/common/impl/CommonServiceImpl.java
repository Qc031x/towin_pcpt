package com.sgfm.datacenter.service.common.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgfm.base.util.PropsLoader;
import com.sgfm.datacenter.SysConstant;
import com.sgfm.datacenter.dao.common.CommonDao;
import com.sgfm.datacenter.service.common.CommonService;
import com.sgfm.datacenter.util.TbMsgUtil;
import com.taobao.api.ApiException;
@Service
public class CommonServiceImpl implements CommonService{
	
	@Autowired
	private PropsLoader propsLoader;
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 
	 * @param params
	 * @param templateName
	 * @param mobile
	 * @return Map<String,Object>
	 * 			key   ：   value
	 * 		success   ：   true ： 成功，   false ： 失败
	 * 		   msg    ：    返回消息提示
	 * @throws ApiException
	 */
	public Map<String, Object> sendMessage(Map<String, String> params, String templateName, String mobile) throws ApiException {
		Map<String, Object> info = new HashMap<String, Object>();
		StringBuilder sb = new StringBuilder();
		if (checkTodayMsgCount()) {
			info.put("success", false);
			sb.append("当日短信发送量已经到达上限！");
			logger.info(sb.toString());
			info.put("msg", sb.toString());
			return info;
		}
		Boolean flag = TbMsgUtil.sendMessage(params, templateName, mobile);
		if (flag) {
			sb.append("发送成功!");
		} else {
			sb.append("发送失败!");
		}
		logger.info(sb.toString());
		info.put("success", flag);
		info.put("msg", sb.toString());
		return info;
	}
	
	private boolean checkTodayMsgCount() {
		String date = new SimpleDateFormat("yyyy-MM-ss", Locale.CHINA).format(new Date());
		return commonDao.getTodayMsgCount(date) > SysConstant.MSG_CHECKCOUNT;
	}

	@Override
	public void sendWechatTemplate(Map<String, String> map) {
		String openId=map.get("openId");
		String result=null;
		String accessToken=getAccessToken();
		String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
    	String temp_id="ZfQubvedaUXvV94PmlDSUbgIeIrfhc2LCGAaAl99Q0w";
    	Map <String,Object> body=new HashMap<String,Object> ();
    	Map <String,Object> data=new HashMap<String,Object> ();
    	Map <String,Object> dataNode=new HashMap<String,Object> ();
    	
    	body.put("touser", openId);
    	body.put("template_id", temp_id);
    	body.put("url", "http://p.51towin.com/core/user.findUserReservation.do?openId="+openId);
    	
    	String first="亲，您于"+(new Date()).toLocaleString()+"提交了体检预约单";
    	dataNode.put("value",first);
    	dataNode.put("color", "#173177"); 
    	data.put("first", dataNode);
    	
    	//体检人
    	dataNode=new HashMap<String,Object> ();
    	String name =(String)map.get("uname");
    	dataNode.put("value", name);
    	dataNode.put("color", "#173177");
    	data.put("keyword1", dataNode);
    	
    	//体检套餐
    	dataNode=new HashMap<String,Object> ();
    	String product=map.get("pname")+"(地址:"+map.get("address")+")";
    	dataNode.put("value", product);
    	dataNode.put("color", "#173177");
    	data.put("keyword2", dataNode);
    	
    	//预约时间
    	dataNode=new HashMap<String,Object> ();
    	String orderTime = (String)map.get("orderTime");
    	dataNode.put("value", orderTime);
    	dataNode.put("color", "#173177");
    	data.put("keyword3", dataNode);
    	
    	body.put("data", data);
    	
    	
    	HttpPost httpPost = new HttpPost(url);
		DefaultHttpClient httpClient=new DefaultHttpClient();
		String json=JSONObject.fromObject(body).toString();
		StringEntity se = new StringEntity(json,"UTF-8");
	    se.setContentType("text/json");
	    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	    httpPost.setEntity(se);
	    HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
	        result = EntityUtils.toString(entity, "UTF-8");
	        logger.info("httpPostWithJSON 返回结果 : result:"+result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}
	
	//获取微信access_token
	public String getAccessToken(){
		String result=null;
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx44a26b8a7ef26809&secret=ce5575b427a386ce491e2549a515c4b0";
		HttpPost httpPost = new HttpPost(url);
		DefaultHttpClient httpClient=new DefaultHttpClient();
		HttpResponse response;
		try {
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			String getReturn = EntityUtils.toString(entity, "UTF-8");
			if(getReturn.indexOf("access_token")>=0){
				result= JSONObject.fromObject(getReturn).get("access_token").toString();
				logger.info("成功获取access_token！");
			}else{
				logger.info("获取access_token失败！");
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


}