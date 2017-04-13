<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>出错啦！！页面找不到咯~</title>
<style type="text/css">
*{margin: 0; padding: 0; border: none; list-style: none;}
a{text-decoration: none; color: #fff;}
.errbg{ width: 35rem; height: 40rem; margin: auto; position: absolute; top: 0; left: 0; bottom: 0; right: 0; overflow: auto;}
.errbg .errbox{ text-align: center; font-family: "微软雅黑"; overflow: hidden;}
.errbg .errbox img{width: 35rem;}
</style>
<script type="text/javascript" src="/js/jquery.js"></script>
<script>
$(function(){
	$("#goback").click(function(){
		window.history.back();
	})
})
</script>
</head>
<body>
<div class="errbg">
	<div class="errbox">
		<a href="${ctxFront}/"><img src="/images/error.png"></a>
	</div>
</div>
</body>
</html>
