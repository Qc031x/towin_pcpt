<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/content/public/head.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <link rel="stylesheet" type="text/css" href="/css/tjkReserve.css">
    <title>修改预约信息</title>
</head>
<body>
<div class="main">
    <div class="protip">预约单信息</div>
    <div class="interval"></div>
    <div class="protip" style="color: #716f6f; font-size: 13px; font-weight: normal;">套餐名称：${re.P_NAME }
    	<c:choose>   	
			<c:when test="${re.base.SEX eq 1}">
				(男)
			</c:when>    	
			<c:when test="${re.base.SEX eq 2}">
				(已婚女)
			</c:when>    	
			<c:when test="${re.base.SEX eq 3}">
				(未婚女)
			</c:when>    	
    	</c:choose>
    </div>
     <div class="protip" style="color: #716f6f; font-size: 13px; font-weight: normal;">套餐价格：￥${re.base.SHOP_PRICE}</div>
    <c:if test="${fn:indexOf(re.CARD,'--/--')<0}">
	  <div class="protip" style="color: #716f6f; font-size: 13px; font-weight: normal;">体检卡号：${re.CARD }</div>
	</c:if> 
    <div class="interval"></div>
	<form id="updReservationForm" method="post">
    <div class="protip">体检人信息</div>
    <div class="interval"></div>
	
	<input type="hidden" value="${re.OLID }" name="sReservation.olid">
    <input type="hidden" value="${re.PID }" name="sReservation.pid">
    <input type="hidden" value="${re.RID }" name="sReservation.rid">
    <input type="hidden" value="${re.CARD }" name="sReservation.card">
    <input type="hidden" value="${re.PASSWORD }" name="sReservation.password">
    <input type="hidden" id="esid" value="${re.ESID }" name="sReservation.esid">
    <input type="hidden" id="page_esid" value="${re.ESID }">
    
    <div class="peobox">
        <div id="msg" class="errme">请输入正确信息</div>
        <label><p><i>姓&nbsp;&nbsp;&nbsp;名：</i><b>
        	<input type="text" id="name1" placeholder="请输入体检人姓名" value="${re.RE_NAME }" nullmsg="请输入体检人姓名" datatype="*" name="sReservation.reName">
        </b></p></label>
        
        <label><p><i>性&nbsp;&nbsp;&nbsp;别：</i><b>
        	<c:choose>
        		<c:when test="${re.base.SEX eq 1 }">
        			<c:if test="${re.RE_SEX eq 0 }">
        				<span class="active" re_prop="0">男</span>
        			</c:if>
        		</c:when>
        		<c:when test="${re.base.SEX eq 2 }">
        			<c:if test="${re.RE_SEX eq 1 }">
        				<span class="active" re_prop="1">女</span>
        			</c:if>
        		</c:when>
        		<c:when test="${re.base.SEX eq 4 }">
        			<c:if test="${re.RE_SEX eq 1 }">
        				<span class="active" re_prop="1">女</span>
        			</c:if>
        		</c:when>
        	</c:choose>
        		<input type="hidden" id="re_sex" value="${re.RE_SEX }" name="sReservation.reSex">
        </b></p></label>
        <label><p><i>身份证：</i><b>
        	<input type="text" id="UUserCard" placeholder="请输入体检人身份证" value="${re.RE_CID }" datatype="z" 
        		nullmsg="请输入身份证" errormsg="请输入正确的身份证" name="sReservation.reCid">
        </b></p></label>
        <label><p><i>年&nbsp;&nbsp;&nbsp;龄：</i><b>
        	<input type="text" id="age" value="${re.RE_YEAR }" readonly="true" name="sReservation.reYear">
        </b></p></label>
        <label><p><i>婚&nbsp;&nbsp;&nbsp;否：</i><b>
        	<c:choose>
        	
        		<c:when test="${re.base.SEX == 1}">
        		    <c:if test="${re.RE_MARRIAGE ==0}">
        		    <span class="active" re_prop="0">未婚</span>
		        	<span re_prop="1">已婚</span>
        		    </c:if>
        		    <c:if test="${re.RE_MARRIAGE ==1}">
        		    <span re_prop="0">未婚</span>
		        	<span class="active" re_prop="1">已婚</span>
        		    </c:if>	
        		</c:when>
        		<c:when test="${re.base.SEX ==2 }">
		        	<span class="active" re_prop="1">已婚</span>
        		</c:when>
        		<c:when test="${re.base.SEX == 4}">
		        	<span class="active" re_prop="0">未婚</span>
        		</c:when>
        		
        	</c:choose>
        	<input type="hidden" id="re_marriage" value="${re.RE_MARRIAGE }" name="sReservation.reMarriage">
        </b></p></label>
        <label><p><i>手&nbsp;&nbsp;&nbsp;机：</i><b>
        	<input type="text" id="telphone1" placeholder="请输入体检人手机" value="${re.RE_TEL }" datatype="b"  
        		nullmsg="请输入体检人手机号码" errormsg="请输入正确的手机号码" name="sReservation.reTel">
        </b></p></label>
    </div>
    <div class="clear"></div>
    <div class="interval"></div>

    <div class="protip">确认体检分院和时间</div>
    <div class="interval"></div>
    <div class="peobox">
        <label><p><i>城&nbsp;&nbsp;&nbsp;市：</i><b>
            <select id="selCity" onchange="getShop(this)" datatype="*" nullmsg="请选择城市">
            	<c:forEach items="${cityMap }" var="city">
            		<c:choose>
            			<c:when test="${city.key == re.CITY }">
            				<option value ="${city.key }" selected="selected">${city.value }</option>
            			</c:when>
            			<c:otherwise>
		            	    <option value ="${city.key }">${city.value }</option>
            			</c:otherwise>
            		</c:choose>
            	</c:forEach>
            </select>
        </b></p></label>
        <label><p><i>分&nbsp;&nbsp;&nbsp;院：</i><b>
            <select class="marleft" id="shopSel" onchange="getAddress(this)" datatype="*" nullmsg="请选择体检分院">
            </select>
            <input type="hidden" id="tjzx" value="${re.PNAME}">
            <input type="hidden" id="fy" value="${re.NAME}">
        </b></p></label>
        <label><p><i>地&nbsp;&nbsp;&nbsp;址：</i><b>
        	<input type="text" id="hospitalAddress" value="${re.ADDRESS }" readonly="true" name="sReservation.address">
        </b></p></label>
        <label><p><i>体检时间：</i><b>
        	<input class="input1 checklookrili" id="cal" placeholder="请选择" value="${re.CREATER_TIME }"
        		  datatype="*" nullmsg="请选择体检日期" name="sReservation.createrTime" readonly="true">
        </b></p></label>
    </div>
    
    <div class="clear"></div>
    <div class="interval"></div>
    
    <div class="formmain">
        <div class="xmlist">
            <span class="tit">是否享受VIP服务：</span>
            <c:choose>
            	<c:when test="${re.IS_VIP == 1 }">
		            <span class="cons">是</span>
            	</c:when>
            	<c:otherwise>
            		 <span class="cons">否</span>
            	</c:otherwise>
            </c:choose>
        </div>
        <div class="xmlist">
            <span class="tit">电子体检报告：</span>
        	<c:choose>
        		<c:when test="${re.DOC_REPORTS == 1}">
		            <span class="cons">有</span>
        		</c:when>
        		<c:otherwise>
            		 <span class="cons">无</span>
            	</c:otherwise>
        	</c:choose>
        </div>
        <div class="xmlist">
            <span class="tit">提交时间：</span><span class="cons">${re.POST_DATE }</span>
        </div>
        <%-- <div class="xmlist">
            <span class="tit1">备注：</span>
            <label><textarea name="sReservation.reDesc">${re.RE_DESC }</textarea></label>
        </div> --%>
    </div>
    
    <div class="bottomblank1"></div>

    <div class="buybox1">
        <a href="javascript:history.go(-1);">取消</a>
        <a href="javascript:;" class="buybtn">确认</a>
    </div>
	</form>

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
<script type="text/javascript" src="/js/ykcalendar.js"></script>
<script type="text/javascript" src="/js/validateIdCard.js"></script>
<script type="text/javascript" src="/js/jquery.sgfmform.js"></script>
<script>
$(document).ready(function () {
    getBrandShop('${re.CITY}',0);

    $(".buybtn").click(function(){
		$("#updReservationForm").submit();           	
    })
    
    $("#updReservationForm").sgfmform({
    	ajaxurl     : '/core/reservation.updateCardReservation.do',
    	tiptype     : 1,
		submittype  : 2,
    	callback    : function(data,url){
    		if (data['results'] == 1){
    			alertMsg({
    				type : 1,
    				content : '预约单修改成功！',
    				close : true,
    				timeout : 1500
    			})
    		}
    		setTimeout("self.location='/core/user.findUserReservation.do'",1800);
    	}
    });
});
	$(".checklookrili").click(function(){
		ykrili.getjiazairili();
		$(".calenderbox").fadeIn();
		$(".calenderbox .yuyue_rili").fadeIn();
	});

    /*性别婚否*/
    $(".peobox span").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        $(this).siblings("input").val($(this).attr("re_prop"));
    });

    $(".cons span").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
    });


    $(".cancel,.calenderbg").click(function () {
        $(".calenderbox,.yuyue_rili").stop().fadeOut();
        $(window).off("touchmove");
    });

    /*根据身份证自动填入年龄*/
    $("#UUserCard").on("input",function () {
        var UUserCard = $("#UUserCard").val();
        var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();

        var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
        if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
            age++;
        }
        $("#age").val(age);
    });

