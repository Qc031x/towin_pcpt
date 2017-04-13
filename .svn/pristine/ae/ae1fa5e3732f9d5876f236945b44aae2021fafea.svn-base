package com.sgfm.datacenter.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewHttpPlatformMsg {
	
	private static Log logger = LogFactory.getLog(NewHttpPlatformMsg.class);
	
	/*	//帐号
	static String account = "JSM40001";//JSM40013
	//密码
	static String password = "123456";//40013
	//校验码
	static String veryCode = "111222";*/
	static String account = "JSM40693";//
	//密码
	static String password = "ykw520177";//
	//校验码
	static String veryCode = "xkjh55vaaows";
	
	final static String HTTP_URL = "http://112.74.76.186:8030";
	
//	static String SERVERIP = "http://202.102.89.226:8030";
	
	
	/**
	 * 发送普通短信
	 * @param mobile 手机号码, 多个号码以英文逗号隔开,最多支持100个号码
	 * @param content 短信内容 
	 * @return 
	 * @throws Exception
	 */
	public static String sendTextSms(String mobile,String content) throws Exception{
		
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=sendMsg";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		params.append("&mobile=").append(mobile);
		params.append("&content=").append(content);
		params.append("&msgtype=").append("1");
		params.append("&code=").append("utf-8");
//		params.append("&sendtime=").append("20151001113000"); //发送定时短信
		
		System.out.println(params);
		
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
		out.write(params.toString()); //post的关键所在！
		out.flush();
		out.close();
		String temp = "";
		String result = "";
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		while (( temp = br.readLine()) != null) {
			result += temp;
		}
		return result;
	}
	
	/**
	 * 发送模版短信(短信模版的创建参考客户端操作手册)
	 * 
	 *   模版：@1@会员，动态验证码@2@(五分钟内有效)。请注意保密，勿将验证码告知他人。
	 *   参数值:@1@=member,@2@=4293
	 *   最终短信内容：【短信签名】member会员，动态验证码4293(五分钟内有效)。请注意保密，勿将验证码告知他人。
	 * 
	 * @param mobile 手机号码
	 * @param tempId 模版编号，对应客户端模版编号
	 * @param content 各参数值，以英文逗号隔开，如：@1@=member,@2@=4293
	 * @return
	 * @throws Exception
	 */
	public static String sendTemplateSms(String mobile,String tempId,String content) throws Exception{
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=sendMsg";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		params.append("&mobile=").append(mobile);
		params.append("&content=").append(content);
		params.append("&msgtype=").append("2");
		params.append("&tempid=").append(tempId);
		params.append("&code=").append("utf-8");
		
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
		out.write(params.toString()); //post的关键所在！
		out.flush();
		out.close();
		String temp = "";
		String result = "";
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		while (( temp = br.readLine()) != null) {
			result += temp;
		}
		System.out.println(result);
		
		return result;
	}

	/**
	 * 获取短信状态报告
	 * @return 返回数据格式：
	 * <?xml version="1.0" encoding="UTF-8"?>
		<sms>
			<rpt>
				<mobile>15951977097</mobile> 
				<msgid>7799292837969854465</msgid>
				<status>MA:0006</status>
				<time>2015-06-12 15:10:53</time>
				<extno/>
			</rpt>
			<rpt>
				<mobile>15951977097</mobile>
				<msgid>7799388495481470979</msgid>
				<status>MA:0006</status>
				<time>2015-06-12 15:16:17</time>
				<extno/>
			</rpt>
		</sms>
	 * @throws Exception
	 */
	public static String queryReport() throws Exception{
		
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=queryReport";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
		out.write(params.toString()); //post的关键所在！
		out.flush();
		out.close();
		String temp = "";
		String result = "";
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while (( temp = br.readLine()) != null) {
			result += temp;
		}
		return result;
	}
	
	
	public static String queryMo() throws Exception{
		
		String address = HTTP_URL + "/service/httpService/httpInterface.do?method=queryMo";
		
		StringBuilder params = new StringBuilder();
		params.append("username=").append(account);
		params.append("&password=").append(password);
		params.append("&veryCode=").append(veryCode);
		
		URL url = new URL(address);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
		out.write(params.toString()); //post的关键所在！
		out.flush();
		out.close();
		String temp = "";
		String result = "";
		InputStream is = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while (( temp = br.readLine()) != null) {
			result += temp;
		}
		return result;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		String result = "";
		//发送普通短信
//		result = sendTextSms("15951977097", "您的验证码为：3123，请勿向任何人提供短信验证码");
		
		//发送模版短信
		//result = sendTemplateSms("15951977097", "JSM40001-0001", "@1@=Member,@2@=3918");
		
		//获取短信状态报告
		//result = queryReport();
		
		String returnStr = sendMobileMsg(getMobileMsgMap(new String []{"1234"}, "18576690075", "JSM40990-0007"));
		
		System.out.println(returnStr);
		
		//获取上行短信
		//result = queryMo();
		
		//System.out.println(result);
	}
	
	public static  Map<String, String> getMobileMsgMap(String[] context,String mobile,String mobileTemp){
		Map<String, String> map = new HashMap<String, String>();
		map.put("mobile",mobile);//此处填写发送号码
		map.put("tempid",mobileTemp);//此处填写模板短信编号
		  
		if(mobileTemp.equals("JSM40693-0004")){		 
			map.put("content","@account@="+context[0]+",@securityCode@="+context[1]);//此处填写模板短信内容
		}else if(mobileTemp.equals("JSM40693-0003")){
			map.put("content","@account@="+context[0]+",@password@="+context[1]);
		}else if(mobileTemp.equals("JSM40693-0005")){
			map.put("content","@account@="+context[0]+",@entityShop@="+context[1]+",@name@="+context[2]+",@price@="+context[3]+",@entityAddress@="+context[4]);
		}else if(mobileTemp.equals("JSM40693-0006")){
			map.put("content","@account@="+context[0]+",@checkDec@="+context[1]);
		}else if(mobileTemp.equals("JSM40693-0002")){
			map.put("content","@name@="+context[0]+",@canals@="+context[1]+",@count@="+context[2]+",@detail@="+context[3]+",@signature@="+context[4]);
		}else if(mobileTemp.equals("JSM40693-0010")){
			map.put("content","@account@="+context[0]+",@entityShop@="+context[1]+",@entityAddress@="+context[2]);
		}else if(mobileTemp.equals("JSM40693-0009")){
			map.put("content","@name@="+context[0]);
		}
		return map;
	}
	
	public static String sendMobileMsg(Map<String, String> parmMap) throws UnsupportedEncodingException {
		String returnMsg = "";
		
		try {
			String address = HTTP_URL + "/service/httpService/httpInterface.do?method=sendMsg";
			
			StringBuilder params = new StringBuilder();
			params.append("username=").append(account);
			params.append("&password=").append(password);
			params.append("&veryCode=").append(veryCode);
			params.append("&mobile=").append(parmMap.get("mobile"));
			params.append("&content=").append(parmMap.get("content"));
			params.append("&msgtype=").append("2");
			params.append("&tempid=").append(parmMap.get("tempid"));
			params.append("&code=").append("utf-8");
			
			URL url = new URL(address);
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "utf-8");
			out.write(params.toString()); //post的关键所在！
			out.flush();
			out.close();
			String temp = "";
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			while (( temp = br.readLine()) != null) {
				returnMsg += temp;
			}
			
			logger.info("短信返回码:---------"+returnMsg+"-----时间:"+new Date().toLocaleString());
		} catch (IOException e) {
			logger.info("发送短信异常"+new Date().toLocaleString());
			e.printStackTrace();
		}
		//System.out.println(result);
		return returnMsg;
	}
	
}
