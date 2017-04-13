// JavaScript Document
(function ($) {
    var instances,max_zindex = 0,dialogs=[],isIE6=$.browser.msie&&/msie 6\.0/i.test(navigator.userAgent),//判断是否IE6浏览器
		jXHR={},//正在请求的XMLHttpRequest对象
		info={"rtCode":"返回失败返回码：","noMsg":"不存在对话框内容","hasDia":"对话框已经打开",
				"errorMsg":"错误信息","warnMsg":"警告信息","tipMsg":"提示信息","confirm":"确 定","cancel":"取 消"};//默认信息
	
	$(document).ready(function(){
		//从页面获取国际化信息
		if(window.sgfm && window.sgfm.dialogInfo){
			$.extend(info,sgfm.dialogInfo);
		}		
	});

    //对话框插件的事明
    $.fn.sgfmdialog = function(settings){
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
        }else{
            settings = init_params(settings);
            if(!$.isFunction(args[0])){args[0] = $.noop;}
            //调用初始化的方法
            if(!settings){settings = {};}     
            init.call(this,settings,args[0]);

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
        if(!$.isFunction(settings.callback)){settings.callback = $.noop;}
        return settings;
    }
    
    //获取信息，返回true有链接已经发送请求，返回false链不正确没有发送请求
    function get_info(settings,callback,error,complete){
        var type = settings.ajaxtype ? settings.ajaxtype:"POST",params = settings.params;
        if(settings.url){
            $.ajax({
                "url": settings.url,
                "context": this,
                "data": params,
                "type": type,
                "cache":false,
                "success": callback,
                "error": function(jqXHR,textStatus,errorThrown){
                    /*
                     * todo 对请求错误信息进行处理
                     */
                     if(settings.haserrmsg){
                         $.sgfmdialog(info.rtCode + data.returncode);
                     }
                },
		"complete":complete
            });
            return true;
        }else if(typeof settings.html === "string"){
            callback.call(this,settings.html)
        }else{
            return false;
        }
    }    
     //初始化对话框对象
     function init(settings,callback){
         
        var $this = this,url = settings.url,params = settings.params,css = !settings.css ? "dialog" : settings.css;
        //隐藏相同样式的对话框
        if(!settings.isModel){
            $("."+css).sgfmdialog("close_dialog");
        }
        if(typeof settings.context == 'string'){settings.context = $(settings.context).get(0);}
        if(typeof settings.needclose === "undefined"){settings.needclose = true;}
        
        if($this.length === 0){
			return (function(){
				if(typeof jXHR[$this.selector] !== "undefined"){
					//终止的代码
					//jXHR[$this.selector].abort();
					//delete jXHR[$this.selector];
					window.setTimeout(arguments.callee,1000);
					return;
				}
				if(!get_info.call(settings.context ? settings.context : $("body").get(0),settings,function(html){
					var element = $(html);
					if (element.is($this.selector)){
						//删除原来的对话框
						$($this.selector).remove();
						$(this).append(element);
						$this = $($this.selector);
						if(!settings.isModel){
							$this.addClass(css);
						}
						if($.isFunction(settings.initpage)){
							settings.initpage.apply($this.get(0));
						}
						if($.isFunction(settings.callback)){
							settings.callback.call($this.get(0),true);
						}
						if($.isFunction(callback)){
							callback.call($this.get(0),true);
						}
						showDialog.call($this,settings,true);
					}
				},$.noop,function(jqXHR, textStatus){
					delete jXHR[$this.selector];
				})){
					/*
					 * todo 错误信息进行处理
					 */
					 if(settings.haserrmsg){
						 $.sgfmdialog(info.noMsg);
					 }
					return false;
				}
			})();
        }else{
            if($this.is(":visible")){
                /*
                 * todo 错误信息进行处理
                 */
                 if(settings.haserrmsg){
                     $.sgfmdialog(info.hasDia);
                 }
                return false;
            }
            $this.show();
            if($.isFunction(settings.callback)){settings.callback.call($this.get(0),false);}
            if($.isFunction(callback)){callback.call(this.get(0),false);}
            showDialog.call($this,settings);
        }
    }
    
    //显示出对话框
    function showDialog(settings,initpage){
        var tmpDialog = {};
        if(settings.isModel){
            tmpDialog = showModel(tmpDialog);
            tmpDialog["dialog"] = this;
            tmpDialog["dialog"].css({"position":"absolute","z-index": getMaxZIndex(),"margin":0});
            if(typeof settings.middle === "undefined"){ settings.middle = true;}
        }else{
            tmpDialog["dialog"] = this;
        }
        if(settings.middle){
            //对话框在中间显示
            showMiddle.call(tmpDialog["dialog"]);
        }
        //有头的对话框初始化为可拖动
        $(this).find(".tit").mousedown(function(event){
            tmpDialog["doc_moveevent"] = moveDialog.call(tmpDialog["dialog"].get(0),event);
        }).css({"cursor":"default","-moz-user-select":"none"}).bind("selectstart",function(){return false;});
        tmpDialog["doc_upevent"] = function(){$(document).unbind("mousemove",tmpDialog["doc_moveevent"]); return false;};
        $(document).mouseup(tmpDialog["doc_upevent"]);
        //添加到列表中准备删除使用 结构：{"model":"","dialog":"","doc_event":"","win_event":"","isremove":"","type":"alert"}
        if(settings.needclose){
            tmpDialog["isremove"] = settings.isremove;
            tmpDialog["type"] = "model";
            //初始化关闭事件
            closeEventInit.call(tmpDialog,settings);
            dialogs.push(tmpDialog);
        }
    }

    //获取对话框居中显示位置
    function showMiddle(){
    	var $document=$(document),$window=$(window),$this=$(this),
            st=$document.scrollTop(),//滚动条距顶部的距离
            sl=$document.scrollLeft(),//滚动条距左边的距离
            ch=$window.height(),//屏幕的高度
            cw=$window.width(),//屏幕的宽度
            objH=$this.height(),//浮动对象的高度
            objW=$this.width(),//浮动对象的宽度
            objT=Number(st)+(Number(ch)-Number(objH))/2,
            objL=Number(sl)+(Number(cw)-Number(objW))/2;
        if(objT<0){objT=0;}
        if(objL<0){objL=0;}
        $this.css({"top":function(){return objT;},"left":function(){return objL;}});
    };


    //提示对话框
    $.sgfmdialog = function(settings){
		var args = Array.prototype.slice.call(arguments, 1);
        if(/^close_dialog|get_max_zindex|remove_dialog|timing_dialog|screen_wait$/.test(settings)){
            //关闭对话框
            if(instances && $.isFunction(instances[settings])){ return instances[settings](args); };
        }else{
            //打开提示窗口
            showDefault.apply(this,arguments);
        }
    };
    
    //默认弹出框
    function showDefault(settings){
        var isMethodCall = (typeof settings === "string"),alertClass = "alert_ico0",level = 3,tmpDialog={},levelRegExp=/^[0-3]$/;
        if(!isMethodCall && typeof settings.isModel === "undefined"){
            settings.isModel = true;
        }
        if(isMethodCall || settings.isModel){
            //将对话框对象进行保存
            tmpDialog = showModel(tmpDialog);
        }
        //获取级别
        if(levelRegExp.test(settings.level)){
            level = parseInt(settings.level,10);
        }else if(levelRegExp.test(arguments[1])){
            level = parseInt(arguments[1],10);
        }
        //级别样式
        alertClass += level;
		
		var sInfo,title;
		if(isMethodCall){
			sInfo = settings;
			if(typeof arguments[1] === "string" && isNaN(arguments[1])){
				title = arguments[1];
			}
		}else{
			sInfo = settings.info;
			if(!sInfo){sInfo = "";}
			if(settings.title){
				title = settings.title;
			}
		}
		if(!title || $.trim(title) === ""){
			switch (level) {
				 case 1 :
					 title = info.errorMsg;
					 break;
				 case 2 :
					 title = info.warnMsg;
					 break;
				 case 0 :
				 	 title = info.tipMsg;
					 break;
				 case 3 :
				 default :
					 title = info.tipMsg;
					 break;
			}
		}
		tmpDialog = showAlert(sInfo,title,tmpDialog);
		//对话框在中间显示
		showMiddle.call(tmpDialog["dialog"]);
		tmpDialog["dialog"].addClass(alertClass).children("input[name='confirm']").focus();
		//增加取消按钮
		if(level == 0){
			tmpDialog["dialog"].append("<input type='button' name='cancel' value='"+info.cancel+"' class='bt_not' />");
		}
        if(true){
            /*
             * todo 预留选择对话框有两按钮
             */
        }else{
            /*
             * todo 预留临时提示对话框
             */
        }
		
        tmpDialog["isremove"] = true;
		if(!$.isFunction(arguments[1]) && $.isFunction(arguments[2])){
			arguments[1] = arguments[2];
		}
        closeEventInit.apply(tmpDialog,arguments);
		if(settings.showTime &&!isNaN(settings.showTime))
		{
			window.setTimeout(function(){
				 instances["close_dialog"](tmpDialog);
			},settings.showTime);
		}
        //添加到列表中准备删除使用 结构：{"model":"","dialog":"","doc_event":"","win_event":"","isremove":"","type":"alert"}
        dialogs.push(tmpDialog);

    }

    //初始化对话框拖动事
    function moveDialog(event,oldMoveEvent){
        var place    = $(this).offset(),bnum = event.eventPhase ? 0 : 1,$this=$(this),moveEvent;
        if(event.which == 1)
        {
            //样式的边框大小需要加上去
            var X = event.pageX - place.left,Y =    event.pageY -place.top,h = $(document).height() - $this.height()-2,w = $(document).width() - $this.width()-2;
            $(document).unbind("mousemove",oldMoveEvent);
            moveEvent = function(obj){
                var left = obj.pageX - X, top = obj.pageY - Y;
                if(top < 0){top = 0;}else if(top > h){top = h;}
                if(left < 0){left = 0;}else if(left > w){left = w;}
                $this.css({"left":function(){return left;},"top":function(){return top;}});
                return false;
            };
            $(document).bind("mousemove",moveEvent); 
            
        }
        return moveEvent;
    }

    //显示提示框
    function showAlert(text,title,tmpDialog){
        tmpDialog["dialog"] = $("<div>").addClass("alert")
        .append($("<h4>")
                    .addClass("tit").text(title ? title : info.tipMsg)// todo 需要进行国标化
                    .append($("<div>")
                            .addClass("b_close"))
                    .bind("selectstart",function(){return false;})
                    .css({"-moz-user-select":"none"})
                    .mousedown(function(event){
                        //初始化对话框拖动事件
                        tmpDialog["doc_moveevent"] = moveDialog.call(tmpDialog["dialog"].get(0),event,tmpDialog["doc_moveevent"]);
                    }))
        .append($("<div class='key_body'></div>").text(text))
        .append($("<input type='button' name='confirm' value='"+info.confirm+"' class='bt_yes' />"));//todo 需要进行国标化
        $("body").append(tmpDialog["dialog"]);
        tmpDialog["dialog"].css({"z-index": getMaxZIndex()})
        tmpDialog["dialog"].css({"width":function(){return tmpDialog["dialog"].width()}});
        tmpDialog["type"] = "alert";
        tmpDialog["doc_upevent"] = function(){$(document).unbind("mousemove",tmpDialog["doc_moveevent"]); return false;};
        $(document).mouseup(tmpDialog["doc_upevent"]);
        return tmpDialog;
    }
    
    //遮罩层
    function showModel(tmpDialog){
        var element = $(document),w = element.width(),h = element.height(),tmpDialog = {};
        tmpDialog["model"] = $("<div></div>").css({"width":function(){return w;},"height":function(){return h;},"opacity":.65,"position":"absolute","z-index":getMaxZIndex()}).addClass("mask_layer");
        $("body").append(tmpDialog["model"]);
        //遮罩层大小改变
        tmpDialog["win_event"] = function(){
            var e1=$("body"),bw=e1.width(),bh=e1.height();
            tmpDialog["model"].css({width:function(){return bw;},height:function(){return bh;}});
            tmpDialog["model"].css({width:function(){return $(document).width();},height:function(){return $(document).height();}});
        };
        $(window).bind("scroll resize",tmpDialog["win_event"]);
		if(isIE6){
			tmpDialog["model"].html("<iframe frameborder='0' scrolling='no' height='100%' width='100%' scr='javascript:false;' style='position:absolute; left:0px; top:0px; background-color:transparent;z-index:-1;' />");
		}
        return tmpDialog;
    }
    
    //初始化关闭事件
    function closeEventInit(settings,callback){
		var closeCallback = $.noop;
		if($.isFunction(settings.closeCallback)){closeCallback=settings.closeCallback;}
		if(!$.isFunction(callback)){callback=$.noop;}
        var tmpDialog = this;
        if(settings.maskclick && this["model"]){
            this["model"].click(function(){
                closeDialog.call(tmpDialog,closeCallback,callback,false);
            });
        };
        if(this["dialog"] && !settings.isremove){
            this["dialog"].find("div.b_close").click(function(){
                closeDialog.call(tmpDialog,closeCallback,callback,false);
            }).css({"cursor":"pointer"});
            if(this["type"] === "alert"){
                this["dialog"].find("input[name=confirm]").click(function(){
                    closeDialog.call(tmpDialog,closeCallback,callback,true);
                });
                this["dialog"].find("input[name=cancel]").click(function(){
                    closeDialog.call(tmpDialog,closeCallback,callback,false);
                });
            }
            if(this["type"] === "model"){
                this["dialog"].find("input.bt_del_1,input.bt_del_2").click(function(){
                    closeDialog.call(tmpDialog,closeCallback,callback,false);
                });
            }
            
        }
    }
	
	//关闭对话框
	function closeDialog(closeCallback,callback,isYes){
		if(closeCallback(isYes)!==false){
			if(callback(isYes)!==false){
				instances["close_dialog"](this);
			}
		}
					
	}
    
    //取消事件绑定
    function unbindEvent(){
        this.find(".tit").unbind("mousedown");
        this.find("div.b_close").unbind("click");
        this.find("input[name=confirm]").unbind("click");
        this.find("input.bt_del_1,input.bt_del_2").unbind("click");
    }
    
	//显示错误提示信息  
    instances = $.sgfmdialog._fn = {
        //对外正常关闭打开的对话框
        close_dialog : function(dialog){
            var di = dialogs.length-1,//获取当前打开对话框的最大
                did=-1;
            if(di>=0){
                if($.isPlainObject(dialog)){
                    for(var i=di;i>=0;i--){
                        if(dialogs[i] === dialog){
                            did = i;
                            break;
                        }
                    }
                }else{
                    if(this!==instances){
                        for(var i=di;i>=0;i--){
                            if(dialogs[i].dialog.get(0) === this){
                                did = i;
                                break;
                            }
                        }
                    }else{
                        did = di;
                    }
                    if(did>=0){
                        dialog = dialogs[did];
                    }
                }
                if($.isPlainObject(dialog)){
                    if(dialog.dialog){
                        if(dialog.isremove){
                            dialog.dialog.remove();
                        }else{
                            unbindEvent.apply(dialog.dialog);
                            dialog.dialog.hide();
                        }
                    }
                    if(dialog.model){
                        dialog.model.remove();
                    }
                    if(dialog.win_event){
                        $(window).unbind("scroll resize",dialog.win_event);
                    }
                    if(dialog.doc_moveevent){
                        
                        $(document).unbind("mousemove",dialog.doc_moveevent);
                    }
                    if(dialog.doc_upevent){
                        $(document).unbind("mouseup",dialog.doc_upevent);
                    }
                    dialog = null;
                    if(did>=0){
                        dialogs.splice(did,1);
                    }
                }
            }
        },
        //将对话框内容从页面删除
        remove_dialog:function(dialog){
            var element=this,isDel=false,di=dialogs.length-1;				
            if(this!==instances){
                for(var i=di;i>=0;i--){
					if(dialogs[i].dialog.get(0) === element){
                        isDel=true;
						removeDialog.call(dialogs[i],i);
					}
				}
				if(!isDel){
					element.remove();
				}
            }else{
                for(var i=di;i>=0;i--){
					removeDialog.call(dialogs[i],i);
				}
			}
        },
        //获取页面层次最高的zindex
        get_max_zindex : function(){
            if(dialogs.length > 0 || max_zindex < 1){
                return getMaxZIndex();
            }else{
                return max_zindex;
            }
        },
		//打开页面等待
		screen_wait : function(str){
			//将对话框对象进行保存
			var tmpDialog = showModel(tmpDialog);
			
			tmpDialog.dialog = tmpDialog.model;
			$("<div></div>").appendTo(tmpDialog.dialog).addClass("loading");
						
			tmpDialog["isremove"] = true;
			
			//添加到列表中准备删除使用 结构：{"model":"","dialog":"","doc_event":"","win_event":"","isremove":"","type":"alert"}
			dialogs.push(tmpDialog);			
		}
    };
	
	//删除打开过的对话框
	function removeDialog(i){
		if(this.dialog){
			this.dialog.remove();
		}
		if(this.model){
			this.model.remove();
		}
		if(this.win_event){
			$(window).unbind("scroll resize",this.win_event);
		}
		if(this.doc_moveevent){
			
			$(document).unbind("mousemove",this.doc_moveevent);
		}
		if(this.doc_upevent){
			$(document).unbind("mouseup",this.doc_upevent);
		}
		dialogs.splice(i,1);

	}
     // 获取页面最大的z-Index
    function getMaxZIndex() {
        if(max_zindex < 1){
            $("div").each(function(){
                var curz = $(this).css("z-index");
                if(!isNaN(curz)){
                    curz = parseInt(curz);
                    max_zindex = Math.max(curz,max_zindex);
                }
            });
            if(max_zindex < 1){max_zindex = 999;}
        }
        return ++max_zindex;
    }

    /*
     *            还对需要对IE6的 select 标签进行遮盖
     */
    
    
})(jQuery);