var appId,timestamp,nonceStr,signature;
$.getMyJSON("/core/shop.toWXmap.do", {"esid":$("#esid").val()}, function(data){
	console.info(data.data[0]);
	appId=data.data[0].appId;
	timestamp=data.data[0].timestamp;
	nonceStr=data.data[0].nonceStr;
	signature=data.data[0].signature;

wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: appId, // 必填，公众号的唯一标识
    timestamp: timestamp, // 必填，生成签名的时间戳
    nonceStr: nonceStr, // 必填，生成签名的随机串
    signature: signature,// 必填，签名，见附录1
    jsApiList: [
	    'checkJsApi',
        'onMenuShareTimeline',
        'onMenuShareAppMessage',
        'onMenuShareQQ',
        'onMenuShareWeibo',
        'hideMenuItems',
        'showMenuItems',
        'hideAllNonBaseMenuItem',
        'showAllNonBaseMenuItem',
        'translateVoice',
        'startRecord',
        'stopRecord',
        'onRecordEnd',
        'playVoice',
        'pauseVoice',
        'stopVoice',
        'uploadVoice',
        'downloadVoice',
        'chooseImage',
        'previewImage',
        'uploadImage',
        'downloadImage',
        'getNetworkType',
        'openLocation',
        'getLocation',
        'hideOptionMenu',
        'showOptionMenu',
        'closeWindow',
        'scanQRCode',
        'chooseWXPay',
        'openProductSpecificView',
        'addCard',
        'chooseCard',
        'openCard'
    ] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
	
wx.ready(function(){
	//alert("通过权限验证！");
	// config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
	var lat=$("#lat").val();
	var lng=$("#lng").val();
	var txlat,txlng;
	 //转换百度坐标为腾讯坐标
    qq.maps.convertor.translate(new qq.maps.LatLng(lat,lng), 3, function(res){
        //latlng = res[0];
        console.info(res)
        txlat=res[0].lat;
        txlng=res[0].lng;
        $("#showMap").click(function(){
			wx.getLocation({
			    type: 'wgs84', // 默认为wgs84的gps坐标，如果要返回直接给openLocation用的火星坐标，可传入'gcj02'
			    success: function (res) {
			    	wx.openLocation({
			    	      latitude: txlat, // 纬度，浮点数，范围为90 ~ -90
			    	      longitude: txlng, // 经度，浮点数，范围为180 ~ -180。
			    	      name: $(".hosname").text(), // 位置名
			    	      address: $("#address").text(), // 地址详情说明
			    	      scale: 14, // 地图缩放级别,整形值,范围从1~28。默认为最大
			    	      infoUrl: 'http://www.wilia.top' // 在查看位置界面底部显示的超链接,可点击跳转（测试好像不可用）
			    	    });
			    }
			});
        })
    });
	
});

wx.error(function(res){
	alert("微信定位权限验证失败，请刷新页面重试");
// config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
});

	
})

