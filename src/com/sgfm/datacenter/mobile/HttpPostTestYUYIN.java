package com.sgfm.datacenter.mobile;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class HttpPostTestYUYIN {
	private static int connectTimeOut = 5000;
	private static int readTimeOut = 10000;
	private static String requestEncoding = "UTF-8";
	public static int getConnectTimeOut() {
		return connectTimeOut;
	}
	public static void setConnectTimeOut(int connectTimeOut) {
		HttpPostTestYUYIN.connectTimeOut = connectTimeOut;
	}
	public static int getReadTimeOut() {
		return readTimeOut;
	}
	public static void setReadTimeOut(int readTimeOut) {
		HttpPostTestYUYIN.readTimeOut = readTimeOut;
	}
	public static String getRequestEncoding() {
		return requestEncoding;
	}
	public static void setRequestEncoding(String requestEncoding) {
		HttpPostTestYUYIN.requestEncoding = requestEncoding;
	}
	
	public static String doGet(String requrl,Map<?,?> parameters,String recvEndcoding){
		HttpURLConnection url_con=null;
		String responseContent = null;
		String vchartset=recvEndcoding==""?HttpPostTestYUYIN.requestEncoding:recvEndcoding;
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
				System.setProperty("连接超时：", String.valueOf(HttpPostTestYUYIN.connectTimeOut));
				System.setProperty("访问超时：", String.valueOf(HttpPostTestYUYIN.readTimeOut)); 
				url_con.setDoOutput(true);//
				byte[] b=params.toString().getBytes();
				url_con.getOutputStream().write(b, 0,b.length);
				url_con.getOutputStream().flush();
				url_con.getOutputStream().close();
				url_con.getInputStream();
				byte[] echo=new byte[10*1024];
				//int len=in.read(echo);
				//responseContent=(new String(echo,0,len).trim());
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
		String vchartset=recvEncoding==""?HttpPostTestYUYIN.requestEncoding:recvEncoding;
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
				System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(HttpPostTestYUYIN.connectTimeOut));
				System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(HttpPostTestYUYIN.readTimeOut));
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
		String vchartset=recvEncoding==""?HttpPostTestYUYIN.requestEncoding:recvEncoding;
		try {
			StringBuffer params = new StringBuffer();
			for (Iterator<?> iter = parameters.entrySet().iterator(); iter.hasNext();) {
				Entry<?, ?> element = (Entry<?, ?>) iter.next();
				params.append(element.getKey().toString());
				params.append("=");
				params.append(URLEncoder.encode(element.getValue().toString(), vchartset));
				params.append("&");
			}

			if (params.length() > 0) {
				params = params.deleteCharAt(params.length() - 1);
			}

			URL url = new URL(reqUrl);
			url_con = (HttpURLConnection) url.openConnection();
			url_con.setRequestMethod("POST");
			url_con.setConnectTimeout(HttpPostTestYUYIN.connectTimeOut);
			url_con.setReadTimeout(HttpPostTestYUYIN.readTimeOut);
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
			if (url_con != null)  {
				url_con.disconnect();
			}
		}
		return responseContent;
	}


	/*public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "jjiejiej");//此处填写用户账号
		
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8080/appController/yikang.MemberAction.getmobilemsg2",map, "utf-8");
		
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8080/product/detail-10244.html?brand_id=10008&city_id=10312",map, "utf-8");
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8080/appController/ec.PayAction.asyncCallBackByAlipayRN",map, "utf-8");
		String temp = HttpPostTest.doPost("http://www.1k360.com",map, "utf-8");
		//String temp = HttpPostTest.doPost("http://www.1k360.com/appController/yikang.Memb搞rActio",map, "utf-8");
		for(int i=0;i<1000;i++){
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8081",map, "utf-8");
		}
		System.out.println("值:" + temp);//此处为短信发送的返回值
	}*/
	
	/**
	 * 语音验证码
	 */
	public static String sendMobileYuyin(String phone,String verification) throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("interfaceType", "webCall");//此处填写用户账号
		map.put("enterpriseId", "3002678");
		map.put("userName", "admin");
		map.put("pwd", "afdd0b4ad2ec172c586e2150770fbf9e");
		map.put("customerNumber", phone);
		map.put("paramNames", "alarm");
		map.put("paramTypes", "2");
		map.put("alarm", verification);
		map.put("sync", "1");//此处填写用户账号
		map.put("ivrId", "2273");//此处填写用户账号
		
		String retMsg = HttpPostTestYUYIN.doPost("http://1.ccic2.com/interface/entrance/OpenInterfaceEntrance",map, "utf-8");
		System.out.println("值:" + retMsg);//此处为短信发送的返回值
		return retMsg;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		/*Map<String, String> map = new HashMap<String, String>();
		map.put("interfaceType", "webCall");//此处填写用户账号
		map.put("enterpriseId", "3002678");
		map.put("userName", "admin");
		map.put("pwd", "afdd0b4ad2ec172c586e2150770fbf9e");
		map.put("customerNumber", "18902468121");
		map.put("paramNames", "alarm");
		map.put("paramTypes", "2");
		map.put("alarm", "4423");
		map.put("sync", "1");//此处填写用户账号
		map.put("ivrId", "2273");//此处填写用户账号
		
		
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8080/appController/yikang.MemberAction.getmobilemsg2",map, "utf-8");
		
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8080/product/detail-10244.html?brand_id=10008&city_id=10312",map, "utf-8");
		//String temp = HttpPostTest.doPost("http://127.0.0.1:8080/appController/ec.PayAction.asyncCallBackByAlipayRN",map, "utf-8");
		//String temp = HttpPostTestYUYIN.doPost("http://1.ccic2.com/interface/entrance/OpenInterfaceEntrance",map, "utf-8");
		//String temp = HttpPostTest.doPost("http://www.1k360.com/appController/yikang.Memb搞rActio",map, "utf-8");
		 //for(int i=1000;i<1020;i++){
			// map.put("alarm", String.valueOf(i));//此处填写用户账号
			 HttpPostTestYUYIN.doPost("http://1.ccic2.com/interface/entrance/OpenInterfaceEntrance",map, "utf-8");
			// System.out.println("值:" + i);//此处为短信发送的返回值
		 //}
		//System.out.println("值:" + temp);//此处为短信发送的返回值*/	
		sendMobileYuyin("18576690075","1234");
	}
}

