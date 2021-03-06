
var listAdapter = '';

//定义数据结构map类
function Map(){
	this.container = new Object();
}

//定义map的一系列方法
Map.prototype.put = function(key,value){
	this.container[key] = value;
}

Map.prototype.get = function(key){
	return this.container[key];
}

Map.prototype.size = function(){
	var count = 0;
	for(var key in this.container){
		count++;
	}
	return count;
}

//把Map的键存入一个数组
Map.prototype.keySet = function(){
	var keySet = new Array();
	var count = 0;
	for(var key in this.container){
		keySet[count]  = key;
		count++;
	}
	return keySet;
}

//遍历获取Map里面的数据
Map.prototype.getListByPageNo = function(offset,pageNo,pageSize){
	
	var array = this.keySet(); 
	var pageData = '';
	var json = '';

	for(var i in array){
		
		var min = Number(offset*pageSize);
		var max = Number(pageNo*pageSize);
		//alert(this.get(array[i])+"\n"+min+"\n"+max+"\n"+i);
		if(i>=min&&i<max){
			var temp=JSON.stringify(this.get(array[i]));		
			//alert(min+"\n"+max+"\n"+i+"\n"+temp)
			json = json+temp+",";
			if(i == array.length-1){
        		json = json+true+",";
        	}
		}
	}
	json = json.substring(0, json.length-1);
	json = jQuery.parseJSON("["+json+"]");
	return json;
}

function shopFactory(){
	this.pageSize = '';
	this.map = '';
	this.offset = 0;
	this.pageNo = 1;
	//判断是否从db加载还是从js缓存加载
	this.bool=false;
	//模版加载内容id <script id="productInfo" type="text/x-jsrender">
	this.productObj='';
	//页面需要渲染的div名称，模版内容会渲染该div <div class="productInfoDiv"></div>
	this.productDiv='';
	//当前查询条件共有多少页，按照每页xx条计算得出
	this.pages=0;
	//从db加载的页面需要渲染的最新数据
	this.currData='';
	//调用的url地址
	this.requestUrl='';
	//共多少条记录
	this.rowConut=0;

	
	this.initData=function(data,pageSize){
		
		this.initMap(data,pageSize);
		
	}
	
	this.initMap = function(data,pageSize){
	  
		this.pageSize = pageSize;
		this.map = new Map();
		for(var i = 0;i<data.length;i++){
			
			this.map.put("shop_"+i,data[i]);
		}
	}
	
	this.getListByPageNo=function(pageNo){
		//alert(pageNo+"\n"+this.offset+"\n"+this.pageSize)
		var data = this.map.getListByPageNo(this.offset,pageNo,this.pageSize);
		
		this.offset = pageNo;
		return data;
	}
	
	this.ajaxLoadMore=function(){
		//alert($("#condition").val()==undefined)
		//var condition = $("#condition").val()==undefined?$("#condition").val().replace(/(^\s*)|(\s*$)/g, ""); 
		var currentPage = Number($("#currentPage").val()==''?1:$("#currentPage").val())+1;
		//alert(currentPage+" "+this.pages)
		if(this.pages<currentPage){
			$("#currentPage").val(currentPage);
			return false;
		}
		 var url = self.location+"";
		 //如果地址栏没有？号，表示没有参数
		 var parm=url.indexOf("?")==-1?"":url.substring(url.indexOf("?"));
		// alert(this.requestUrl+"\n"+url.substring(url.indexOf("?")));
//		 alert(this.requestUrl)
		 var arrUrl = url.split("//");
		 var start = arrUrl[1].indexOf("/");
		 var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符
		 if(relUrl.indexOf("?") != -1){
			relUrl = relUrl.split("?")[0];
		 }
		 var arrUrl2 = this.requestUrl.split("//");
		 var start2 = arrUrl[1].indexOf("/");
		 var relUrl2 = arrUrl[1].substring(start2);//stop省略，截取从start开始到结尾的所有字符
		 
		 if(relUrl2.indexOf("?") != -1){
			relUrl2 = relUrl2.split("?")[0];
		 }
//		 alert(relUrl)
		 url = this.requestUrl;
		 if(relUrl == relUrl2)
			 url += parm;
		 // var url = self.location+"";
		  //url = this.requestUrl+url.substring(url.indexOf("?"));
		 // var data = {"cgVariable.condition" : condition, "cgVariable.currentPage" : currentPage};
		  var data={"cgVariable.currentPage" : currentPage};
		//alert(url)
		   $.getMyJSON(url, data, function(data){	
			   listAdapter.currData=data;
			  // listAdapter.offset=0;
			//alert(13+"\n"+listAdapter.currData)
			$("#currentPage").val(currentPage);
		});
	}
	//是否显示加载更多链接，根据当前页面和总页码判断
   this.isShowMoreHtml=function(){
		//alert(this.pages+"\n"+Number($("#currentPage").val())+"\n"+listAdapter.bool)&&listAdapter.bool==true
	  // alert($('.productInfoDiv').children('a').length+" "+listAdapter.rowConut);
	// alert($('.productInfoDiv').children().length+" "+listAdapter.rowConut);
	   if(listAdapter.productDiv.children().length==listAdapter.rowConut){
		  $(".loadMore").remove();
	   }
   }
   
   this.removeLastData=function(obj){
	   if(obj[obj.length-1]==true){
			obj.pop();	
		}
	   return obj;
  }
}





