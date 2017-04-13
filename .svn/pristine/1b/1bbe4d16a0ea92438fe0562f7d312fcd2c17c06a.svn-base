<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>会员注册</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" href="/css/register.css" />
<%@include file="../public/head.jsp"%>
<%@include file="../public/sgfm.jsp"%>
<script type="text/javascript" src="/js/placeHolderTips.js"></script>
</head>
<body style="background: #F5F5F5;">
	<div class="regconxiangbox">
		<form id="registerForm" method="post">
			<%-- <div class="regconxiang1">
				<input name="tMember.account" class="inputk1" type="text" tips="用户名" ajaxurl="${ctx}/core/user.findRegisterByAccount.do" datatype="*6-16" nullmsg="请输入用户名!" errormsg="用户名为字母、数字或符号组成的6位以上字符！" />
			</div>
			<div class="regconxiang1">
				<input name="tMember.password" class="inputk2" type="password" tips="密码" datatype="*6-16" nullmsg="请输入密码" errormsg="密码长度不能少于6位，且不多于16位！" />
			</div>
			<div class="regconxiang1">
				<input class="inputk2" type="password" tips="确认密码"  />
			</div> --%>
			<div class="regconxiang1">
				<input class="inputk3" id="mobile" type="text" name="tMember.mobile" tips="手机号码" datatype="b" nullmsg="请输入手机号码!" errormsg="手机号码填写错误！" />
			</div>
			<div class="regconxiang1">
				<input type="text" class="inputk5 login_acc2" style="width: 57.7%;" name="veriCode" datatype="*" nullmsg="请输入验证码" maxlength="4" tips="请输入验证码" tips="请输入验证码"/>
                <div class="get_ver" id="btnSendcode"  onclick="achCaptchas()">获取验证码</div>
                <input type="hidden" id="mark" name="mark" value="">
			</div>
			<div class="regconxiang1">
				<input name="captchas" class="inputk4" type="text" tips="验证码" datatype="*" nullmsg="请输入验证码!" maxlength="4" />
				<a class="yzmimg" href="javascript:void(0)">
					<img style='width: 100%; height: 100%' src="${ctx}/loginLicence" alt="验证码" id="authCode" onclick="changeImg()" />
				</a>
			</div>

		
			<div class="reg_xieyi2">
				<%--<a id="tanyieyi" href="javascript:void(0);" style="color: #1aa9b9;">《用户注册协议》</a> --%>
			</div>
			<div class="reg_an2">
				<a onclick="javascript:void(0);" id='doRegister'>确定</a>
			</div>
		</form>
		<div class="botzhanwei" id="pro_con1"></div>
		<div class="huitop"></div>
	</div>
	
	<script>
		/**
		 * 点击刷新验证码
		 */
		function changeImg() {
			$("#authCode").attr("src", "${ctx}/loginLicence?t=" + randomChar(6));
			$("#captchas").val("");
		}
		/**
		 *  产生随机数
		 */
		function randomChar(l) {
			var x = "123456789poiuytrewqasdfghjklmnbvcxzQWERTYUIPLKJHGFDSAZXCVBNM";
			var tmp = "";
			for (var i = 0; i < l; i++) {
				tmp += x.charAt(Math.ceil(Math.random() * 100000000) % x.length);
			}
			return tmp;
		}
	</script>
</body>
<script type="text/javascript" src="/js/order/orderUtil.js"></script>
<script type="text/javascript" src="/js/order/orderSubmit.js"></script>
</html>
