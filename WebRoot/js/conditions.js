/*
 * 选择品牌：xzpp
 * 套餐类型：tclx
 * 适用年龄：synl
 * 适用性别：syxb
 * 价格范围：jgfw
 * 医院等级：yydj
 * 选择区域：xzqy
 * 
 */

function conditionsUtil(obj,condition,isCard){
	var url = "/core/product.findConditionsDataList.do";
	var data= {"condition":condition};
	$.getMyJSON(url,data,function(rs){
		if(rs == null){
			return;
		}else{
			//alert(productType)
			//alert(rs.data[0].productTypeList[0]);
			var brandList,productTypeList,productAgeList,countryList,brandTypeList,html='<form id="searchForm">';
			var conditionList=condition.split(",");
			for(var i=0;i<conditionList.length;i++){
				if(conditionList[i]=="tclx"){//套餐类型
					productTypeList=rs.data[0].productTypeList;
					if(productTypeList!=null && productTypeList!="undefined"){
					html+='<div class="tiaojianlist tjl1 ';
					if(productType==""){html+='moretitle1';}
					html+=' tclx"> <h2>套餐类别</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
					for(var a=0;a<productTypeList.length;a++){
					html+='<span data="'+productTypeList[a].KEY+'"';
						if(productType==productTypeList[a].KEY){
							html+='class="active"';
						}
					html+='>'+productTypeList[a].NAME+'</span>';
					}
					html+='<input id="categoryidOne" type="hidden" value="'+productType+'" />';
					html+='</p></div><div class="box2 moreA"><span ';
					if(productType!=""){html+='class="direction"';}
					html+='>点击查看更多</span></div><div class="fenjieline"></div>';
					}
				}else if(conditionList[i]=="xzpp"){//选择品牌
					brandList=rs.data[0].brandList;
					if(brandList!=null && brandList!="undefined"){
					html+='<div class="tiaojianlist tjl2 ';
					if(brandId==""){html+='moretitle1';}
					html+=' xzpp"> <h2>选择品牌</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
						//判断是否为体检卡列表页，是（isCard==1）则额外增加一卡通条件
						if(isCard==1){
							html+='<span data="10000"';
							if(brandId=='10000'){
								html+='class="active"';
							}
							html+='>一卡通</span>';
						}
					for(var b=0;b<brandList.length;b++){
					html+='<span data="'+brandList[b].BRANDID+'"';
					if(brandId==brandList[b].BRANDID){
						html+='class="active"';
					}
					html+='>'+brandList[b].BRANDNAME+'</span>';
					}
					html+='<input id="brandId" type="hidden" value="'+brandId+'" />';
					html+='</p></div><div class="box2 moreB"><span ';
					if(brandId!=""){html+='class="direction"';}
					html+='>点击查看更多</span></div><div class="fenjieline"></div>';
					}
				}else if(conditionList[i]=="synl"){//适用年龄
					productAgeList=rs.data[0].productAgeList;
					if(productAgeList!=null && productAgeList!="undefined"){
					html+='<div class="tiaojianlist synl"> <h2>适用年龄</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
					for(var c=0;c<productAgeList.length;c++){
					html+='<span data="'+productAgeList[c].KEY+'"';
					if(productAge==productAgeList[c].KEY){
						html+='class="active"';
					}
					html+='>'+productAgeList[c].NAME+'</span>';
					}
					html+='<input id="categoryidTwo" type="hidden" value="'+productAge+'" />';
					html+='</p></div><div class="fenjieline"></div>';
					}
				}else if(conditionList[i]=="xzqy"){//选择区域
					countryList=rs.data[0].countryList;
					if(countryList!=null && countryList!="undefined"){
					html+='<div class="tiaojianlist xzqy"> <h2>选择区域</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
					for(var d=0;d<countryList.length;d++){
					html+='<span data="'+countryList[d].COUNTRYID+'"';
					if(country==countryList[d].COUNTRYID){
						html+='class="active"';
					}
					html+='>'+countryList[d].COUNTRY+'</span>';
					}
					html+='<input id="country" type="hidden" value="'+country+'" />';
					html+='</p></div><div class="fenjieline"></div>';
					}
				}else if(conditionList[i]=="yydj"){//医院等级
					brandTypeList=rs.data[0].brandTypeList;
					if(brandTypeList!=null && brandTypeList!="undefined"){
					html+='<div class="tiaojianlist yydj"> <h2>医院等级</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
					for(var e=0;e<brandTypeList.length;e++){
					html+='<span data="'+brandTypeList[e].ID+'"';
					if(hospitalLevel==brandTypeList[e].ID){
						html+='class="active"';
					}
					html+='>'+brandTypeList[e].NAME+'</span>';
					}
					html+='<input id="categoryidFour" type="hidden" value="'+hospitalLevel+'"/>';
					html+='</p></div><div class="fenjieline"></div>';
					}
				}else if(conditionList[i]=="syxb"){//适用性别
					html+='<div class="tiaojianlist syxb"> <h2>适用性别</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
					
					html+='<span data="1"';
					if(prosex=="1"){html+='class="active"';}
					html+='>男性</span>';
					html+='<span data="2"';
					if(prosex=="2"){html+='class="active"';}
					html+='>已婚女</span>';
					html+='<span data="4"';
					if(prosex=="4"){html+='class="active"';}
					html+='>未婚女</span>';
					
					html+='<input id="prosex" type="hidden" value="'+prosex+'">';
					html+='</p></div><div class="fenjieline"></div>';
				}else if(conditionList[i]=="jgfw"){//价格范围
					html+='<div class="tiaojianlist jgfw"> <h2>价格范围</h2><p>';
					html+='<span data="" class="active" style="display:none;">不限</span>';
					
					html+='<span data="1"';
					if(price=="1"){html+='class="active"';}
					html+='>500以下</span>';
					html+='<span data="2"';
					if(price=="2"){html+='class="active"';}
					html+='>500-1000</span>';
					html+='<span data="3"';
					if(price=="3"){html+='class="active"';}
					html+='>1001-2000</span>';
					html+='<span data="4"';
					if(price=="4"){html+='class="active"';}
					html+='>2001-3000</span>';
					html+='<span data="5"';
					if(price=="5"){html+='class="active"';}
					html+='>3001-5000</span>';
					html+='<span data="6"';
					if(price=="6"){html+='class="active"';}
					html+='>大于5000</span>';
					
					html+='<input id="price" type="hidden" value="'+price+'" />';
					html+='</p></div><div class="fenjieline"></div>';
				}
			}
			html+=' <div class="sxtjtjdiv3"><input class="qzan" type="reset" value="重置" style="font-size:0.8rem" /><input class="tjan" type="button" value="确定" onclick="goSearch(1)" style="font-size:0.8rem"/></div>';
			html+='</form>';
			$("#"+obj).html(html);
		}
		
		$(".tiaojianlist span").click(function(){
			var thisSpan=$(this).parent("p").parent();
			if($(this).hasClass("active")){
				$(this).removeClass("active");
				if(thisSpan.hasClass("xzpp")){		$("#brandId").val("");
				}else if(thisSpan.hasClass("tclx")){$("#categoryidOne").val("");
				}else if(thisSpan.hasClass("synl")){$("#categoryidTwo").val("");
				}else if(thisSpan.hasClass("syxb")){$("#prosex").val("");
				}else if(thisSpan.hasClass("jgfw")){$("#price").val("");
				}else if(thisSpan.hasClass("yydj")){$("#categoryidFour").val("");
				}else if(thisSpan.hasClass("xzqy")){$("#country").val("");
				}
			}else{
	            $(this).addClass("active").siblings().removeClass("active");
	            if(thisSpan.hasClass("xzpp")){		$("#brandId").val($(this).attr("data"));
				}else if(thisSpan.hasClass("tclx")){$("#categoryidOne").val($(this).attr("data"));
				}else if(thisSpan.hasClass("synl")){$("#categoryidTwo").val($(this).attr("data"));
				}else if(thisSpan.hasClass("syxb")){$("#prosex").val($(this).attr("data"));
				}else if(thisSpan.hasClass("jgfw")){$("#price").val($(this).attr("data"));
				}else if(thisSpan.hasClass("yydj")){$("#categoryidFour").val($(this).attr("data"));
				}else if(thisSpan.hasClass("xzqy")){$("#country").val($(this).attr("data"));
				}
			}
        });
		$(".qzan").click(function(){
            $(".tiaojianlist span").removeClass("active");
            $("#brandId").val("");
            $("#categoryidOne").val("");
            $("#categoryidTwo").val("");
            $("#prosex").val("");
            $("#price").val("");
            $("#categoryidFour").val("");
            $("#country").val("");
        });
		$(".tjan").click(function(){
            $(".saixuantjbg1").hide();
            $(".sxbtn").removeClass("active");
            $(".prolist").show();
        });
		$(".moreB").click(function(){
	            $(this).children().toggleClass("direction");
	            $(".tjl2").toggleClass("moretitle1");
	    })
	    $(".moreA").click(function(){
	            $(this).children().toggleClass("direction");
	            $(".tjl1").toggleClass("moretitle1");
	    })
	    
	    //各个条件选中事件
	    /*$(".xzpp p span").click(function(){
	    		$("#brandId").val($(this).attr("data"));
	    })
	    $(".tclx p span").click(function(){
	    		$("#categoryidOne").val($(this).attr("data"));
	    })
	    $(".synl p span").click(function(){
	    		$("#categoryidTwo").val($(this).attr("data"));
	    })
	    $(".syxb p span").click(function(){
	    		$("#prosex").val($(this).attr("data"));
	    })
	    $(".jgfw p span").click(function(){
	    		$("#price").val($(this).attr("data"));
	    })
	    $(".yydj p span").click(function(){
	    		$("#categoryidFour").val($(this).attr("data"));
	    })
	    $(".xzqy p span").click(function(){
	    		$("#country").val($(this).attr("data"));
	    })*/
	    /*$("#categoryidOne").val(productType);
	    $("#brandId").val(brandId);
	    $("#categoryidTwo").val(productAge);
	    $("#country").val(country);
	    $("#categoryidFour").val(hospitalLevel);
	    $("#prosex").val(prosex);
	    $("#price").val(price);*/
	    
		 
	});
}
