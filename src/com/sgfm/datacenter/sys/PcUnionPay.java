package com.sgfm.datacenter.sys;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import chinapay.PrivateKey;
import chinapay.SecureLink;

import com.sgfm.base.util.PropsLoader;
import com.sgfm.base.util.StringUtil;
import com.sgfm.datacenter.util.AppContext;
 
/**
 * 
 * 
 * 名称： 第一卷 商户卷 第1/6部分 手机支付 ——跳转网关支付产品/手机网页支付产品<br>
 * 功能： 6.2　消费类交易<br>
 * 前台交易类<br>
 * 版本： 5.0<br>
 * 日期： 2014-07<br>
 * 作者： 中国银联ACP团队<br>
 * 版权： 中国银联<br>
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 * 
 * zfb
 * <form name='online_pay30002' method='get' action='https://mapi.alipay.com/gateway.do?_input_charset=UTF-8' target='_blank'>
<input type='hidden' name='subject' value='商务高端套餐'/>
<input type='hidden' name='sign_type' value='MD5'/>
<input type='hidden' name='paygateway' value='https://mapi.alipay.com/gateway.do'/>
<input type='hidden' name='notify_url' value='http://www.1k360.com/appController/ec.PayAction.asyncCallBackByAlipayRN'/>
<input type='hidden' name='qr_pay_mode' value='2'/>
<input type='hidden' name='out_trade_no' value='2015061711680'/>
<input type='hidden' name='url' value='http://www.1k360.com'/>
<input type='hidden' name='return_url' value='http://www.1k360.com/appController/ec.PayAction.callBackByAlipayRN2'/>
<input type='hidden' name='sign' value='b12e4b6389be94722eb18edbb42ac2e1'/>
<input type='hidden' name='_input_charset' value='UTF-8'/>
<input type='hidden' name='total_fee' value='1380.00'/>
<input type='hidden' name='service' value='create_direct_pay_by_user'/>
<input type='hidden' name='paymethod' value='directPay'/>
<input type='hidden' name='partner' value='2088111472920164'/>
<input type='hidden' name='seller_email' value='383001163@qq.com'/>
<input type='hidden' name='payment_type' value='2'/>
<input type='submit' id='btn_window' />
</form>
-----支付宝wap----
<form id="alipaysubmit" name="alipaysubmit" action="https://mapi.alipay.com/gateway.do?_input_charset=utf-8" method="get">
<input type="hidden" name="subject" value="3453"/>
<input type="hidden" name="sign_type" value="MD5"/>
<input type="hidden" name="notify_url" value="http://商户网关地址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/notify_url.jsp"/>
<input type="hidden" name="out_trade_no" value="5435345345"/>
<input type="hidden" name="return_url" value="http://商户网关地址/alipay.wap.create.direct.pay.by.user-JAVA-UTF-8/return_url.jsp"/>
<input type="hidden" name="sign" value="59cc4121c2f7998d66abfacfa6d2c83b"/>
<input type="hidden" name="_input_charset" value="utf-8"/>
<input type="hidden" name="total_fee" value="0.1"/>
<input type="hidden" name="service" value="alipay.wap.create.direct.pay.by.user"/>
<input type="hidden" name="partner" value="2088111472920164"/>
<input type="hidden" name="seller_id" value="2088111472920164"/>
<input type="hidden" name="payment_type" value="1"/>
<input type="hidden" name="show_url" value="343445"/>
<input type="submit" value="确认" style="display:none;">
</form><script>document.forms['alipaysubmit'].submit();</script>

 * ------------------------
 * pc<form name='online_pay10001' method='get' action='https://payment.chinapay.com/pay/TransGet' target='second'>
	<input type='hidden' name='BgRetUrl' value='http://www.1k360.com/appController/ec.PayAction.asyAllinPayNew1'/>
	<input type='hidden' name='TransAmt' value='000000026800'/>
	<input type='hidden' name='PageRetUrl' value='http://www.1k360.com/appController/ec.PayAction.allinPayNew1'/>
	<input type='hidden' name='ChkValue' value='708C4B1FDD11593B7DF3D6B6FF0FB9C416F0071A00A884D5F1F812ABC1B5C7056AB4CFE4DD476CB28D2BED127222D6894D439AC48F43A6B93007927A0624EA24AC33D55C3E1155BD4D5483BC6CB406834A63996F5C930E6187DFD0D87128E6938E1091CCC4DA61ED7111C456C4B1975116134019D99302C60B87B5C096310EB5'/>
	<input type='hidden' name='TransType' value='0001'/>
	<input type='hidden' name='OrdId' value='0020150616175603'/>
	<input type='hidden' name='MerId' value='808080201306513'/>
	<input type='hidden' name='GateId' value='0005'/>
	<input type='hidden' name='Version' value='20070129'/>
	<input type='hidden' name='Priv1' value='2015061611673'/>
	<input type='hidden' name='CuryId' value='156'/>
	<input type='hidden' name='TransDate' value='20150616'/>
	<input type='submit' id='btn_window' /></form>
	-------------------------------
	txnType(txnType)	01
respCode(respCode)	00
currencyCode(currencyCode)	156
merId(merId)	898440380110216
settleDate(settleDate)	0616
txnSubType(txnSubType)	01
version(version)	5.0.0
txnAmt(txnAmt)	1
signMethod(signMethod)	01
certId(certId)	21267647932558653966460913033289351200
settleAmt(settleAmt)	1
settleCurrencyCode(settleCurrencyCode)	156
traceTime(traceTime)	0616212845
encoding(encoding)	UTF-8
respMsg(respMsg)	success
bizType(bizType)	000201
traceNo(traceNo)	777159
queryId(queryId)	201506162128457771598
orderId(orderId)	2043511459
signature(signature)	PL+QQY8keJ3fL1lI7GfPyo76dApJ5/cylRzKa4vjurBiM+ltVtquAwzA4/lzk0rLF59Jvsdmk2oIuZIluioN5zotd3uGPDDYZzB4UiWI+wegYvG0P0uDcKQf/D1wxx8LFkud4W+Kc36kVox2iAzjKFPi3XXjqB/UOSgil1oY/Xq93AwezbpJoiClpnptZ5fTtvFXKZXg0v2R+ZhrJdOw4QU+SWQbvYi93Ir7mScg50qOOmNA281lkJxAu6Avo008Vfb1IsKCW45c8TphFcFWUlr14agZeJY0A8P5IHiUpVaOy7f8vvTykysyluGeLBUUAXix2jiV63IJUiNo8pbQYA==
accessType(accessType)	0
txnTime(txnTime)	20150616212845
 */
