<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@ include file="/WEB-INF/content/public/head.jsp" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="/css/orderInfo.css">
    <title>订单详情</title>
    <script>
        $(document).ready(function () {
            $(".header_right").click(function(){
                $(this).children().toggleClass("rbgchange");
                $(".linklist").slideToggle(200).css("position","fixed");
            });
        });
        function checkPay(oid,postDate){
        	if(oid !='' && oid!=0){
        		var date = new Date(postDate.replace(/-/g,"\/"));
        		var hours=(new Date().getTime()-date.getTime())/(60 * 60 * 1000);
        		if(hours<72){

	        			window.location.href="/core/order.toPayMent.do?order.oid="+oid;
	        		
        		}else{
        			alertMsg({"content": "该订单已超过72小时，支付失败！","type": 2,"close": true});
        		}
        	}else{
        		window.location.href="/content/error.lossProduct.do";
        		return;
        	}
        }
    </script>
    <style type="text/css">
    .imgbox1 {position: relative;float: left;}
    .imgbox1 .recommend {position: absolute;height: 1.0rem;top: 0;bottom: 0;margin: auto;left: 9%;color: #fff;font-size: 0.3rem;font-weight: bold;}
	.onsale {width: 100%;cursor: pointer;border: #ddd solid;border-width: 1px 0;overflow: hidden;padding: 0.4rem 3.2%;text-align: center;}
	.onsale a {color: #7b7b7c;padding-left: 1.1rem;background: url(../images/onsale.png) no-repeat left center;font-size: 13px;background-size: 17px auto;}
	.zxzf {height: 18px;background: #f36060;border-radius: 3px 0 0 3px;text-align: center;line-height: 18px;color: #fff;display: inline-block;}
	.ljje {color: #716f6f;height: 18px;text-align: center;line-height: 18px;display: inline-block;border-radius: 0 3px 3px 0;background-color: #e3e3e3;}
</style>
</head>
<body style="background-color:#fff;">

<div class="main">
    <div class="orderInfo" style="border-bottom: none;">
    <c:if test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')>=0}">
        <div class="orderTop">订单号：${orderInfo.orderDetail.OID }</div>
	</c:if>	
		<c:forEach items="${orderInfo.product}" var="product">
			<div class="orderCon newBg">
                <c:choose>
	                <c:when test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
	               		 <a href="/core/product.toProductDetail.do?cgVariable.pid=${product.PID}&isCard=1">
	                	<div class="imgbox1">
	                     <img src="/cardImg/${product.CAR_IMG}"  onerror="this.src='/images/default_img.png'">
	                	 </div>
	                </c:when>
	                <c:otherwise>
	                   <a href="/core/product.toProductDetail.do?cgVariable.pid=${product.PID}">
	                	<img src="${ctxImg}/product/${product.CSID}/${product.IMG}"  onerror="this.src='/images/default_img.png'">
	                </c:otherwise>
               	</c:choose>
                <div class="proinfo">
                    <p>${product.PNAME}
						<c:choose>
						<c:when test="${product.SEX == 1}">
						(男)
						</c:when>
						<c:when test="${product.SEX == 2}">
						(已婚女)
						</c:when>
						<c:when test="${product.SEX == 4}">
						(未婚女)
						</c:when>
						</c:choose>
						<%-- <c:if test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
							<c:choose>
							<c:when test="${product.CARD_TYPE == 0}">
							<span>虚拟卡</span>
							</c:when>
							<c:when test="${product.CARD_TYPE == 1}">
							<span>实体卡</span>
							</c:when>
							</c:choose>
						</c:if> --%>
					</p>
                    <p class="sold">${product.BNAME }</p>
                    <p>
                        <span><b>¥${product.SHOP_PRICE }</b><em>¥${product.MARKET_PRICE }</em> </span>
                        <span class="newDec">
                        	<c:if test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
                        		<c:if test="${product.COUNTS>1}">
                        		x${product.COUNTS }
                        		</c:if>
                        	</c:if>
                        </span>
                    </p>
                </div>
            	</a>
        	</div>
        	<div class="interval"></div>
      			<c:forEach items="${product['promotions']}" var="promotion" varStatus="status">
				<div class="onsale" style="background:">
			        <a href="/core/product.toPromotionsDetail.do?pid=${product['PID']}">${promotion['PROMOTION_TITLE']}</a>
			    </div>
			    <div class="interval"></div>
				</c:forEach>
		
    </div>

	
		<c:if test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')>=0 }">
			<c:choose>
			<c:when test="${product.RE_NAME!=null }">
				<div class="peobox">
			        <div class="protip">体检人信息</div>
			        <p><span>体检人：</span><span>${product.RE_NAME }</span></p>
			        <p><span>性别：</span><span>${product.RE_SEX==0?'男':'女'}</span></p>
			        <p><span>体检机构：</span><span>${product.ENAME}</span></p>
			        <p><span>体检时间：</span><span>${product.CREATER_TIME}</span></p>
			        <p><span>手机号：</span><span>${product.RE_TEL }</span></p>
			        <p><span>身份证：</span><span>${product.RE_CID }</span></p>
			    </div>
			</c:when>
			<c:otherwise>
		     	<div class="peobox">
			        <div class="protip">体检人信息</div>
			        <p style="text-align: center;">抱歉，找不到预约信息...</p>
			    </div>
		     </c:otherwise>
			</c:choose>
			<div class="clear"></div>
			<div class="interval1"></div>
     </c:if>
    </c:forEach>

    <div class="peobox">
        <div class="protip">订单信息</div>
        <c:if test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
        <p><span>订单编号：</span><span>${orderInfo.orderDetail.OID}</span></p>
        </c:if>
        <p><span>下单时间：</span><span class="blue">${orderInfo.orderDetail.POST_DATE }</span></p>
 		<p><span>订单联系人：</span><span>${orderInfo.orderDetail.NAME}</span></p>
 		<%-- <p><span>联系手机：</span><span>${orderInfo.orderDetail.MOBILE}</span></p> --%>
   		<c:if test="${orderInfo.orderDetail.ORDER_TYPE==3}">
   		<p><span>收货人：</span><span>${orderInfo.orderDetail.CNAME}</span></p>
   		<p><span>联系手机：</span><span>${orderInfo.orderDetail.CMOBILE}</span></p>
     	
     	<c:if test="${fn:length(orderInfo.orderDetail.ADDRESS)>23}">
     	<p id="addressP" style="height:3rem;"><span>收货地址：</span>
     	<span class="item"  style="max-width: 70%;overflow: hidden;line-height:1rem;">
     	${orderInfo.orderDetail.PROVINCE}${orderInfo.orderDetail.CITY}${orderInfo.orderDetail.COUNTY}${orderInfo.orderDetail.ADDRESS}
     	</span>
     	</p>
     	</c:if>
     	<c:if test="${fn:length(orderInfo.orderDetail.ADDRESS)<=23}">
     	<p id="addressP"><span>收货地址：</span>
     	<span class="item"  style="max-width: 70%;overflow: hidden;line-height:1rem;">
     	${orderInfo.orderDetail.PROVINCE}${orderInfo.orderDetail.CITY}${orderInfo.orderDetail.COUNTY}${orderInfo.orderDetail.ADDRESS}
     	</span>
     	</p>
     	</c:if>
     	
        </c:if>

        
        
        	<c:choose>
              <c:when test="${orderInfo.orderDetail.PAY_STATUS==0 && orderInfo.orderDetail.IS_VALID==0}">
              	<p><span>订单状态：</span><span class="red"> 待付款 </span></p>
              	<p><span>套餐价格：</span><span class="red"> ¥${orderInfo.orderDetail.sumOrderPrice==null?'0':orderInfo.orderDetail.sumOrderPrice} </span></p>
              	<c:choose>
	              	<c:when test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
		              	<p><span>积分抵扣：</span><span class="red">¥${orderInfo.orderDetail.creditLog.opCredit==null?'0':orderInfo.orderDetail.creditLog.opCredit/10}</span></p>
		              	<p><span>优惠券抵扣：</span><span class="red">¥${orderInfo.orderDetail.creditLog.opCoupon==null?'0':orderInfo.orderDetail.creditLog.opCoupon}</span></p>
		            </c:when>
	              	<c:otherwise>
	              		<p><span>立减金额：</span><span class="red">¥${orderInfo.orderDetail.sumCpPrice==null?'0':orderInfo.orderDetail.sumCpPrice}</span></p>
	              	</c:otherwise>
              	</c:choose>
              	<p><span>应付金额：</span><span class="red">¥${orderInfo.orderDetail.SUM_PRICE}</span></p>
              </c:when>
               <c:when test="${orderInfo.orderDetail.PAY_STATUS==2 && orderInfo.orderDetail.IS_VALID==0}">
              	 <p><span>订单状态：</span><span class="red">已付款 </span></p>
              	 <p><span>总商品金额：</span><span class="red"> ¥${orderInfo.orderDetail.sumOrderPrice==null?'0':orderInfo.orderDetail.sumOrderPrice} </span></p>
              	 <c:choose>
	              	<c:when test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
		              	<p><span>积分抵扣：</span><span class="red">¥${orderInfo.orderDetail.creditLog.opCredit==null?'0':orderInfo.orderDetail.creditLog.opCredit/10}</span></p>
		              	<p><span>优惠券抵扣：</span><span class="red">¥${orderInfo.orderDetail.creditLog.opCoupon==null?'0':orderInfo.orderDetail.creditLog.opCoupon}</span></p>
		            </c:when>
	              	<c:otherwise>
	              		<p><span>立减金额：</span><span class="red">¥${orderInfo.orderDetail.sumCpPrice==null?'0':orderInfo.orderDetail.sumCpPrice}</span></p>
	              	</c:otherwise>
              	 </c:choose>
              	 <p><span>结算金额：</span><span class="red">¥${orderInfo.orderDetail.SUM_PRICE==null?'0':orderInfo.orderDetail.SUM_PRICE}</span></p>
              	 <p><span>实付金额：</span><span class="red">¥${orderInfo.orderDetail.ACTUAL_PAY==null?'0':orderInfo.orderDetail.ACTUAL_PAY}</span></p>
              </c:when>
              <c:when test="${orderInfo.orderDetail.IS_VALID==1}">
              	 <p><span>订单状态：</span><span class="red">已失效 </span></p>
              	 <p><span>总商品金额：</span><span class="red"> ¥${orderInfo.orderDetail.sumOrderPrice==null?'0':orderInfo.orderDetail.sumOrderPrice} </span></p>
              	 <c:choose>
	              	<c:when test="${fn:indexOf(orderInfo.orderDetail.OID,'YY')<0}">
		              	<p><span>积分抵扣：</span><span class="red">¥${orderInfo.orderDetail.creditLog.opCredit==null?'0':orderInfo.orderDetail.creditLog.opCredit/10}</span></p>
		              	<p><span>优惠券抵扣：</span><span class="red">¥${orderInfo.orderDetail.creditLog.opCoupon==null?'0':orderInfo.orderDetail.creditLog.opCoupon}</span></p>
		            </c:when>
	              	<c:otherwise>
	              		<p><span>立减金额：</span><span class="red">¥${orderInfo.orderDetail.sumCpPrice==null?'0':orderInfo.orderDetail.sumCpPrice}</span></p>
	              	</c:otherwise>
              	 </c:choose>
              	 <p><span>结算金额：</span><span class="red">¥${orderInfo.orderDetail.SUM_PRICE==null?'0':orderInfo.orderDetail.SUM_PRICE}</span></p>
              	 <p><span>实付金额：</span><span class="red">¥${orderInfo.orderDetail.ACTUAL_PAY==null?'0':orderInfo.orderDetail.ACTUAL_PAY}</span></p>
              </c:when>
            </c:choose>
            
    </div>
    <div class="clear"></div>
    
    <div class="bottomblank" style="background: #ffffff"></div>
    <c:if test="${orderInfo.orderDetail.PAY_STATUS == 0 && orderInfo.orderDetail.IS_VALID==0}">
    <div class="buybox">
         <a class="buybtn" href="javascript:checkPay('${orderInfo.orderDetail.OID}','${orderInfo.orderDetail.POST_DATE }');">立即支付</a>
    </div>
    </c:if>
</div>

</body>
</html>