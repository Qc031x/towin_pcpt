package com.sgfm.datacenter.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import com.sgfm.base.util.Md5;
import com.sgfm.datacenter.SysConstant;

/**
 * 工具集合类.<br/>
 * 
 * @author Feiqiumin
 * @date 2011-5-26
 */
public class SysUtils {

	public static String append(String str, String tmp) {
		if (SysUtils.isEmpty(str)) {
			str += tmp;
		} else {
			str += "," + tmp;
		}
		return str;
	}

	/**
	 * 判断一个对象是否为空
	 * 
	 * @param obj
	 *            任意对象
	 * @return 是/否
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		} else {
			if (obj instanceof java.lang.String) {
				if ("".equals(obj.toString().trim())) {
					return true;
				}
			} else if (obj instanceof java.lang.StringBuffer) {
				if ("".equals(obj.toString().trim())) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 描述 :通过 java.net.URL 类访问一个页面，并且返回结果<br/>
	 * 参考：http://java.sun.com
	 * 
	 * @param strUrl
	 *            ：访问路径
	 * @return：返回字符串，错误则返回"error open url"
	 */
	public static String getContent(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url
					.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			while ((s = br.readLine()) != null) {
				sb.append(s);
				// sb.append(s + "\r\n");
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "error open url" + strUrl;
		}
	}

	/**
	 * MD5加密方法。
	 * 
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static String retMd5Pwd(String pwd) throws Exception {
		String s = pwd;
		byte[] strTemp = s.getBytes();
		MessageDigest mdTemp = MessageDigest.getInstance("MD5");
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		StringBuffer bufHs = new StringBuffer();

		String stmp = "";
		for (int n = 0; n < md.length; n++) {
			stmp = (java.lang.Integer.toHexString(md[n] & 0XFF));
			if (stmp.length() == 1) {
				bufHs.append("0").append(stmp);
			} else {
				bufHs.append(stmp);
			}
		}
		return bufHs.toString().toUpperCase();

	}
	
	public static String MD5(String pwd){
		if (pwd== null)
			return null;
		if (pwd.trim().length() == 0)
			return pwd;
		Md5 md5 = new Md5();
	    return md5.getMD5ofStr(pwd);
	}

	public static String format(Object temp) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (temp instanceof String) {
			return temp.toString();
		} else if (temp instanceof java.util.Date) {
			return sf.format(temp);
		} else if (temp instanceof java.sql.Date) {
			return sf.format(temp);
		} else {
			if (temp == null) {
				return "";
			}
			return temp.toString();
		}
	}

	public static String format(Date obj, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(obj);
	}

	public static Date parseDate(String str) {

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date parseDate(String str, String format) {

		SimpleDateFormat sf = new SimpleDateFormat(format);
		try {
			return sf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据时间字符串把天数加number天 把1改成-1就为最近1天的。
	 * 
	 * @param number
	 *            为正整数，则为现在时间加是几天。为负整数则为最近几天。
	 * @return
	 */
	public static String addNumberDay(String time, int number) {
		String add = null;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.util.Date timeNow = df.parse(time);
			Calendar begin = Calendar.getInstance();
			begin.setTime(timeNow);
			begin.add(Calendar.DAY_OF_MONTH, number);
			add = df.format(begin.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return add;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 格式大小写有区别
		String sysDatetime = fmt.format(rightNow.getTime());
		return sysDatetime;
	}

	// 处理空字符串
	public static String getStringValue(String str) {
		if ("".equals(str) || str == null) {
			return "";
		}
		return str.trim();
	}
	
	public static String postHttpReq(String url, InputStream in, HashMap<String, Object> map) {
		HttpClient httpClient = new HttpClient();
		EntityEnclosingMethod postMethod = new PostMethod(url);
		String appendData = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			appendData += entry.getKey() + "=" + entry.getValue() + "&";
		}
		postMethod.setQueryString(appendData.substring(0, appendData.length() - 1));
		postMethod.setRequestHeader("Content-Type", "application/octet-stream");

		RequestEntity request = new InputStreamRequestEntity(in);
		postMethod.setRequestEntity(request);

		String responseMsg = "";
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);// 发送请求
			//responseMsg = postMethod.getResponseBodyAsString();// 获取返回值
			BufferedReader reader = new BufferedReader(new InputStreamReader(postMethod.getResponseBodyAsStream()));  

			StringBuffer stringBuffer = new StringBuffer();  

			String str = "";  

			while((str = reader.readLine())!=null){  

			   stringBuffer.append(str);  

			}  

			responseMsg = stringBuffer.toString();  
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}

		return responseMsg;
	}
	
	public static String postHttpReq(String url, HashMap<String, Object> map) {
		HttpClient httpClient = new HttpClient();
		EntityEnclosingMethod postMethod = new PostMethod(url);
		String appendData = "";
		for (Entry<String, Object> entry : map.entrySet()) {
			appendData += entry.getKey() + "=" + entry.getValue() + "&";
		}
		if(appendData.length() > 0) postMethod.setQueryString(appendData.substring(0, appendData.length() - 1));
		postMethod.setRequestHeader("Content-Type", "application/octet-stream");

		//RequestEntity request = new InputStreamRequestEntity(in);
		//postMethod.setRequestEntity(request);

		String responseMsg = "";
		int statusCode = 0;
		try {
			statusCode = httpClient.executeMethod(postMethod);// 发送请求
			responseMsg = postMethod.getResponseBodyAsString();// 获取返回值
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}

		return responseMsg;
	}
	
	public static String getRemoteDomain(int port){
		switch (port) {
		case 8080:
			return "http://admin.51towin.com";
		case 8081:
			return "http://h.51towin.com";
		case 8082:
			return "http://e.51towin.com";
		case 8083:
			return "http://r.51towin.com";
		case 8089:
			return "http://p.51towin.com";
		}
		return "";
	}
}
