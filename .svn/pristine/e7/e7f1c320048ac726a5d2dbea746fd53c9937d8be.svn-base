$(".tab-more").on("click", function() {
	if(!$(this).hasClass("active")) {
		$(this).addClass("active");
		$("#model_box1").show();
		//$("body").css({ "overflow": "hidden", "height": "100%", "position": "fixed", "left": "0", "top": "0", "right": "0", "bottom": "0" });
	} else {
		$(this).removeClass("active");
		$("#model_box1").hide();
		//$("body").css({ "overflow": "auto", "height": "auto", "position": "static" });
	}
	$("#model_box1").click(function() {
		$(this).css("display", "none");
		//$("body").css({ "overflow": "auto", "height": "auto", "position": "static" });
		$(".tab-more").removeClass("active");
	});

});