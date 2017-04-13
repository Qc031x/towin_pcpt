package com.tencent.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tencent.common.Configure;
import com.tencent.protocol.order.ScanAddOrderReqData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 16:03
 */
public class ScanPayService extends BaseService{
	 private Log logger = LogFactory.getLog(this.getClass()); 
    public ScanPayService() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        super(Configure.ADDORDER_API);
    }

    /**
     * 请求支付服务
     * @param scanPayReqData 这个数据对象里面包含了API要求提交的各种数据字段
     * @return API返回的数据
     * @throws Exception
     */
    public String request(ScanAddOrderReqData scanPayReqData) throws Exception {

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendPost(scanPayReqData);

        return responseString;
    }
    public String request2(String wapApiUrl,ScanAddOrderReqData scanPayReqData) throws Exception {
    	 logger.info("ScanPayService-》request2");

        //--------------------------------------------------------------------
        //发送HTTPS的Post请求到API地址
        //--------------------------------------------------------------------
        String responseString = sendHttpsWapPost(wapApiUrl,scanPayReqData);

        return responseString;
    }
    
}
