<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/content/public/tags.jsp"%>
<script type="text/javascript" src="/js/rem.js"></script>
<script type="text/javascript" src="/js/jquery.js"></script>
<link rel="stylesheet" href="/css/public.css"/>
<link rel="stylesheet" type="text/css" href="/css/flavr/animate.css">
<link href="/css/flavr/flavr.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="/js/commonLib.js"></script>
<script type="text/javascript" src="/js/flavr/flavr.js"></script>
<script type="text/javascript" src="/js/globalMsg.js"></script>
<script>
$(document).ready(function () {
    $(".header_right").click(function(){
            $(this).children().toggleClass("rbgchange");
            $(".linklist").slideToggle(200).css("position","fixed");
        });
});
</script>
<div class="header posiFixTop">
    <div class="header_left">
        <a href="/core/user.valiLoginInfo.do">返回</a>
    </div>
    <div class="header_info">
        ${title}
    </div>
    <div class="header_right">
        <div class="header_rbg"></div>
    </div>
</div>
<div style="width:100%;height:2.5rem;"></div>
<ul class="linklist">
    <li><a href="/" class="indexlink">首页</a>
    <li><a href="/core/user.valiLoginInfo.do" class="personlink">我的</a>
    <li><a href="/core/product.toSearch.do" class="searchlink">搜索</a>
</ul>
<div class="clear"></div>