//判断手机屏幕，经单位转化为rem
(function(doc, win) {
	var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function() {
			var clientWidth = docEl.clientWidth;
			if(!clientWidth) return;
			docEl.style.fontSize = 20 * (clientWidth / 320) + 'px';
			if((20 * (clientWidth / 320)) > 40)docEl.style.fontSize = 30+'px';
		};

	// Abort if browser does not support addEventListener
	if(!doc.addEventListener) return;
	win.addEventListener(resizeEvt, recalc, false);
	doc.addEventListener('DOMContentLoaded', recalc, false);
})(document, window);
//全屏宽为16rem