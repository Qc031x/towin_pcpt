package com.sgfm.datacenter.sys;

import java.util.ArrayList;  
import java.util.HashMap;
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.util.EntityUtils;  
/* 
 * 利用HttpClient进行post请求的工具类 
 */  
@SuppressWarnings("deprecation")
public class HttpClientUtil {  
    public String doPost(String url,Map<String,String> map,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new DefaultHttpClient();  
            httpPost = new HttpPost(url);  
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }
    
    /**
     * 请求其它tomcat节点更新缓存
     * @param method
     */
    public void cachePost(String method,String masterTomcat){
    	HashMap<String,String> map = new HashMap<String, String>();
    	map.put("cache", "true");
    	System.out.println("tomcat主节点 ："+masterTomcat);
    	List<String> urlList = new ArrayList<String>();
    	urlList.add("http://192.168.1.150:8085");
    	urlList.add("http://192.168.1.130:8085");
    	
    	for(int i = 0;i<urlList.size();i++){
    		if(!urlList.get(i).equals(masterTomcat)){
    			this.doPost(urlList.get(i)+method, map, "utf-8");
    		}
    	}
    	
    }
}  