<%@ page language="java" contentType="text/html; charset=utf-8"    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/content/public/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name=viewport content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>微信支付</title>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script>
var appId='${data.appId}';
var timeStamp='${data.timeStamp}';
var nonceStr='${data.nonceStr}';
var package1="${data['package']}";
var signType='${data.signType}';
var paySign='${data.sign}'; 
var oid = '${data.oid}'; 
$(document).ready(function(){
	init();
});

function onBridgeReady(){
	
	 WeixinJSBridge.invoke(
	     'getBrandWCPayRequest', {
	         "appId" : appId,     //公众号名称，由商户传入     
	         "timeStamp":timeStamp,         //时间戳，自1970年以来的秒数     
	         "nonceStr" : nonceStr, //随机串     
	         "package" :package1,     
	         "signType" : "MD5",         //微信签名方式：     
	         "paySign": paySign //微信签名 
	     },
	     function(res){
	         if(res.err_msg == "get_brand_wcpay_request:ok" ) {
			     //alert('支付成功');
				 self.location='http://r.51towin.com/core/payMent.paySuccess.do?oid='+oid;
			 }else if(res.err_msg == "get_brand_wcpay_request:fail" ) {
			     alert('支付失败');
				 self.location='http://r.51towin.com/core/twechat.toReservation.do';
			 }else if(res.err_msg == "get_brand_wcpay_request:cancel" ) {
			     alert('您已经取消支付');
			}
	
			// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
	     }
	 ); 
}

function init(){
	if (typeof WeixinJSBridge == "undefined"){
	    if( document.addEventListener ){
	        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	    }else if (document.attachEvent){
	        document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	    }
	 }else{
	    onBridgeReady();
	 } 
}

</script>
</head>
<body style="background:#e2e2e2;">
欢迎进入微信支付界面! <p><a href='#' onclick='onBridgeReady()'><font color=blue>继续支付</font></a>
</body>
</html>