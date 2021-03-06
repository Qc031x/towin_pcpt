<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/content/public/tags.jsp" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>体检卡</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="shortcut icon" href="/images/icon.ico" />
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/css/nav.css" />
		<link rel="stylesheet" type="text/css" href="/css/list.css" />
		<link rel="stylesheet" type="text/css" href="/css/tw_dialog.css" />
		
		<script type="text/javascript" src="/js/jquery.min.js"></script>
		
		<script src="/js/jsrender.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="/js/commonLib.js"></script>
		<script type="text/javascript" src="/js/tw_dialog.js"></script>
		
		<script type="text/javascript">
		var param={};
		$(function(){
	    	//ajaxLoadList(${cardList},"product","cardInfo",$(".cardInfoDiv"),'${pages}',"/core/card.ajaxCardLoadMore.do",'${totalCount}');
	    	getProductType();
	    	getCardData("");
	    	
	    	$("#search li").click(function(){
	    		if($(this).hasClass("tab-bar")){
	    			$(this).addClass("tab-active").siblings("li").removeClass("tab-active");
	    			param={"hosptType":$(this).attr("hosptType")};
	    			getCardData(param);
	    			$("#model_box1").hide();
	    			$(".tab-more").removeClass("active");
	    			$("#model_box1 .indexNav ul li").removeClass("tab-active");
	    		}else if($(this).hasClass("tab-all")){
	    			$(this).addClass("tab-active").siblings("li").removeClass("tab-active");
	    			param={};
	    			getCardData(param);
	    			$("#model_box1").hide();
	    			$(".tab-more").removeClass("active");
	    			$("#model_box1 .indexNav ul li").removeClass("tab-active");
	    		}
	    	})
			 	
		})
		var cardList;
		var thisPageNo;
		var totalPages;
		var totalRows;
		var loadNum=1;
		function renderData(data,params){
			if(data == null || data==""){
				$(".page-more").hide();
				return;
			}else{
				$(".page-more").show();
			}
			cardList=data;
			var pageCount=data.length;//当前2页的体检卡数量
			var thisPageData=new Array();
			if(loadNum==1){
				if(pageCount >0){
					for(var i=0;i<10;i++){
						thisPageData.push(data[i]);
					}
				}else{
					thisPageData=null;
				}
				loadNum=2;
			}else{
				//alert(totalPages+","+thisPageNo)
				for(var i=10;i<20;i++){
					thisPageData.push(data[i]);
				}
				loadNum=1;
				thisPageNo++;
				if(params == null || params=='' || params=="undefined"){
					params={};
				}
				params["pageNo"]=thisPageNo;
				console.info(params)
				$.getMyJSON("${ctx}/core/card.ajaxLoadCardList.do", params, function(data){
					cardList=data.data[0].list;
				});
				
			}
			console.info(thisPageData)
			var html = $("#cardInfo").render(thisPageData);
			$(".cardInfoDiv").append(html);
			
			if(totalRows==$(".cardInfoDiv li").length){
				$(".page-more").hide();
			}
		}
		function getCardData(params){
			$(".cardInfoDiv").html("");
			$.showLoading("正在加载");
			$.getMyJSON("${ctx}/core/card.ajaxLoadCardList.do", params, function(data){
				totalPages=data.data[0].totalPages;
				thisPageNo=data.data[0].pageNo;
				totalRows=data.data[0].totalRows;
				loadNum=1;
				renderData(data.data[0].list,params);
				$.closeLoading();
			});
		}
		function nextPage(){
			renderData(cardList,param);
		}
		function getProductType(){
			$.getMyJSON("${ctx}/core/card.getProductType.do", null, function(data){
				var pthtml="";
				for(var i=0;i<data.data[0].length;i++){
					pthtml+='<li key='+data.data[0][i].KEY+'>'+data.data[0][i].NAME+'</li>'
				}
				
				var ys=data.data[0].length%3;
				if(ys>0){
					for(var j=0;j<3-ys;j++){
						pthtml += '<li></li>'
					}
				}
				$("#model_box1 .indexNav ul").html(pthtml);
				$("#model_box1 .indexNav ul li").click(function(){
					$(this).addClass("tab-active").siblings("li").removeClass("tab-active");
					var params={"prodType":$(this).attr("key")};
					getCardData(params);
					$("#model_box1").hide();
					$(".tab-more").removeClass("active").addClass("tab-active").siblings("li").removeClass("tab-active");
				})	   
			});
		}
		</script>
		<style type="text/css">
			.page-content .clearfix:before{
				margin-top: 46px;
			}
			.page-content ul li p.title {
				white-space: nowrap;
			    overflow: hidden;
			    text-overflow: ellipsis;
			    font-size: 1.4rem;
			}
			.cardDataInfo{
				position: absolute;
			    top: 0;
			    padding: 0.2rem;
			    width: 100%;
			    color: #ffffff;
			}
			.cardDataInfo .card-tag{
			    width: 3.3rem;
			    height: 1.5rem;
			    margin: 0px 10%;
			    text-align: center;
			    font-size: 0.7rem;
			    line-height:0.8rem;
			    padding:3% 0;
    			
			}
			.cardDataInfo .card-title{
			   margin-top: 25%;
			   padding: 0 0.5rem;
			   font-size: 1rem;
			   white-space: nowrap;
			    overflow: hidden;
			    text-overflow: ellipsis;
			}
			.cardDataInfo .card-subtitle{
			   padding: 0 0.5rem;
			   font-size: 0.8rem;
			   white-space: nowrap;
			   overflow: hidden;
			   text-overflow: ellipsis;
			}
			    
		</style>
	</head>

	<body>
		<input type="hidden" id="showChangeCity" value="true">
		<!--导航栏-->
		<%@include file="/WEB-INF/content/public/card_top_menu.jsp" %>
		<div class="page">
			<!--内容在这里写-->
			<div class="page-tab">
				<ul id="search">
					<li class="tab-all tab-active">全部</li>
					<li class="tab-bar" hosptType="others">医院卡</li>
					<li class="tab-bar" hosptType="zytjjg">专业体检中心卡</li>
					<li class="tab-more">
						<span>更多</span>
						<img src="/images/down.png" />
					</li>
				</ul>
				<div id="model_box1">
					<div class="indexNav">
						<ul class="more clearfix">
							
						</ul>
					</div>
				</div>

			</div>
			<div class="page-content">

			<ul class="clearfix cardInfoDiv"></ul>
		
	<script id="cardInfo" type="text/x-jsrender">		
 	  {{for}}
		  <li>
			<a href="/core/product.toProductDetail.do?cgVariable.pid={{:PID}}&isCard=1">	
			  <img src="/cardImg/{{:CAR_IMG}}" />
			  <div class="cardDataInfo">
				<div class="card-tag">
					<span>{{:LABEL}}</span>
				</div>
				<div class="card-title">
					<span>{{:MAIN_TITLE}}</span>
				</div>
				<div class="card-subtitle">
					<span>{{:SUB_TITLE1}}</span>
				</div>
				<div class="card-subtitle">
					<span>{{:SUB_TITLE2}}</span>
				</div>
			  </div>
		  	  <p class="title">{{:PNAME}}</p>
			  <p class="content"><span>￥{{:SHOP_PRICE}}</span><span>已售：100</span></p>
			</a>  
		</li>
 	  {{/for}}
	</script>
				
				<p class="page-more" onclick="nextPage();">
					<span>点击加载更多</span>
					<img src="/images/more.png" />
				</p>
			</div>
		</div>
		<div style="width:100%;height:0.3rem;"></div>
	</body>
<script src="/js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/list.js" type="text/javascript" charset="utf-8"></script>
</html>