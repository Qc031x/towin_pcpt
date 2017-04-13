$.alert = function(title, text, onOK) {
	if(title == undefined){
		title="提示";
	}
	if(text == undefined){
		text="提示内容巴拉巴拉~。";
	}
	if(onOK == undefined){
		onOK="确定";
	}
	
	var htmlText='<div class="weui-mask weui-mask--visible"></div>';
	htmlText+='<div class="weui-dialog" > <div  style="padding: 1.3em 1.6em .5em;">';
	htmlText+='<strong class="weui-dialog__title">'+title+'</strong>';	
	htmlText+='</div>';
	htmlText+='<div class="weui-dialog__bd">'+text+'</div>';
	htmlText+='<div class="weui-dialog__ft">';
	htmlText+='<a href="javascript:;" class="weui-dialog__btn primary twbtn-onOK">'+onOK+'</a>';
	htmlText+='</div></div>';
	
	$("body").append(htmlText);
	
	$(".weui-dialog").fadeIn();
	$(".weui-mask--visible").fadeIn();
	
	
	$('.twbtn-onOK').click(function(){
		$(".weui-mask--visible").fadeOut();
		$(".weui-dialog").fadeOut();
		setTimeout(function(){
			$(".weui-mask--visible").remove();
			$(".weui-dialog").remove();
		},500);
		
	})
  }



//用户确认操作弹框
$.confirm = function(title, text, onOK, onCancel) {
	var htmlText='<div class="weui-mask weui-mask--visible"></div>';
	htmlText+='<div class="weui-dialog" > <div  style="padding: 1.3em 1.6em .5em;">';
	htmlText+='<strong class="weui-dialog__title">'+title+'</strong>';	
	htmlText+='</div>';
	htmlText+='<div class="weui-dialog__bd">'+text+'</div>';
	htmlText+='<div class="weui-dialog__ft">';
	htmlText+='<a href="javascript:;" class="weui-dialog__btn twbtn-onCancel" style="color:#999;border-right:1px solid #f1eeee">取消</a>';
	htmlText+='<a href="javascript:;" class="weui-dialog__btn primary twbtn-onOK">继续</a>';
	htmlText+='</div></div>';
	$("body").append(htmlText);
	$(".weui-dialog").fadeIn();
	$(".weui-mask--visible").fadeIn();
	$(".twbtn-onCancel").click(function(){
		onCancel();
		$.closeDialog();
	})
	$(".twbtn-onOK").click(function(){
		onOK();
		$.closeDialog();
	})
	
	
};
  
//用户自定义内容弹框
$.choose = function(title, json, onOk, onCancel){
	var htmlText='<div class="weui-mask weui-mask--visible"></div>';
	htmlText+='<div class="weui-dialog" > <div  style="padding: 1.3em 1.6em .5em;">';
	htmlText+='<strong class="weui-dialog__title">'+title+'</strong>';	
	htmlText+='</div>';
	htmlText+='<div class="weui-dialog__bd">'
	for(var i=0;i<json.length;i++){
		console.info(json[i])
		htmlText+='<div class="twui-check"><span class="remember" data="'+json[i].id+'" data2="'+json[i].id2+'"></span> ';
		htmlText+='<span style="text-overflow:ellipsis;width: 85%;white-space: nowrap;overflow: hidden;text-align: left;">'+json[i].name+'</span></div>';
	}
	htmlText+='<div style="height: 1.3rem;"><div class="chooseTips"  style="display:none;color:red;width: 80%;font-size:1rem; margin: 0 auto;">请选择一个选项！</div></div> ';
	htmlText+='</div><div class="weui-dialog__ft">';
	htmlText+='<a href="javascript:;" class="weui-dialog__btn twbtn-onCancel" style="color:#999;border-right:1px solid #f1eeee">取消</a>';
	htmlText+='<a href="javascript:;" class="weui-dialog__btn primary twbtn-onOK">确认</a>';
	htmlText+='</div></div>';
	$("body").append(htmlText);
	$(".weui-dialog").fadeIn();
	$(".weui-mask--visible").fadeIn();
	$(".twbtn-onCancel").click(function(){
		onCancel();
		$.closeDialog();
	})
	$(".twbtn-onOK").click(function(){
		var batchId=$(".twui-check").find(".check").attr("data");
		var id2=$(".twui-check").find(".check").attr("data2");
		if(batchId=="undefined" || batchId=="" || batchId==null){
			$(".chooseTips").fadeIn();
			return false;
		}
		onOk(batchId,id2);
		$.closeDialog();
	})
	$(".twui-check").click(function(){
		$(".remember").removeClass("check");
		$(this).find(".remember").addClass("check");
		$(".chooseTips").fadeOut();
	})
}




$.closeDialog=function(){
	$(".weui-mask--visible").fadeOut();
	$(".weui-dialog").fadeOut();
	setTimeout(function(){
		$(".weui-mask--visible").remove();
		$(".weui-dialog").remove();
	},500);
}
var deg=30;
var interval;
$.showLoading = function(text) {
    var html = '<div class="twui-toast twui_loading_toast twui-toast--visible" style="display:none;">';
    html += '<div class="twui_loading" style="margin-top: 50%;height: 80px;">';
    html += '<i class="twui-loading twui-icon_toast"></i></div>';
    html += '<p class="twui-toast_content">'+text+'</p></div>';
    $("body").append(html);
	$(".twui_loading_toast").show();
    
	interval=setInterval(loading,25);
  }
$.closeLoading = function(){
	$(".twui_loading_toast").remove();
	window.clearInterval(interval); 
}

function loading(){
	deg=deg+30;
	$(".twui-icon_toast").css({  
        'transform':     'rotate('+deg+'deg)',  
        '-moz-transform':'rotate('+deg+'deg)',  
        '-o-transform':  'rotate('+deg+'deg)'  
    }); 
}





