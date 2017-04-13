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
    <link rel="stylesheet" type="text/css" href="/css/myOrder.css">
    <%@include file="../public/head.jsp" %>
    <title>我的订单</title>
    <script>
        $(document).ready(function () {
            $(".header_right").click(function(){
                $(this).children().toggleClass("rbgchange");
                $(".linklist").slideToggle(200).css("position","fixed");
            });

            var num1 = $(".afterPay .orderInfo").length;
            $("#afterPay").html(num1);
            var num2 = $(".beforePay .orderInfo").length;
            $("#beforePay").html(num2);
            var num3 = $(".failPay .orderInfo").length;
            $("#failPay").html(num3);

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
        
        function changeIsValid(oid){
        	var url = "${ctx}/core/user.changeOrderIsValid.do";
        	var data= {"oid":oid};
        	$.getMyJSON(url,data,function(rs){
        		if(rs.returncode=='0'){
        			$("#"+oid).slideUp();
        			setTimeout(function(){window.location.href="/core/user.findUserOrder.do?s=1"},600);
        		}
        	});
        }
        function checkPay(oid,postDate){
        	if(oid !='' && oid!=0){
        		var date = new Date(postDate.replace(/-/g,"\/"));
        		var hours=(new Date().getTime()-date.getTime())/(60 * 60 * 1000);
        		//alert(postDate.replace(/-/g,"\/"));
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
</head>
<body>

<div class="main">
    
    
    <!-- 切换栏目 -->
    <div class="myOrder">
        <a href="javascript:;" class="active">已付款<span>(<em id="afterPay">0</em>)</span></a>
        <a href="javascript:;">未付款<span>(<em id="beforePay">10</em>)</span></a>
        <a href="javascript:;">已失效<span>(<em id="failPay">10</em>)</span></a>
    </div>
    
    <div class="clear"></div>

    <!-- 订单信息 -->
    <div class="tablebox">
        <div class="afterPay" style="display: block;">
            <!-- 体检套餐订单 -->
            <c:forEach items="${orderList}" var="order">
        	<%--已付款订单--%>
        	<c:if test="${order.PAY_STATUS==2 && order.IS_VALID==0}">
        	<div class="orderInfo">
                <div class="orderTop">
                    <span>订单号：${order.OID }</span>
                    <span>联系人：${order.NAME }</span>
                </div>
				
				<c:if test="${empty order.product}">
							<div class="orderCon"  style="line-height: 3.425rem;text-align: center;">商品已下架或被转移</div>
							<div class="orderBot">
		                    	<span class="allPay">实付¥<em>${order.SUM_PRICE==null?'0':order.SUM_PRICE}</em></span>
                    			<span class="status">已付款</span>
		                	</div>
				</c:if>
				<c:if test="${!empty order.product}">
				<c:forEach items="${order.product}" var="product" varStatus="index">
						<c:choose>
							<c:when test="${empty product.PNAME}">
									<div class="orderCon"  style="line-height: 3.425rem;text-align: center;">商品已下架或被转移</div>
									<c:if test="${index.last}">
									<div class="orderBot">
										<span class="allPay">实付¥<em>${order.SUM_PRICE==null?'0':order.SUM_PRICE}</em></span>
                    					<span class="status">已付款</span>
									</div>
									</c:if>
							</c:when>
							<c:otherwise>
								<div class="orderCon">
				                    <a href="/core/order.toOrderDetail.do?oid=${order.OID}">
										<c:choose>
				                        <c:when test="${fn:indexOf(order.OID,'YY')<0}">
				                        	<div class="imgbox1">
					                            <img src="/cardImg/${product.CAR_IMG}" onerror="this.src='/images/default_img.png'">
					                        </div>
				                        </c:when>
				                        <c:otherwise>
				                        	<img src="${ctxImg}/product/${product.CSID}/${product.IMG}" onerror="this.src='/images/default_img.png'">
				                        </c:otherwise>
		                        		</c:choose>
				                        <div class="proinfo">
				                            <p>
											${product.PNAME}
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
											</p>
				                            <p class="sold">${product.BNAME }</p>
				                            <p>
				                                <span><b>¥${product.SHOP_PRICE }</b><em>¥${product.MARKET_PRICE }</em></span>
				                                <span class="newDec">
				                                	<c:if test="${product.COUNTS>1}">x${product.COUNTS}</c:if>	
				                                </span>
				                            </p>
				                        </div>
				                    </a>
				                </div>
								<c:if test="${index.last}">
									<div class="orderBot">
										<span class="allPay">实付¥<em>${order.SUM_PRICE==null?'0':order.SUM_PRICE}</em></span>
                    					<span class="status">已付款</span>
									</div>
								</c:if>
							</c:otherwise>
						</c:choose>
				</c:forEach>			
				</c:if>
            </div>
        	</c:if>
        </c:forEach>
        </div>

        <div class="beforePay" style="display: none;">
            <c:forEach items="${orderList}" var="order">
        	<%--未支付订单--%>
        	<c:if test="${order.PAY_STATUS==0 && order.IS_VALID==0}">
        	<div class="orderInfo" id="${order.OID}">
                <div class="orderTop">
                    <span>订单号：${order.OID }</span>
                    <span>联系人：${order.NAME }</span>
                </div>
				
				<c:if test="${empty order.product}">
							<div class="orderCon"  style="line-height: 3.425rem;text-align: center;">商品已下架或被转移</div>
							<div class="orderBot">
								<span class="allPay">应付¥<em>${order.SUM_PRICE}</em></span>
			                   	<a class="firsra" href="javascript:checkPay(0,'');">去支付</a>
	                    		<a href="javascript:changeIsValid('${order.OID}');">取消订单</a>
		                	</div>
				</c:if>
				<c:if test="${!empty order.product}">
				<c:forEach items="${order.product}" var="product" varStatus="index">
					<c:choose>
						<c:when test="${empty product.PNAME}">
							<div class="orderCon"  style="line-height: 3.425rem;text-align: center;">商品已下架或被转移</div>
							<c:if test="${index.last}">
			                	<div class="orderBot">
			                	<span class="allPay">应付¥<em>${order.SUM_PRICE}</em></span>
			                	<a class="firsra" href="javascript:checkPay(0,'');">去支付</a>
	                    		<a href="javascript:changeIsValid('${order.OID}');">取消订单</a>
	                    		</div>
	           	 			</c:if>
						</c:when>
						<c:otherwise>
							<div class="orderCon">
			                    <a href="/core/order.toOrderDetail.do?oid=${order.OID}">
			                        	<c:choose>
				                        <c:when test="${fn:indexOf(order.OID,'YY')<0}">
				                        	<div class="imgbox1">
					                           <img src="/cardImg/${product.CAR_IMG}" onerror="this.src='/images/default_img.png'">
					                        </div>
				                        </c:when>
				                        <c:otherwise>
				                        	<img src="${ctxImg}/product/${product.CSID}/${product.IMG}" onerror="this.src='/images/default_img.png'">
				                        </c:otherwise>
			                        	</c:choose>
			                        <div class="proinfo">
			                            <p>
										${product.PNAME}
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
										</p>
			                            <p class="sold">${product.BNAME }</p>
			                            <p>
			                                <span><b>¥${product.SHOP_PRICE }</b><em>¥${product.MARKET_PRICE }</em></span>
			                                <span class="newDec">
				                                <c:if test="${product.COUNTS>1}">x${product.COUNTS}</c:if>
			                                </span>
			                            </p>
			                        </div>
			                    </a>
			                </div>
							<c:if test="${index.last}">
			                <div class="orderBot">
			                    <span class="allPay">应付¥<em>${order.SUM_PRICE}</em></span>
			                    <a class="firsra" href="javascript:checkPay('${order.OID}','${order.POST_DATE }');">去支付</a>
	                    		<a href="javascript:changeIsValid('${order.OID}');">取消订单</a>
			                </div>
	           	 			</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>			
				</c:if>
            </div>
        	</c:if>
        </c:forEach>
        </div>

        <div class="failPay" style="display: none;">
        	<c:forEach items="${orderList}" var="order">
        	<%--失效订单--%>
        	<c:if test="${order.IS_VALID==1}">
        	<div class="orderInfo">
                <div class="orderTop">
                    <span>订单号：${order.OID }</span>
                    <span>联系人：${order.NAME }</span>
                </div>
				
				<c:if test="${empty order.product}">
							<div class="orderCon" style="line-height: 3.425rem;text-align: center;">商品已下架或被转移</div>
							<div class="orderBot">
		                    	<span class="allPay">应付¥<em>${order.SUM_PRICE==null?order.SUM_PRICE:order.SUM_PRICE}</em></span>
                    			<span class="status">已失效</span>
		                	</div>
				</c:if>
				<c:if test="${!empty order.product}">
				<c:forEach items="${order.product}" var="product" varStatus="index">
					<c:choose>
						<c:when test="${empty product.PNAME}">
							<div class="orderCon" style="line-height: 3.425rem;text-align: center;">商品已下架或被转移</div>
		                    <c:if test="${index.last}">
		                    	<div class="orderBot">
		                    		<span class="allPay">应付¥<em>${order.SUM_PRICE==null?order.SUM_PRICE:order.SUM_PRICE}</em></span>
                    				<span class="status">已失效</span>
		                    	</div>
		                    </c:if>
						</c:when>
						<c:otherwise>
							<div class="orderCon">
			                    <a href="/core/order.toOrderDetail.do?oid=${order.OID}">
			                        <c:choose>
				                        <c:when test="${fn:indexOf(order.OID,'YY')<0}">
				                        	<div class="imgbox1">
					                            <img src="/cardImg/${product.CAR_IMG}" onerror="this.src='/images/default_img.png'">
					                        </div>
				                        </c:when>
				                        <c:otherwise>
				                        	<img src="${ctxImg}/product/${product.CSID}/${product.IMG}" onerror="this.src='/images/default_img.png'">
				                        </c:otherwise>
			                        </c:choose>
			                        <div class="proinfo">
			                            <p>
										${product.PNAME}
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
										</p>
			                            <p class="sold">${product.BNAME}</p>
			                            <p>
			                                <span><b>¥${product.SHOP_PRICE}</b><em>¥${product.MARKET_PRICE }</em></span>
			                                <span class="newDec">
				                                <c:if test="${product.COUNTS>1}">x${product.COUNTS}</c:if>
			                                </span>
			                            </p>
			                        </div>
			                    </a>
			                </div>
							<c:if test="${index.last}">
								<div class="orderBot">
									<span class="allPay">应付¥<em>${order.SUM_PRICE==null?order.SUM_PRICE:order.SUM_PRICE}</em></span>
                    				<span class="status">已失效</span>
								</div>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>			
				</c:if>
            </div>
        	</c:if>
        </c:forEach>
            
        </div>
    </div>
    
</div>
</body>
</html>