@Component
public class PcUnionPay  {

//public static void main(String[] args) {
	//static String indexUrl="http://k.1k360.com/";
	static String indexUrl="";
	static String payType = "";
	 @Autowired
	private PropsLoader propsLoader;
	// SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	    //快捷支付银联商户号
	    static String MerId = "808080201306514";
	    //银联网上支付银联商户号
	    //static String MerId2 = "808080201306513"; 
	    //随机生成的订单编号
		//String OrdId = "00" + sf.format(dt);
		static String Version = "20070129";
		String TransAmt = "";// 12
		static 	String CuryId = "156";// 3
		//SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
		//String TransDate = sf2.format(dt);// 8
		static 	String TransType = "0001";// 4
		//后台应答
		//static	String BgRetUrl = new StringBuffer().append("http://k.1k360.com").append("/").append("appController/ec.PayAction.asyAllinPayNew").toString();;//yinkangtijian.f3322.org:8088
		
		//网关
		static String GateId = "8607";
		//这里放殷康自己的订单号
		static	String Priv1 = "";
		private static final String KEY_CHINAPAY_MERKEY_FILEPATH = "chinapay.merkey.filepath";
		private static final String PAYMENT_URL = "chinapay.payment.url";
		//生产环境地址
		private static String paygateway = "https://payment.chinapay.com/CTITS/payment/TransGet";
		@PostConstruct
	 	public void init() {
	 		 indexUrl =propsLoader.props.getProperty("indexUrl");
	 		 payType = propsLoader.props.getProperty("yk.payType");
	 	}
		public static String getPayURL(Map <String,String>payMap) {
	        StringBuffer html = new StringBuffer();
	        Map<String, String> newmap = createMap(payMap);
	        List keys = new ArrayList(newmap.keySet());
	        for (int i = 0; i < keys.size(); i++) {
	            String key = (String) keys.get(i);
	            String value = (String) newmap.get(key);
	            if (!value.equals("")) {
	                html.append("<input type='hidden' name='").append(key).append("' value='").append(value).append("'/>\n");
	            }
	        }
	        return html.toString();
	    }
		
