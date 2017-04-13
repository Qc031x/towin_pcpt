<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <%@include file="../public/head.jsp" %>
    <link rel="stylesheet" href="/css/tjkForm.css"/>
    <link rel="stylesheet" href="/css/sgfmform.css"/>
    <script type="text/javascript" src="/js/jquery.sgfmform.js"></script>
    <title>体检卡支付</title>
</head>
<body style="background: #eeeff3;">
<div class="main">
<form id="subCardForm">
    <div class="topBox">
        <ul>
            <li class="li1 fontCol">确认订单</li>
            <li class="li2"></li>
            <li class="li1">订单支付</li>
            <li class="li2 col"></li>
            <li class="li1">完成</li>
        </ul>
    </div>
    <div class="clear"></div>

	<c:if test="${map['cardType'] eq 1}">
		<div class="address address2 peradd">
			<c:if test="${empty map['address']}">
				<input type="hidden" value="" datatype="*" nullmsg="请新增并选择收件地址" name="caid" id="caid"/>
				<p style="width: 9.5rem;">请新增收件地址</p>
			</c:if>
			<c:if test="${!empty map['address']}">
				<input type="hidden" value="${map['address']['0']['CAID']}" datatype="*" nullmsg="请新增并选择收件地址" name="caid" id="caid" />
				<p>收货地址</p>
	        	<p><span>${map['address']['0']['NAME']}</span>，<span>${map['address']['0']['EPROVINCE']}${map['address']['0']['ECITY']}${map['address']['0']['ECOUNTY']}${map['address']['0']['ADDRESS']}</span></p>
			</c:if>
	    </div>
	</c:if>
    <div class="clear"></div>
    
    <a href="/core/product.toProductDetail.do?cgVariable.pid=${map['product']['PID']}&isCard=1">
	    <div class="buyinfo">
	        <div class="imgbox1">
	        	<img src="/cardImg/${map['cardImg']['CAR_IMG']}" onerror="this.src='/images/default_img.png'">
	            <!-- <img src="${ctxImg}/card/${map['cardImg']['BACKGROUND_FOLDER']}/${map['cardImg']['BACKGROUND_IMG']}" alt=""> -->
	            <div class="recommend">
	                <p class="rectitle">${map['cardImg']['MAIN_TITLE']}</p>
	                <p>${map['cardImg']['SUB_TITLE1']}</p>
	                <p>${map['cardImg']['SUB_TITLE2']}</p>
	            </div>
	        </div>
	        <div class="info">
	        	<h1>${map['product']['BNAME']}</h1>
	            <c:choose>
					<c:when test="${map['product']['SEX'] eq 1}">
						<p><span>${map['product']['PNAME']}(男)</span><span>X ${map['quantity']}</span></p>
					</c:when>
					<c:when test="${map['product']['SEX'] eq 2}">
						<p><span>${map['product']['PNAME']}(已婚女)</span><span>X ${map['quantity']}</span></p>
					</c:when>
					<c:otherwise>
						<p><span>${map['product']['PNAME']}(未婚女)</span><span>X ${map['quantity']}</span></p>
					</c:otherwise>
				</c:choose>
	            <div class="clear"></div>
	            <p><b>￥${map['product']['SHOP_PRICE']}</b></p>
	        </div>
	    </div>
    </a>

    <div class="clear"></div>

 
	<%-- 
    <div class="order_dikou">
        <div class="list">
            <p class="tit">使用积分</p>
            <c:if test="${!empty map['memberCard']['currentCredit']}" >
	            <div class="dikoucon syjifenjine">
	            	<c:if test="${map['memberCard']['currentCredit'] <= 0}" >
						<p class="nohas"><span>您当前没有可使用的积分。</span></p>
	                </c:if>
	                <c:if test="${map['memberCard']['currentCredit'] > 0}" >
	                	<div class="us_yue">
		                    <p style="float: left;">积分余额:<em class="zonge">${map['memberCard']['currentCredit']}</em> 分 ,下单可使用 <em class="maxus"></em> 分</p>
		                    <p style="float: left;">
		                        <i class="usyhq"><span>使用</span></i>
		                        <input class="shurujine" type="hidden" value="${map['memberCard']['currentCredit']}" readonly="readonly" />
		                        <!-- <a class="shiyongyhq" href="javascript:void(0);">使用</a> -->
		                    </p>
		                </div>
	                </c:if>
	                <p><a class="shiyongtiaojian" href="javascript:void(0);">积分使用规则及获取方式>></a></p>
	                <div class="guizhecon"><div>
	                    <p class="guizedetail">用户在殷康网(1k360.com)注册会员、购买商品、对商品或服务评价都可获得相应的积分，具体如下：</p>
                    	<p>1、新注册用户即可获得20积分；</p>
                    	<p>2、购买体检套餐可根据消费获得相对应积分，积分消费比例为20：1。例：消费100元可获得5积分，以此类推；</p>	             
                    	<p class="guizedetail pt10">使用说明：</p>
                    	<p>1、积分与现金兑换比例为10:1，积分只能用于在线支付时抵扣支付金额，不能兑现现金，积分使用额度不设限制，账户失效或注销时该账户下的积分视为自动放弃。</p>
                    	<p>2、订单积分抵扣金额不开具发票，订单退款时抵扣金额不予退还，使用的积分也不再返还。详情请拨打客服电话400-855-7000咨询。</p>
	                </div></div>
	            </div>
            </c:if>
            <c:if test="${empty map['memberCard']['currentCredit']}" >
             	<div class="dikoucon syjifenjine">
             		<p class="nohas"><span>您当前没有可使用的积分。</span></p>
             	</div>
             </c:if>
        </div>
        <div class="list">
            <p class="tit">使用优惠券</p>
            <c:if test="${!empty map['memberCard']['balance'] && map['memberCard']['balance'] > 0}" >
	            <div class="dikoucon syyhqjine">
	                <div class="us_yue">
	                    <p style="float: left;">优惠券余额:<em class="zonge" id="totalCoin2">${map['memberCard']['balance']}</em> 元 ,下单可抵扣 <em class="maxus">0</em> 元</p>
	                    <p style="float: left;">
	                        <i class="usyhq"><span>使用</span></i>
	                        <input class="shurujine" type="hidden" value="${map['memberCard']['balance']}" readonly="readonly" />
	                        <!-- <a class="shiyongyhq" href="javascript:void(0);">使用</a> -->
	                    </p>
	                    <div class="clear"></div>
	                    <div><b><a class="new_add_yhq" href="javascript:void(0);">新增/激活优惠券</a></b></div>
	                    <div class="add_yhq">
	                        <p><span>优惠券号码:</span><input id="couponCard" class="yhqshuruk" type="text" /></p>
	                        <p><span>密码:</span><input id="couponPwd" class="yhqshuruk2" type="text" /><a class="add_yhqan" onclick="checkCoupon(1)" href="javascript:void(0);">激活</a></p>
	                    </div>
	                </div>
	                <p><a class="shiyongtiaojian" href="javascript:void(0);">优惠券使用规则及获取方式>></a></p>
	                <div class="guizhecon"><div>
	                    <p class="guizedetail pt10">优惠券获取规则及使用说明：</p>
                    	<p>1、优惠券指殷康网（1k360.com）平台发放的在该平台购买商品在线支付时使用的优惠凭证，优惠券共有电子券和纸质券两种，殷康网会在不同情况下对注册会员发放优惠券。会员可在购买商品提交订单时或个人中心激活优惠券，优惠券激活后优惠券面值金额将会保存到会员个人账户余额中。</p>
                    	<p>2、优惠券使用时会根据不同体检套餐设置使用比例，最高不超过10%。订单优惠券抵扣金额不开具发票，订单退款时使用的优惠券金额不予退还。详情请拨打客服电话400-855-7000咨询。</p>
	                </div></div>
	            </div>
            </c:if>
            <c:if test="${empty map['memberCard']['balance'] || map['memberCard']['balance'] <= 0}" >
             	<div class="dikoucon syyhqjine">
             		<div class="us_yue">
	             		<p class="nohas"><span>您还没有可使用的优惠券。</span></p>
	             		<div class="clear"></div>
	                    <div><b><a class="new_add_yhq" href="javascript:void(0);">新增/激活优惠券</a></b></div>
	                    <div class="add_yhq">
	                        <p><span>优惠券号码:</span><input id="couponCard" class="yhqshuruk" type="text" /></p>
	                        <p><span>密码:</span><input id="couponPwd" class="yhqshuruk2" type="text" /><a class="add_yhqan" onclick="checkCoupon(2)" href="javascript:void(0);">激活</a></p>
	                    </div>
                    </div>
             	</div>
             </c:if>
        </div>
    </div>
    <div class="clear"></div>
	 --%>


    
    <div class="slidebox" style="background: #fff;">
        <div class="allpay">
            <span class="fl">订单总额</span>
            <span class="fr">￥<em id="total">${map['product']['SHOP_PRICE'] * map['quantity']}</em></span>
        </div>
        <div class="allpay">
            <!-- <p><span class="fl">积分抵扣</span><span class="fr">- ￥<em id="integral">0</em></span></p>
            <p><span class="fl">优惠券抵扣</span><span class="fr">- ￥<em id="coupon">0</em></span></p> -->
            <input type="hidden" id="ratios" value="${map['ratios']}" />
            <input type="hidden" value="0" id="sumPrice" name="sumPrice" />
            <input type="hidden" value="0" id="itg" name="itg"/>
            <input type="hidden" value="0" id="cp" name="cp"/>
            <input type="hidden" class="kyhjezs" value="0" />
            <input type="hidden" name="pid" value="${map['product']['PID']}" />
            <input type="hidden" name="plat_token" value="${token}" />
        </div>
    </div>

    <div class="bottomblank3"></div>

    <div class="allpaybox">
        
        <a class="fixpay" href="javascript:;">
            在线实付:<em>￥<em id="allprice">0.00</em></em>
        </a>
        <a class="btn" href="javascript:;" onclick="subCardForm()">提交订单</a>
    </div>
