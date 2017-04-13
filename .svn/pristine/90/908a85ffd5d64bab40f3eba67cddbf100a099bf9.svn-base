<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
<meta name="format-detection" content="telephone=no">
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<title>选择城市</title>
<link rel="stylesheet" type="text/css" href="/css/citySelect.css">
<link rel="stylesheet" href="/css/public.css"/>
<script type="text/javascript" src="/js/jquery.js"></script>
<%@include file="/WEB-INF/content/public/tags.jsp" %>
<body>
<div class="main">
    <div class="header">
        <div class="header_left">
            <a href="javascript:history.go(-1)">返回</a>
        </div>
        <div class="header_info">选择城市</div>
        <div class="header_right" onclick="changeMenu()">
            <div class="header_rbg"></div>
               <span>导航</span>
        </div>
    </div>
    <%@include file="/WEB-INF/content/public/top_menu.jsp" %>

  
    <div class="checkcity">
        <div class="checkcity_title">
            <h3>选择城市:</h3>
            <p>${cityname_pcpt }</p>
        </div>
        <div class="select_city">
            <div class="select_city_list">
                <b>热门城市</b>
                <ul>
		            <li><a href="javascript:void(0);" onclick="updateCity(10101, '北京')">北京</a></li>
		            <li><a href="javascript:void(0);" onclick="updateCity(10173, '上海')">上海</a></li>
		            <li><a href="javascript:void(0);" onclick="updateCity(10301, '广州')">广州</a></li>
		            <li><a href="javascript:void(0);" onclick="updateCity(10312, '深圳')">深圳</a></li>
		        </ul>
            </div>

       <div class="all_city">
        <b>全部城市</b>
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_1">A</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_2">B</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_3">C</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_4">D</a></li>
        </ul>
        
        <ul id="showinitials_a" class="city">
        </ul>
 
        <ul id="showinitials_b" class="city">
        </ul>
        
        <ul id="showinitials_c" class="city" >
        </ul>
        
        <ul id="showinitials_d" class="city" >
        </ul>
        
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_5">E</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_6">F</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_7">G</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_8">H</a></li>
        </ul>

        <ul id="showinitials_e" class="city">
        </ul>
        
        <ul id="showinitials_f" class="city">
        </ul>
        
        <ul id="showinitials_g" class="city" >
        </ul>
        
        <ul id="showinitials_h" class="city">
        </ul>
        
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_9">I</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_10">J</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_11">K</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_12">L</a></li>
        </ul>
        
        <ul id="showinitials_i" class="city">
        </ul>
        
        <ul id="showinitials_j" class="city">
        </ul>
        
        <ul id="showinitials_k" class="city">
        </ul>
		
		<ul id="showinitials_l" class="city">
        </ul>
        
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_13">M</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_14">N</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_15">O</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_16">P</a></li>
        </ul>
        
        <ul id="showinitials_m" class="city">
        </ul>
        
        <ul id="showinitials_n" class="city">
        </ul>
        
        <ul id="showinitials_o" class="city">
        </ul>
       
		<ul id="showinitials_p" class="city">
        </ul>
        
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_17">Q</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_18">R</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_19">S</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_20">T</a></li>
        </ul>
        
        <ul id="showinitials_q" class="city">
        </ul>
        
        <ul id="showinitials_r" class="city">
        </ul>
        
        <ul id="showinitials_s" class="city">
        </ul>
        
        <ul id="showinitials_t" class="city">
        </ul>
        
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_21">U</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_22">V</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_23">W</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_24">X</a></li>
        </ul>
        
        <ul id="showinitials_u" class="city">
        </ul>
        
        <ul id="showinitials_v" class="city">
        </ul>
        
        <ul id="showinitials_w" class="city">
        </ul>
        
        <ul id="showinitials_x" class="city">
        </ul>
        
        <ul class="initials">
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_25">Y</a></li>
            <li><a href="javascript:void(0)" class="initials_title" id="sub_initials_26">Z</a></li>
        </ul>
        
        <ul id="showinitials_y" class="city">
        </ul>
        
        <ul id="showinitials_z" class="city">
        </ul>

    
    </div>
        </div>
    </div>

</div>
</body>
<script type="text/javascript" src="/js/commonLib.js"></script>
<script type="text/javascript" src="/js/rem.js"></script>
<script type="text/javascript" src="/js/checkCity.js"></script>
<script type="text/javascript">
var ctxFront = "${ctxFront}";
var allcity = ${allCity};
</script>
</html>