//初始化方法
function ajaxLoadList(data,type,tempObj,divObj,pages,url,rowConut){
	if(rowConut==0){
		$(".loadMore").remove();	
		$('<div class="loadMore" ><span>没有查询到数据</span></div>').appendTo(divObj.parent()); 

	  return;
	}
	
   // if(listAdapter==''){
    	listAdapter = new shopFactory();
    //}
    	
	listAdapter.initData(data,8);
	
	var obj = listAdapter.getListByPageNo(1);
	
	obj=listAdapter.removeLastData(obj)
	listAdapter.pageNo = 1;
//	alert(obj.length)
	// beg 渲染数据
	//alert(tempObj+"\n"+divObj)
	  listAdapter.productObj=tempObj;
	  listAdapter.productDiv=divObj;
	  listAdapter.requestUrl=url; 
	  listAdapter.rowConut=rowConut;
      var proListTemp = $.templates("#"+tempObj);
	  var productTemp = proListTemp.render(obj);
	  listAdapter.pages=pages;
	
	  divObj.append(productTemp);
	  listAdapter.isShowMoreHtml();
		//alert("ajaxLoadList"+listAdapter.bool)
	//end 渲染数据
	//return obj;
}

//加载更多方法从缓存调用
function loadMoreList(){
	var pageNo = Number(listAdapter.pageNo+1);
	var obj = listAdapter.getListByPageNo(pageNo);
	listAdapter.pageNo = pageNo;
	obj=listAdapter.removeLastData(obj)
	
	// beg 渲染数据
	//alert(tempObj+"\n"+divObj)
      var proListTemp = $.templates("#"+listAdapter.productObj);
	  var productTemp = proListTemp.render(obj);	
	  listAdapter.productDiv.append(productTemp);
	
	  listAdapter.isShowMoreHtml();
	  getDistinct()
	//end 渲染数据
//	return obj;
}


function clickMoreData(){
	//alert("clickMoreData1"+listAdapter.bool)
	//第一次点击加载更多时候
	if(listAdapter.bool == false){
		//alert(11)
	//	加载更多方法从缓存调用
		loadMoreList();
		listAdapter.bool = true;
	}
	else if(listAdapter.bool == true){	
		//alert(22)
		 ajaxLoadList(listAdapter.currData,"entityShop",listAdapter.productObj,listAdapter.productDiv,listAdapter.pages,listAdapter.requestUrl,listAdapter.rowConut);
		// listAdapter.bool = false;
	}
	//从缓存调用完之后，bool为true后，马上从db加载
	if(listAdapter.bool==true){
	//	alert(33)
		listAdapter.ajaxLoadMore();
		//alert(listAdapter.currData)
	}
//	dwmap();
	//alert("clickMoreData2"+listAdapter.bool)
}


