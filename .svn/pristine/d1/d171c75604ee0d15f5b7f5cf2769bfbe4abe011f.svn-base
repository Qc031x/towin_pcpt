<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="/WEB-INF/content/public/tags.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <title>您查看的商品已下架</title>
</head>
<body>
<input type="hidden" id="requestUrl" value="${url}" />
</body>
<script>
   var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx44a26b8a7ef26809&redirect_uri=http://r.51towin.com/&response_type=code&scope=snsapi_base&state=2-"+$("#requestUrl").val()+"#wechat_redirect";
   window.location.href = url;
</script>
</html>