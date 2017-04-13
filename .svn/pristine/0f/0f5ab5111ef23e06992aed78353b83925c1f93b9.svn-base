(function ($) {
	$("#makePoint").sgfmform({
		btnSubmit : "#subRe",
	 	ajaxurl 	: "/core/reservation.doLoginRs.do",  
	 	tiptype 	: 1,
	 	submittype  : 2,
	 	callback    : function(data,url){
	 		if(data.returncode == 0){
	 			postwith("/core/reservation.submitOnlineTreservation.do",{"sReservation.card" : data.data[0], "sReservation.password" : data.data[1], "sReservation.pid" : data.data[2], "TK" : data.data[3]});
	 		}else{
	 			alertMsg({"type" : "3", "content" : data.errmsg, "close" : true});
	 		}
	 	}
   });
	
	$(".delete1").click(function(){
		$(this).parents(".xiang").find("input:eq(0)").val("").focus();
	});
	
	$("#saveCardRe").sgfmform({
	 	ajaxurl 	: "/core/reservation.doCreateNewReservation.do",  
	 	tiptype 	: 1,
	 	submittype  : 2,
	 	postonce	: true,
	 	callback    : function(data,url){
	 		if(data.returncode == 0){
	 			//alertMsg({"type" : "1", "content" : "预约单提交成功!", "close" : true});
	 			//setTimeout("window.location.href='/content/reservation.rs_login.do'", 2000);
	 			confirmMsg("预约单提交成功！您可以", function(){window.location.href='/content/reservation.rs_login.do'}, function(){window.location.href='/core/user.findUserReservation.do'}, "再次预约", "查看我的预约单");
	 		}else{
	 			alertMsg({"type" : "3", "content" : data.errmsg, "close" : true});
	 		}
	 	}
   });

	
})(jQuery);


