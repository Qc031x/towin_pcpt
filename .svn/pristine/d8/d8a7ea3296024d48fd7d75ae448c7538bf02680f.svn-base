<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/commonLib.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5a3ac451ae5ee05db20ca3fa4e8b0ac3"></script>
<script type="text/javascript">
$(function(){
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var pt = r.point;
			var locationPoint_lat = pt.lat;
			var locationPoint_lng = pt.lng;
			//$("body").append("<br>页面获取的坐标(GPS)： " + locationPoint_lng + ", " + locationPoint_lat);
			
			var convertor = new BMap.Convertor();
			var pointArr = [];
	        pointArr.push(pt);
	        convertor.translate(pointArr, 1, 5, function (data){
	        	//console.log(data)
	            if(data.status === 0) {
	            	var pt2 = data.points[0];
	    			var locationPoint_lat2 = pt2.lat;
	    			var locationPoint_lng2 = pt2.lng;
	    			//$("body").append("<br>GPS坐标转换成百度坐标：  " + locationPoint_lng2 + ", " + locationPoint_lat2);

	    			var geoc = new BMap.Geocoder();
	    			geoc.getLocation(pt, function(rs){
	    				var addComp = rs.addressComponents;
	    				var address = addComp.province + "-" +  addComp.city + "-" + addComp.district + "-" + addComp.street + "-" + addComp.streetNumber;
	    				//$("body").append("<br>地址： " + address);
	    				var url = "/core/shop.setLocationPoint.do";
	    				var data = {"locationPoint_lng":locationPoint_lng2, "locationPoint_lat":locationPoint_lat2,"city":addComp.city, "district":addComp.district, "address":addComp.street}
	    				$.getMyJSON(url, data, function(data){
	    					window.location.reload(true);
	    				});
	    				
	    			}); 
	    			
	              }
	            })
			
		}
		else {
			alert('failed'+this.getStatus());
			window.location.reload(true);
		}
	},{enableHighAccuracy: true});
	
})
</script>
</head>
<body>
	跳转中...
</body>
</html>