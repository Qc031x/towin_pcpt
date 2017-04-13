<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<%@include file="../public/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="/css/orderform.css">
	<link rel="stylesheet" type="text/css" href="/css/sgfmform.css">
	<script type="text/javascript" src="/js/ykcalendar.js"></script>
	<script type="text/javascript" src="/js/jquery.sgfmform.js"></script>
    <title>核对订单信息</title>
</head>
<body>
<form id="subRsForm">
<div class="main">
    <div class="ornament check_box">
        <div class="ornament_img">
            <img src="${ctxImg}/product/${map['CSID']}/${map['IMG']}">
        </div>
        <div class="info">
            <c:choose>
           		<c:when test="${map['SEX'] eq '1'}">
           			<h1> ${map['PNAME']}(男)</h1>
           		</c:when>
           		<c:when test="${map['SEX'] eq '2'}">
           			<h1> ${map['PNAME']}(已婚女)</h1>
           		</c:when>
           		<c:otherwise>
           			<h1> ${map['PNAME']}(未婚女)</h1>
           		</c:otherwise>
           	</c:choose>
            <p><b id="singlePrice">${map['SHOP_PRICE']}</b></p>
            <c:choose>
             	<c:when test="${map['PAY_TYPE'] eq 2}">
             		<div class="onsale" data_id="0"><p><span class="obg">到店付款</span></p></div>
             		<input type="hidden" name="yt" value="0" />
             	</c:when>
             	<c:otherwise>
             		<c:choose>
             			<c:when test="${map['coupon'] eq 1}">
             				<div class="onsale" data_id="1"><p><span>在线支付</span><em>立减<i>${map['PRICE']}</i>元</em></p></div>
             			</c:when>
             			<c:otherwise>
             				<div class="onsale" data_id="1"><p><span>在线支付</span></p></div>
             			</c:otherwise>
             		</c:choose>
             		<input type="hidden" name="yt" value="1" />
             	</c:otherwise>
             </c:choose>
        </div>
    </div>
    <div class="interval"></div>


	<c:forEach step="1" begin="1" end="${map['QUANTITY']}">
		<div class="protip">体检人信息</div>
    	<div class="interval"></div>
		<div class="peobox rsInfo">
	        <div id="msg" class="errme">请输入正确信息</div>
	        <label><p><i>姓&nbsp;&nbsp;&nbsp;名：</i><b><input type="text" placeholder="请输入体检人姓名" datatype="*" nullmsg="请输入体检人姓名" errormsg="请输入体检人姓名" name="names" posiFix></b></p></label>
	        <c:choose>
          		<c:when test="${map['SEX'] eq '1'}">
					<label class="info"><p><i>性&nbsp;&nbsp;&nbsp;别：</i><b><span class="active" data="0">男</span></b></p><input type="hidden" name="sexs" value="0" datatype="*" nullmsg="请选择体检人性别" errormsg="请选择体检人性别" id="sex"/></label>
            	</c:when>
          		<c:when test="${map['SEX'] eq '2' || map['SEX'] eq '4'}">
					<label><p><i>性&nbsp;&nbsp;&nbsp;别：</i><b><span class="active">女</span></b></p></label>
					<input type="hidden" name="sexs" value="1" />
            	</c:when>
          	</c:choose>
	        <label><p><i>身份证：</i><b><input class="idcard" type="text" placeholder="请输入体检人身份证" datatype="z" nullmsg="请输入体检人身份证号码" errormsg="身份证号码输入不正确" name="ids" posiFix></b></p></label>
	        <label><p><i>年&nbsp;&nbsp;&nbsp;龄：</i><b><input class="age" type="text" datatype="*" placeholder="请输入体检人年龄" nullmsg="请输入体检人年龄" errormsg="年龄输入不正确" name="ages" posiFix></b></p></label>
	        <c:choose>
           		<c:when test="${map['SEX'] eq '1'}">
					<label class="info"><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b><span class="active" data="1">已婚</span><span data="0">未婚</span></b></p><input type="hidden" name="marrias" value="1" datatype="*" nullmsg="请选择体检人婚否" id="marriage"/></label>
            	</c:when>
            	<c:when test="${map['SEX'] eq '2'}">
					<label class="info"><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b><span class="active" data="1">已婚</span></b></p><input type="hidden" name="marrias" value="1" datatype="*" nullmsg="请选择体检人婚否" id="marriage"/></label>
            	</c:when>
           		<c:otherwise>
           			<label><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b><span class="active">未婚</span></b></p></label>
           			<input type="hidden" name="marrias" value="0" />
           		</c:otherwise>
           	</c:choose>
	        <label><p><i>手&nbsp;&nbsp;&nbsp;机：</i><b><input type="text" placeholder="请输入体检人手机" datatype="b" nullmsg="请输入体检人手机号码" errormsg="手机号码输入不正确" name="mobiles" posiFix></b></p></label>
	    </div>
	</c:forEach>
    <div class="clear"></div>
    <div class="interval"></div>

    <div class="protip">确认体检分院和时间</div>
    <div class="interval"></div>

    <div class="peobox">
        <label><p><i>城&nbsp;&nbsp;&nbsp;市：</i><b>
            <select class="city selFix" datatype="*" nullmsg="请选择体检城市" errormsg="请选择体检城市" onchange="getMedical(this, ${map['PID']})">
                <option value="">请选择</option>
                <c:forEach items="${map['areas']}" var="areas">
                	<option value="${areas.key}">${areas.value}</option>
                </c:forEach>
            </select>
        </b></p></label>
        <label><p><i>分&nbsp;&nbsp;&nbsp;院：</i><b>
            <select class="marleft shop selFix" datatype="*" nullmsg="请选择体检分院" errormsg="请选择体检分院" onchange="getAddress(this)" name="rs.esid">
                <option value="">请先选择城市</option>
            </select>
        </b></p></label>
        <input type="hidden" name="address" />
        <label><p><i>地&nbsp;&nbsp;&nbsp;址：</i><b><input type="text" class="address" readonly></b></p></label>
        <label><p><i>体检时间：</i><b><input class="input1 checklookrili" id="cal" name="rs.createrTime" datatype="*" nullmsg="请选择预约日期" errormsg="请选择预约日期" readonly posiFix /></b></p></label>
    </div>
    <div class="clear"></div>


    <div class="interval"></div>



    <div class="slidebox ordertongji">
        <div class="allpay">
            <span class="fl">订单总额</span>
            <span class="fr jine"  id="i_totpri">¥<em></em></span>
        </div>
        <div class="allpay">
            <p><span class="fl">到店付款</span><span class="fr" id="payStore"><em>0</em></span></p>
            <p><span class="fl">在线支付</span><span class="fr" id="onlinePay"><em>0</em></span></p>
            <p><span class="fl">在线支付立减</span><span class="fr" id="lijian"><em>0</em></span></p>
            <input type="hidden" id="sumPrice" name="sumPrice" value="0" />
        </div>
    </div>

    <div class="bottomblank2"></div>

    <div class="allpaybox fix" id="allpay">
        <a class="fixpay" href="javascript:;">
            在线实付:<em>¥<e></e></em>
        </a>
        <a class="btn" href="javascript:void(0);" onclick="subRsForm();">提交订单</a>
    </div>

    <div class="calenderbox">
        <div class="calenderbg"></div>
        <div class="yuyue_rili calenderadss">
            <div class="yue_tit">
                <div class="riliqiean1"></div>
                <div class="riliyear"><span class="xlxzrili" id="xlyear"></span> 年 <span class="xlxzrili" id="xlmonth"></span> 月</div>
                <div class="riliqiean2"></div>
            </div>
            <ul class="fixxingqi">
                <li>星期日</li>
                <li>星期一</li>
                <li>星期二</li>
                <li>星期三</li>
                <li>星期四</li>
                <li>星期五</li>
                <li>星期六</li>
            </ul>
            <div class="yue_rili">
                <ul class="yue_day"></ul>
            </div>
            <div class="clear"></div>
        </div>
        <div class="cancel">取消</div>
    </div>
    <input type="hidden" id="page_esid" />
    <input type="hidden" name="rs.pid" value="${map['PID']}" />
    <input type="hidden" name="quantity" value="${map['QUANTITY']}" />
    <input type="hidden" name="rs.isVip" value="${map['VIP']}" />
    <input type="hidden" name="rs.docReports" value="${map['DOC_REPORT']}" />
    <input type="hidden" name="coupon" id="coupon"/>
	<input type="hidden" name="plat_token" value="${token}" />
</div>
</form>

<script>
    /*性别婚否*/
    $(".peobox span").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        $(this).parents(".info").find("input").val($(this).attr("data"));
    });

    $(".cancel,.calenderbg").click(function () {
        $(".calenderbox,.yuyue_rili").stop().fadeOut();
        $(window).off("touchmove");
    });


    
</script>
<script type="text/javascript" src="/js/validateIdCard.js"></script>
<script type="text/javascript" src="/js/order/orderSubmit.js"></script>
<script type="text/javascript" src="/js/positionFix.js"></script>
</body>
</html>