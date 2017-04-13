(function($) {
	$(".bottomblank3").hide();
	/* rs_confirm */
	$(".checklookrili").click(function(){
		if($(this).parents(".peobox").find(".shop").val() == ''){
			alertMsg({"content" : "请先选择预约门店!", "type" : 3, "close" : true});
			return false;
		}  
		$("#page_esid").val($(this).attr("data"));
		ykrili.getjiazairili();
		$(".calenderbox").fadeIn();
		$(".calenderbox .yuyue_rili").fadeIn();
	});
	
	cal_totPri();
	
	//articlePayment("onlinePay", "lijian", 2); //统计在线支付和立减金额
	articlePayment("onlinePay", "lijian", 1); //统计在线支付和立减金额
	articlePayment("payStore", "", 0); //统计到店付款金额

	/*实际支付*/
	
	allPayOn();
	
	
	$("#subRsForm").sgfmform({
		ajaxurl : "/core/order.doCreateReservation.do",
		tiptype : 1,
		submittype : 2,
		callback : function(data, url){
			//console.info(data)
			if(data.returncode == 0){
				if(data.data[0] == "" || data.data[0] == undefined){
					var content = "您可在个人中心查看预约详情"
					alertMsg({"title" : "您的预约信息已提交成功！", "content" : content, "button" : "返回首页"}, function(){
						window.location.href='/core/user.findUserReservation.do';
					})
				}else{
					window.location.href="/core/order.toPayMent.do?order.oid="+data.data[0];
				}
			}else{
				if(data.data[0] == 0){
					window.location.href="/content/error.lossProduct.do";
				}else if(data.data[0] == 1){
					window.location.href="/content/error.orderResub.do";
				}else{
					alertMsg({"type" : "3", "content" : data.errmsg, "clost" : true});
				}
			}
		}
	});
	
	/* card_confirm */
	
	$("#subCardForm").sgfmform({
		ajaxurl : "/core/order.doCreateOrder.do",
		tiptype : 1,
		submittype : 2,
		callback : function(data, url){
			if(data.returncode == 0){
				window.location.href="/core/order.toPayMent.do?order.oid="+data.data[0];
				//alert(1)
			}else{
				if(data.data[0] == 0){
					window.location.href="/content/error.lossProduct.do";
				}else{
					window.location.href="/content/error.orderResub.do";
				}
			}
		}
	});
	
	/*积分优惠券*/
    $(".order_dikou .list .tit").click(function(){
        $(this).parents(".list").find(".dikoucon").slideToggle();
        $(this).parents(".list").find(".tit").toggleClass('active');
        if($(".order_dikou .list .tit.active").size()==0){
        	$(".bottomblank3").hide();
        }else{
        	$(".bottomblank3").show();
        }
    });

    $(".order_dikou .list .us_yue i").click(function(){
        $(this).parents(".list").find(".dikoucon").toggleClass('active');
        $(".order_dikou .list .us_yue").each(function(){
            var wenbenz=Number($(this).find(".shurujine").val());
            var kdbdz=Number($(this).find(".maxus").text());
            if(wenbenz>kdbdz){
                $(this).find(".shurujine").val(kdbdz);
                //alert("您输入的数值超出了可使用数值,请重新输入！
            }
            orderheji();
        })
    });
    $(".order_dikou .shiyongtiaojian").click(function(){
        $(this).parents(".dikoucon").find(".guizhecon").slideToggle();
    });
    $(".order_dikou .new_add_yhq").click(function(){
        $(this).parents(".dikoucon").find(".us_yue .add_yhq").slideToggle();
    });

    $(".order_dikou .list .usyhq").click(function(){
        $(".order_dikou .list .us_yue").each(function(){
            var wenbenz=Number($(this).find(".shurujine").val());
            var kdbdz=Number($(this).find(".maxus").text());
            if(wenbenz>kdbdz){
                $(this).find(".shurujine").val(kdbdz);
                //alert("您输入的数值超出了可使用数值,请重新输入！");
            }
            orderheji();
        })
    });
    
    orderheji();
    
    //新增地址是否默认
    $(".peobox span").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        $(this).siblings("input").val($(this).attr("data"));
    });
    
    /*地址*/
    $(".address2").click(function () {
        $(".address2").find("p").eq(1).toggleClass("active1");
        $(".addressBox").slideToggle();
        $("#areaMask").toggle();
    });
    
    $("#areaMask").click(function () {
    	$(".address2").find("p").eq(1).toggleClass("active1");
        $(".tjrInfo,#areaMask").hide();
        $(".addressBox").slideUp();
    });

    $("#tjrInfo1,#tjrInfo2").click(function () {
    	 $(this).find("p").eq(1).toggleClass("active1");
        $(".tjrInfo").slideToggle();
        $(".addressBox").slideDown();
    });
    
    $(".myAddress1").on("click", ".list,.checked", function(){
    	$(this).parent().addClass("active3").siblings().removeClass("active3");
        $(this).parent().siblings().children(".checked").removeClass("active2");
        if($(this).hasClass("checked")){
        	$(this).addClass("active2");
        }else{
        	$(this).siblings(".checked").addClass("active2");
        }
    });
    /*$(".myAddressBox .list,.checked").click(function () {
        
    });*/
    
    /*$("#save1").click(function () {
        $(".tjrInfo").slideUp();
    });*/
    /*添加地址*/
    $(".addAdd").click(function () {
    	$("#addAddress").show();
    	$("#editAddress").hide();
        $("#addAddress input[type=reset]").trigger("click");
        $("#addAddress dd").text("选择城市");
        $(".addressBox").slideUp();
        $(".tjrInfo").slideDown();
    });
    
    $(".myAddress1").on("click", ".edit", function () {
    	$("#editAddress").attr("data-source", $(this).attr("data-source"));
    	$("#addAddress").hide();
    	$("#editAddress").show();
    	$("#name2").val($(this).parent().find(".name").text());
    	$("#telphone2").val($(this).parent().find(".tel").text());
    	$("#newAddress2").val($(this).parent().find(".add em:last").text());
    	$("#editAddress .expressArea dd").text($(this).parent().find(".add em:first").text());
    	if($(this).parent().find(".checked").hasClass("active2")){
    		$("#editDefault span:first").addClass("active").siblings().removeClass("active");
    		$("#default2").val("1");
    	}else{
    		$("#editDefault span:last").addClass("active").siblings().removeClass("active");
    		$("#default2").val("0");
    	}
    	$("#editCaid").val($(this).parent().attr("data"));
    	$(".addressBox").slideUp();
        $(".tjrInfo").slideDown();
    });
    
    $("#save").click(function () {
    	if($(".myAddress .active3").length <= 0){
    		alertMsg({"type" : "3", "content" : "请选择地址", "close" : true}); 
    		return;
    	}
        $(".addressBox").hide();
        $("#areaMask").hide();
        if($(".peradd p").length > 1){
        	$("#caid").val($(".myAddressBox.active3").attr("data"));
            $(".peradd p:last span:last").text($(".myAddressBox.active3 .add em:first").text() + $(".myAddressBox.active3 .add em:last").text());
            $(".peradd p:last span:first").text($(".myAddressBox.active3 p:first span:first").text());
        }else{
        	$(".peradd").html("");
        	var ui = $("<input type='hidden' value='"+$(".myAddressBox.active3").attr("data")+"' datatype='*' nullmsg='请新增并选择收件地址' name='caid' id='caid' />" +
        			"<p>收货地址</p><p><span>"+$(".myAddressBox.active3 p:first span:first").text()+"</span>，<span>"+$(".myAddressBox.active3 .add em:first").text() + $(".myAddressBox.active3 .add em:last").text()+"</span></p>");
        	$(".peradd").append(ui);
        }
    });
    
    
    $("#addAddress").sgfmform({
    	btnSubmit : "#save1",
		ajaxurl : "/core/order.addMemberAddress.do",
		tiptype : 1,
		submittype : 2,
		callback : function(data, url){
			if(data.returncode == 0){
				var name = $("#name1").val();
				var mobile = $("#telphone1").val();
				var add1 = $("#addAddress .expressArea dd").text();
				var add2 = $("#newAddress1").val();
				if($(".myAddressBox").length > 0){
					var uii = $(".myAddressBox:eq(0)");
					var ui = null;
					if($("#default1").val() == 1){
						$(".myAddressBox").removeClass("active3");
						$(".myAddressBox").find(".checked").removeClass("active2");
						ui = $("<div class='myAddressBox active3' data='"+data.data[0]+"'>" + $(".myAddressBox:eq(0)")[0].innerHTML + "</div>");
						ui.find(".checked").addClass("active2");
						ui.insertBefore(uii)
					}else{
						ui = $("<div class='myAddressBox' data='"+data.data[0]+"'>" + $(".myAddressBox:eq(0)")[0].innerHTML + "</div>");
						ui.insertAfter(uii)
						ui.find(".checked").removeClass("active2");
					}
					ui.find(".name").text(name);
					ui.find(".tel").text(mobile);
					ui.find(".add em:first").text(add1);
					ui.find(".add em:last").text(add2);
				}else{
					var ui = $("" +
							"<div class='myAddressBox active3' data='"+data.data[0]+"'>" +
								"<span class='edit'></span>" +
								"<a class='list' href='javascript:;'>" +
									"<p><span class='name'>"+name+"</span><span class='tel'>"+mobile+"</span></p>" +
									"<p class='add'><em>"+add1+"</em><em>"+add2+"</em></p>" +
								"</a>" +
								"<span class='checked active2'></span>" +
							"</div>");
					$(".myAddress1").append(ui);
				}
				$(".tjrInfo").slideToggle();
		        $(".addressBox").slideDown();
			}else{
				alertMsg({"type" : "3", "content" : data.errmsg, "close" : true})
			}
		}
	});
    
    $("#editAddress").sgfmform({
		ajaxurl : "/core/order.editAddress.do",
		tiptype : 1,
		submittype : 2,
		callback : function(data, url){
			if(data.returncode == 0){
				var ui = $(".edit[data-source="+$("#editAddress").attr("data-source")+"]").parent();
				ui.find(".name").text($("#name2").val());
				ui.find(".tel").text($("#telphone2").val());
				ui.find(".add em:first").text($("#editAddress .expressArea dd").text());
				ui.find(".add em:last").text($("#newAddress2").val());
				if($("#default2").val() == 1){
					ui.siblings(".myAddressBox").removeClass("active3").find(".checked").removeClass("active2 default");
					ui.addClass("active3").find(".checked").addClass("active2 default");
				}
				$(".tjrInfo").slideToggle();
		        $(".addressBox").slideDown();
			}
		}
	});

    
})(jQuery)

