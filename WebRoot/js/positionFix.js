

var position = {
	fix :function(){
	//底部position:fix修复
	$("input[posiFix]").focus(function(){
		$(".fix").css({"position" : "absolute", "bottom" : $(window).height() - $(document.body).height()});
		//头部position:fix修复
		$(".posiFixTop").css({"position" : "absolute", "top" : "0"});
	});
	$("input[posiFix]").blur(function(){
		$(".fix").css({"position" : "fixed", "bottom" : "0"});
		//头部position:fix修复
		$(".posiFixTop").css("position", "fixed");
	});
	
	$(".selFix").focus(function(){
		$(".fix").css({"position" : "absolute", "bottom" : $(window).height() - $(document.body).height()});
		$(".posiFixTop").css({"position" : "absolute", "top" : "0"});
	});
	
	$(".selFix").blur(function(){
		$(".fix").css({"position" : "fixed", "bottom" : "0"});
		$(".posiFixTop").css("position", "fixed");
	});
	
	/*$("select[posiFix]").click(function(){
		$(".fix").css("position", "absolute");
	});
	$("select[posiFix]").change(function(){
		$(".fix").css("position", "fixed");
	});*/
	
	}
};
jQuery(function(){
	position.fix();
});




