<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="/WEB-INF/content/public/tags.jsp" %>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	 <script type="text/javascript" src="/js/rem.js"></script>
    <link rel="stylesheet" href="/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="/css/packagelist.css">
    <script type="text/javascript" src="/js/jquery.js"></script>
    <SCRIPT type="text/javascript" src="/js/idangerous.js"></SCRIPT>
    <script type="text/javascript" src="/js/calendar.js"></script>
    <script type="text/javascript" src="/js/commonLib.js"></script>
    <script type="text/javascript" src="/js/jquery.sgfmform.js"></script>
    <title>套餐详情</title>
    <script>
        $(document).ready(function () {
            $(".header_right").click(function(){
                $(this).children().toggleClass("rbgchange");
                $(".linklist").slideToggle(200).css("position","fixed");
            });

            jiazhairili();

        });
    </script>
	<style type="text/css">
		.hotsal {position: relative;padding: 0.5rem 2rem;text-align: center;background-color: #eee;border-bottom: 1px solid #dbdbdb;}
		.hotsal .line {position: absolute;top: 50%;width: 48%;height: 2px;left: 26%;background-color: #dbdbdb;}
		.hotsal span {position: relative;z-index: 2;padding: 0 5%; font-size: 0.65rem;background-color: #eee;font-weight: bold;}
	</style>
</head>
<body>
<div class="header">
    <div class="header_left">
        <a href="javascript:history.go(-1);">返回</a>
    </div>
    <div class="header_info">
        套餐详情
    </div>
    <div class="header_right" onclick="changeMenu()">
        <div class="header_rbg"></div>
        <span>导航</span>
    </div>
</div>
<%@include file="/WEB-INF/content/public/top_menu.jsp" %>


<div class="ornament">
    <div class="ornament_img">
    		<c:choose>
        		<c:when test="${isCard == 1}">
        			<img src="/cardImg/${map['cardInfo']['CAR_IMG']}" onerror="this.src='/images/default_img.png'">
        		</c:when>
        		<c:otherwise>
        			<img src="${ctxImg}/product/${map['baseInfoMap']['CSID']}/${map['baseInfoMap']['IMG']}" onerror="this.src='/images/default_img.png'">
        		</c:otherwise>
        	</c:choose>
    </div>
    <div class="info">
        <h1> ${map['baseInfoMap']['PNAME']}
		<c:choose>
		<c:when test="${map['baseInfoMap']['SEX'] == '1'}">
		(男)
		</c:when>
		<c:when test="${map['baseInfoMap']['SEX'] == '2'}">
		(已婚女)
		</c:when>
		<c:when test="${map['baseInfoMap']['SEX'] == '4'}">
		(未婚女)
		</c:when>
		</c:choose></h1>
        <p style="line-height: 2;"><b>${map['baseInfoMap']['SHOP_PRICE']}</b><s>￥${map['baseInfoMap']['MARKET_PRICE']}</s><span style="line-height: 2.5;">已售${map['baseInfoMap']['COUNT']}</span></p>
    	<c:choose>
         	<c:when test="${map['baseInfoMap']['PAY_TYPE'] eq 2}">
         		<p style="margin: 0;color: #999999;font-size: 10px;padding: 0;line-height: 1.9;">
         		<span style="float: left;width: 60px;height: 18px;background: #ff8f00;border-radius: 3px;text-align: center;line-height: 18px;color: #fff;display: inline-block;">到店付款</span>
         		</p>
         	</c:when>
         	<c:otherwise>
         		<c:choose>
         			<c:when test="${map['baseInfoMap']['coupon'] eq 1}">
         				<div data_id="1"><p style="margin: 0;color: #999999;font-size: 10px;padding: 0;line-height: 1.9;"><span>在线支付</span><em>立减<i style="color: #f36060;">${map['baseInfoMap']['PRICE']}</i>元</em></p></div>
         			</c:when>
         			<c:otherwise>
         				<p style="margin: 0;color: #999999;font-size: 10px;padding: 0;line-height: 1.9;">
		         		<span style="float: left;width: 50px;height: 18px;background: #f36060;border-radius: 3px;text-align: center;line-height: 18px;color: #fff;display: inline-block;">在线支付</span>
		         		</p>
         			</c:otherwise>
         		</c:choose>
         	</c:otherwise>
         </c:choose>
    </div>
    	<input type="hidden" id="pid" value="${map['baseInfoMap']['PID']}" />
        
</div>
<div class="interval"></div>

<div class="clear"></div>

<div class="address">
	<c:forEach items="${map['shopList']}" var="shop">
    <p><span>体检机构：</span><span class="addinfo bcolor">${shop['ENAME']}</span></p>
    <p><span>机构地址：</span><span class="addinfo">${info.shop['CITYNAME']}${info.shop['COUNTYNAME']}${shop['ADDRESS']}</span></p>
    <p><span>营业时间：</span><span class="addinfo">${shop['CHECKUP_TIME']}</span></p>
    <p><span>休息时间：</span><span class="addinfo">${shop['REST_TIME']}</span></p>
    <input type="hidden" id="esid" value="${shop['ESID']}" />
    </c:forEach>
</div>
<div class="interval1"></div>

<div class="clear"></div>

<%-- 	<c:forEach items="${map['promotions']}" var="promotion" varStatus="status">
	<div class="onsale">
        <a href="${ctx}/core/product.toPromotionsDetail.do?pid=${map['baseInfoMap']['PID']}">${promotion['PROMOTION_TITLE']}</a>
    </div>
	</c:forEach> --%>
<div class="interval"></div>

<div class="calender">
    <h3>最近可约日期:</h3>
    <p id="lately"></p>
    <i></i>
</div>

<div class="yuyue_rili">
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
<div class="interval"></div>



<div class="address">
	<p><span class="bcolor">商品编号：</span><span class="addinfo"> ${map['baseInfoMap']['PNO']}</span></p>
    <p><span class="bcolor">套餐标签：</span><span class="addinfo">
		<c:forEach items="${map['productTypeList']}" var="type" varStatus="index">
        	${type.NAME} 
        </c:forEach></span>
    </p>
    <p><span class="bcolor">适用年龄：</span><span class="addinfo">
		<c:forEach items="${map['productAgeList']}" var="age" varStatus="index">
        	${age.NAME} 
        </c:forEach></span>
    </p>
    <p><span class="bcolor">套餐特点：</span><span class="addinfo">${map['baseInfoMap']['PRODUCT_BRIEF']}</span></p>
</div>
<div class="interval"></div>

<div class="clear"></div>

<div class="prolist">
    体检项目
    <i></i>
</div>

<div class="proinfo">
    <table cellpadding="0" cellspacing="0" class="table" border="0">
        <tbody>
        <tr>
            <th scope="col" width="90">体检项目</th>
            <th scope="col">检查指标意义</th>
        </tr>
        <c:forEach items="${map['singleProjectMap']}" var="single" varStatus="index">
				<tr style="">
                    <td><span>${single['MODEL_NAME']}</span></td>
                    <td><span>${single['CONTEXT']}</span></td>
                </tr>
		</c:forEach>
        </tbody>
    </table>
    <div class="loadmore" id="loadmore">
        <span>点击查看更多</span>
    </div>
</div>
<div class="clear"></div>
<div class="interval"></div>


<div class="process">
    <div class="process_title">
        <h3>预约流程：</h3>
    </div>
    <div class="order_process">
        <a class="process1">选择套餐<b></b></a>
        <a class="process2">预约时间<b></b></a>
        <a class="process3">完成支付<b></b></a>
        <a class="process4">前往体检<b></b></a>
        <a class="process5">领取报告</a>
    </div>
</div>
<div class="clear"></div>

<%@include file="/WEB-INF/content/public/bottom.jsp" %>
<div class="clear"></div>
<div class="interval1"></div>

<div class="bottomblank"></div>

<c:choose>
	<c:when test="${isCard == 1}">
		<!--购买-->
    <div class="buybox m-cotrl">
        <div class="buyleft" onclick="window.location.href='tel:4006616669'">
            <div class="cuser">
                <img src="/images/cuser.png">
            </div>
            <h3>咨询客服</h3>
        </div>
        <div class="buycen">
            价格：<b>￥${map['baseInfoMap']['SHOP_PRICE']}</b>
        </div>

        <a href="javascript:;" class="buyright">立即购买</a>
    </div>
    <div class="buybg" style="z-index: 998; display:none;"></div>
    <div class="buycheck_box">
        <div class="buycheck">
            <div class="buyinfo">
            <div class="imgbox1">
            <img src="/cardImg/${map['cardInfo']['CAR_IMG']}" onerror="this.src='/images/default_img.png'">
            <%-- <span class="imgleft">${map['cardInfo']['LABLE_WORDS']}</span>
            <span class="imgright">&nbsp;&nbsp;&nbsp;${map['baseInfoMap']['SHOP_PRICE']}元</span> --%>
            <%-- <div class="recommend">
                <p class="rectitle">${infoMap['medicalCardImg']['MAIN_TITLE']}</p>
                <p>${infoMap['medicalCardImg']['SUB_TITLE1']}</p>
                <p>${infoMap['medicalCardImg']['SUB_TITLE2']}</p>
            </div> --%>
        	</div>
                <div class="info">
                    <h1>${fn:substring(map['baseInfoMap']['PNAME'], 0, 12)}
                    <c:choose>
						<c:when test="${map['baseInfoMap']['SEX'] eq 1}">
						(男)
						</c:when>
						<c:when test="${map['baseInfoMap']['SEX'] eq 2}">
						(已婚女)
						</c:when>
						<c:otherwise>
						(未婚女)
						</c:otherwise>
					</c:choose>
                    </h1>
                    <p><b>￥${map['baseInfoMap']['SHOP_PRICE']}</b></p>
                    <p>已售${map['baseInfoMap']['COUNT']}</p>
                </div>
            </div>
            <form id="cardSubmit">
            	<input type="hidden" id="pid" name="pid" value="${map['baseInfoMap']['PID']}" />
            	<div class="num">
	                <span>购买数量：</span>
	                <div class="goumaishu">
	                    <a href="javascript:;" id="subtract" class="colcha">-</a>
	                    <input type="text" value="1" name="quantity">
	                    <a href="javascript:;" id="add">+</a>
	                </div>
	            </div>
	
	            <div class="num">
	                <span>卡类型</span>
	                <div class="cardtype">
	                    <p data="0"><em class="radiobg checked"></em><span>虚拟卡</span></p>
	                    <p data="1"><em class="radiobg"></em><span>实体卡</span></p>
	                    <input type="hidden" name="cardType" value="0" datatype="*" nullmsg="请选择体检卡类型" />
	                </div>
	            </div>
	
	            <div class="close"></div>
	
	            <div class="buybox mbuy">
	                <a href="javascript:;" class="buybtn" id="cardSub">立即购买</a>
	            </div>
            </form>
        </div>
    </div>
	</c:when>
	<c:otherwise>
		<!--购买-->
    <div class="buybox">
        <a href="javascript:;" class="buybtn">立即购买</a>
    </div>
    <!-- 体检套餐 -->
	<div class="buybg" style="z-index: 998; display:none;"></div>
    <div class="buycheck_box">
        <div class="buycheck" style="bottom: 0rem;">
            <div class="buyinfo">
                <div class="proimg">
                     <img src="${ctxImg}/product/${map['baseInfoMap']['CSID']}/${map['baseInfoMap']['IMG']}" onerror="this.src='/images/default_img.png'">
                </div>
                <div class="info">
                    <h1>${map['baseInfoMap']['PNAME']}
						<c:choose>
						<c:when test="${map['baseInfoMap']['SEX'] == '1'}">
						(男)
						</c:when>
						<c:when test="${map['baseInfoMap']['SEX'] == '2'}">
						(已婚女)
						</c:when>
						<c:when test="${map['baseInfoMap']['SEX'] == '4'}">
						(未婚女)
						</c:when>
						</c:choose>
					</h1>
                    <p><b>￥${map['baseInfoMap']['SHOP_PRICE']}</b><s>￥${map['baseInfoMap']['MARKET_PRICE']}</s></p>
                    <p>已售${map['baseInfoMap']['COUNT']}</p>
                </div>
            </div>
            <div class="num">
                <span>购买数量：</span>
                <div class="goumaishu">
                    <a href="javascript:;" id="subtract" class="colcha">-</a>
                    <input type="text" value="1">
                    <a href="javascript:;" id="add">+</a>
                </div>
            </div>
            <div class="close"></div>
            <a href="javascript:;" class="buybtn1">确认</a>
        </div>
    </div>
    <!-- 体检套餐 -->
	</c:otherwise>
</c:choose>

<script>
$(".cardtype em").click(function(){
	$(this).addClass("checked").parent().siblings().find("em").removeClass("checked");
	$(this).parents(".cardtype").find("input").val($(this).parent().attr("data"));
});
    /*体检项目下拉*/
    $(".prolist").click(function () {
        var prolist = $(".proinfo");
        $(".prolist i").toggleClass("active");
        if (!prolist.is(":visible")) {
            prolist.slideDown();
            $(".loadmore").show();
            $(".table tr").css("display", "");
        } else {
            prolist.slideUp();
        }
    });
    $(".loadmore").click(function () {
        $(".loadmore").hide();
        $(".table tr").css("display", "table-row");
    });


    /*日历下拉*/
    $(".calender").click(function () {
        var calBox = $(".yuyue_rili");
        $(".calender i").toggleClass("active");
        if (!calBox.is(":visible")) {
            calBox.slideDown();
        } else {
            calBox.slideUp();
        }
    });


    /*购买窗口*/
    $(".buybtn").click(function () {
    	$(".buybg").stop().fadeIn();
        $(".buycheck_box").stop().fadeIn();
        $(".buybox").stop().fadeOut();
        $(window).on("touchmove",function(e){e.preventDefault();});
    });

    /*购买窗口*/
    $(".buyright").click(function () {
    	$(".m-cotrl").hide();
    	$(".mbuy").show();
    	$("input[name=cardType]").val($(".cardtype .radiobg.checked").parent().attr("data"));
    	$(".buybg").stop().fadeIn();
        $(".buycheck_box").stop().fadeIn();
        $(window).on("touchmove",function(e){e.preventDefault();});
    });

    $(".close,.buybg").click(function () {
    	$(".buybg").stop().fadeOut();
        $(".buycheck_box").stop().fadeOut();
        $(".buybox").stop().fadeIn();
        $(window).off("touchmove");
    });


    /*购买数量*/
    $('#add').click(function () {
        var oldzhi = parseInt($(this).parents(".goumaishu").find("input").val());
        var newzhi = oldzhi + 1;
        $(this).parents(".goumaishu").find("input").val(newzhi);
        if (newzhi == 1) {
            $("#subtract").addClass("colcha");
        } else {
            $("#subtract").removeClass("colcha");
        }
    });
    $('#subtract').click(function () {
        var oldzhi = parseInt($(this).parents(".goumaishu").find("input").val());
        var newzhi = oldzhi - 1;
        if (newzhi <= 1) {
            newzhi = 1;
            $(this).parents(".goumaishu").find("input").val("1");
        }
        $(this).parents(".goumaishu").find("input").val(newzhi);
        if (newzhi == 1) {
            $("#subtract").addClass("colcha");
        } else {
            $("#subtract").removeClass("colcha");
        }
    });
</script>
<script type="text/javascript" src="/js/order/orderUtil.js"></script>
</body>
</html>