</form>
    <div class="addressBox">
        <div class="address address2">
            <p>收货地址</p>
            <p class="active1"></p>
        </div>

        <div class="myAddress">
        	<div class="myAddress1">
	        	<c:forEach items="${map['address']}" var="list" varStatus="index">
	        		<c:choose>
	        			<c:when test="${list['IS_DEFAULT'] eq 1}">
	        				<div class="myAddressBox active3" data="${list['CAID']}">
				                <span class="edit" data-source="${index.index}"></span>
				                <a class="list" href="javascript:;">
				                    <p><span class="name">${list['NAME']}</span><span class="tel">${empty list['MOBILE'] ? list['TEL'] : list['MOBILE']}</span></p>
				                    <p class="add"><em>${list['EPROVINCE']}${list['ECITY']}${list['ECOUNTY']}</em><em>${list['ADDRESS']}</em></p>
				                </a>
				                <span class="checked active2 default"></span>
				            </div>
	        			</c:when>
	        			<c:otherwise>
	        				<div class="myAddressBox" data="${list['CAID']}">
				                <span class="edit" data-source="${index.index}"></span>
				                <a class="list" href="javascript:;">
				                    <p><span class="name">${list['NAME']}</span><span class="tel">${empty list['MOBILE'] ? list['TEL'] : list['MOBILE']}</span></p>
				                    <p class="add"><em>${list['EPROVINCE']}${list['ECITY']}${list['ECOUNTY']}</em><em>${list['ADDRESS']}</em></p>
				                </a>
				                <span class="checked"></span>
				            </div>
	        			</c:otherwise>
	        		</c:choose>
	        	</c:forEach>
			</div>
            <p class="addAdd">添加新地址</p>
        </div>

        <div class="btn1" id="save">确认</div>

    </div>

    <div class="tjrInfo">
    	<!-- 新增地址 -->
    	<form id="addAddress" style="display: none;">
	        <div class="address address1" id="tjrInfo1">
	            <p>收件地址</p>
	            <p class="active1"></p>
	        </div>
	
	        <div class="peobox">
	            <label><p><i>收件人姓名：</i><b><input type="text" id="name1" placeholder="请输入姓名" name="add.name" datatype="s2-18" nullmsg="请输入姓名" errormsg="姓名格式不正确"></b></p></label>
	            <label><p><i>手&nbsp;机&nbsp;号&nbsp;码：</i><b><input type="text" id="telphone1" placeholder="请输入手机号码" name="add.mobile" datatype="b" nullmsg="请输入手机号码" errormsg="手机号码格式不正确"></b></p></label>
	            <section class="express-area">
	                <a class="expressArea" href="javascript:void(0)" data-type="1">
	                    <dl>
	                        <dt>省&nbsp;&nbsp;&nbsp;市&nbsp;&nbsp;&nbsp;区：</dt>
	                        <dd>选择城市</dd>
	                    </dl>
	                    <input type="hidden" name="add.province" value=0 class="province" datatype="*" nullmsg="请选择城市"/>
					    <input type="hidden" name="add.city" value=0 class="city" datatype="*" nullmsg="请选择城市"/>
					    <input type="hidden" name="add.county" value=0 class="county" datatype="*" nullmsg="请选择城市"/>
	                </a>
	            </section>
	            <label><p><i>详&nbsp;细&nbsp;地&nbsp;址：</i><b><input type="text" id="newAddress1" placeholder="请输入收件地址"  name="add.address" datatype="^[^\s]{2,50}$" nullmsg="请输入详细地址" errormsg="仅支持中英文或数字下划线"></b></p></label>
	            <label><p><i>默&nbsp;认&nbsp;地&nbsp;址：</i><b><span class="active" data="1">是</span><span data="0">否</span><input type="hidden" name="add.isDefault" value="1" id="default1" /></b></p></label>
	        </div>
	
	        <div class="btn1" id="save1">保存</div>
	        <input type="reset" style="display: none;">
		</form>
		
		<!-- 修改地址 -->
		<form id="editAddress" style="display: none;">
	        <div class="address address1" id="tjrInfo2">
	            <p>收件地址</p>
	            <p class="active1"></p>
	        </div>
	
	        <div class="peobox">
	            <label><p><i>收件人姓名：</i><b><input type="text" id="name2" placeholder="请输入姓名" name="add.name" datatype="^[^\s]{2,50}$" nullmsg="请输入姓名" errormsg="姓名格式不正确"></b></p></label>
	            <label><p><i>手&nbsp;机&nbsp;号&nbsp;码：</i><b><input type="text" id="telphone2" placeholder="请输入手机号码" name="add.mobile" datatype="b" nullmsg="请输入手机号码" errormsg="手机号码格式不正确"></b></p></label>
	            <section class="express-area">
	                <a class="expressArea" href="javascript:void(0)" data-type="2">
	                    <dl>
	                        <dt>省&nbsp;&nbsp;&nbsp;市&nbsp;&nbsp;&nbsp;区：</dt>
	                        <dd>选择城市</dd>
	                    </dl>
	                    <input type="hidden" name="add.province" value=0 class="province" />
					    <input type="hidden" name="add.city" value=0 class="city"/>
					    <input type="hidden" name="add.county" value=0 class="county"/>
	                </a>
	            </section>
	            <label><p><i>详&nbsp;细&nbsp;地&nbsp;址：</i><b><input type="text" id="newAddress2" placeholder="请输入收件地址"  name="add.address" datatype="s2-18" nullmsg="请输入详细地址" errormsg="仅支持中英文或数字下划线"></b></p></label>
	            <label><p><i>默&nbsp;认&nbsp;地&nbsp;址：</i><b id="editDefault"><span class="active" data="1">是</span><span data="0">否</span><input type="hidden" name="add.isDefault" value="1" id="default2" /></b></p></label>
	        </div>
	
	        <div class="btn1" onclick="editAddress()">保存</div>
	        <input type="reset" style="display: none;">
	        <input type="hidden" name=add.caid value="" id="editCaid"/>
		</form>
    </div>


    <!--选择地区弹层-->
    <section id="areaLayer" class="express-area-box">
        <header>
            <h3>选择地区</h3>
            <a id="backUp" class="back" href="javascript:void(0)" title="返回"></a>
            <a id="closeArea" class="close" href="javascript:void(0)" title="关闭"></a>
        </header>
        <article id="areaBox">
            <ul id="areaList" class="area-list"></ul>
        </article>
    </section>
    <!--遮罩层-->
    <div id="areaMask" class="mask"></div>
    <script src="/js/jquery.area.js"></script>

    
</div>
<script type="text/javascript" src="/js/order/orderSubmit.js"></script>
</body>
</html>