(function ($) {
	$(".idcard").blur(function(){
		var $this = $(this);
		var value = $this.val();
		if($this.hasClass("age")){
			value = $this.parents(".listinfo").find(".tansfzh").val();
		}
		if(value == "111111111111111111" || value == "111111111111111"){
			$this.parents(".useinfo_input").find(".age").val("0");
			return;
		}
		if(isChinaIDCard(value)){
			var myDate = new Date(); 
			var month = myDate.getMonth() + 1; 
			var day = myDate.getDate();
			var age = myDate.getFullYear() - value.substring(6, 10) - 1;
			if(value.substring(10, 12) < month || value.substring(10, 12) == month && value.substring(12, 14) <= day) {
				age++;
			}
			$this.parents(".rsInfo").find(".age").val(age);
		}
	});
})(jQuery);

function isChinaIDCard(StrNo){
	StrNo = StrNo.toString()  
	var repost = /^[1]{18}$/;
	if(repost.test(StrNo)){return true}
	if(StrNo.length==18){
		var a,b,c   
		if(isNaN(StrNo.substr(0,17))){
			alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); 
			return false
		}
		
		var date = new Date();
		var year = StrNo.substr(6, 4);
		var month = StrNo.substr(10, 2);
		var day = StrNo.substr(12, 2);
		if(year > date.getFullYear() || month > 12 || day > 31){
			alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); 
			return false;
		}
		
		a=parseInt(StrNo.substr(0,1))*7+parseInt(StrNo.substr(1,1))*9+parseInt(StrNo.substr(2,1))*10;   
		a=a+parseInt(StrNo.substr(3,1))*5+parseInt(StrNo.substr(4,1))*8+parseInt(StrNo.substr(5,1))*4;   
		a=a+parseInt(StrNo.substr(6,1))*2+parseInt(StrNo.substr(7,1))*1+parseInt(StrNo.substr(8,1))*6;     
		a=a+parseInt(StrNo.substr(9,1))*3+parseInt(StrNo.substr(10,1))*7+parseInt(StrNo.substr(11,1))*9;     
		a=a+parseInt(StrNo.substr(12,1))*10+parseInt(StrNo.substr(13,1))*5+parseInt(StrNo.substr(14,1))*8;     
		a=a+parseInt(StrNo.substr(15,1))*4+parseInt(StrNo.substr(16,1))*2;   
		b=a%11;   
		if(b==2){
			//最后一位为校验位   
			c=StrNo.substr(17,1).toUpperCase();//转为大写X   
		}else{   
			c=parseInt(StrNo.substr(17,1));
		}   
		     
		switch(b){   
		case 0: if(c!=1){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 1: if(c!=0){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 2: if(c!="X"){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 3: if(c!=9){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 4: if(c!=8){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 5: if(c!=7){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 6: if(c!=6){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 7: if(c!=5){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 8: if(c!=4){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 9: if(c!=3){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false;}break;   
		case 10: if(c!=2){alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); return false}   
		}
		
	}else{
		if(StrNo == ""){
			alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); 
			return false;
		}
		if(!isNaN(StrNo)){
			alertMsg({"type" : "3", "content" : "身份证输入错误!", "close" : true}); 
			return false
		}     
	}   
	
	switch(StrNo.length){   
	   case 15:
		   return true;
	   case 18:
		   return true;
	}   
	return false;
} 