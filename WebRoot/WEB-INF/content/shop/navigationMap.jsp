<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<%@include file="/WEB-INF/content/public/tags.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=5a3ac451ae5ee05db20ca3fa4e8b0ac3&v=1.0"></script>
<title>导航</title>
<script type="text/javascript">
	/*start|end：（必选）
	{name:string,latlng:Lnglat}
	opts:
	mode：导航模式，固定为
	BMAP_MODE_TRANSIT、BMAP_MODE_DRIVING、
	BMAP_MODE_WALKING、BMAP_MODE_NAVIGATION
	分别表示公交、驾车、步行和导航，（必选）
	region：城市名或县名  当给定region时，认为起点和终点都在同一城市，除非单独给定起点或终点的城市
	origin_region/destination_region：同上
	 */
	var start = {
		name : "${sessionScope.CITY}${sessionScope.DISTRICT}${sessionScope.ADDRESS}"
	}
	var end = {
		name : "${info['POINT_CITY']}${info['POINT_COUNTY']}${info['POINT_ADDRESS']}"
	}
	var opts = {
		mode : BMAP_MODE_TRANSIT,
		origin_region : "${sessionScope.CITY}",
		destination_region : "${info['POINT_CITY']}"
	}
	var ss = new BMap.RouteSearch();
	ss.routeCall(start, end, opts);
</script>
</head>
<body>
	<div id="allmap"></div>
</body>
</html>
