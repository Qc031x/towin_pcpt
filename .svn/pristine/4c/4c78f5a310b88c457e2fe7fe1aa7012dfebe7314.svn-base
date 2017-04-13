<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="/WEB-INF/content/public/tags.jsp" %>
    <title>体检机构列表</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name = "format-detection" content = "telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <script type="text/javascript" src="/js/jquery.js"></script>
    <script type="text/javascript" src="/js/rem.js"></script>
    
    <link rel="stylesheet" href="/css/public.css"/>
    <link rel="stylesheet" href="/css/jigou_list.css"/>
    
</head>
<body>
<div id="main" class="main" style="height: auto;">
	<input type="hidden" id="showChangeCity" value="true">
    <div class="header">

        <div class="header_left">
            <a href="/">返回</a>
        </div>
        <div class="header_midd">
            <span class="activeR"><a href="/core/shop.toEntityShopListPage.do" >体检机构</a></span>
            <span><a href="/core/product.toProductListPage.do" >体检套餐</a></span>
        </div>
        <div class="header_right" onclick="changeMenu()">
            <div class="header_rbg"></div>
               <span>导航</span>
        </div>
    </div>

    <%@include file="/WEB-INF/content/public/top_menu.jsp" %>

    <div class="nav">
        <div class="navcon">
            <ul>
                <li class="nav_qc sel">
                    <a href="javascript:void(0)" id="searchArea">${cityname_pcpt}</a>
                    <img src="/images/nav_down.png" alt=""/>
                </li>
                <li class="nav_yiyuan sel">
                    <a href="javascript:void(0)" id="searchYiyuan">全部医院</a>
                    <img src="/images/nav_down.png" alt=""/>
                </li>
                <li class="nav_paixu sel">
                    <a href="javascript:void(0)" id="searchPaixu">综合排序</a>
                    <img src="/images/nav_down.png" alt=""/>
                </li>
                <li class="nav_distance">
                    <a href="javascript:void(0)">离我最近</a>
                    <img src="/images/nav_down.png" alt=""/>
                </li>
            </ul>
        </div>
    </div>

    <div class="filter-cont_mask"></div>
    <div class="filter-container">
        <div class="area_box_qc">
            <div class="area_box">
                <div class="area_box_list left_side">
                    <ul>
                        <li class="current"><a href="javascript:;">区域</a></li>
                        <li class="" onclick="goSearchss('countyId','')"><a href="#">全市</a></li>
                        <c:forEach items="${countryList}" var="mapItem">
                        	<li class="" onclick="goSearchss('countyId','${mapItem['COUNTRYID']}')" id="qy${mapItem['COUNTRYID']}" tjz="${mapItem['COUNTRYID']}"><a href="#" class="hopsid">${mapItem['COUNTRY']}</a> </li>
                        </c:forEach>
                    </ul>
                    <input id="countyId" type="hidden" value="${cgVariable.countyid}" />
                </div>
            </div>
        </div>
        <div class="area_box_yiyuan">
            <div class="area_box">
                <div class="area_box_list left_side" style="height: auto;">
                    <ul>
                        <li class="current"><a href="#">医院级别</a></li>
                        <li class="" onclick="goSearchss('categoryId','')"><a href="#">全部</a></li>
                        <c:forEach items="${branchTypeList}" var="mapItem">
		        			<li class="" onclick="goSearchss('categoryId','${mapItem['KEY']}')" id="lb${mapItem['KEY']}" tjz="${mapItem['KEY']}"><a href="#" class="hopsid">${mapItem['NAME']}</a> </li>
                        </c:forEach>
                    </ul>
                    <input id="categoryId" type="hidden" value="${cgVariable.categoryidFour}" />
                </div>
            </div>
        </div>
        <div class="area_box_paixu">
            <div class="area_box">
                <div class="area_box_list left_side" style="height: auto;">
                    <ul>
                        <li class="current"><a href="#">排序</a></li>
                        <li class="" onclick="goSearchss('paixu','')"><a href="#">综合排序</a></li>
                        <li class="" onclick="goSearchss('paixu','countssdesc')"><a href="#" id="countss">预约量</a></li>
                        <li class="" onclick="goSearchss('paixu','commentdesc')"><a href="#" id="comment">评论</a></li>
                    </ul>
            		<input type="hidden" id="paixu" value="${cgVariable.paixu}">
                </div>
            </div>
        </div>
    </div>
    <div style="height:1.8rem;width:100%;"></div>
    <div class="weizhi">
        <div class="wz_bread">
            <a href="javascript:void(0)">${sessionScope.CITY}-${sessionScope.DISTRICT}-${sessionScope.ADDRESS}</a>
        </div>
    </div>
    
    <div class="tjjg_list">
        <ul class="jigou_list" id="jigouInfoUl">
        
        </ul>
        
        <script id="jigouInfo" type="text/x-jsrender">
			 {{for}}
				<li class="jigou_list_con" onclick="goMECDetail('introduce', {{:ESID}})">
            		<input type="hidden" id="currentPage" value="1"/>
            	<div class="jigou_img">
					<img  src="${ctxImg}/shop/{{:CSID}}/{{:ESID}}/{{:IMG}}" onerror="this.src='/images/default_img.png'"/>
                </div>
                <div class="jigou_intro">
                    <div class="jg_inro1">【{{:BNAME}}】{{:ENAME}}</div>
                    <div class="jg_inro2">{{:TYPENAME}}</div>
                    <div class="jg_inro3">
						<span class="distance">{{:DISTANCE}}<em>km</em></span>
						<input type="hidden" class="lat" value="{{:LAT}}"/>
						<input type="hidden" class="lgt" value="{{:LGT}}"/>	
                        <span>{{:COUNTYNAME}}</span>
						{{if MINPRICE != 0}}
							<span>{{:MINPRICE}}元起</span>
						{{else}}
							<span></span>
		                {{else}}
		                {{/if}}
                    </div>
                </div>
            </li>
			 {{/for}}
		</script>
        
	 <div class="loadMore" onclick='clickMoreData()' loads="1">
            <span>点击加载更多</span>
        </div>
    </div>
    </div>
