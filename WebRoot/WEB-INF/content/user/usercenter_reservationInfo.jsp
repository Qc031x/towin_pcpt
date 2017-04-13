<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/public/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="wap-font-scale" content="no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="/css/reservationInfo.css">
    <title>预约单详情</title>
</head>
<body>

<div class="main" style="background-color:#fff;">
    <div class="peobox">
        <div class="protip">预约单信息</div>
        <p>套餐名称：${reservation.P_NAME }
        	<c:choose>
        		<c:when test="${reservation.base.SEX == 1 }">(男)</c:when>
        		<c:when test="${reservation.base.SEX == 2 }">(已婚女)</c:when>
        		<c:when test="${reservation.base.SEX == 4 }">(未婚女)</c:when>
        	</c:choose>
        </p>
        <p>套餐价格：¥${reservation.base.SHOP_PRICE}</p>
        <p>预约单状态：
            		<c:if test="${reservation.STATUS==0}">
            			审核中
            		</c:if>
            		<c:if test="${reservation.STATUS==1}">
            			待体检
            		</c:if>
            		<c:if test="${reservation.STATUS==2}">
            			已驳回
            		</c:if>
            		<c:if test="${reservation.STATUS==3}">
            			已体检
            		</c:if>
        </p>
    </div>
    
    <div class="interval"></div>
    
    <div class="peobox">
        <div class="protip">体检人信息</div>
        <p><span>姓名</span><span>${reservation.RE_NAME}</span></p>
        <p><span>性别</span>
        	<c:choose>
	        	<c:when test="${reservation.RE_SEX == 0 }"><span>男</span></c:when>
	        	<c:when test="${reservation.RE_SEX == 1 }"><span>女</span></c:when>
        	</c:choose> 
        </p>
        <p><span>身份证</span><span>${reservation.RE_CID }</span></p>
        <p><span>年龄</span><span>${reservation.RE_YEAR }</span></p>
        <p>
        	<span>婚否</span>
        	<c:choose>
	        	<c:when test="${reservation.RE_MARRIAGE == 0 }"><span>未婚</span></c:when>
	        	<c:when test="${reservation.RE_MARRIAGE == 1 }"><span>已婚</span></c:when>
        	</c:choose>
        </p>
        <p><span>手机号</span><span class="blue">${reservation.RE_TEL}</span></p>
    </div>

    <div class="interval"></div>

    <div class="peobox">
        <div class="protip">体检分院和时间</div>
        <p><span>城市</span><span>${reservation.TITLE}</span></p>
        <p><span>体检中心</span><span>${reservation.BNAME}</span></p>
        <p><span>分院</span><span>${reservation.ENAME}</span></p>
        <p><span>地址</span><span>${fn:substring(reservation.ADDRESS,0,15)}</span></p>
        <p><span>体检时间</span><span>${reservation.CREATER_TIME }</span></p>
    </div>
    

    <div class="interval"></div>

    <div class="peobox">
        <p>
        	<span>是否享受VIP服务</span>
        	<c:choose>
	        	<c:when test="${reservation.IS_VIP == 1 }"><span>是</span></c:when>
	        	<c:when test="${reservation.IS_VIP == 0 }"><span>否</span></c:when>
        	</c:choose>
        </p>
        <p>
        	<span>电子体检报告</span>
        	<c:choose>
	        	<c:when test="${reservation.DOC_REPORTS == 1 }"><span>有</span></c:when>
	        	<c:when test="${reservation.DOC_REPORTS == 0 }"><span>无</span></c:when>
        	</c:choose>
        </p>
        <p>
        	<span>预约方式</span>
        	<c:choose>
	        	<c:when test="${reservation.R_TYPE == 1 }"><span>体检卡预约</span></c:when>
	        	<c:when test="${reservation.R_TYPE == 2 }"><span>在线支付预约</span></c:when>
	        	<c:when test="${reservation.R_TYPE == 3 }"><span>到店付款预约</span></c:when>
        	</c:choose>
        </p>
        <p><span>提交时间</span><span>${reservation.POST_DATE }</span></p>
    </div>

  <%--   <div class="remark">
        <p>备注</p>
        <textarea>${reservation.RE_DESC}</textarea>
    </div> --%>

    <div class="clear"></div>
    
    <c:if test="${reservation.STATUS==0 || reservation.STATUS==2}">
    	<div class="bottomblank"></div>
    	<div class="buybox">
    		<a href="/core/user.toUpdateOnlinePayReservation.do?rid=${reservation.RID}&pid=${reservation.PID}" class="buybtn">修改</a>
    		<%-- <c:choose>
    			<c:when test="${reservation.R_TYPE == 1 }">体检卡在线支付
		    	    <a href="/core/user.toUpdateOnlinePayReservation.do?rid=${reservation.RID}&pid=${reservation.PID}&pro_sex=${reservation.SEX}" class="buybtn">修改</a>
    			</c:when>
    			<c:when test="${reservation.R_TYPE == 2 }">平台在线支付
		    	    <a href="/core/user.toUpdateOnlinePayReservation.do?rid=${reservation.RID}&pid=${reservation.PID}&pro_sex=${reservation.SEX}" class="buybtn">修改</a>
    			</c:when>
    			<c:when test="${reservation.R_TYPE == 3 }">到店付款
		    	    <a href="/core/user.toUpdateShopReservation.do?rid=${reservation.RID}&pid=${reservation.PID}&pro_sex=${reservation.SEX}" class="buybtn">修改</a>
    			</c:when>
    		</c:choose> --%>
	    </div>
    </c:if>
</div>

</body>
</html>