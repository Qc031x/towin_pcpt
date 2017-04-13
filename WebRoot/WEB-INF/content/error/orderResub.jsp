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
    <script type="text/javascript" src="/js/rem.js"></script>
    <link rel="stylesheet" href="/css/public.css"/>
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/commonLib.js"></script>
    <title>订单重复提交</title>
    <script>
        $(document).ready(function () {
            $(".header_right").click(function(){
                $(this).children().toggleClass("rbgchange");
                $(".linklist").slideToggle(200).css("position","fixed");
            });

        });
    </script>
<style type="text/css">
.offEhelf{padding: 3rem 1.5rem 0; overflow: hidden;}
.offEhelf img{width: 9rem; margin: 0 0 0.5rem 2rem;}
.offEhelf p{line-height: 1.4rem; font-size: 0.6rem; color: #666; text-align: center; width: 100%;}

.sbtn{margin: 3.75rem 1.0rem 0; font-size: 0.65rem; text-align: center; line-height: 2.0rem;}
.sbtn a{color: #fff; height: 2.0rem; background: #1e8bc3; border-radius: 5px; display: block;}
</style>
</head>
<body>
<div class="header">
        <div class="header_left">
            <a href="javascript:history.go(-1);">返回</a>
        </div>
        <div class="header_info">订单已提交</div>
        <div class="header_right">
            <div class="header_rbg"></div>
        </div>
    </div>
    <%@include file="/WEB-INF/content/public/top_menu.jsp" %>

<div class="offEhelf">
    <img src="/images/resubmit.png">
    <p>您的订单已经提交了哦,因网速或者其他问题可能存在延迟,请耐心等待,不要重复提交订单~</p>
</div>
<div class="sbtn">
    <a href="${ctxFront}/">返回首页</a>
</div>
</body>
</html>