</div>

    <%@include file="/WEB-INF/content/public/footer.jsp" %>

</body>
<!-- 加载更多组件 -->
<script type="text/javaScript" src="/js/jsrender.js"></script>
<script type="text/javaScript" src="/js/cacheJs/shopCacheControl.js"></script>
<script type="text/javascript" src="/js/commonLib.js"></script>
 	
<script>
    $(function(){
        countyId = '${cgVariable.countyid}';
        if(''!=countyId) $("#searchArea").html($("#qy"+countyId).html());
        
        categoryId = '${cgVariable.categoryidFour}';
        if(''!=categoryId) $("#searchYiyuan").html($("#lb"+categoryId).html());
        
        paixu = '${cgVariable.paixu}';
        if(''!=paixu) $("#searchPaixu").html($("#"+paixu.substring(0,7)).html());
        
        distance = '${distance}';
        if('1' == distance) {
        	$(".nav_distance").addClass("active");
        }
        
    	ajaxLoadList(${medList},"entityShop","jigouInfo",$("#jigouInfoUl"),'${pages.totalPages}',"/core/shop.ajaxMedLoadMore.do",'${pages.totalRows}');
    	
        $(".header_right").click(function(){
            $(this).children().toggleClass("rbgchange");
            $(".linklist").slideToggle(200).css("position","fixed");
        });
        var idx = 0;
        $(".sel").click(function () {
            if ($(".sel.active").index() == $(this).index()) {
                if ($(".filter-container").height() > 0) {
                    $(this).removeClass("active");
                } else {
                    $(this).addClass("active");
                }
                if (idx==0) {
                    $(this).removeClass("active");
                    idx++;
                } else if(idx==1){
                    $(this).addClass("active");
                    idx--;
                }
            } else {
                $(".sel.active").removeClass("active");
                $(this).addClass("active");
                if(idx==1)idx--;
            }
            var f = $(this).index();
            if ($(".filter-container").children().eq(f).is(":visible")) {
                $(".filter-cont_mask").hide();
                $(".filter-container").children().eq(f).slideUp(500);
            }else {
                $(".filter-container").children().eq(f).siblings().slideUp(200);
                $(".filter-container").children().eq(f).slideDown(500);
                $(".filter-cont_mask").css("overflow","auto").show();
            }
            if(f==3){
                $(".filter-cont_mask").hide();
                $(".filter-container").children().hide();
            };
        });

        $(".nav_distance").click(function(){
        	if(distance == 0){
         	$(this).addClass("active");
         	distance = 1;
        	} else {
        		$(this).removeClass("active");
         	distance = 0;
        	}
        	goSearchss("distance", distance);
        });
        
        $(".filter-cont_mask").click(function(){
            $(this).hide();
            $(".filter-container").children().slideUp(500);
            $(".sel.active").removeClass("active");
        });
        //alert(countyid+"-"+categoryId+"-"+paixu)
        
        positioning();
    });
    
    function goSearchss(key,val){
    	if("countyId" == key) countyId = val;
    	if("categoryId" == key) categoryId = val;
    	if("paixu" == key) paixu = val;
    	if("distance" == key) distance = val;
    	window.location.href="/core/shop.toEntityShopListPage.do?cgVariable.countyid="+countyId+"&cgVariable.categoryidFour="+categoryId+"&cgVariable.paixu="+paixu+"&distance="+distance;
    }
    
    function goMECDetail(pageType, esid){
    	window.location.href= "/core/shop.toBranchDetails.do?esid="+esid;
    }
    
    function toRadians(d) {  return d * Math.PI / 180;}
    function getDistance(lat1, lng1, lat2, lng2) { 
        var dis = 0;
        var radLat1 = toRadians(lat1);
        var radLat2 = toRadians(lat2);
        var deltaLat = radLat1 - radLat2;
        var deltaLng = toRadians(lng1) - toRadians(lng2);
        var dis = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2)));
        return (dis * 6378137 / 1000).toFixed(2);
    } 
    
    function getDistinct(){
    	var lat, lng;
		$(".jigou_list_con").each(function(){
			lat = $(this).find(".lat").val();
			lng = $(this).find(".lgt").val()
			$(this).find(".distance").text(getDistance(lat, lng, locationPoint_lat2, locationPoint_lng2)+"km")
		});
    }
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=5a3ac451ae5ee05db20ca3fa4e8b0ac3"></script>
<script type="text/javascript">
var locationPoint_lat2, locationPoint_lng2;
function positioning(){
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
	    			locationPoint_lat2 = pt2.lat;
	    			locationPoint_lng2 = pt2.lng;
	    			//$("body").append("<br>GPS坐标转换成百度坐标：  " + locationPoint_lng2 + ", " + locationPoint_lat2);

	    			var geoc = new BMap.Geocoder();
	    			geoc.getLocation(pt, function(rs){
	    				var addComp = rs.addressComponents;
	    				$(".wz_bread").find('a').text(addComp.city + "-" + addComp.district + "-" + addComp.street);
	    				//var address = addComp.province + "-" +  addComp.city + "-" + addComp.district + "-" + addComp.street + "-" + addComp.streetNumber;
	    				//$("body").append("<br>地址： " + address);
	    				var url = "/core/shop.setLocationPoint.do";
	    				var data = {"locationPoint_lng":locationPoint_lng2, "locationPoint_lat":locationPoint_lat2,"city":addComp.city, "district":addComp.district, "address":addComp.street}
	    				$.getMyJSON(url, data, function(data){
	    					/* window.location.reload(true); */
	    					getDistinct();
	    				});
	    				
	    			}); 
	    			
	              }
	            })
			
		}
		else {
			alert('failed'+this.getStatus());
			/* window.location.reload(true); */
		}
	},{enableHighAccuracy: true});
}
</script>
</html>