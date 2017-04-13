$(".nav-mid ul li").click(function() {
	$(".nav-mid ul li").toggleClass('myActive')
})
$(".nav-right").on("click", function() {
	if(!$(this).hasClass("active")) {
		$(this).addClass("active");
		$("#model_box2").css("display", "block");
		//$("body").css({ "overflow": "hidden", "height": "100%", "position": "fixed", "left": "0", "top": "0", "right": "0", "bottom": "0" });
	} else {
		$(this).removeClass("active");
		$("#model_box2").css("display", "none");
		//$("body").css({ "overflow": "auto", "height": "auto", "position": "static" });
	}
	$("#model_box2").click(function() {
		$(this).css("display", "none");
		//$("body").css({ "overflow": "auto", "height": "auto", "position": "static" });
		$(".nav-right").removeClass("active");
	});

});