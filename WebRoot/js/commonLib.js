function CommonLib() {
	this.tagName = null;
	this.getColumVal = function(key) {
		var value;
		for (temp in this.tagName) {

			if (key == temp) {
				value = this.tagName[temp];
				break;
			}
		}

		if (value)
			return value;
		else
			return key;
	}

	// 设置标签名字
	this.setTagName = function(tagName) {

		this.tagName = eval('(' + tagName + ')');

	}

	/**
	 * objForm 当前元素 temp key为esid value为cityid的map转成的json list key为esid
	 * value为门店名称的map转成的json
	 */
	this.setSelectT = function(objForm, temp, list) {
		var id = objForm.value;
		$("#select2 option:not(:first)").remove();
		$(".address").val("");
		for ( var key in temp[0]) {
			// alert(medical[0][key])
			if (id == temp[0][key]) {
				$("#select2").append(
						"<option value='" + key + "' title='" + list[0][key]
								+ "'>" + list[0][key] + "</option>");
			}
		}
		$(".yuyuetime").val("");
	}

	/**
	 * objForm 当前元素 temp key为esid value为门店名称的map转成的json list key为esid
	 * value为门店地址的map转成的json
	 */
	this.setSelectH = function(objForm, temp, list) {
		var id = objForm.value;
		$("#page_esid").val(id);
		for ( var key in temp[0]) {
			if (id == key) {
				$(".address").val(list[0][key]);
				// $(".address").attr("title", list[0][key])
			} else if (id == "") {
				$(".address").val("");
			}
		}
		$(".yuyuetime").val("");
	}

	/**
	 * obj多个省市区时唯一的下标 val当前select的value 页面上要有var area = ${area}; ${area}
	 * 所有省市区json
	 */
	// 设置省份
	this.setProvince = function(obj) {
		for (i = 0; i < area.length; i++) {
			if (area[i].PARENTID == 0) {
				var op = "<option value='" + area[i].ID + "'>" + area[i].TITLE
						+ "</option>";
				try {
					$("#province" + obj).append(op);
				} catch (ex) {
					$("#province" + obj).append(op);
				}
			}
		}
		$("#province" + obj + " option:first").attr("selected", true);
		$("#province" + obj).change();
	}

	// 获取市
	this.getCity = function(index, val) {
		$("#city" + index).empty();
		$("#county" + index).empty();
		for (i = 0; i < area.length; i++) {
			if (val == area[i].PARENTID) {
				var op = new Option(area[i].TITLE, area[i].ID);
				try {
					$("#city" + index).append(op);
				} catch (ex) {
					$("#city" + index).append(op);
				}
			}
		}
		$("#city" + index + " option:first").attr("selected", true);
		$("#city" + index).change();
	}

	// 获取区、县
	this.getCounty = function(index, val) {
		$("#county" + index).empty();
		for (i = 0; i < area.length; i++) {
			if (val == area[i].PARENTID) {
				var op = new Option(area[i].TITLE, area[i].ID);
				try {
					$("#county" + index).append(op);
				} catch (ex) {
					$("#county" + index).append(op);
				}
			}
		}
	}

}
var comm = new CommonLib();

var regs = new Array(
		/^[\u4E00-\u9FA5\uf900-\ufa2d\w]{2,}$/,
		/^0{0,1}1[3|4|5|7|8]\d{9}$/,
		/^\d{4}$/,
		/(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)/);

(function($) {

	$.getMyJSON = function(url, data, callback) {
		$.ajax({
			"url" : url,
			"context" : this,
			"data" : data,
			"type" : "post",
			"cache" : false,
			"dataType" : "json",
			"success" : function(html) {
				callback.call(this, html);
			}
		});
	};

	$.getMyJSON2 = function(url, data, callback) {
		$.ajax({
			"async" : false,
			"url" : url,
			"context" : this,
			"data" : data,
			"type" : "post",
			"cache" : false,
			"dataType" : "json",
			"success" : function(html) {
				callback.call(this, html);
			}
		});
	};

	$(document).ready(
			function() {
				$(".code").bind("click", sendCode);

				$(".check").keyup(
						function() {
							if ($(this).val().length == 4) {
								if (regs[2].test($(this).val())) {
									var url = "/core/reservation.checkCode.do";
									var data = {
										"code" : $(this).val(),
										"time" : $("#time").val(),
										"mobile" : $(".phone").val()
									}

									$.getMyJSON(url, data, function(data) {
										if (data.returncode == 0) {
											$(".formwxtips").remove();
											$(".yyformman").remove();
											// console.info(data)
											/*
											 * if(data.data[1] == '1'){
											 * $("input[name='names']").parent().append("<a
											 * class='xzlxran'
											 * href='javascript:void(0);'>常用联系人</a>");
											 * var personTemp =
											 * $.templates("#personnelTemp");
											 * var html =
											 * personTemp.render(data.data[0]);
											 * $(".tanlxrlist").html(html);
											 * $("#loginfo").val(data.data[2].id); }
											 */
											$("#loginfo").val(data.data[2].id);
										} else {
											$.fn.sgfmform.sn.showmsg("验证码错误！",
													1, {}, "alwaysshow");
										}
									});
								} else {
									$.fn.sgfmform.sn.showmsg("验证码错误！", 1, {},
											"alwaysshow");
								}
							}
						});

				$(".idCheck").on(
						'blur',
						function() {
							var value = $(this).val();
							if (checkReg("z", value)) {
								var myDate = new Date();
								var month = myDate.getMonth() + 1;
								var day = myDate.getDate();
								var age = myDate.getFullYear()
										- value.substring(6, 10) - 1;
								if (value.substring(10, 12) < month
										|| value.substring(10, 12) == month
										&& value.substring(12, 14) <= day) {
									age++;
								} else if (value == "111111111111111111"
										|| value == "111111111111111") {
									age = 0;
								}
								$(this).parents(".qydzformcon").find(".tanage")
										.val(age);
							}
						});
				// 控制机构详细页面的查询效果 beg
				$(".qaz-container .indextit a").click(function() {
					$(".main").show();
					$(".qaz-container").hide();
					dyncContScroll($(this).attr("id"))
					// alert($(".qaz-container .indextit a").attr("id"))

				});
				// 控制机构详细页面的查询效果 end
			});

})(jQuery);

