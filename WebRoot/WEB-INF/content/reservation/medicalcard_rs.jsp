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
    <script type="text/javascript" src="/js/ykcalendar.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/tjkReserve.css">
	<link rel="stylesheet" type="text/css" href="/css/sgfmform.css">
	<script type="text/javascript" src="/js/jquery.sgfmform.js"></script>
    <title>体检卡预约</title>
</head>
<script type="text/javascript">
function saveCardRs(){
    $("#saveCardRe").submit();
}
</script>
<body>
<form id="saveCardRe">
<div class="main">
    <div class="protip">体检卡信息</div>
    <div class="interval"></div>
    <c:choose>
    	<c:when test="${infoMap['product']['SEX'] eq 1}">
	    	<div class="protip" style="color: #716f6f; font-size: 13px; font-weight: normal;">套餐名称：${infoMap['product']['PNAME']}(男)</div>
	    </c:when>
	    <c:when test="${infoMap['product']['SEX'] eq 2}">
	    	<div class="protip" style="color: #716f6f; font-size: 13px; font-weight: normal;">套餐名称：${infoMap['product']['PNAME']}(已婚女)</div>
	    </c:when>
	    <c:when test="${infoMap['product']['SEX'] eq 4}">
	    	<div class="protip" style="color: #716f6f; font-size: 13px; font-weight: normal;">套餐名称：${infoMap['product']['PNAME']}(未婚女)</div>
	    </c:when>
    </c:choose>
    <div class="interval"></div>

    <div class="protip">体检人信息</div>
    <div class="interval"></div>

    <div class="peobox selbox rsInfo">
        <div id="msg" class="errme">请输入正确信息</div>
        <label><p><i>姓&nbsp;&nbsp;&nbsp;名：</i><b><input type="text" placeholder="请输入体检人姓名" datatype="*" nullmsg="请输入体检人姓名" errormsg="请输入体检人姓名"  name="sReservation.reName" posiFix></b></p></label>
        <c:choose>        		
         	<c:when test="${infoMap['product']['SEX'] eq 1}">
				<label><p><i>性&nbsp;&nbsp;&nbsp;别：</i><b><span class="active">男</span></b></p></label>
				<input type="hidden" name="sReservation.reSex" value="0" id="sex"/>
           	</c:when>
         	<c:when test="${infoMap['product']['SEX'] eq 2 || infoMap['product']['SEX'] eq 4}">
				<label><p><i>性&nbsp;&nbsp;&nbsp;别：</i><b><span class="active">女</span></b></p></label>
				<input type="hidden" name="sReservation.reSex" value="1" id="sex" />
           	</c:when>
        </c:choose>
        <label><p><i>身份证：</i><b><input class="idcard" type="text" placeholder="请输入体检人身份证" datatype="z" nullmsg="请输入体检人身份证号码" errormsg="身份证号码输入不正确" name="sReservation.reCid" posiFix></b></p></label>
        <label><p><i>年&nbsp;&nbsp;&nbsp;龄：</i><b><input class="age" type="text" datatype="*" nullmsg="请输入体检人年龄" errormsg="年龄输入不正确" name="sReservation.reYear" posiFix></b></p></label>
        	<c:choose>
          		<c:when test="${infoMap['product']['SEX'] eq 1}">   
				<label class="info"><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b><span class="active" data="1">已婚</span><span data="0">未婚</span></b></p><input type="hidden" name="sReservation.reMarriage" value="1" datatype="*" nullmsg="请选择体检人婚否" id="marriage"/></label>
           		</c:when>
           		<c:when test="${infoMap['product']['SEX'] eq 2}">   
					<label><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b><span class="active">已婚</span></b></p></label>
          			<input type="hidden" name="sReservation.reMarriage" value="1" id="marriage" />
           		</c:when>
           		<c:when test="${infoMap['product']['SEX'] eq 4}">   
					<label><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b><span class="active">未婚</span></b></p></label>
          			<input type="hidden" name="sReservation.reMarriage" value="0" id="marriage" />
           		</c:when>         		
          	</c:choose>
        <label><p><i>手&nbsp;&nbsp;&nbsp;机：</i><b><input type="text" placeholder="请输入体检人手机" datatype="b" nullmsg="请输入体检人手机号码" errormsg="手机号码输入不正确" name="sReservation.reTel" posiFix></b></p></label>
    </div>
    <div class="clear"></div>
    <div class="interval"></div>

    <div class="protip">确认体检分院和时间</div>
    <div class="interval"></div>

    <div class="peobox">
        <label><p><i>城&nbsp;&nbsp;&nbsp;市：</i><b>
            <select class="city selFix" datatype="*" nullmsg="请选择体检城市" errormsg="请选择体检城市" onchange="getMedical(this, ${infoMap['product']['PID']})">
                <option value="">请选择城市</option>
	            <c:forEach items="${infoMap['areaMap']}" var="areas" >
	             	<option value="${areas.key}">${areas.value}</option>
	             </c:forEach>
            </select>
        </b></p></label>
        <label><p><i>分&nbsp;&nbsp;&nbsp;院：</i><b>
            <select class="marleft shop selFix" datatype="*" nullmsg="请选择体检分院" errormsg="请选择体检分院" onchange="getAddress(this)" name="sReservation.esid">
                <option value="">请先选择城市</option>
            </select>
        </b></p></label>
        <label><p><i>地&nbsp;&nbsp;&nbsp;址：</i><b><input type="text" class="address" readonly></b></p></label>
        <label><p><i>体检时间：</i><b><input class="input1 checklookrili" id="cal" name="sReservation.createrTime" datatype="*" nullmsg="请选择预约日期" errormsg="请选择预约日期" readonly posiFix /></b></p></label>
        <input type="hidden" name="address" />
    </div>
    <div class="clear"></div>


    <div class="interval"></div>

    <div class="formmain">
        <div class="xmlist">
        	<c:if test="${infoMap['product']['VIP'] eq 0}">
        		<span class="tit">是否享受VIP服务：</span><span class="cons">否</span>
        	</c:if>
        	<c:if test="${infoMap['product']['VIP'] eq 1}">
        		<span class="tit">是否享受VIP服务：</span><span class="cons">是</span>
        	</c:if>
        </div>
        <div class="xmlist">
        	<c:if test="${infoMap['product']['DOC_REPORT'] eq 0}">
        		<span class="tit">电子体检报告：</span><span class="cons">无</span>
        	</c:if>
        	<c:if test="${infoMap['product']['DOC_REPORT'] eq 1}">
        		<span class="tit">电子体检报告：</span><span class="cons">有</span>
        	</c:if>
        </div>
        <div class="xmlist info">
            <span class="tit">纸质报告领取方式：</span>
            <span class="cons selbox">
                <span class="active" data="1">自取</span><span data="2">邮寄</span>
            </span>
            <input type="hidden" name="sReservation.paperReports" value="1" />
        </div>
       <!--  <div class="xmlist">
            <span class="tit1">备注：</span>
            <label><textarea name="sReservation.reDesc"></textarea></label>
        </div> -->
        <input type="hidden" name="sReservation.docReports" value="${infoMap['product']['DOC_REPORT']}" />
        <input type="hidden" name="sReservation.isVip" value="${infoMap['product']['VIP']}" />
        <input type="hidden" name="sReservation.pid" value="${infoMap['product']['PID']}" />
        <input type="hidden" name="sReservation.PName" value="${infoMap['product']['PNAME']}" />
        <input type="hidden" name="sReservation.card" value="${card}" />
        <input type="hidden" name="sReservation.password" value="${password}" />
        <input type="hidden" name="sReservation.rType" value="1" />
        <input type="hidden" name="sReservation.olid" value="${olid}" />
    </div>
    
    <div class="bottomblank1"></div>

    <div class="buybox1 fix">
        <a href="/content/reservation.rs_login.do">取消</a>
        <a href="javascript:;" class="buybtn" onclick="saveCardRs(1);">确认</a>
    </div>

    <div class="calenderbox">
        <div class="calenderbg"></div>
        <div class="yuyue_rili calenderadss">
            <div class="yue_tit">
                <div class="riliqiean1"></div>
                <div class="riliyear"><span class="xlxzrili" id="xlyear">2016</span> 年 <span class="xlxzrili" id="xlmonth">8</span> 月</div>
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
</div>
<input type="hidden" id="page_esid" />
<input type="hidden" name="plat_token" value="${token}" />
</form>

<script>
    /*性别婚否*/
    $(".selbox span").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        $(this).parents(".info").find("input").val($(this).attr("data"));
    });

    $(".checklookrili").click(function(){
		if($(this).parents(".peobox").find(".shop").val() == ''){
			alertMsg({"content" : "请先选择预约门店!", "type" : 3, "close" : true});
			return false;
		}  
		$("#page_esid").val($(this).attr("data"));
		ykrili.getjiazairili();
		$(".calenderbox").fadeIn();
		$(".calenderbox .yuyue_rili").fadeIn();
	});
    
    $(".cancel,.calenderbg").click(function () {
        $(".calenderbox,.yuyue_rili").stop().fadeOut();
        $(window).off("touchmove");
    });
    
    

</script>
<script type="text/javascript" src="/js/validateIdCard.js"></script>
<script type="text/javascript" src="/js/reservation/rsSubmit.js"></script>
<script type="text/javascript" src="/js/positionFix.js"></script>
</body>
</html>