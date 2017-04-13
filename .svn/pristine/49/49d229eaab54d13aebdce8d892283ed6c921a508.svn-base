package com.tencent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tencent.business.ScanPayBusiness;
import com.tencent.common.Configure;
import com.tencent.protocol.order.ScanAddOrderReqData;

/**
 * SDK总入口
 */
public class WXPay {
    private Log logger = LogFactory.getLog(this.getClass()); 
    /**
     * 初始化SDK依赖的几个关键配置
     * @param key 签名算法需要用到的秘钥
     * @param appID 公众账号ID
     * @param mchID 商户ID
     * @param sdbMchID 子商户ID，受理模式必填
     * @param certLocalPath HTTP证书在服务器中的路径，用来加载证书用
     * @param certPassword HTTP证书的密码，默认等于MCHID
     */
    public static void initSDKConfiguration(String key,String appID,String mchID,String sdbMchID,String certLocalPath,String certPassword){
        Configure.setKey(key);
        Configure.setAppID(appID);
        Configure.setMchID(mchID);
        Configure.setSubMchID(sdbMchID);
        Configure.setCertLocalPath(certLocalPath);
        Configure.setCertPassword(certPassword);
    }
    /**
     * 统一下单
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String doAddOrderBusiness(ScanAddOrderReqData scanAddOrderReqData, ScanPayBusiness.ResultListener resultListener) throws Exception {
    	ScanPayBusiness s= new ScanPayBusiness();
       String xml= s.addOrder(scanAddOrderReqData, resultListener);
       return xml;
    }
    public String getWapToken(String wapurl,ScanAddOrderReqData scanAddOrderReqData, ScanPayBusiness.ResultListener resultListener)throws Exception  {
    	logger.info("wapurl=" + wapurl); 
    	String xml="";;
		try {
			ScanPayBusiness s= new ScanPayBusiness();
			xml = s.getWapToken(wapurl,scanAddOrderReqData, resultListener);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("getWapToken e:\n"+e.toString());
		}
        return xml;
    }
    
    
   
}