function sendCode() {
	if (!regs[1].test($(".phone").val())) {
		$.fn.sgfmform.sn.showmsg("请填写正确电话号码！", 1, {}, "alwaysshow");
		return;
	}
	$(".dxhayzm").hide();
	$(".codeR").text("发送中...").show();
	$(".check").attr("readonly", false);
	var url = "/core/reservation.reSendMsg.do";
	var data = {
		"mobile" : $(".phone").val()
	};

	$.getMyJSON(url, data, function(data) {
		if (data.returncode == 0) {
			$("#time").val(data.data);
			stopSendCode();
		}
	});
}

function stopSendCode() {
	var i = 59;
	var time1 = window.setInterval(function() {
		if (i > 0) {
			$(".codeR").text(i + "秒后重新获取");
		} else {
			$(".dxhayzm").show();
			$(".codeR").hide();
			// clearInterval(timer1);
			// $(".code").bind("click", sendCode);
		}
		i--;
	}, 1000);
}

function checkReg(type, gets) {
	switch (type) {
	case "*":
		if ($.trim(gets) == "") {
			return false;
		} else {
			return true;
		}
	case "*6-16":
		var repost = /^[^\s]{6,16}$/;
		return repost.test(gets);
	case "n":
		var repost = /^\d+\.?\d*$/;
		return repost.test(gets);
	case "s":
		return isNaN(gets);
	case "s2-18":
		var repost = /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{2,18}$/;
		return repost.test(gets);
	case "p":
		var repost = /^[0-9]{6}$/;
		return repost.test(gets);
	case "m":
		var repost = /^\+?(\d+-)*\d+$/;
		return repost.test(gets);
	case "e":
		var repost = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
		return repost.test(gets);
	case "z":
		return isChinaIDCard(gets);
	case "b":
		var repost = /^([-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3})|0{0,1}1[3|4|5|7|8]\d{9}$/;
		return repost.test(gets);
	default:
		if ($.trim(type) == "" || type.indexOf("*") === 0
				|| type.indexOf("?") === 0) {
			return false;
		} else {
			var repost = new RegExp(type);
			return repost.test(gets);
		}
	}
}

/**
 * 获取字典表显示的值。
 * 
 * @param tagName
 * @param val
 * @returns
 */
function getDictName(tagName, val) {
	var tagNameJson = eval('(' + tagName + ')');
	return tagNameJson[val];
}

/**
 * 限制文本域最大长度。
 * 
 * @param Objs
 *            文本域对象
 * @param MaxCount
 *            最大长度
 * @returns
 */
function TxtMaxlength(Objs, MaxCount) {
	if (MaxCount == null || typeof (Number(MaxCount)) !== "number")
		MaxCount = 50;
	var ObjCou = Objs.value.length;
	if (ObjCou > MaxCount) {
		Objs.value = Objs.value.substr(0, MaxCount);
	}
}
/**
 * 判断是因为session过期抛出异常 还是因为程序异常
 * 
 * @param strTrow
 *            异常信息
 * @param
 * @returns
 */
function throwTpyeFlag(strTrow) {
	if (strTrow.indexOf('DataCenter Session Timeout') >= 0
			|| strTrow.indexOf('not called') >= 0) {
		window.location.reload();
		return false;
	} else

		return true;

}

/**
 * 限制输入的字符数。
 * 
 * @param obj
 */
function checklength(obj) {
	var max = obj.attributes['maxlength'].nodeValue;
	if (max == null || max == "" || max == undefined) {
		return;
	}
	if (obj.value.length > max) {
		alert("请不要超过最大长度:" + max);
		obj.value = obj.value.substring(0, (max - 1));
		return;
	}
}

