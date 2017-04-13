<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="/css/myCard.css">
    <%@include file="../public/head.jsp" %>
    <title>我的卡密</title>
    <script>
        $(document).ready(function () {
            var num2 = $(".beforeCard .cardInfo").length;
            $("#beforeCard").html(num2);
            var num3 = $(".afterCard .cardInfo").length;
            $("#afterCard").html(num3);

            $(".myOrder a span em").each(function(){
                if (parseInt($(this).text()) > 0) {
                    $(this).parent("span").show();

                } else {
                    $(this).parent("span").hide();
                }
            });
            
            var status = '${param.s}';
            if(status == 1)
            {
             $(".myOrder a").eq(1).addClass("active").siblings().removeClass("active");
             $(".tablebox>div").css("display", "none");
             $(".tablebox>div").eq(1).css("display", "block");
            }

            $(".myOrder a").click(function(){
                $(this).addClass("active").siblings().removeClass("active");
                $(".tablebox>div").css("display", "none");
                $(".tablebox>div").eq($(this).index()).css("display", "block");
            })
        });
    </script>
</head>
<body>

<div class="main">

    <!-- 切换栏目 -->
    <div class="myOrder">
        <a href="javascript:;" class="active">全部<span></span></a>
        <a href="javascript:;">未使用<span>(<em id="beforeCard">10</em>)</span></a>
        <a href="javascript:;">已使用<span>(<em id="afterCard">10</em>)</span></a>
    </div><div class="clear"></div>

    <div class="tablebox">
    	<%--全部卡密 --%>
        <div class="allCard" style="display: block;">
        	<c:choose>
        	<c:when test="${not empty cardList }">
        	<c:forEach items="${cardList }" var="card">
        
            <div class="cardInfo">
                <a href="/core/user.toCardInfo.do?oid=${card.OID}&card=${card.CARD_NUMBER}&pwd=${card.PASSWORD}" style="color: #fff;">
                <div class="infoBg" style="background: url(/cardImg/${card['cardImg']['CAR_IMG']}) 
                		no-repeat left -2.1rem; background-size: 15.0rem auto;">
                    <span class="infoBgL"></span>
                    <span class="infoBgL infoBgR"></span>
                    
                    <div class="info">
                        <p class="p1">商品编号：${card['proInfo']['PNO']}</p>
                        <h1>${card['proInfo']['PNAME']} 
                        	<c:choose>
                        		<c:when test="${card['proInfo']['SEX'] eq 1 }">(男)</c:when>
                        		<c:when test="${card['proInfo']['SEX'] eq 2 }">(已婚女)</c:when>
                        		<c:when test="${card['proInfo']['SEX'] eq 4 }">(未婚女)</c:when>
                        	</c:choose>
                        </h1>
                        <p class="p2">价格：¥ <em>${card['proInfo']['SHOP_PRICE']}</em></p>
                         <p class="p3">
                        	<span>有效期：${card.LAST_DATE}</span>
                        	<c:choose>
                        		<c:when test="${card.IS_VALID == 0}">
		                        	<span>未使用</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == 1}">
		                        	<span>审核中</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == 2}">
		                        	<span>已完成</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == -1}">
		                        	<span>已失效</span>
                        		</c:when>
                        	</c:choose>
                        </p>
                    </div>
                    
                </div>
                </a>
            </div>
        	</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<div class="protip">您还没有购买卡密<a href="/content/product.cardList.do">随便逛逛</a></div>
        	</c:otherwise>
        	</c:choose>
        </div>


		<%--未使用 --%>
        <div class="beforeCard" style="display: none;">
        	<c:forEach items="${cardList }" var="card">
        	<c:if test="${card.IS_VALID == 0 }">
	            <div class="cardInfo">
	            	 <a href="/core/user.toCardInfo.do?oid=${card.OID}&card=${card.CARD_NUMBER}&pwd=${card.PASSWORD}" style="color: #fff;">
	                <div class="infoBg" style="background: url(/cardImg/${card['cardImg']['CAR_IMG']})
	                		no-repeat left -2.1rem; background-size: 15.0rem auto;">
	                    <span class="infoBgL"></span>
	                    <span class="infoBgL infoBgR"></span>
	                    
	                   <div class="info">
                        <p class="p1">商品编号：${card['proInfo']['PNO']}</p>
                        <h1>${card['proInfo']['PNAME']} 
                        	<c:choose>
                        		<c:when test="${card['proInfo']['SEX'] eq 1 }">(男)</c:when>
                        		<c:when test="${card['proInfo']['SEX'] eq 2 }">(已婚女)</c:when>
                        		<c:when test="${card['proInfo']['SEX'] eq 4 }">(未婚女)</c:when>
                        	</c:choose>
                        </h1>
                        <p class="p2">价格：¥ <em>${card['proInfo']['SHOP_PRICE']}</em></p>
                         <p class="p3">
                        	<span>有效期：${card.LAST_DATE}</span>
                        	<c:choose>
                        		<c:when test="${card.IS_VALID == 0}">
		                        	<span>未使用</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == 1}">
		                        	<span>审核中</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == 2}">
		                        	<span>已完成</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == -1}">
		                        	<span>已失效</span>
                        		</c:when>
                        	</c:choose>
                        </p>
                    </div>
	                    
	                </div>
	                </a>
	            </div>
        	</c:if>
        	</c:forEach>
        </div>

		<%--已使用 --%>
        <div class="afterCard" style="display: none;">
        	<c:forEach items="${cardList }" var="card">
        	<c:if test="${card.IS_VALID == 1 || card.IS_VALID == 2 }">
              <div class="cardInfo">
              		 <a href="/core/user.toCardInfo.do?oid=${card.OID}&card=${card.CARD_NUMBER}&pwd=${card.PASSWORD}" style="color: #fff;">
	                <div class="infoBg" style="background: url(/cardImg/${card['cardImg']['CAR_IMG']})
	                			no-repeat left -2.1rem; background-size: 15.0rem auto;">
	                    <span class="infoBgL"></span>
	                    <span class="infoBgL infoBgR"></span>
	                    
	                    <div class="info">
                        <p class="p1">商品编号：${card['proInfo']['PNO']}</p>
                        <h1>${card['proInfo']['PNAME']} 
                        	<c:choose>
                        		<c:when test="${card['proInfo']['SEX'] eq 1 }">(男)</c:when>
                        		<c:when test="${card['proInfo']['SEX'] eq 2 }">(已婚女)</c:when>
                        		<c:when test="${card['proInfo']['SEX'] eq 4 }">(未婚女)</c:when>
                        	</c:choose>
                        </h1>
                        <p class="p2">价格：¥ <em>${card['proInfo']['SHOP_PRICE']}</em></p>
                         <p class="p3">
                        	<span>有效期：${card.LAST_DATE}</span>
                        	<c:choose>
                        		<c:when test="${card.IS_VALID == 0}">
		                        	<span>未使用</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == 1}">
		                        	<span>审核中</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == 2}">
		                        	<span>已完成</span>
                        		</c:when>
                        		<c:when test="${card.IS_VALID == -1}">
		                        	<span>已失效</span>
                        		</c:when>
                        	</c:choose>
                        </p>
                    </div>
	                </div>
	                </a>
	            </div>
        	</c:if>
        	</c:forEach>
        </div>
    </div>
</div>
</body>
</html>