		public static String getBankInterface(Map <String,String>payMap) {
	        StringBuffer sb = new StringBuffer("");
	       // String name = "online_pay" + String.valueOf(i);
	        String name = "online_unionpay_form";
	     //   sb.append("<form name='").append(name).append("' method='get' action='").append(paygateway).append("' target='second'>\n");
	        sb.append("<form name='").append(name).append("' method='get' action='").append(paygateway).append("' >\n");
	        // row.add("i", i);
	        sb.append(getPayURL(payMap));
	        sb.append("<input type='submit' id='btn_window' />\n");
	        sb.append("</form>");
	        return sb.toString();
	    }
		private static Map<String, String> createMap(Map <String,String>payMap) {
	        Map<String, String> newmap = new HashMap<String, String>();
	        Date dt = new Date();
	        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
	    	SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");
			String TransDate = sf2.format(dt);// 8
	     
	    	String OrdId = "00" + sf.format(dt);
	        newmap.put("OrdId", OrdId);
	        String amt=String.format("%012d", StringUtil.getInt(payMap.get("transamt").substring(0,payMap.get("transamt").length() -2)+"00"));
	        if(payType.equals("T")){
				// d=0.01;
	        	amt="000000000001"; 	
			 }
	        //String amt=payMap.get("transamt");
			try {
				//amt =String.format("%012d", StringUtil.getInt(row.getString("transamt").substring(0,row.getString("transamt").length() -2)+"00"));
				//amt =String.format("%012d", StringUtil.getInt(payMap.get("transamt").substring(0,payMap.get("transamt").length() -2)+"00")); 
			
			} catch (NumberFormatException e1) {
				
				e1.printStackTrace();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}  
				//String BgRetUrl =indexUrl+"/core/payMent.unionAsyback.do";
			//页面应答
			//static	String PageRetUrl = new StringBuffer().append("http://k.1k360.com").append("/").append("appController/ec.PayAction.allinPayNew").toString();
				//String PageRetUrl =indexUrl+"/core/payMent.unionSynback.do";
	        newmap.put("TransAmt", amt);//
	      
	        newmap.put("TransDate", TransDate);
	        newmap.put("TransType", TransType);
	        newmap.put("Version", Version);
	        newmap.put("CuryId", CuryId);
	        newmap.put("GateId", GateId);
	        //newmap.put("PageRetUrl",PageRetUrl);
	        //newmap.put("BgRetUrl", BgRetUrl);
	        newmap.put("PageRetUrl",payMap.get("PageRetUrl"));
	        newmap.put("BgRetUrl", payMap.get("BgRetUrl"));
	       newmap.put("Priv1", payMap.get("oId"));
	       
	       List errorList = new ArrayList();
	        ApplicationContext app = AppContext.getAppContext();
	    	PropsLoader propsLoader= (PropsLoader) app.getBean("propsLoader");
	        String	MerKeyPath =propsLoader.props.getProperty("chinapay.merkey.filepath"); 
			String	pay_url =  propsLoader.props.getProperty("chinapay.payment.url"); 
	       //如果是非快捷支付，是银联网上支付
	       if(payMap.get("GateId")!=null){
	    	   newmap.put("GateId",payMap.get("GateId"));
	    	   MerId="808080201306513";//网上银联支付
	    	   	MerKeyPath =propsLoader.props.getProperty("chinapay.merkey.filepath_WY"); 
	    	   newmap.put("MerId", MerId);  
	       }
	       else{
	    	   MerId="808080201306514";//网上快捷支付
	       }
	       //必须放在这里，因为银联支付和快捷支付都会改变MerId,所有要最后赋值MerId
	       newmap.put("MerId", MerId);
	        
			try {
			
				//MerKeyPath = config.getProperty("chinapay.merkey.filepath");
				//pay_url = config.getProperty("chinapay.payment.url");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	        boolean buildOK = false;
			int KeyUsage = 0;
	      PrivateKey key = new PrivateKey();
			try {
				buildOK = key.buildKey(MerId, KeyUsage, MerKeyPath); 
			} catch (Exception e) {
				
			}
			if (!buildOK) {
				System.out.println("build error!");
			}
	        SecureLink sl = new SecureLink(key);
	        String ChkValue = sl.Sign(MerId + OrdId + amt + CuryId
					+ TransDate + TransType + payMap.get("oId"));
	        newmap.put("ChkValue", ChkValue);
	        return newmap;
	    }
public static Map<String, String> getUnionQPayCallBackData(HttpServletRequest httpservletrequest)throws Exception {
	 ApplicationContext app = AppContext.getAppContext();
     PropsLoader propsLoader= (PropsLoader) app.getBean("propsLoader");
	String	PubKeyPath =  propsLoader.props.getProperty("chinapay.pubkey.filepath"); 


	System.out.println("<====Receive BgReturnData Start!");
	// 支付订单数据准备
	String Version = httpservletrequest.getParameter("version");
	String MerId = httpservletrequest.getParameter("merid");
	String OrdId = httpservletrequest.getParameter("orderno");
	String TransAmt = httpservletrequest.getParameter("amount");// 12
	String CuryId = httpservletrequest.getParameter("currencycode");// 3
	String TransDate = httpservletrequest.getParameter("transdate");// 8
	String TransType = httpservletrequest.getParameter("transtype");// 4
	String Status = httpservletrequest.getParameter("status");
	String BgRetUrl = httpservletrequest.getParameter("BgRetUrl");
	String PageRetUrl = httpservletrequest.getParameter("PageRetUrl");
	String GateId = httpservletrequest.getParameter("GateId");
	String Priv1 = httpservletrequest.getParameter("Priv1");

	String ChkValue = httpservletrequest.getParameter("checkvalue");

	boolean buildOK = false;
	boolean res = false;
	int KeyUsage = 0;
	PrivateKey key = new PrivateKey();
	try {
		buildOK = key.buildKey("999999999999999", KeyUsage, PubKeyPath);
	} catch (Exception e) {
		e.printStackTrace();
	}
	if (!buildOK) {
		System.out.println("build error!");
		return null;
	}
	try {
		SecureLink sl = new SecureLink(key);
		res = sl.verifyTransResponse(MerId, OrdId, TransAmt, CuryId,
				TransDate, TransType, Status, ChkValue);
	} catch (Exception e) {
	}
	
	Map<String, String>  pay = new  HashMap<String, String> ();
	pay.put("merid", MerId);
	
	pay.put("transamt", TransAmt);
	pay.put("transdate", TransDate);
	pay.put("transtype", TransType);
	pay.put("version", Version);
	pay.put("curyid", CuryId);
	pay.put("gateid", GateId);
	pay.put("status", Status);
	pay.put("pagereturl", PageRetUrl);
	pay.put("bgreturl", BgRetUrl);
	pay.put("priv1", Priv1);
	pay.put("chkvalue", ChkValue);
	pay.put("wap_pay_type", "10001");//表示银联快捷支付
	pay.put("orderId", OrdId); 
	pay.put("total_fee", TransAmt);
	
	 
	if (res) {
		System.out.println("BgReturn Check OK!");
		System.out.println(pay.toString());
		System.out.println("Receive BgReturnData End!====>");
		if (Status.equals("1001")) {
			return pay;
		}
	} else {
		pay.put("status", "false");
	}
	return null;
}


}