function getFilterStr(str, maxLength) {
	if (str.length > maxLength) {
		return str.substring(0, (maxLength - 1)) + "...";
	} else {
		return str;
	}
}
function getYkCookie(sname) {// 获取单个cookies
	var acookie = document.cookie.split(";");
	// alert(acookie)
	for (var i = 0; i < acookie.length; i++) {
		var arr = acookie[i].split("=");
		var str = arr[0].trim();
		// alert("arr[0]="+str+"\n arr[1]="+arr[1]+"sname="+sname);
		if (sname == str) {
			return arr[1].trim();

		}
	}
	return "";
}
function setCookie(cname, cvalue, exdays) {
	var d = new Date();
	d.setTime(d.getTime() + (exdays * 24 * 60 * 60));
	var expires = "expires=" + d.toUTCString();
	document.cookie = cname + "=" + cvalue + "; " + expires;
}

function loginInfo() {
	var account = getYkCookie("yikang_account");
	var pwd = getYkCookie("yikang_pwd");
	if (account == "") {
		account = "用户名/手机/邮箱";
	}
	if (pwd == "") {
		pwd = "密码";
	}
	$("#account").val(account);
	$("#pwd").val(pwd);
	document.getElementById("autoLogin").checked = "checked";
	if (pwd == "密码") {
		document.getElementById("pwd").type = "text";
		document.getElementById("autoLogin").checked = "";
	}
}
// 列表：筛选产品中的控制样式 beg

function controlProFiterStyle() {

	var bg = $("#productFilterEm").css("background");
	// alert(bg)
	// 表示当前筛选条件处于展开状态
	if (bg.indexOf('jgtcsxanbgup.png') > -1) {
		$(".main").show();

	}
	// 表示当前筛选条件处于没有展开状态
	else if (bg.indexOf('jgtcsxanbgdown.png') > -1) {

		$(".main").hide();
	}

}

// 列表：筛选产品中的控制样式 end
// 机构详细页面：筛选产品中的控制样式 beg

function contShopDetilFiterStyle(obj) {

	// var bg=$(".indextit .xlsaixuan").css("background");
	var bg = obj.css("background");
	// alert(bg)
	// 表示当前筛选条件处于展开状态
	if (bg.indexOf('jgtcsxanbgup.png') > -1) {

		$(".qaz-container").hide();
		$(".main").show();

	}
	// 表示当前筛选条件处于没有展开状态
	else if (bg.indexOf('jgtcsxanbgdown.png') > -1) {

		$(".main").hide();
		// alert($(".qaz-container").html())
		$(".qaz-container").show();

		$(".qaz-container .indextit a").removeClass()
		// alert($(".qaz-container .indextit .xlsaixuan").html())
		// $(".indextit
		// .xlsaixuan").css("background","../images/jgtcsxanbgup.png");url(../images/jgtcsxanbgup.png)
		// 3.2rem center no-repeat; background-size:1rem auto;

		$("#propDIv").removeClass();
		$("#propDIv").addClass("xlsaixuan xlsaixuan_divshop");
	}
	$('html,body').animate({
		scrollTop : '0px'
	}, 800);

}
function submitFiter() {

	$(".main").show();
	$(".qaz-container").hide();
}
function setMenuShow() {
	if ($(".main").css("display") == 'none')
		return true;
	else {
		return false;
	}
	// alert($(".main").css("display"))
	// if($(".main").css("display")).show();
	// $(".qaz-container").hide();
}

// 机构详细页面：筛选产品中的控制样式 end
ListAdapterFactory = {
	getAdapter : function(_adapterName) {
		switch (_adapterName) {
		case "product":
			return new productFactory();
			break;
		case "entityShop":
			return new shopFactory();
			break;
		default:
			throw "invalid adpter name";
			break;
		}
	}
}

function showTips(obj) {
	$.fn.sgfmform.sn.showmsg(obj, 1, {}, 'alwaysshow');
	setTimeout("$.Hidemsg();", 1500);
}
function changeMenu() {
	// alert($('#topnavcomp').css('display'))
	if($("#showChangeCity").val() == undefined){
		$("#qiehuan").hide();
	}else if($("#showChangeCity").val() == "true"){
		$("#qiehuan").show();
	}

	if ($('#topnavcomp').css('display') == 'none') {
		$('#topnavcomp').show();
		$('#model_box1').show();
		return;
	}
	$('#topnavcomp').hide();
	$('#model_box1').hide();

}

function postwith(to, p) {
	var myForm = document.createElement("form");
	myForm.method = "post";
	myForm.action = to;
	for ( var k in p) {
		var myInput = document.createElement("input");
		myInput.setAttribute("name", k);
		myInput.setAttribute("value", p[k]);
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
	document.body.removeChild(myForm);
}