function editAddress(){
	//alert($(".edit[data-source="+$("#editAddress").attr("data-source")+"]").siblings(".checked").attr("class"))
	if($(".edit[data-source="+$("#editAddress").attr("data-source")+"]").siblings(".checked").hasClass("default") && $("#default2").val() == 0){
		alertMsg({"type" : "3", "content" : "当前修改地址为默认地址,修改前请先设置其他地址为默认!"});
		return;
	}
	$("#editAddress").submit();
}

function orderheji(){
    var orderzongjine=Number($("#total").text());
    $(".kyhjezs").val(Number($("#ratios").val() * $("#total").html() * 0.01).toFixed(2));
    var kszdyhe=Number($(".kyhjezs").val());
    $(".order_dikou .list .dikoucon").each(function(){

        if($(this).hasClass("syjifenjine")){
            var yhe=Number($(this).find(".zonge").text())*0.1.toFixed(2);
            if(orderzongjine.toFixed(2)<yhe){
                if(Number($(this).find(".shurujine").val())<=Number(orderzongjine)*10){
                    yhe=Number($(this).find(".shurujine").val())*0.1;
                }else{
                    yhe=Number(orderzongjine);
                }
                $(this).find(".shurujine").val(Number(yhe*10).toFixed(0));
                $(".order_dikou .list .syjifenjine .maxus").text(Number(orderzongjine)*10);
            }else{
                if(Number($(this).find(".shurujine").val())<=Number(yhe)*10){
                    yhe=Number($(this).find(".shurujine").val())*0.1;
                }
                $(this).find(".shurujine").val(Number(yhe*10).toFixed(0));
                $(".order_dikou .list .syjifenjine .maxus").text($(this).find(".zonge").text());
            }
            if($(this).hasClass("active")){
                $("#integral").text(Number(yhe).toFixed(2));
            }else{
                $("#integral").text("0");
            }
        }

        if($(this).hasClass("syyhqjine")){
            var uszongyue2=Number($(".order_dikou .list .syyhqjine .zonge").text()).toFixed(2);
        	var kedikou2;
            if(Number(Number(kszdyhe)-Number($("#integral").text())).toFixed(2)<=0  ||  Number(uszongyue2).toFixed(0)==0){
                kedikou2=0;
            }else if(uszongyue2 < Number(kszdyhe).toFixed(2) - Number($("#integral").text()).toFixed(2)){
                kedikou2=Number(uszongyue2).toFixed(2);
            }else{
                kedikou2=Number(Number(kszdyhe)-Number($("#integral").text())).toFixed(2);
            }


            $(".order_dikou .list .syyhqjine .maxus").text(kedikou2);
            if($(".order_dikou .list .syyhqjine .shurujine").val()=="0" ||$(".order_dikou .list .syyhqjine .shurujine").val()=="" || $(".order_dikou .list .syyhqjine .shurujine").val()>=kedikou2){
                $(".order_dikou .list .syyhqjine .shurujine").val(kedikou2);
            }
            if($(this).hasClass("active")){
                $("#coupon").text(Number($(this).find(".shurujine").val()).toFixed(2))
            }else{
                $("#coupon").text("0");
            }
        }
    });

    var yhqjine=Number($("#coupon").text());
    var jfjine=Number($("#integral").text());
    //alert(yhqjine+","+jfjine)
    //$("#storepay").text(Number(orderzongjine-yhqjine-jfjine).toFixed(2));
    var total = orderzongjine-yhqjine-jfjine;
    if(total<0.1){
    $("#allprice").html(0.10);
    }else{
    $("#allprice").html(Number(total).toFixed(2));
    }
}

