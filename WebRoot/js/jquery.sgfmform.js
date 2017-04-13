/*
	通用表单验证方法
	
	Demo:
	$(".demoform").sgfmform({//$(".demoform")指明是哪一表单需要验证,名称需加在from表单上;
		btnSubmit:"#btn_sub", //#btn_sub是该表单下要绑定点击提交表单事件的按钮;如果form内含有submit按钮该参数可省略;
		tiptype:1, //可选项 1=>pop box,2=>side tip，默认为1;
		postonce:true, //可选项 是否开启网速慢时的二次提交防御，true开启，不填则默认关闭;
		ajaxurl:"demo_url", //ajax提交表单数据;
		callback:function(data){操作；
			//这里执行回调操作;
		}
	});
*/

(function($){
	var errorobj=null,//指示当前验证失败的表单元素;
		msgobj=false,//pop box object 
		msghidden=true, //msgbox hidden?
		tipThis,
		tipmsg={//默认提示文字;
			title:"提示信息",
			w:"请输入正确信息！",
			r:"通过信息验证！",
			c:"正在检测信息…",
			s:"请填入信息！",
			v:"所填信息没有经过验证，请稍后…",
			p:"正在提交数据…"
		};/*,
		creatMsgbox=function(){
			if($("#Validform_msg").length!=0){return false;}
			msgobj=$('<div id="Validform_msg"><div class="Validform_title">'+tipmsg.title+'<a class="Validform_close" href="javascript:void(0);">&chi;</a></div><div class="Validform_info"></div><div class="iframe"><iframe frameborder="0" scrolling="no" height="100%" width="100%"></iframe></div></div>').appendTo("body");//提示信息框;
		
			msgobj.find("a.Validform_close").click(function(){
				msgobj.hide();
				msghidden=true;
				if(errorobj){
					errorobj.focus().addClass("Validform_error");
				}
				return false;
			}).focus(function(){this.blur();});

			$(window).bind("scroll resize",function(){
				if(!msghidden){				  
					var left=($(window).width()-msgobj.width())/2;
					var top=($(window).height()-msgobj.height())/2;
					var topTo=(document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop)+(top>0?top:0);
					msgobj.animate({
						left : left,
						top : topTo
					},{ duration:400 , queue:false });
				}
			})
		}*/
		
		$(document).ready(function(){
			//从页面获取国际化信息		
			if(window.sgfm && window.sgfm.formInfo){
				$.extend(tipmsg, sgfm.formInfo);
			}
		});
		
	
	$.fn.sgfmform=function(settings){
		var defaults={}, m_this=$(this);
		settings=$.extend({},$.fn.sgfmform.sn.defaults,settings);
		tipThis=$(this);
		if(!$.isFunction(settings.errcall)){settings.errcall=$.noop;}
		
		this.each(function(){
			var $this=$(this);
			var posting=false; //防止表单按钮双击提交两次;
			
			
			//存储初始化提示信息
			$this.find(".Validform_checktip").each(function(){
				var $span = $(this);
				$span.data("text",$span.text());
			});
			
			$this.find("[tip]").each(function(){//tip是表单元素的默认提示信息,这是点击清空效果;
				var defaultvalue=$(this).attr("tip");
				var altercss=$(this).attr("altercss");
				$(this).val(defaultvalue).addClass(altercss);
				$(this).focus(function(){
					if($(this).val()==defaultvalue){
						$(this).val('');
						if(altercss){$(this).removeClass(altercss);}
					}
				}).blur(function(){
					if($.trim($(this).val())==''){
						$(this).val(defaultvalue);
						if(altercss){$(this).addClass(altercss);}
					}
				});
			});
			//绑定blur事件;
			$this.find("[datatype]").blur(function(){
				var flag=true, b_this=$(this);
				//弹出框提示时，关闭提示框
				if(msgobj){
					$.Hidemsg();
				}
				//alert($this.val())
				
					if($(this).attr("ajaxurl")){
						var inputobj=$(this),
							backmethod=eval(inputobj.attr("ajaxback"));
						//inputobj.attr("valid",tipmsg.c);
						//$.fn.sgfmform.sn.showmsg(settings.ajaxinfo==true?tipmsg.c:"",settings.tiptype,{obj:inputobj,type:1},"hide");
						var data = inputobj.attr("name")+"="+$(this).val();
						if(inputobj.attr("checkCode")){
							data = data + "&" + inputobj.attr("checkCode") + "=" + $("#"+inputobj.attr("checkCode")).val();
						}
						$.ajax({
							type: "POST",
							url: inputobj.attr("ajaxurl"),
							data: data,
							dataType: "json",
							cache: false,
							success: function(s){
								if(s.returncode==0){
									inputobj.attr("valid","true");
									//$.fn.sgfmform.sn.showmsg(b_this.attr("passinfo")||tipmsg.r,settings.tiptype,{obj:inputobj,type:2},"hide");
									//alertMsg({"content": b_this.attr("passinfo")||tipmsg.r,"type": 2,"close": true});
									if($.isFunction(backmethod)){
										backmethod.call(inputobj.get(0), true, s);
									}
								}else{
									inputobj.attr("valid",s.errmsg);
									errorobj=inputobj;
									//$.fn.sgfmform.sn.showmsg(s.errmsg,settings.tiptype,{obj:inputobj});
									alertMsg({"content": s.errmsg,"type": 2,"close": true});
									return false;
									if($.isFunction(backmethod)){
										backmethod.call(inputobj.get(0), false, s);
									}
									
								}
							},
							error: function(jqXHR, textStatus, errorThrown){
								settings.errcall.call(this, jqXHR, textStatus, errorThrown);
								if($.isFunction(backmethod)){
									backmethod.call(inputobj.get(0), false);
								}
							}
						});
				};
				
			});
			
			//subform
			var subform=function(){
				//alert(1)
				var flag=true;
				if(posting){return false;}
				
				$this.find("[datatype]").each(function(){
					var s_this=$(this);
					flag=$.fn.sgfmform.sn.checkform(settings,$(this),$this,settings.tiptype);

					if(!flag){
						errorobj.focus();
						return false;
					}
					
					if(typeof(flag)!="boolean"){
						flag=true;
						return true;
					}
					
					flag=$.fn.sgfmform.sn.regcheck($(this).attr("datatype"),$(this).val()); //检查单个元素的正则表达式
					
					if(!flag){
						if($(this).attr("ignore")=="ignore" && ( $(this).val()=="" || $(this).val()==$(this).attr("tip") )){
							flag=true;
							return true;
						}
						errorobj=$(this);
						errorobj.focus();
						//$.fn.sgfmform.sn.showmsg($(this).attr("errormsg")||tipmsg.w,settings.tiptype,{obj:$(this)});
						alertMsg({"content" : $(this).attr("errormsg")||tipmsg.w, "type" : "2", "close" : true})
						return false;
					}
					
					if($(this).attr("ajaxurl")){
						if($(this).attr("valid")!="true"){
							flag=false;
							var thisobj=$(this);
							errorobj=thisobj;
							//errorobj.focus();
							//$.fn.sgfmform.sn.showmsg(thisobj.attr("valid") || tipmsg.v,settings.tiptype,{obj:thisobj});
							alertMsg({"content" : thisobj.attr("valid") || tipmsg.v, "type" : "3", "close" : true});
							//if(!msghidden || settings.tiptype==2){
								//setTimeout(function(){ //--无需延时触发失去焦点，点击提交直接触发，否则会提示两次
									//thisobj.trigger("blur");
								//},2000);
							//}
							return false;
						}else{
							//$.fn.sgfmform.sn.showmsg(s_this.attr("passinfo")||tipmsg.r,settings.tiptype,{obj:$(this),type:2},"hide");
							//alertMsg({"content" : s_this.attr("passinfo")||tipmsg.r, "type" : "1", "close" : true})
							flag=true;
						}
					}
				})
				
				if(flag && !posting){
					errorobj=null;
					if(settings.postonce){posting=true;}
					var url = settings.ajaxurl ? settings.ajaxurl : $this.attr("action");
					var subType = settings.submittype;
					var contFlag = true;
					while(contFlag){
						if(subType == 2){
							if(settings.ajaxinfo==true){
								//$.fn.sgfmform.sn.showmsg(tipmsg.p,settings.tiptype,{obj:$(this)},"alwaysshow");//传入"alwaysshow"则让提示框不管当前tiptype为1还是2都弹出;
								alertMsg({"content" : tipmsg.p, "type" : "3", "close" : true})
							}
							
							$.ajax({
								type: "POST",
								dataType:"json",
								url: settings.ajaxurl,
								data: $this.serialize(),
								cache: false,
								success: function(data){
									m_this.find(".Validform_checktip").removeClass("Validform_wrong Validform_right");//清除提示信息
									if(data.info){
										alertMsg({"content" : data.info, "type" : "1", "close" : true})
										//$.fn.sgfmform.sn.showmsg(data.info,settings.tiptype,{obj:$(this)},"alwaysshow");
									}else{
										if(msgobj){
											$.Hidemsg();										
										}
									}
									(settings.callback)(data);
								},
								error: function(jqXHR, textStatus, errorThrown){
									settings.errcall.call(this, jqXHR, textStatus, errorThrown);	
								}
							});
							contFlag = false;
							return false;
						}else if(subType == 1){
							$this.attr("action",url);
							$this.get(0).submit();
							contFlag = false;
						}else{
							var mode = (settings.callback).call($this.get(0),$this.serialize(),url);//接收返回值判断提交方式
							if(mode == 1){
								subType = 1;
								contFlag = true;
							}else if(mode ==2){
								subType = 2;
								contFlag = true;
							}else{
								contFlag = false;
							}
							
						}					
					}

				}
				
			}
			
			$this.find(settings.btnSubmit).bind("click",subform);
			$this.submit(function(){
				subform();
				return false;
			});
		})
		
		//预创建pop box;
		/*if(settings.tiptype!=2 || settings.ajaxurl){		
			creatMsgbox();
		}*/
		
		//清除提示信息
		m_this.find("input.msgempty").click(function(){
			m_this.find(".Validform_checktip").removeClass("Validform_wrong Validform_right");
		});
		
	}
	
	$.fn.sgfmform.sn={
		defaults:{
			tiptype:1,
			ajaxinfo:false
		},
		
		regcheck:function(type,gets){
			switch(type){
				case "*":
					return true;
				case "*6-16":
					var repost= /^[^\s]{6,16}$/;
					return repost.test(gets);
				case "n":
					var repost= /^\d+\.?\d*$/;
					return repost.test(gets);
				case "s":
					return isNaN(gets);
				case "s2-18":
					var repost= /^[\u4E00-\u9FA5\uf900-\ufa2d\w]{2,18}$/;
					return repost.test(gets);
				case "p":
					var repost= /^[0-9]{6}$/;
					return repost.test(gets);
				case "m":
					var repost= /^\+?(\d+-)*\d+$/;
					return repost.test(gets);
				case "e":
					var repost = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
					return repost.test(gets);
				case "z":
					return isChinaIDCard(gets);
				case "b":
					var repost = /^([-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3})|0{0,1}1[3|4|5|7|8]\d{9}$/;
					return repost.test(gets);
				case "d":
					var repost = /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
					return repost.test(gets);
				default:
					if($.trim(type) == "" || type.indexOf("*") === 0 || type.indexOf("?") === 0)
					{
						return false;
					}else{
						var repost = new RegExp(type);
						return repost.test(gets);
					}
			}
		},
		
		showmsg:function(msg,type,o,show){//o:{obj:当前对象, type:1=>正在检测 | 2=>通过}, show用来判断tiptype=1的情况下是否弹出信息框;
			creatMsgbox();
			if(errorobj){errorobj.addClass("Validform_error");}
			if(type==1 || show=="alwaysshow"){
				//提示信息为空时不弹出
				if($.trim(msg)!==""){
					msgobj.find(".Validform_info").text(msg);
				}
			}

			if(type==1 && show!="hide" || show=="alwaysshow"){
				//提示信息为空时不弹出
				if($.trim(msg)!==""){
					msghidden=false;
					//msgobj.find(".iframe").css("height",msgobj.height());
					if($.sgfmdialog){msgobj.css({"z-index":$.sgfmdialog("get_max_zindex")})}
					var left=($(window).width()-msgobj.width())/2;
					var top=($(window).height()-msgobj.height())/2;
					top=(document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop)+(top>0?top:0);
					msgobj.css({
						"left":left
					}).show().animate({
						top:top
					},100);
				}
				
			}
			
			if(type==2){
				if(o.type){
					switch(o.type){
						case 1://正在检测;
							o.obj.parent().find(".Validform_checktip").removeClass().addClass("Validform_checktip Validform_loading").text(msg);
							break;
						case 2://检测通过;
							if(msg=="null"){
								o.obj.parent().find(".Validform_checktip").removeClass().addClass("Validform_checktip").text("");
							}else if(msg=="icon"){
								if($.trim(o.obj.val()) !== ""){
									o.obj.parent().find(".Validform_checktip").removeClass().addClass("Validform_checktip Validform_right").text("");
								}else{
									o.obj.parent().find(".Validform_checktip").removeClass().addClass("Validform_checktip").text("");
								}
							}else{
								o.obj.parent().find(".Validform_checktip").removeClass().addClass("Validform_checktip Validform_right").text(msg);	
							}
					}
				}else{
					o.obj.parent().find(".Validform_checktip").removeClass().addClass("Validform_wrong Validform_checktip").text(msg);
				}
			}
			
		},
		
		checkform:function(args,obj,parentobj,tiptype,show){//show用来判断是表单提交还是blur事件引发的检测;
			//alert(parentobj.val())
			var errormsg=obj.attr("errormsg") || tipmsg.w;
			if(obj.is("[datatype='radio']")){  //判断radio表单元素;
				var inputname=obj.attr("name");
				var radiovalue=parentobj.find(":radio[name='"+inputname+"']:checked").val();
				if(!radiovalue){
					errorobj=obj;
					alertMsg({"content" : errormsg, "type" : "2", "close" : true})
					//this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
				errorobj=null;
				//this.showmsg(obj.attr("passinfo")||tipmsg.r,tiptype,{obj:obj,type:2},"hide");
				return "radio";
			}

			if(obj.is("[datatype='checkbox']")){  //判断checkbox表单元素;
				var inputname=obj.attr("name");
				var checkboxvalue=parentobj.find(":checkbox[name='"+inputname+"']:checked").val();
				if(!checkboxvalue){
					errorobj=obj;
					alertMsg({"content" : errormsg, "type" : "2", "close" : true})
					//this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
				errorobj=null;
				//this.showmsg(obj.attr("passinfo")||tipmsg.r,tiptype,{obj:obj,type:2},"hide");
				return "checkbox";
			}

			if(obj.is("[datatype='select']")){  //判断select表单元素;
				if(!obj.val()){
				  errorobj=obj;
				  alertMsg({"content" : errormsg, "type" : "2", "close" : true})
				  //this.showmsg(errormsg,tiptype,{obj:obj},show);
				  return false;
				}
				errorobj=null;
				//this.showmsg(obj.attr("passinfo")||tipmsg.r,tiptype,{obj:obj,type:2},"hide");
				return "select";
			}
			
			var defaultvalue=obj.attr("tip");	//文本框默认显示信息
			if((obj.val()=="" || obj.val()==defaultvalue) && obj.attr("ignore")!="ignore"){
				errorobj=obj;
				alertMsg({"content" : errormsg, "type" : "2", "close" : true})
				//this.showmsg(obj.attr("nullmsg") || tipmsg.s,tiptype,{obj:obj},show);
				return false;
			}
			
			if(obj.attr("recheck")){	//判断两次密码输入是否一致
				//alert(obj.attr("recheck"))
				var theother=parentobj.find("input[name='"+obj.attr("recheck")+"']:first");
				//console.info(theother.val())
				if(obj.val()!=theother.val()){
					errorobj=obj;
					alertMsg({"content" : errormsg, "type" : "2", "close" : true})
					//this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
			}
			
			if(show!="hide" && obj.attr("redate")){	//判断两个文本框的值是否同时存在或同时为空
				var otherdate = parentobj.find("input[name='"+obj.attr("redate")+"']");
				if($.trim(otherdate.val())!="" && $.trim(obj.val())==""){
					errorobj=obj;
					alertMsg({"content" : errormsg, "type" : "2", "close" : true})
					//this.showmsg(errormsg,tiptype,{obj:obj},show);
					return false;
				}
			}
			
			if(obj.attr("validmethod")){	//判断是否为自定义方法验证
				var methodName = obj.attr("validmethod");
				if($.trim(methodName) != ""){
					var methodArray = methodName.split("(");
					if(methodArray.length === 2){
						methodName = methodArray[0]+"("+methodArray[1].replace("this", "obj.get(0)");
					}
					var rtMsg = eval(methodName);			
					if(rtMsg.returncode == 0){
						obj.attr("valid","true");
					}else{
						obj.attr("valid",rtMsg.errmsg);
						errorobj=obj;
						alertMsg({"content" : rtMsg.errmsg, "type" : "2", "close" : true})
						//this.showmsg(rtMsg.errmsg,tiptype,{obj:obj});
						return false;
					}
				}
			}
			obj.removeClass("Validform_error");
			errorobj=null;
			return true;
		}
		
	}
	
	//公用方法显示&关闭信息提示框;
	$.Showmsg=function(msg){
		creatMsgbox();
		$.fn.sgfmform.sn.showmsg(msg,1);
	};
	$.Hidemsg=function(){
		msgobj.hide();
		msghidden=true;
	};
	
	//公用方法清除提示信息
	$.Cleartips=function(myForm){
		$(myForm).find(".Validform_checktip").removeClass("Validform_wrong Validform_right").text("");
	};
	
	//公用方法重置默认提示信息
	$.Resettips=function(myForm){
		$(myForm).find(".Validform_checktip").each(function(){
			var $sp = $(this).removeClass("Validform_wrong Validform_right");
			$sp.text($sp.data("text"));
		});
	};

})(jQuery);