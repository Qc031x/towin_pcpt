// JavaScript Document
(function ($) {
	var instances,
		info={"noData":"暂无数据!","reqErr":"请求失败，返回码：","askError":"请求失效!","loading":"加载中..."},
		fillNum = "0000000000";
	$(document).ready(function(){
		//从页面获取国际化信息		
		if(window.sgfm && window.sgfm.tableInfo){
			$.extend(info, sgfm.tableInfo);
		}
		if(window.sgfm && window.sgfm.fillNum){
			fillNum=sgfm.fillNum;
		}
	});
	//对话框插件的事明
	$.fn.sgfmtable = function(settings){
		
		var isMethodCall = (typeof settings == 'string'),
			args = Array.prototype.slice.call(arguments, 1), 
			returnValue = this;
			
		// 如果是下划线开头的方法不让访问
		if(isMethodCall && settings.substring(0, 1) == '_') { return returnValue; }
		// 如果一个方法的调用执行方法
		if(isMethodCall) {
			var methodReturn;
			this.each(function() {
				if(instances && $.isFunction(instances[settings])){ methodReturn = instances[settings].apply(this,args); };		
				if(methodReturn){returnValue = methodReturn;}
			});
		}
		else {
			settings = init_params(settings);
			this.each(function() {
				//调用初始化的方法
				init.call(this,settings,args[0]);
			});
		}
		// 返加jQuery对象	
		return returnValue;
	};

	//参数初始化
    function init_params(settings){
        if(!settings){settings={};}
        if(!settings.url){settings.url=false;}
        if(!settings.params){settings.params="";}
        if(!settings.haserrmsg){settings.haserrmsg=false;}
		if(typeof settings.isaddrow !== "boolean"){settings.isaddrow=true;}
		if(!settings.ajaxtype){settings.ajaxtype="POST";}
		if(typeof settings.pagenoname !== "string"){settings.pagenoname = "pageNo";}
        if(!$.isFunction(settings.callback)){settings.callback = $.noop;}
		if(!$.isFunction(settings.rowClickCallback)){settings.rowClickCallback = $.noop;}
		if(!$.isFunction(settings.error)){settings.error=function(){return true;};}
        return settings;
    }

	//初始化对话框对象
	function init(settings,callback){
		//获取表头数据
		var $this = $(this),headNames =  get_head.call($this),
		//请求获取数据
		PageClick = function(pageclickednumber){
			var colnum=$this.data("colnum"),$errTd=$("<td align='center'></td>").attr("colspan",colnum),
				pageName = settings.pagenoname,params;
			$this.siblings(".quotes").empty();
			get_tbody($this).children("tr:has(td)").remove();
			$errTd.append("<span class='loading'>"+info.loading+"</span>").appendTo($("<tr></tr>").appendTo(get_tbody($this)));
			
			if(typeof pageName !== "string" || $.trim(pageName) === ""){pageName = "pageNo";}
			if(!isNaN(pageclickednumber)){
				if(typeof settings.params === "string"){
					if(settings.params.indexOf(pageName)!==-1){
						settings.params = settings.params.replace(new RegExp(pageName +"=\\d*&?"),"");
					}
					params = $.trim(settings.params);
					if(settings.params !== ""){params += "&";}
					params += pageName + "=" + pageclickednumber;
				}else{
					if(!$.isPlainObject(settings.params)){settings.params = {};}
					settings.params[pageName] = pageclickednumber;
				}
			}
			get_info.call($this,settings,params,function(json){
				this.removeData("metadata");
				if(json.returncode == 0){
					//填充列表
					if(json.list && json.list.length>0){
						this.data("metadata",json.list);
						get_tbody(this).children("tr:has(td)").remove();
						$.each(json.list,function(i,data){
							var startRow = json.startRow;
							if(isNaN(startRow)){startRow = 0;}
							if(!data.sequence){
								data.sequence = 1 + i + startRow;
							}
							fill_info.call($this,headNames,data,settings,i);
						});
						if(!isNaN(json.totalPages)){
							if(!json.pageNo){json.pageNo = 1;}
							var pagebox =  $("#abc3"),pageNo={};
							if(pagebox.length == 0){
								//if(this.data("hasCheckBox")){
								//	pagebox = this.find(".quotes");
								//}else{
									pagebox = $("<div></div>").addClass("quotes");
									this.after(pagebox);
								//}
							}
							
							pagebox.sgfmpager({ "pagenumber": json.pageNo, "pagecount": json.totalPages, "buttonClickCallback": PageClick,"totalrows":json.totalRows });
							//alert(pagebox.html())
							//$("#abc3").html( pagebox.html());
							pageNo[pageName]=json.pageNo;
							$this.data("pageNo",pageNo);
						}
					}else{
						$("#abc3").html("");
						/*
						 * todo 处理没有数据的错误处理信息
						 */
						 this.find(".loading").text(info.noData).removeClass("loading").addClass("errinfo");
						 if(settings.error.call(this,null,json.returncode,json.errmsg) && settings.haserrmsg){
							 $.sgfmdialog(info.noData);
						 }
					}
				}else{
					/*
					* todo 处理返回的错误信息
					*/
					this.find(".loading").text(json.errmsg).removeClass("loading").addClass("errinfo");
					if(settings.error.call(this,null,json.returncode,json.errmsg) && settings.haserrmsg){
						if(json.errmsg){
							$.sgfmdialog(json.errmsg);
						}else{
							$.sgfmdialog(info.reqErr + json.returncode);
						}
					}
				}
				if($.isFunction(callback)){callback.call(get_tbody(this).get(0),json);}
			});
			$errTd=null;
		};
		PageClick.call($this);
	}
	
	//获取表格主体
	function get_tbody(obj){
		if(obj.children("tbody").length > 0)
		{
			return obj.children("tbody");
		}else{
			return obj;
		}
	}
	
	//获取表格每一列需要存放的内容
	function get_head(){
		var head,headNames = this.data("sgfmhead"),headInfo = {"name":"","rowclick":false,"bindmode":"","align":"","tdclass":false},hasCheckBox=false;
		if(!headNames){
			head = get_tbody(this).children("tr:has(th)");
			headNames = pushHeadInfo.call(head,0,headInfo,(new Array),0);
			//将信息添加到表的数据中
			this.data("sgfmhead",headNames).data("colnum",headNames.length);//.data("hasCheckBox",hasCheckBox);
		}
		return headNames;
	}
	
	//处理头信息
	function pushHeadInfo(eq,headInfo,headNames,startNum,endNum){
		var jObj = this.eq(eq).children("th");
		if(!endNum){endNum=jObj.length}
		for(var i=startNum;i<endNum;i++){
			var col = jObj.eq(i).attr("colspan"),tmpInfo={
				"name" : jObj.eq(i).attr("sgfm-binddata"),
				"rowclick" : jObj.eq(i).attr("sgfm-rowclick"),
				"bindmode" : jObj.eq(i).attr("sgfm-bindmode"),
				"align" : jObj.eq(i).attr("align"),
				"tdclass" : jObj.eq(i).attr("tdclass")},tmpHead={};
			if(headInfo.rowclick=="true"){headInfo.rowclick = true;}else if(typeof headInfo.rowclick === "string"){headInfo.rowclick = false;}
			//if(headInfo.bindmode === "checkbox"){headInfo.hasCheckBox = true;}
			$.extend(tmpHead,headInfo,tmpInfo);
			if(!isNaN(col) && col > 1){
				headNames = pushHeadInfo.call(this,eq+1,tmpHead,headNames,startNum,startNum+col);
				startNum+=col;
			}else{
				headNames.push(tmpHead);
			}
		}
		return headNames;
	}
	
	
	
	//获取列信息，返回true有链接已经发送请求，返回false链不正确没有发送请求
	function get_info(settings,params,callback){
		var type = settings.ajaxtype;
		if(!params){params = settings.params;}
		if(settings.url){
			$.ajax({
				"url": settings.url,
				"context": this,
				"data": params,
				"type": type,
				"cache":false,
				"dataType": "json",
				"success": function(html){
					callback.call(this,html);
				},
				"error": function(jqXHR,textStatus,errorThrown){
					/*
					 * todo 对请求错误信息进行处理
					 */
					 this.find(".loading").text(info.askError).removeClass("loading").addClass("errinfo");
					 if(settings.haserrmsg && settings.error.call(this,jqXHR,textStatus,errorThrown)){
						 $.sgfmdialog(info.askError);
					 }
				}
			});
			return true;
		}else{
			return false;
		}
	}
	
	//通过参数获取到链接地址
	function get_url(settings){
		var url = settings.url,params = settings.params;
		if(typeof url == 'string'){
			url += "?";
			if($.isPlainObject(params)){
				$.each(params, function(key, value){
					url += key + "=" + value + "&";
				});
			}
			url = url.substr(0,url.length-1);
		}else{
			url = false;
		}
		return url;
	}
	
	//填充列表信息
	function fill_info(headNames,json,settings,index){
		var td,td2,tr = $("<tr></tr>").data("metadata",json),isaddrow = settings.isaddrow,
		colclick = json.colclick,tmpcol,colnum = headNames.length,table = this;
		//if(typeof settings.isaddrow === "boolean"){isaddrow = settings.isaddrow;}
		//支持点击的列
		if(typeof colclick == 'string'){
			tmpcol = colclick.split(",");
			colclick = "{";
			$.each(tmpcol,function(i,col){
				colclick += '"' + col + '": true ,';
			});
			colclick = colclick.substr(0,colclick.length-1);
			colclick += "}";
			colclick = $.parseJSON(colclick)
		}else{colclick = {};}
		$.each(headNames,function(i,headInfo){
			//获取单元格显示内容
			td = getTD(headInfo,json);
			
			//如果是序号列居中
			if("" !== headInfo.align){td.attr("align",headInfo.align);}
			if(headInfo.tdclass){td.addClass(headInfo.tdclass);}
			tr.append(td);
			if(headInfo.rowclick){
				td.click(function(event){
					if(isaddrow){
						if(tr.next().hasClass("sgfmaddtd")){
							get_tbody(table).children("tr.sgfmaddtd").remove();
						}else{
							get_tbody(table).children("tr.sgfmaddtd").remove();
							td2 = $("<td></td>").attr("colspan",colnum).attr("align","center");
							//tr.after($("<tr></tr>").addClass("sgfmaddtd").append(td2));
							settings.rowClickCallback.call(tr.get(0),tr.data("metadata"),td2.get(0),index,i);
						}
					}else{
						settings.rowClickCallback.call(tr.get(0),tr.data("metadata"),null,index,i);
					}
				}).css({"cursor":""});
			}
		});
		get_tbody(this).append(tr);
		settings.callback.call(tr.get(0),tr.data("metadata"),index);
	}
	
	//处理单元格显示内容
	function getTD(headInfo,json){
		var html="",names=[],modes=[],td = $("<td></td>");
		if("checkbox" === headInfo.bindmode){
			return td.append("<input type='checkbox' name='"+headInfo.name+"' value='"+json[headInfo.name]+"'  />");
		}
		if(headInfo.name){names = headInfo.name.split("|");}
		if(headInfo.bindmode){modes = headInfo.bindmode.split("|");}
		
		if(modes.length > 0){
			var tmpMode = modes[0].toLowerCase(),evalString,NorS=/^number|string$/;
			if(tmpMode === "number"){
				//数字格式化
				html = formNumber(json[names[0]],modes[1]);
				td.html(html);
				return td;
		    }else if(modes.length > 1){
				if(tmpMode === "function"){
					var isEnd=false,objs=[]; //对象是否在后面
					evalString = modes[1].split("@");
					$.each(evalString,function(j,fun){
						var tmpHtml = "",tmp=[];
						if(names.length > 0){
							$.each(names,function(i,name){
								var stmp = "";
								tmp[i] = json[name];
								if(typeof tmp[i] === "undefined" || tmp[i] === null){
									stmp = "''";
								}else if(typeof tmp[i] === "string"){
									stmp = "'"+HTMLEncode(tmp[i])+"'";
								}else{
									stmp = "tmp["+i+"]";
								}
								fun = fun.replace(new RegExp("\\{"+i+"\\}","g"), stmp);
							});
						}
						tmpHtml = eval(fun);
						if(NorS.test(typeof tmpHtml)){
							html += tmpHtml;
							if(j===0){isEnd =true;}
						}else if(typeof tmpHtml !== "undefined"){
							objs.push(tmpHtml);
						}
					});
					//添加信息
					td.html(html);
					
					if(isEnd){
						$.each(objs,function(i,obj){
							if(i!=0){
								td.append("&nbsp;");
							}
							td.append(obj);
						});
					}else{
						//反插入
						for(var c = objs.length - 1;c >= 0;c--){
							td.prepend(objs[c]);
							if(c!=0){
								td.prepend("&nbsp;");
							}
						}
					}
					return td;
					
				}else if(/^array|json$/.test(tmpMode)){
					//数组
					evalString = eval(modes[1]);
					if(($.isArray(evalString) || $.isPlainObject(evalString)) && names.length > 0){
						$.each(names,function(i,name){
							var tmp = json[name];
							if(NorS.test(typeof tmp)){
								html += evalString[tmp];
							}
						});
						
						if(NorS.test(typeof html)){
							td.html(html);
						}else{
							td.append(html);
						}
					}
					return td;
				}
			}
		}
		//将所有信息的值连接起来
		if(names.length > 0){
			$.each(names,function(i,name){
				var tmp = json[name];
				if(typeof tmp === "undefined" || null === tmp){tmp = "";}
				html += tmp;
			});
		}
		td.html(html);
		
		return td;
	}
	
	//格式化数字111,111.00
	function formNumber(str,num){
		if(!isNaN(str) && $.trim(str) !==""){
			var html = str.toString(),digits=num,prefix=str<0?"-":"";
			if(!(/^[\d]+$/.test(digits))){
				digits = 2;
			}
			var pow10 = Math.pow(10,digits);
			html = Math.abs(Math.round(parseFloat(html)*pow10)/pow10).toString();
			var index = html.indexOf("."),sDigits="",length=html.length;
			if(index===-1){index=length++;sDigits=".";}
			if(digits>0){
				var inum = digits-(length-index-1);
				if(inum>0){
					sDigits += fillNum.substr(0,inum);
					html+=sDigits;
				}
			}
			if(index===0){
				html = "0"+ html;
			}else if(index > 3){
				var inum = Math.ceil(index/3)-1,text=html.substr(index-3),tmpText=html.substring(0,index-3);
				for(var i=1;i<inum;i++){
					var tmpLength=tmpText.length;
					text = tmpText.substr(tmpLength-3) + "," + text;
					tmpText = tmpText.substring(0,tmpLength-3)
				}
				html = tmpText+","+text;
			}
			return prefix+html;
		}
		return str;
	}
	
	
	
	$.sgfmtable = function(){};
	instances = $.sgfmtable._fn = {
			
	};
	
	instances = {
		//获取当前页数
		get_pageno:function(){
			var pageNo = $(this).data("pageNo");
			if(!pageNo){pageNo={};}
			return pageNo;
		}
	}
	
	//处理特殊影响显示的字符
	function HTMLEncode(text){
		text = text.replace(/"/g, "&#34;")
				.replace(/'/g, "&#35;")
				.replace(/[$]/g, "&#36;")
				.replace(/</g, "&#60;")
				.replace(/>/g, "&#62;")
				.replace(/\\/g, "&#92;")
				.replace(/[\n]/g,"\\n")
				.replace(/[\r]/g,"\\r");
      return text;
 	}
	/*
	 * todo  后面还需要扩展
	 *      
	 * 
	 */
	
	
})(jQuery);