function subCardForm(){
	orderheji();
	$("#sumPrice").val($("#allprice").text());
	$("#itg").val($("#integral").text());
	$("#cp").val($("#coupon").text());
	$("#subCardForm").submit();
}

function subRsForm(){
	var sex = false;
	$("#coupon").val($(".onsale").attr("data_id"));
	$("#onlinePay em").html("0");
	$("#payStore em").html("0");
	cal_totPri();
	//articlePayment("onlinePay", "lijian", 2);
	articlePayment("onlinePay", "lijian", 1); //统计在线支付和立减金额
	articlePayment("payStore", "", 0); //统计到店付款金额
	allPayOn();
	if(sex){
		confirmMsg("无性生活史女性不能做妇科检查，如能检查妇科，请填写“已婚”", function(){
			$("#subRsForm").submit();
		})
	}else{
		$("#subRsForm").submit();
	}
}

/*计算总额*/
function cal_totPri() {
	var tag_i = $("#singlePrice").html() * $(".rsInfo").length;
	$("#i_totpri em").html(Number(tag_i).toFixed(2));
}

function articlePayment(id, id1, d_id) {
	var pm_i = $(".check_box").find(".onsale[data_id=" + d_id + "]").prev().find("b"); //套餐小计 到店和在线分开
	if(pm_i.length > 0){
		var pm = pm_i[0].innerHTML * Number($(".rsInfo").length); //套餐类型总额
		/*for(var i = 0; i < pm_i.length; i++) {
			pm += Number(pm_i[i].innerHTML);
		}*/
		$("#" + id + " em").html(Number(pm).toFixed(2));
		if(d_id == 1) {
			var pm_i1 = $(".check_box").find(".onsale[data_id=" + d_id + "]").find("i"); //在线支付立减元素3
			var dj = $(".rsInfo").length;
			var pm1 = (Number(pm_i1.html()) * Number(dj)); //立减金额
			$("#" + id1 + " em").html(Number(pm1).toFixed(2)); //立减金额
		}
	}
}

