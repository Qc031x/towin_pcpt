<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>账号安全</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name = "format-detection" content = "telephone=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
	<%@include file="../public/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="/css/accountSafe.css">
  </head>
  <body style="background:#F5F5F5;">
 	 <script>
        $(document).ready(function () {
             /* $(".sp2").html($(".sp2").html().substring(0, 5) + "******"); */
        });
    </script> 
<div class="accountSafe">
	<p><span class="sp1">已验证手机</span><span class="sp2">${mobile}</span></p>
    <p><a href="/core/user.toUpdatePwd.do"><span class="sp1 sp4">修改密码</span><span class="sp3"></span></a></p>
</div>
  </body>
</html>
