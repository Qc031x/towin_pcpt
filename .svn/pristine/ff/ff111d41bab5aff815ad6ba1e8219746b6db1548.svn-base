(function($) {
	$(".buybtn1").on("click", function(){
		var url = "/core/order.addRsToCookie.do",
			data = {"pid" : $("#pid").val(), "quantity" : $(".goumaishu input").val()};
		toAddRsCookie(url, data);
	});
	$("#cardSubmit").sgfmform({
		btnSubmit: "#cardSub",
		ajaxurl : "/core/order.addCardToCookie.do",
		tiptype : 1,
		submittype : 2,
		callback : function(data, url){
			if(data.returncode == 0){
				window.location.href = "/core/order.toCardConfirm.do?TK="+data.data[0];
			}else{
				window.location.href = "/content/user.register.do?backurl="+encodeURIComponent(data.data[0])+"&data="+encodeURIComponent(JSON.stringify(data.data[1]));
			}
		}
	});
	
	
	$("#tanyieyi").click(function(){$(".fix_reg_xieyi").fadeIn();});
	$(".fixxieyitit .guanbifixxy").click(function(){$(".fix_reg_xieyi").fadeOut();});
	
	$('#captchas').val('');
	
	$("#registerForm").sgfmform({
		btnSubmit  : '#doRegister',
		ajaxurl    : '/core/user.doRegister.do',
		tiptype    : 2,
		submittype : 2,
		callback   : function(data,url){
			if (data.returncode == 0){
				var url = decodeURIComponent(window.location.href);
				if(url.indexOf("?") > -1){
					var jsonData = url.substr(url.indexOf("{"), url.length);
					var urlData = url.substring((Number(url.indexOf("=")) + 1), url.indexOf("&"));
					alert(jsonData)
					if(urlData.indexOf("Card") > -1){
						toAddCardCookie(urlData, JSON.parse(jsonData));
					}else{
						toAddRsCookie(urlData, JSON.parse(jsonData));
					}
				}else{
					postwith(url, null);
				}
			}else{
				alertMsg({"content" : data.errmsg, "type" : "3", "close" : true})
			}
		}
	});
})(jQuery)

function toAddRsCookie(url, data){
	$.getMyJSON(url, data, function(returndata) {
		if(returndata.returncode == 0){
			window.location.href = "/core/order.toRsConfirm.do?TK="+returndata.data[0];
		}else{
			window.location.href = "/content/user.register.do?backurl="+encodeURIComponent(url)+"&data="+encodeURIComponent(JSON.stringify(data));
		}
	});
}

function toAddCardCookie(url, data){
	$.getMyJSON(url, data, function(returndata) {
		if(returndata.returncode == 0){
			window.location.href = "/core/order.toCardConfirm.do?TK="+returndata.data[0];
		}else{
			window.location.href = "/content/user.register.do?backurl="+encodeURIComponent(returndata.data[0])+"&data="+encodeURIComponent(JSON.stringify(returndata.data[1]));
		}
	});
}

function achCaptchas(){
	var mobile = $('#mobile').val().trim();
	if (!$.fn.sgfmform.sn.regcheck('b', mobile)){
		//showTips("请输入正确手机号码!");
		alertMsg({"content": "请输入正确手机号码!","type": 2,"close": true,"timeout" : 2000});
		return ;
	}
	$("#btnSendcode").text("正在发送...");
	$("#btnSendcode").css("background","#a9a9a9");
	var url = "/core/user.getMobileAchCaptchas.do";
	var data = {"mobile":mobile};
	$.getMyJSON(url,data,function(data){
		if(data.returncode == 0){
			$("#mark").val(data.data[0]);
		}else{
			alertMsg({"content": data.errmsg,"type": 3,"close": true,"timeout" : 2000});
		}
		$("#mark").val(data.data[0]);
		stopCode();
	});
}

function stopCode(){
	$("#btnSendcode").attr("disabled", true);
	$("#btnSendcode").css("cursor","default");
	var i = 60;
	var time1 = window.setInterval(function(){
		if(i>0){
			$("#btnSendcode").removeAttr("onclick");
			$("#btnSendcode").attr("disabled", true);
			$("#btnSendcode").text(i + "秒后重新获取");
			$("#btnSendcode").css("background","#a9a9a9");
			$("#btnSendcode").css("cursor","default");
		}else{
			$("#btnSendcode").attr("onclick","achCaptchas()");
			$("#btnSendcode").attr("disabled",false);
			$("#btnSendcode").text("获取验证码");
			window.clearInterval(time1); 
			$("#btnSendcode").css("background","#1aa9b9");
			$("#btnSendcode").css("cursor","pointer");
		}
		i--;}
	,1000);	
}