function allPayOn() {
	var pm_div = Number($("#onlinePay em").html()) - Number($("#lijian em").html());
	$("#allpay em e").html(Number(pm_div).toFixed(2));
	$("#sumPrice").val(pm_div);
	var tot_num = $(".ui_input1").length;
	$(".item_tot").html(tot_num);
}


function checkCoupon(type){
	var card = $("#couponCard").val(),
		password = $("#couponPwd").val();
	
	if(card == '' || password == ''){
		alertMsg({"type" : "3", "content" : "请输入优惠券卡号密码", "close" : true})
		return false;
	}
	
	var url = "/core/user.checkCoupon.do",
		data = {"coupon.card" : card.toUpperCase(), "coupon.password" : password};
	
	$.getMyJSON(url,data,function(data){
		if(data.flag == 0){
			alertMsg({"type" : "1", "content" : "账户当前优惠券余额: "+data.totalCoin+"元", "close" : true})
			$("#totalCoin2").text(data.totalCoin);
			$(".add_yhq").slideUp();
			$("#couponCard").val("");
			$("#couponPwd").val("");
			//orderheji();
			if(type == 2) setTimeout('window.location.reload(true)', 1500);
		}else if(data.flag == 1){
			alertMsg({"type" : "3", "content" : "卡号或密码错误，请仔细核对后激活", "close" : true})
		}else if(data.flag == 2){
			alertMsg({"type" : "3", "content" : "优惠券已失效，请使用其他优惠券", "close" : true})
		}else{
			alertMsg({"type" : "3", "content" : "优惠券余额不足，请使用其他优惠券", "close" : true})
		}
	});
}