function getShop(obj){
	var val = obj.value;
	var shopSel = $("#shopSel");
	shopSel.length = 1;
	var shopIdMap = ${hospitalCity}[0];
	var shopMap = ${hospitalName}[0];
	var tjzx=$("#tjzx").val();
	var fy=$("#fy").val();
	for(var k in shopIdMap){
		if (val == shopIdMap[k]){
			var op = new Option(shopMap[k],k);
			try {
				if ('${re.CITY}' != val){
					$("#cal").val('');
				}
				shopSel.add(op,null);
				if (shopMap[k] == tjzx + fy){
					$("#esid").val(k);
					$("#page_esid").val(key);
					$("#hospitalAddress").val(${esidAndAddress}[0][k]);
				}
			} catch (ex) {
				shopSel.add(op);
				if (shopMap[k] == tjzx + fy){
					$("#hospital").val(shopMap[k]);
					$("#hospitalAddress").val(${esidAndAddress}[0][k]);
				}
			}
		}
	}
	getBrandShop(obj.value,1);
}
function getBrandShop(val,type){
	//val城市id
	var form = document.getElementById("updReservationForm");
	var shopMap = ${hospitalName}[0];
	var shopIdMap = ${hospitalCity}[0];
	var address = ${esidAndAddress}[0];
	var shopSel = form.shopSel;
	shopSel.length = 0;
	var count = 0;
	for (var k in shopIdMap) {
		if (val == shopIdMap[k]){
			var option = new Option(shopMap[k],k);
			shopSel.add(option);
			if (count == 0){
				$("#hospitalAddress").val(address[k]);
			}
			++count;
		}
	}
	if (type === 0){
		var event = document.createEvent("HTMLEvents");
		event.initEvent("change", true, false);
		$(shopSel).val('${re.ESID}');
		shopSel.dispatchEvent(event);
		/* $(shopSel).find("option[value='"+${re.ESID}+"']").attr("selected",true); */
	}else {
		$("#esid").val($(shopSel).val());
		$("#page_esid").val($(shopSel).val());
	}
}
function getAddress(obj) {
	var shopAddress = ${esidAndAddress}[0];
	for (var k in shopAddress){
		if (k == obj.value){
			$("#hospitalAddress").val(shopAddress[k]);
			break;
		}
	}
	$("#cal").val('');
	$("#esid").val(obj.value);
	$("#page_esid").val(obj.value);
}
</script>
</body>
</html>