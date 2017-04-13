<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/content/public/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="/css/cardInfo.css">
    <title>卡密详情</title>
</head>
<body>
<!--头部-->
<div class="main">

    <!--套餐介绍-->
    <div class="tjchead">
        <div class="imgbox">
            <img src="/cardImg/${card['cardImg']['CAR_IMG']}" onerror="this.src='/images/default_img.png'">
            <span class="imgleft">${card.cardImg.LABEL==null?'':card.cardImg.LABEL}</span>
            <span class="imgright">¥${card.SHOP_PRICE }</span>
            <div class="recommend">
                <p class="rectitle">
                	${card.NAME }
                </p>
               <%--  <p class="paddleft">${fn:substring(card.cardImg.MAIN_TITLE,0,10)}</p>
                <p class="paddleft">${fn:substring(card.cardImg.SUB_TITLE1,0,10)}</p> --%>
              <%--   <p class="paddleft">${fn:substring(card.cardImg.SUB_TITLE2,0,10)}</p> --%>
            </div>
        </div>
    </div>

    <div class="info">
        <p>卡&emsp;&emsp;号：<span>${card.CARD_NUMBER}</span></p>
        <p>密&emsp;&emsp;码：<span>${card.PASSWORD }</span></p>
        <p>编&emsp;&emsp;号：<span>${card.OID }</span></p>
        <p>价&emsp;&emsp;格：<span class="ocol">¥ ${card.SHOP_PRICE}</span></p>
        <p>适用性别：<span><c:choose>
                	<c:when test="${card.SEX eq 1 }">男</c:when>
                	<c:when test="${card.SEX eq 2 }">已婚女</c:when>
                	<c:when test="${card.SEX eq 4 }">未婚女</c:when>
                	</c:choose></span></p>
        <p>购买日期：<span>${card.POST_DATE }</span></p>
        <p>有&ensp;效&ensp;期：<span>${card.LAST_DATE }</span></p>
        <p>状&emsp;&emsp;态：
        <c:choose>
       		<c:when test="${card.IS_VALID == 0}">
          		<span>未使用</span>
        	</c:when>
        	<c:when test="${card.IS_VALID == 1}">
          		<span>审核中</span>
        	</c:when>
        	<c:when test="${card.IS_VALID == 2}">
          		<span>已使用</span>
        	</c:when>
        	<c:when test="${card.IS_VALID == -1}">
          		<span>已失效</span>
        	</c:when>
        </c:choose>
        </p>
    </div>

    <div class="buybox1">
    	<c:choose>
    		<c:when test="${card.IS_VALID == 0 }">
		        <a href="javascript:;" class="buybtn now">立即预约</a>
    		</c:when>
    		<c:when test="${card.IS_VALID == 1 }">
		        <a href="javascript:;" class="buybtn">审核中</a>
    		</c:when>
    		<c:when test="${card.IS_VALID == 2 }">
		        <a href="javascript:;" class="buybtn">已使用</a>
    		</c:when>
    		<c:when test="${card.IS_VALID == -1 }">
    			 <a href="javascript:;" class="buybtn">已失效</a>
    		</c:when>
    	</c:choose>
    </div>
    
    <div class="alertBg"></div>
    <div class="alertBox">
        <img src="/images/alert.png">
        <div class="alertCon">
            <p>卡号：${card.CARD_NUMBER }</p>
            <p>密码：${card.PASSWORD }</p>
        </div>
        <a id="confirm">确认使用</a>
    </div>
    
</div>
<script type="text/javascript">
	/* $(".buybtn.now").click(function(){
        $(".alertBg,.alertBox").show();
        $(window).on("touchmove",function(e){e.preventDefault();});
    }); */

    $(".alertBg").click(function(){
        $(".alertBg,.alertBox").hide();
        $(window).off("touchmove");
    })
    
    $(".buybtn.now").click(function(){
    	$(".alertBox").fadeOut();
    	$.getMyJSON2(
    	'/core/reservation.doLoginRs.do ',{
    		'sReservation.card':'${card.CARD_NUMBER}',
    		'sReservation.password':'${card.PASSWORD}'
    	},function(data){
    		if (data.returncode == 0){
    			postwith("/core/reservation.submitOnlineTreservation.do",{
    					"sReservation.card" : data.data[0], "sReservation.password" : data.data[1], 
    					"sReservation.pid" : data.data[2], "TK" : data.data[3]
    			});
    		} else {
    			alertMsg({"type" : "3", "content" : data.errmsg, "close" : true});
    		}
    	});
    })
function postwith(to,p){
	 var myForm = document.createElement("form");myForm.method = "post";myForm.action = to;
	for(var k in p) {
		var myInput = document.createElement("input");myInput.setAttribute("name",k);myInput.setAttribute("value",p[k]);myForm.appendChild(myInput);
	}
	 document.body.appendChild(myForm);myForm.submit();
	 document.body.removeChild(myForm);
}  
</script>
</body>
</html>