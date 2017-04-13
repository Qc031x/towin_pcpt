/**
 * 全局弹框提示
 * @param msg json格式 
 * 		参数含义:
 * 			type---提示类型 1成功信息,2失败信息,3警告提示信息,4锁,5下载信息,6提问信息,7微笑,8哭泣
 * 			content---提示文本正文
 * 			title---提示文本标题
 * 			close---是否自动关闭 true false
 * 			timeout---自动关闭时间 前置条件close为true 非必填
 * 			callfunc---回调函数 按下确定按钮后执行的函数 非必填
 * @param callfunc
 * @returns
 * demo:
	alertMsg({"type" : 1, "content" : "12345", "close" : true, "timeout" : 3000}, function(){alert(1)});
 * 注意,回调函数不能写成赋值式函数
 */

var alertMsg = function(msg, callfunc){
  var msgtype;
  var btntype;
  var autoclose = msg.close == undefined ? false : msg.close;
  var time = msg.timeout == undefined ? 2000 : msg.timeout;
  var btnMsg = msg.btn == undefined ? "确定" : msg.btn;
  switch(Number(msg.type)){
    case 1:
      msgtype = 'success.png';//成功
      btntype = 'success';
      break;
    case 2:
      msgtype = 'error.png';//失败
      btntype = 'error';
      break;
    case 3:
      msgtype = 'warning.png';//警告
      btntype = 'warning';
      break;
    case 4:
      msgtype = 'lock.png';//锁
      btntype = 'info';
      break;
    case 5:
      msgtype = 'download.png';//下载
      btntype = 'info';
      break;
    case 6:
      msgtype = 'question.png';//疑问
      btntype = 'info';
      break;
    case 7:
      msgtype = 'smail.png';//笑脸
      btntype = 'info';
      break;
    case 8:
      msgtype = 'cty.png';//哭泣
      btntype = 'info';
      break;
  }
  new $.flavr({
    content   : msg.content,
    position  : 'bottom-mid',
    title     : msg.title,
    icon      : msgtype,
    autoclose : autoclose,
    timeout   : time,
    closeEsc  : true,
    animateEntrance : 'fadeIn',
    buttons   : {
      primary   : {
    	 text: btnMsg,
        style : btntype,
        action: function(){
          if(callfunc){
            callfunc();
          }
        }
      }
    }
  });
}

/**
 * 全局弹框确认选择
 * @param msg 提示文本
 * @param yes_callfunc 确认回调函数 非必填
 * @param no_callfunc 取消回调函数 非必填
 * @returns
 * 
 * demo:
 * 		confirmMsg("12345", function(){alert(1)}, function(){alert(2)})
 * 
 * 注意:回调函数均不能写赋值式函数
 */
var confirmMsg = function(msg, yes_callfunc, no_callfunc, confirmBtn, cancleBtn){
	new $.flavr({
    content : msg,
    dialog  : 'confirm',
    icon    : 'question.png',
    confirmBtn : confirmBtn,
    cancleBtn : cancleBtn,
    onConfirm : function(){
      if(yes_callfunc){
        yes_callfunc();
      }
    },
    onCancel : function(){
      if(no_callfunc){
        no_callfunc();
      }
    }
  });
}

var formMsg = function(formText){
	new $.flavr({
        title       : '账号密码登陆',
        iconPath    : 'flavr/images/icons/',
        icon        : 'email.png',
        content     : 'HTML form example',
        dialog      : 'form',
		formId		: 'btnSubmit',
        form        : { content: formText, method: 'post' },
        onSubmit    : function( $container, $form ){
            //alert( $form.submit() );
            //return false;
        }
    });
}

function getMedical(obj, pid){
	var url = "/core/product.findShopsByPidAndCity.do";
	var data = {"city" : obj.value, "pid" : pid};
	$(".peobox").find(".shop option:first").text("请选择");
	$(".peobox").find(".shop option:not(:first)").remove();
	$(".peobox").find(".address").val("");
	$("#cal").val("");
	$.getMyJSON(url,data,function(data){
		if(data.returncode == 0){
			for(var k in data.data[0].shop[0]){
				$(".peobox").find(".shop").append("<option value="+k+">"+data.data[0].shop[0][k]+"</option>");
			}
			$(".peobox").find("input[name='address']").val(JSON.stringify(data.data[0].address));
		}
	});
}
function getAddress(obj){
	$("#cal").val("");
	$(".peobox").find(".address").val("");
	var address = JSON.parse($(".peobox").find("input[name='address']").val());
	for(var k in address[0]){
		if(k == obj.value){
			$(".peobox").find(".address").val(address[0][k]);
		}
	}
	$("#cal").attr("data", obj.value);
}