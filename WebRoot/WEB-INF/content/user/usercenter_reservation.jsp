<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="/css/myReserve.css">
    <%@include file="../public/head.jsp" %>
    <title>我的预约</title>
    <script>
        $(document).ready(function () {
            var num1 = $(".ingReserve .orderInfo").length;
            $("#ingReserve").html(num1);
            var num2 = $(".overReserve .orderInfo").length;
            $("#overReserve").html(num2);
            var num3 = $(".failReserve .orderInfo").length;
            $("#failReserve").html(num3);

            $(".myReserve a span em").each(function(){
                if (parseInt($(this).text()) > 0) {
                    $(this).parent("span").show();

                } else {
                    $(this).parent("span").hide();
                }
            });
           
           	var status = '${param.s}';
            if(status == 1)
            {
             $(".myReserve a").eq(1).addClass("active").siblings().removeClass("active");
             $(".tablebox>div").css("display", "none");
             $(".tablebox>div").eq(1).css("display", "block");
            }
    
            $(".myReserve a").click(function(){
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
    <div class="myReserve">
        <a href="javascript:;" class="active">全部</a>
        <a href="javascript:;">审核中<span>(<em id="ingReserve">10</em>)</span></a>
        <a href="javascript:;">已完成<span>(<em id="overReserve">10</em>)</span></a>
        <a href="javascript:;">已驳回<span>(<em id="failReserve">10</em>)</span></a>
    </div>
    
    <div class="clear"></div>

    <!-- 订单信息 -->
    <div class="tablebox">

        <div class="allReserve" style="display: block;">
        	<c:choose>
        	<c:when test="${not empty reservationList }">
        	<c:forEach items="${reservationList }" var="re">
	            <div class="orderInfo">
	                <div class="orderTop">
	                    <span>
		                    <c:if test="${fn:indexOf(re.CARD,'--/--')<0}">
	                       		体检卡号：${re.CARD}
	                       	</c:if> 	
	                    </span>
	                    <span>姓名：${re.RE_NAME}</span>
	                </div>
	                <div class="orderCon">
			          <a href="/core/user.toReservationInfo.do?rid=${re.RID}&rtype=${re.R_TYPE}">
	                        <p class="tipInfo">
	                        	<span style="max-width:66%;">
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)>=0}">
	                        	${re.ENAME}
	                        	</c:if> 
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)<0}">
	                        	${re.BNAME}${re.ENAME}
	                        	</c:if> 	
	                        	</span>
	                        	<c:choose>
	                        		<c:when test="${re.R_TYPE eq 1 }">
		                        		<span>体检卡预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 2 }">
		                        		<span>在线支付预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 3 }">
		                        		<span>到店付款预约</span>
	                        		</c:when>
	                        	</c:choose>
	                        </p>
	                        <div class="clear"></div>
	                          <c:choose>
	                        	<c:when test="${re.base.PRODUCT_TYPE == 0 }">
		                        	<img src="/cardImg/${re.base.CAR_IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:when>
	                        	<c:otherwise>
		                        	<img src="${ctxImg}/product/${re.base.CSID}/${re.base.IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:otherwise>
		                    </c:choose>
	                        <div class="proinfo">
	                            <p>${re.P_NAME}
	                            	<c:choose>
	                            		<c:when test="${re.base.SEX eq 1 }">(男)</c:when>
	                            		<c:when test="${re.base.SEX eq 2 }">(已婚女)</c:when>
	                            		<c:when test="${re.base.SEX eq 4 }">(未婚女)</c:when>
	                            	</c:choose>
	                            </p>
	                            <p>
	                            	<span>体检时间：${re.CREATER_TIME}</span>
		                                		<c:if test="${re.STATUS==0}">
		                                			<c:choose>
						                        	<c:when test="${re.R_TYPE eq 2 && re.PAY_STATUS eq 0}">
							                        	<span>待付款</span>
						                        	</c:when>
						                        	<c:otherwise>
							                        	<span>审核中</span>
						                        	</c:otherwise>
							                   		</c:choose>
		                                		</c:if>
		                                		<c:if test="${re.STATUS==1}">
		                                			<span>待体检</span>
		                                		</c:if>
		                                		<c:if test="${re.STATUS==2}">
		                                			<span>已驳回</span>
		                                		</c:if>
		                                		<c:if test="${re.STATUS==3}">
							            			<span>已体检</span>
							            		</c:if>
	                            </p>
	                        </div>
	                    </a>
	                </div>
	            </div>
        	</c:forEach>
        	</c:when>
        	<c:otherwise>
        		<div class="protip">您还没有预约信息<a href="/core/product.toProductListPage.do">去预约套餐</a></div>
        	</c:otherwise>
        	</c:choose>
        </div>

        <div class="ingReserve" style="display: none;">
            <c:forEach items="${reservationList }" var="re">
			<c:if test="${re.STATUS==0}">
	            <div class="orderInfo">
	                <div class="orderTop">
	                    <span>
		                    <c:if test="${fn:indexOf(re.CARD,'--/--')<0}">
	                       		体检卡号：${re.CARD}
	                       	</c:if> 	
	                    </span>
	                    <span>姓名：${re.RE_NAME}</span>
	                </div>
	                <div class="orderCon">
	                    <a href="/core/user.toReservationInfo.do?rid=${re.RID}&rtype=${re.R_TYPE}">
	                        <p class="tipInfo">
	                        	<span style="max-width:66%;">
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)>=0}">
	                        	${re.ENAME}
	                        	</c:if> 
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)<0}">
	                        	${re.BNAME}${re.ENAME}
	                        	</c:if> 	
	                        	</span>
	                        	<c:choose>
	                        		<c:when test="${re.R_TYPE eq 1 }">
		                        		<span>体检卡预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 2 }">
		                        		<span>在线支付预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 3 }">
		                        		<span>到店付款预约</span>
	                        		</c:when>
	                        	</c:choose>
	                        </p>
	                        <div class="clear"></div>
	                          <c:choose>
	                        	<c:when test="${re.base.PRODUCT_TYPE == 0 }">
		                        	<img src="/cardImg/${re.base.CAR_IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:when>
	                        	<c:otherwise>
		                        	<img src="${ctxImg}/product/${re.base.CSID}/${re.base.IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:otherwise>
		                    </c:choose>
	                        <div class="proinfo">
	                            <p>${re.P_NAME }
	                            	<c:choose>
	                            		<c:when test="${re.base.SEX eq 1 }">(男)</c:when>
	                            		<c:when test="${re.base.SEX eq 2 }">(已婚女)</c:when>
	                            		<c:when test="${re.base.SEX eq 4 }">(未婚女)</c:when>
	                            	</c:choose>
	                            </p>
	                            <p>
	                            	<span>体检时间：${re.CREATER_TIME }</span>
	                            	<c:choose>
		                        	<c:when test="${re.R_TYPE eq 2 && re.PAY_STATUS eq 0}">
			                        	<span>待付款</span>
		                        	</c:when>
		                        	<c:otherwise>
			                        	<span>审核中</span>
		                        	</c:otherwise>
			                   		</c:choose>
	                            </p>
	                        </div>
	                    </a>
	                </div>
	            </div>
			</c:if>        	
        	</c:forEach>
        </div>

        <div class="overReserve" style="display: none;">
        	<c:forEach items="${reservationList }" var="re">
			<c:if test="${re.STATUS==1 || re.STATUS==3}">
	            <div class="orderInfo">
	                <div class="orderTop">
	                    <span>
		                    <c:if test="${fn:indexOf(re.CARD,'--/--')<0}">
	                       		体检卡号：${re.CARD}
	                       	</c:if> 	
	                    </span>
	                    <span>姓名：${re.RE_NAME}</span>
	                </div>
	                <div class="orderCon"> 
	                     <a href="/core/user.toReservationInfo.do?rid=${re.RID}&rtype=${re.R_TYPE}">
	                        <p class="tipInfo">
	                        	<span style="max-width:66%;">
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)>=0}">
	                        	${re.ENAME}
	                        	</c:if> 
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)<0}">
	                        	${re.BNAME}${re.ENAME}
	                        	</c:if> 	
	                        	</span>
	                        	<c:choose>
	                        		<c:when test="${re.R_TYPE eq 1 }">
		                        		<span>体检卡预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 2 }">
		                        		<span>在线支付预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 3 }">
		                        		<span>到店付款预约</span>
	                        		</c:when>
	                        	</c:choose>
	                        </p>
	                        <div class="clear"></div>
	                        <c:choose>
	                        	<c:when test="${re.base.PRODUCT_TYPE == 0 }">
		                        	<img src="/cardImg/${re.base.CAR_IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:when>
	                        	<c:otherwise>
		                        	<img src="${ctxImg}/product/${re.base.CSID}/${re.base.IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:otherwise>
		                    </c:choose>
	                        <div class="proinfo">
	                            <p>${re.P_NAME }
	                            	<c:choose>
	                            		<c:when test="${re.base.SEX eq 1 }">(男)</c:when>
	                            		<c:when test="${re.base.SEX eq 2 }">(已婚女)</c:when>
	                            		<c:when test="${re.base.SEX eq 4 }">(未婚女)</c:when>
	                            	</c:choose>
	                            </p>
	                            <p>
	                            	<span>体检时间：${re.CREATER_TIME }</span>
                               		<c:if test="${re.STATUS==1}">
                               			<span>待体检</span>
                               		</c:if>		     
                               		<c:if test="${re.STATUS==3}">
				            			<span>已体检</span>
				        			</c:if>
	                            </p>
	                        </div>
	                    </a>
	                </div>
	            </div>
			</c:if>        	
        	</c:forEach>
        </div>

        <div class="failReserve" style="display: none;">
           <c:forEach items="${reservationList }" var="re">
			<c:if test="${re.STATUS==2}">
	            <div class="orderInfo">
	                <div class="orderTop">
	                    <span>
		                    <c:if test="${fn:indexOf(re.CARD,'--/--')<0}">
	                       		体检卡号：${re.CARD}
	                       	</c:if> 	
	                    </span>
	                    <span>体验人：${re.RE_NAME }</span>
	                </div>
	                <div class="orderCon">
	                     <a href="/core/user.toReservationInfo.do?rid=${re.RID}&rtype=${re.R_TYPE}">
	                        <p class="tipInfo">
	                        	<span>
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)>=0}">
	                        	${re.ENAME}
	                        	</c:if> 
	                        	<c:if test="${fn:indexOf(re.ENAME,re.BNAME)<0}">
	                        	${re.BNAME}${re.ENAME}
	                        	</c:if> 	
	                        	</span>
	                        	<c:choose>
	                        		<c:when test="${re.R_TYPE eq 1 }">
		                        		<span>体检卡预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 2 }">
		                        		<span>在线支付预约</span>
	                        		</c:when>
	                        		<c:when test="${re.R_TYPE eq 3 }">
		                        		<span>到店付款预约</span>
	                        		</c:when>
	                        	</c:choose>
	                        </p>
	                        <div class="clear"></div>
	                          <c:choose>
	                        	<c:when test="${re.base.PRODUCT_TYPE == 0 }">
		                        	<img src="/cardImg/${re.base.CAR_IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:when>
	                        	<c:otherwise>
		                        	<img src="${ctxImg}/product/${re.base.CSID}/${re.base.IMG}" onerror="this.src='/images/default_img.png'">
	                        	</c:otherwise>
		                    </c:choose>
	                        <div class="proinfo">
	                            <p>${re.P_NAME }
	                            	<c:choose>
	                            		<c:when test="${re.base.SEX eq 1 }">(男)</c:when>
	                            		<c:when test="${re.base.SEX eq 2 }">(已婚女)</c:when>
	                            		<c:when test="${re.base.SEX eq 4 }">(未婚女)</c:when>
	                            	</c:choose>
	                            </p>
	                            <p>
	                            	<span>体检时间：${re.CREATER_TIME }</span>
	                            	<span>已驳回</span>
	                            </p>
	                        </div>
	                    </a>
	                </div>
	            </div>
			</c:if>        	
        	</c:forEach>
        </div>
    </div>
</div>

</body>
</html>