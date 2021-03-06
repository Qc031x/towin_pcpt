<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
<%@include file="/WEB-INF/content/public/tags.jsp" %>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<script type="text/javascript" src="/js/jquery.js"></script>
	<script type="text/javascript" src="/js/rem.js"></script>
	<link rel="stylesheet" href="/css/public.css"/>
	<link rel="stylesheet" href="/css/taocan_list.css"/>
	
	<script type="text/javaScript" src="/js/jsrender.js"></script>
	<script type="text/javascript" src="/js/cacheJs/productCacheControl.js"></script>
	<script type="text/javascript" src="/js/commonLib.js"></script>
	<script type="text/javaScript" src="/js/conditions.js"></script>
	
    <title>套餐列表</title>
    <script type="text/javascript">

    var productType='${cgVariable.categoryidOne}';
    var productAge='${cgVariable.categoryidTwo}';
    var prosex='${cgVariable.prosex}';
    var brandId='${cgVariable.brandId}';
    var price='${cgVariable.price}';
    var country='';
    var hospitalLevel='';
    $(function(){
    	ajaxLoadList(${productList},"product","productInfo",$(".productInfoDiv"),'${pages}',"/core/product.ajaxProductLoadMore.do",'${totalCount}');
        $(".header_right").click(function(){
            $(this).children().toggleClass("rbgchange");
            $(".linklist").slideToggle(200).css("position","fixed");
        });
        
        
       
        var idx = 0;
        $(".navcon li").click(function () {
               if ($(".navcon .active").index() == $(this).index()) {
                   if (idx==0) {
                       $(this).removeClass("active");
                       idx++;
                   } else if(idx==1){
                       $(this).addClass("active");
                       idx--;
                   }
               } else{
                   $(".navcon .active").removeClass("active");
                   if($(this).hasClass("sxbtn")){
                	   $(this).addClass("active");
                   }
                   if(idx==1)idx--;
               }
               if($(".sxbtn").hasClass("active")){
                   $(".saixuantjbg1").slideDown();
                   $(".prolist").hide();
                   $(".bottom").hide();
               }else{
                   $(".saixuantjbg1").slideUp();
                   $(".prolist").show();
                   $(".bottom").show();
                   
                   if($(this).hasClass("sxbtn")){
                	   controlTopCss();
                   }
                   
               }
        });
        
        controlTopCss();
    });
   
    function doPaixu(type){
    	$(".navcon ul li a").each(function(){
    		if($(this).attr("paixu") == type){
    			var order=$(this).attr("class");
    			if(order.indexOf("asc")>=0){
    				order="desc";
    			}else if(order.indexOf("desc")>=0){
    				order="asc";
    			}
    			$("#paixu").val(type+order);
    		}else if(type==""){
    			$("#paixu").val("");
    		}
    		goSearch(type);
    	});
    }
    function goSearch(type){
    	if(type==""){
    		window.location.href = "/core/product.toProductListPage.do";
    		return;
    	}
    	var paixu = $("#paixu").val();
		var categoryidOne = $("#categoryidOne").val();
		var categoryidTwo = $("#categoryidTwo").val();
		var brandId = $("#brandId").val();
		var price = $("#price").val();
		var prosex = $("#prosex").val();
		//var currentPage = $("#currentPage").val();
		
		window.location.href = "/core/product.toProductListPage.do?cgVariable.paixu="+paixu
				+"&cgVariable.categoryidOne="+categoryidOne
				+"&cgVariable.categoryidTwo="+categoryidTwo
				+"&cgVariable.brandId="+brandId
				+"&cgVariable.price="+price
				+"&cgVariable.prosex="+prosex;
				//+"&cgVariable.currentPage="+currentPage;
}
    function controlTopCss(){
    	 $(".navcon ul li a").each(function(){
    			if($(this).attr("paixu")==$("#paixu").val().substring(0,7)){
    				$(this).removeClass().addClass($("#paixu").val().substring(7));
    			}
    			})
    	        $(".navcon ul li a").each(function(){
    	    		if($(this).attr("paixu")+$(this).attr("class") == $("#paixu").val()){
    	    			//$(this).attr("class",$("#paixu").val().subString(7));
    	    			//alert($("#paixu").val());
    	    			$(this).parent("li").addClass("active");
    	    			$(this).parent("ul").siblings("li").removeClass("active");
    	    			$(this).parent("ul").siblings("li").removeClass("desc").addClass("asc");
    	    			if($("#paixu").val().substring(7)=="asc"){
    	    				$(this).parent("li").find("img").attr("style","");
    	    			}else{
    	    				$(this).parent("li").find("img").attr("style","transform: rotate(0deg);");
    	    			}
    	    		}else{
    	    			//alert($(this).attr("paixu"))
    	    			if($(this).attr("class")=="all" && $("#paixu").val()==""){
    	    				$(".all").parent("li").addClass("active");
    	    			}
    	    		}
    	    	});
    };
  
    </script>
