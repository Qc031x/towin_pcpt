<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>我的优惠券</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
	<meta name = "format-detection" content = "telephone=no">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />	
	<%@include file="../public/head.jsp" %>
	<link rel="stylesheet" href="/css/coupon.css"/>
  </head>
  <body style="background: #eeeff3;">
 	 <script>
        $(document).ready(function () {

        });
    function checkCoupon(){
	var card = $("#coupon_name").val(),
		password = $("#coupon_pwd").val();		
	if(card == '' || password == ''){
		//$.fn.sgfmform.sn.showmsg("请输入优惠券卡号密码",1,{},"alwaysshow");
		alertMsg({"content": "请输入优惠券卡号密码!","type": 3,"close": true});
		return false;
	}		
	var url = "${ctx}/core/user.checkCoupon.do",
		data = {"coupon.card" : card.toUpperCase(), "coupon.password" : password};

	$.getMyJSON(url,data,function(data){
		if(data.flag == 0){
			//$.fn.sgfmform.sn.showmsg("激活成功，优惠券金额已充值到您的个人账户中！",1,{},"alwaysshow");
			alertMsg({"content": "激活成功，优惠券金额已充值到您的个人账户中！","type": 1,"close": true,"timeout" : 1500});
			$(".dquser_lm span").text("( "+data.totalCoin+" )")
			$("#coupon_name").val("");
			$("#coupon_pwd").val("");
			setTimeout("window.location.reload(true)", 1000);
		}else if(data.flag == 1){
			//$.fn.sgfmform.sn.showmsg("卡号或密码错误，请仔细核对后激活",1,{},"alwaysshow");
			alertMsg({"content": "卡号或密码错误，请仔细核对后激活","type": 2,"close": true,"timeout" : 1500});
		}else if(data.flag == 2){
			//$.fn.sgfmform.sn.showmsg("优惠券已失效，请使用其他优惠券",1,{},"alwaysshow");
			alertMsg({"content": "优惠券已失效，请使用其他优惠券!","type": 3,"close": true});
		}else{
			//$.fn.sgfmform.sn.showmsg("优惠券余额不足，请使用其他优惠券",1,{},"alwaysshow");
			alertMsg({"content": "优惠券余额不足，请使用其他优惠券","type": 3,"close": true});
		}
	});
}
    </script> 
<div class="main">
    <div class="check">
        <span id="coupon" class="active">优惠券</span>
        <span id="integral">积分</span>
    </div>
    
    <div class="clear"></div>

    <div class="tableBox">
        <div class="coupon">
            <label><p><i>优惠券号码：</i><b><input id="coupon_name" type="text"  value=""/></b></p></label>
            <label><p><i>优惠券密码：</i><b><input id="coupon_pwd" type="password" value=""/></b></p></label>
            <div class="activationBtn"><a href="javascript:;" onclick="checkCoupon()">激活</a></div>

            <div class="jfyhqbg">
                <div class="jfyhqtit">余额: <span>
                <c:choose>
				<c:when test="${balance==null}">
				¥ 0
        		</c:when>
       			<c:when test="${balance!=null}">
   				¥ ${balance}
       			</c:when>      	
    			</c:choose>
                </span></div>
                <div class="mingxilist">
                <div class="list toplist">
                    <div class="list1">时间</div>
                    <div class="list2" style="color: #716f6f;">收入/支出</div>
                    <div class="list3" style="text-align: center;">说明</div>
                </div>
                
                <c:forEach items="${couponLogs}" var="coupon">
	        	<div class="list">
	        		<div class="list1">${coupon['POST_DATE']}</div>
	            	<c:choose>
	            		<c:when test="${ coupon['COUPON_OP_TYPE']==0 }">
	            			<div class="list2"><span>-${coupon['OP_COUPON']}</span></div>
	            		</c:when>
	            		<c:when test="${ coupon['COUPON_OP_TYPE']==1 }">
	            			<div class="list2"><span>+${coupon['OP_COUPON']}</span></div>
	            		</c:when>
	            		<c:otherwise>
	            			<div class="list2"><span>数据为空</span></div>
	            		</c:otherwise>
	            	</c:choose>	     
	            	<div class="list3">${coupon['OPER_DESC']}</div>
	        	</div>
       			</c:forEach>               
            </div>
            </div>
        </div>
        
        <div class="integral">
            <p>剩余积分：<span>
            <c:if test="${ totalIntegration <= 0 }">
			0
			</c:if>
			<c:if test="${ totalIntegration > 0 }">
			${totalIntegration}
			</c:if>
            </span></p>
            <div class="jfyhqbg">
                <div class="jfyhqtit" style="color: #716f6f;">明细:</div>
                <div class="mingxilist">
                <div class="list toplist">
                    <div class="list1">时间</div>
                    <div class="list2" style="color: #716f6f;">收入/支出</div>
                    <div class="list3" style="text-align: center;">说明</div>
                </div>
              <c:forEach items="${integrationLogs}" var="integration">
        		<div class="list">
        		<div class="list1">${integration.POST_DATE}</div>
        		<c:choose>
        			<c:when test="${ integration['CREDIT_OP_TYPE']==0 }">
        				<div class="list2"><span>-${integration['OP_CREDIT']}</span></div>
        			</c:when>
        			<c:when test="${ integration['CREDIT_OP_TYPE']==1 }">
        				<div class="list2"><span>+${integration['OP_CREDIT']}</span></div>
        			</c:when>
        		</c:choose>
            	<div class="list3">${integration['OPER_DESC']}</div>
        		</div>
       		 </c:forEach>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    /*切换标题*/
    $(".check span").click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

    $("#coupon").click(function(){
        $("title").html("我的优惠券");
        $(".header_info").html("我的优惠券")
    });

    $("#integral").click(function(){
        $("title").html("我的积分");
        $(".header_info").html("我的积分")
    });

    $(".check span").click(function(){
        $(".tableBox>div").css("display", "none");
        $(".tableBox>div").eq($(this).index()).css("display", "block");
    });
</script>
  </body>
</html>
