package com.sgfm.datacenter.sys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sgfm.datacenter.sys.DemoBase;
import com.unionpay.acp.sdk.SDKConfig;

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
public class WapUnionPay extends DemoBase {
//public static void main(String[] args) {
	//static String indexUrl="http://n.1k360.com/";
	public static String getPayHtml(Map<String,String> map,String indexUrl) {

		/**
		 * 参数初始化
		 * 在java main 方式运行时必须每次都执行加载
		 * 如果是在web应用开发里,这个方写在可使用监听的方式写入缓存,无须在这出现
		 */
		SDKConfig.getConfig().loadPropertiesFromSrc();// 从classpath加载acp_sdk.properties文件

		/**
		 * 组装请求报文
		 */
		Map<String, String> data = new HashMap<String, String>();
		// 版本号
		data.put("version", "5.0.0");
		// 字符集编码 默认"UTF-8"
		data.put("encoding", "UTF-8");
		// 签名方法 01 RSA
		data.put("signMethod", "01");
		// 交易类型 01-消费
		data.put("txnType", "01");
		// 交易子类型 01:自助消费 02:订购 03:分期付款
		data.put("txnSubType", "01");
		// 业务类型
		data.put("bizType", "000201");
		// 渠道类型，07-PC，08-手机
		data.put("channelType", "08");
		
		//后台应答
	//	pc allinPayNew1  asyAllinPayNew1 
	//	String BgRetUrl = new StringBuffer().append("http://www.1k360.com").append("/").append("appController/ec.PayAction.asyAllinPayNew1").toString();;//yinkangtijian.f3322.org:8088
		//页面应答
	//	String PageRetUrl = new StringBuffer().append("http://www.1k360.com").append("/").append("appController/ec.PayAction.allinPayNew1").toString();
		
		if(map.get("type")!=null && map.get("type").toString().equals("platform")){
			// 前台通知地址 ，控件接入方式无作用
			data.put("frontUrl", indexUrl+"core/platformPayMent.synback.do");
			// 后台通知地址
			data.put("backUrl", indexUrl+"core/platformPayMent.asyback.do");
		}else{
			// 前台通知地址 ，控件接入方式无作用
			data.put("frontUrl", indexUrl+"core/payMent.synback.do");
			// 后台通知地址
			data.put("backUrl", indexUrl+"core/payMent.asyback.do");
		}
		
		
		// 接入类型，商户接入填0 0- 商户 ， 1： 收单， 2：平台商户
		data.put("accessType", "0");
		// 商户号码，请改成自己的商户号
		data.put("merId", "898440380110216");
		// 商户订单号，8-40位数字字母
		//data.put("orderId", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		if(map.get("oId").length()<8){
			String str = "";
			for(int i = 0;i<8-map.get("oId").length();i++){
				str += "Y";
			}
			map.put("oId", str+map.get("oId"));
		}
		data.put("orderId", map.get("oId"));
		// 订单发送时间，取系统时间
		data.put("txnTime", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
		// 交易金额，单位分
		data.put("txnAmt", map.get("sum_price"));
		// 交易币种
		data.put("currencyCode", "156");
		// 请求方保留域，透传字段，查询、通知、对账文件中均会原样出现
		// data.put("reqReserved", "透传信息");
		// 订单描述，可不上送，上送时控件中会显示该信息
		// data.put("orderDesc", "订单描述");

		Map<String, String> submitFromData = signData(data);

		// 交易请求url 从配置文件读取
		String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();
		
		/**
		 * 创建表单
		 */
		String html = createHtml(requestFrontUrl, submitFromData);
		System.out.println(html);
         return html;
	}

}
