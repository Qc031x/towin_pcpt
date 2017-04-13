package com.sgfm.datacenter.mobile;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.sgfm.base.util.DateUtil;


public class HttpPost {
	private static Log logger = LogFactory.getLog(HttpPost.class);
	private static int connectTimeOut = 5000;
	private static int readTimeOut = 10000;
	private static String requestEncoding = "UTF-8";
	
	public static int getConnectTimeOut() {
		return connectTimeOut;
	}
	public static void setConnectTimeOut(int connectTimeOut) {
		HttpPost.connectTimeOut = connectTimeOut;
	}
	public static int getReadTimeOut() {
		return readTimeOut;
	}
	public static void setReadTimeOut(int readTimeOut) {
		HttpPost.readTimeOut = readTimeOut;
	}
	public static String getRequestEncoding() {
		return requestEncoding; 
	}
	public static void setRequestEncoding(String requestEncoding) {
		HttpPost.requestEncoding = requestEncoding;
	}
	
	public static String doGet(String requrl,Map<?,?> parameters,String recvEndcoding){
		HttpURLConnection url_con=null;
		String responseContent = null;
		String vchartset=recvEndcoding==""?HttpPost.requestEncoding:recvEndcoding;
		try {
				StringBuffer params=new StringBuffer();
				for (Iterator<?> iter=parameters.entrySet().iterator();iter.hasNext();) {
					Entry<?, ?> element=(Entry<?, ?>) iter.next();
					params.append(element.getKey().toString());
					params.append("=");
					params.append(URLEncoder.encode(element.getValue().toString(),vchartset));
					params.append("&");
				}
				if(params.length()>0){
					params=params.deleteCharAt(params.length()-1);
				}
				URL url=new URL(requrl);
				url_con=(HttpURLConnection) url.openConnection();
				url_con.setRequestMethod("GET");
				System.setProperty("连接超时：", String.valueOf(HttpPost.connectTimeOut));
				System.setProperty("访问超时：", String.valueOf(HttpPost.readTimeOut)); 
				url_con.setDoOutput(true);//
				byte[] b=params.toString().getBytes();
				url_con.getOutputStream().write(b, 0,b.length);
				url_con.getOutputStream().flush();
				url_con.getOutputStream().close();
				InputStream in=url_con.getInputStream();
				byte[] echo=new byte[10*1024];
				int len=in.read(echo);
				responseContent=(new String(echo,0,len).trim());
				int code = url_con.getResponseCode();
				if (code != 200) {
					responseContent = "ERROR" + code;
				}
		} catch (Exception e) {
			System.out.println("网络故障:"+ e.toString());
		}finally{
			if(url_con!=null){
				url_con.disconnect();
			}
		}
		return responseContent;
		
	}
	public static String doGet(String reqUrl, String recvEncoding) {
		HttpURLConnection url_con = null;
		String responseContent = null;
		String vchartset=recvEncoding==""?HttpPost.requestEncoding:recvEncoding;
		try {
				StringBuffer params = new StringBuffer();
				String queryUrl = reqUrl;
				int paramIndex = reqUrl.indexOf("?");
				
				if (paramIndex > 0) {
					queryUrl = reqUrl.substring(0, paramIndex);
					String parameters = reqUrl.substring(paramIndex + 1, reqUrl.length());
					String[] paramArray = parameters.split("&");
					for (int i = 0; i < paramArray.length; i++) {
						String string = paramArray[i];
						int index = string.indexOf("=");
						if (index > 0) {
							String parameter = string.substring(0, index);
							String value = string.substring(index + 1, string.length());
							params.append(parameter);
							params.append("=");
							params.append(URLEncoder.encode(value, vchartset));
							params.append("&");
						}
					}

					params = params.deleteCharAt(params.length() - 1);
				}
				URL url = new URL(queryUrl);
				url_con = (HttpURLConnection) url.openConnection();
				url_con.setRequestMethod("GET");
				System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(HttpPost.connectTimeOut));
				System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(HttpPost.readTimeOut));
				url_con.setDoOutput(true);
				byte[] b = params.toString().getBytes();
				url_con.getOutputStream().write(b, 0, b.length);
				url_con.getOutputStream().flush();
				url_con.getOutputStream().close();
				InputStream in = url_con.getInputStream();
				byte[] echo = new byte[10 * 1024];
				int len = in.read(echo);
				responseContent = (new String(echo, 0, len)).trim();
				int code = url_con.getResponseCode();
				if (code != 200) {
					responseContent = "ERROR" + code;
				}
		} catch (Exception e) {
			System.out.println("网络故障:"+ e.toString());
		}finally{
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
		
	}
	public static String doPost(String reqUrl, Map<String, String> parameters, String recvEncoding) {
		
		HttpURLConnection url_con = null;
		String responseContent = null;
		String vchartset=recvEncoding==""?HttpPost.requestEncoding:recvEncoding;
		Date date = new Date();
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry<?, ?> element = (Entry<?, ?>) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(), vchartset));
				params.append("&");
			}
			//logger.info(DateUtil.format(date, "yyyy-MM-dd HH:mm:ss:SSS"));
			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}
			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(HttpPost.connectTimeOut);
			url_con.setReadTimeout(HttpPost.readTimeOut);
			url_con.setDoOutput(true);
			byte[] b = params.toString().getBytes();
			url_con.getOutputStream().write(b, 0, b.length);
			url_con.getOutputStream().flush();
			url_con.getOutputStream().close();

			InputStream in = url_con.getInputStream();
			byte[] echo = new byte[10 * 1024];
			int len = in.read(echo);
			responseContent = (new String(echo, 0, len)).trim();
			int code = url_con.getResponseCode();
			if (code != 200) {
				responseContent = "ERROR" + code;
			}

		}
		catch (IOException e) {
			System.out.println("网络故障:"+ e.toString());
		}
		finally {
			if (url_con != null) {
				url_con.disconnect();
			}
		}
		return responseContent;
	}
	public static String sendMobileMsg(Map<String, String> parmMap) throws UnsupportedEncodingException {
		parmMap.put("username", "JSMB261030");//此处填写用户账号
		parmMap.put("scode", "261030");//此处填写用户密码
		
		String retMsg = HttpPost.doPost("http://mssms.cn:8000/msm/sdk/http/sendsms.jsp",parmMap, "gbk");
		System.out.println("值:" + retMsg);//此处为短信发送的返回值
		return retMsg;
	}

	public static  Map<String, String> getMobileMsgMap(String[] context,String mobile,String mobileTemp){
		  Map<String, String> map = new HashMap<String, String>();
		  map.put("mobile",mobile);//此处填写发送号码
		  map.put("tempid",mobileTemp);//此处填写模板短信编号
		  
	     if(mobileTemp.equals("MB-2015060300")){		 
		    map.put("content","@1@="+context[0]);//此处填写模板短信内容
	     }else if(mobileTemp.equals("MB-RegisterMsg2")){		 
			map.put("content","@account@="+context[0]+",@securityCode@="+context[1]);//此处填写模板短信内容
		 }else if(mobileTemp.equals("MB-SendPasswordToCustom")){
			 map.put("content","@account@="+context[0]+",@password@="+context[1]);
		 }else if(mobileTemp.equals("MB-PaySuccess")){
				map.put("content", "@account@="+context[0]+",@oid@="+context[1]);
			 }
	     return map;
		
	}
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("username", "JSMB261030");//此处填写用户账号
		map.put("scode", "261030");//此处填写用户密码
		map.put("mobile","18576690075");//此处填写发送号码
		map.put("tempid","MB-SendMsg");//此处填写模板短信编号
		//map.put("extcode","1234");
		map.put("content","@name@=123,@card@=123,@password@=123");//此处填写模板短信内容
*/		//String temp = HttpPost.doPost("http://mssms.cn:8000/msm/sdk/http/sendsms.jsp",map, "utf-8");
		//String kahaoString = "卡号:X111111\\密码:123456，卡号:X111111\\密码:123456，卡号:X111111\\密码:123456";
		//HttpPost.sendMobileMsg(HttpPost.getMobileMsgMap(new String[] {"曹雄","123","444","卡号:X111111\\密码:123456，卡号:X111111\\密码:123456，卡号:X111111\\密码:123456"}, "18576690075", "MB-SendOrderInfo"));
		//System.out.println("值:" + temp);//此处为短信发送的返回值
		Date date = new Date();
		System.out.println(DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
	}
}