</head>
<body>
<div id="main" class="main" style="height: auto;">
	<input type="hidden" id="showChangeCity" value="true">

    <div class="header">
        <div class="header_left">
            <a href="javascript:history.go(-1);">返回</a>
        </div>
        <div class="header_midd">
            <span class="tjjg"><a href="/core/shop.toEntityShopListPage.do">体检机构</a></span>
            <span class="activeR"><a href="/core/product.toProductListPage.do">体检套餐</a></span>
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
                <li><a class="all" href="javascript:doPaixu('')">全部</a></li>
                <li><a class="desc" paixu="countss" href="javascript:doPaixu('countss')">销量</a><img src="/images/nav_down.png" alt=""/></li>
                <li><a class="desc" paixu="pricess" href="javascript:doPaixu('pricess')">价格</a><img src="/images/nav_down.png" alt=""/></li>
                <li class="sxbtn"><a href="javascript:void(0)">筛选</a><img src="/images/nav_down.png" alt=""/></li>
            </ul>
            <input type="hidden" id="paixu" value="${cgVariable.paixu}"/>
            <input type="hidden" id="currentPage" value="${cgVariable.currentPage}"/>
        </div>
    </div>
    <div class="saixuantjbg1">
        <div class="tiaojiancon">
        	<div class="tiaojian" id="conditionDiv"></div>
        </div>
    </div>

    <div style="width:100%;height:1.8rem;"></div>

    <div class="prolist">
    
    
    <div class="productInfoDiv"></div>
    <script id="productInfo" type="text/x-jsrender">		
 	{{for}}
		<div class="list">
        <a style="overflow: hidden; display: block;" href="/core/product.toProductDetail.do?cgVariable.pid={{:PID}}">
            <div class="imgbox">
				<img src="${ctxImg}/product/{{:CSID}}/{{:IMG}}" onerror="this.src='/images/default_img.png'">
                {{if CUXIAO == 1}}
					<span class="list_cu">促销</span>
					{{else}}
				{{/if}}
				 {{if DISCOUNT != 0 && DISCOUNT != 10}}
					 <span class="list_zhe"><em>{{:DISCOUNT}}</em>折</span>
					{{else}}
				{{/if}}
               
            </div>
            <div class="info">
                <p class="titlebox">{{:PNAME}}
				{{if SEX == 1}}
			   			(男)
					{{else}}
				{{/if}}
				{{if SEX == 2}}
			   			(已婚女)
					{{else}}
				{{/if}}
				{{if SEX == 4}}
			   			(未婚女)
					{{else}}
				{{/if}}
				</p>
                <p class="info_col">{{:BNAME}}</p>
                <p class="info_bor"><span class="nowpla">{{:SHOP_PRICE}}</span><span class="ordered">已售：<span>{{:COUNT}}</span></span></p>
            </div>
        </a>
        </div>
 	{{/for}}
	</script>
	 <div class="loadMore" onclick='clickMoreData()' loads="1">
            <span>点击加载更多</span>
     </div>
    </div>
    </div>
    <%@include file="/WEB-INF/content/public/footer.jsp" %>
    <script type="text/javascript">
    conditionsUtil("conditionDiv","xzpp,tclx,synl,syxb,jgfw"); 
    </script>
</body>
</html>