<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<title>数据重建</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/commonLib.js"></script>
<script>
function createCacheTypeInfo(){
	var url = "/core/cache.createCacheTypeInfo.do";
		$.getMyJSON(url,null,function(data){
			if(data=='true'){
				alert("创建类型缓存成功！");
			}else{
				alert("创建类型缓存失败！");	
			}
		});
}

function createCacheCityInfo(){
	var url = "/core/cache.createCacheCityInfo.do";
		$.getMyJSON(url,null,function(data){
			if(data=='true'){
				alert("创建城市缓存成功！");
			}else{
				alert("创建城市缓存失败！");	
			}
		});
}

function createCacheProductInfo(){
	var url = "${ctx}/core/cache.createCacheProductInfo.do";
		$.getMyJSON(url,null,function(data){
			if(data=='true'){
				alert("创建商品缓存成功！");
			}else{
				alert("创建商品缓存失败！");	
			}
		});
}

function createCacheBranchInfo(){
	var url = "${ctx}/core/cache.createCacheBranchInfo.do";
		$.getMyJSON(url,null,function(data){
			if(data=='true'){
				alert("创建分院缓存成功！");
			}else{
				alert("创建分院缓存失败！");	
			}
		});
}

</script>
<style type="text/css">
.button {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0,0,0,.3);
	-webkit-border-radius: .5em; 
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0,0,0,.2);
	-moz-box-shadow: 0 1px 2px rgba(0,0,0,.2);
	box-shadow: 0 1px 2px rgba(0,0,0,.2);
}
.button:hover {
	text-decoration: none;
}
.button:active {
	position: relative;
	top: 1px;
}
table{
	width:100%;
}
table tr{
	display:block;
	margin:5px 0;
}
</style>
  <body>
  
  	
	 <div style="width:600px;">
	    <div style="width:500px; height:100px; line-height:20px; border:1px #ccc solid; margin:0 auto; display:table-cell; vertical-align:middle;text-align:center;">
			<table>
				<tr><td align=left><strong>类型缓存刷新</strong>	</td></tr>
				<tr><td align=left>1.商品类型缓存</td></tr>
				<tr><td align=left>2.商品年龄缓存</td></tr>
				<tr><td align=left>3.分院类型缓存</td></tr>
			</table>
	    </div>
		<div style="width:50px; display:table-cell; vertical-align:middle; text-align:center; padding:0 20px;">
			<img src="../../../images/jiantou.png" style="magin"></img>
		</div>
	    <div style="width:200px; height:100px; display:table-cell; border:1px #ccc solid; display:table-cell; vertical-align:middle; text-align:center;">
			<button onclick="createCacheBranchInfo();">执行操作</button>	
	    </div>
	 </div>
	 
	 
	 <div style="width:600px;">
	    <div style="width:500px; height:100px; line-height:20px; border:1px #ccc solid; margin:0 auto; display:table-cell; vertical-align:middle;text-align:center;">
			<table>
				<tr><td align=left><strong>城市数据缓存刷新</strong>	</td></tr>
				<tr><td align=left>1.有分院的城市缓存</td></tr>
				<tr><td align=left>2.城市下的区域缓存</td></tr>
				<tr><td align=left>3.城市下的品牌缓存</td></tr>
			</table>
	    </div>
		<div style="width:50px; display:table-cell; vertical-align:middle; text-align:center; padding:0 20px;">
			<img src="../../../images/jiantou.png" style="magin"></img>
		</div>
	    <div style="width:200px; height:100px; display:table-cell; border:1px #ccc solid; display:table-cell; vertical-align:middle; text-align:center;">
			<button onclick="createCacheCityInfo();">执行操作</button>	
	    </div>
	 </div> 
	 
	 <div style="width:600px;">
	    <div style="width:500px; height:100px; line-height:20px; border:1px #ccc solid; margin:0 auto; display:table-cell; vertical-align:middle;text-align:center;">
			<table>
				<tr><td align=left><strong>商品数据缓存刷新</strong>	</td></tr>
				<tr><td align=left>1.商品基础信息缓存</td></tr>
				<tr><td align=left>2.商品所对应的类型缓存</td></tr>
				<tr><td align=left>3.商品的体检卡图片信息缓存</td></tr>
				<tr><td align=left>4.商品的体检项目缓存</td></tr>
				<tr><td align=left>5.商品的所属分院缓存</td></tr>
			</table>
	    </div>
		<div style="width:50px; display:table-cell; vertical-align:middle; text-align:center; padding:0 20px;">
			<img src="../../../images/jiantou.png" style="magin"></img>
		</div>
	    <div style="width:200px; height:100px; display:table-cell; border:1px #ccc solid; display:table-cell; vertical-align:middle; text-align:center;">
			<button onclick="createCacheProductInfo();">执行操作</button>	
	    </div>
	 </div>
  	
  	<div style="width:600px;">
	    <div style="width:500px; height:100px; line-height:20px; border:1px #ccc solid; margin:0 auto; display:table-cell; vertical-align:middle;text-align:center;">
			<table>
				<tr><td align=left><strong>分院缓存刷新</strong>	</td></tr>
				<tr><td align=left>1.分院基础信息缓存</td></tr>
				<tr><td align=left>2.分院环境图片信息缓存</td></tr>
			</table>
	    </div>
		<div style="width:50px; display:table-cell; vertical-align:middle; text-align:center; padding:0 20px;">
			<img src="../../../images/jiantou.png" style="magin"></img>
		</div>
	    <div style="width:200px; height:100px; display:table-cell; border:1px #ccc solid; display:table-cell; vertical-align:middle; text-align:center;">
			<button onclick="createCacheBranchInfo();">执行操作</button>	
	    </div>
	 </div>
	 
  </body>
</html>
