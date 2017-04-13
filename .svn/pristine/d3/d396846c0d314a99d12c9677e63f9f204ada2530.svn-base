package com.tencent.service;

import com.tencent.common.Configure;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * User: rizenguo
 * Date: 2014/12/10
 * Time: 15:44
 * 服务的基类
 */
public class BaseService{

    //API的地址
    private String apiURL;
    private Log logger = LogFactory.getLog(this.getClass()); 
    //发请求的HTTPS请求器
    private IServiceRequest serviceRequest;

    public BaseService(String api) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    	logger.info("----BaseService()构造函数-》beg");
        apiURL = api;
        Class c = Class.forName(Configure.HttpsRequestClassName);
        serviceRequest = (IServiceRequest) c.newInstance();
        logger.info("----BaseService()构造函数-》end");
    }

    protected String sendPost(Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return serviceRequest.sendPost(apiURL,xmlObj);
    }
    protected String sendHttpsWapPost(String wapApiUrl,Object xmlObj) throws UnrecoverableKeyException, IOException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    	
    	logger.info("----BaseService-》sendHttpsWapPost---");
    	return serviceRequest.sendHttpsWapPost(wapApiUrl,xmlObj);
    }
    /**
     * 供商户想自定义自己的HTTP请求器用
     * @param request 实现了IserviceRequest接口的HttpsRequest
     */
    public void setServiceRequest(IServiceRequest request){
        serviceRequest = request;
    }
}
