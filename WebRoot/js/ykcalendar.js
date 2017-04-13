/*
 * 日历控件使用指南
 * 1、页面中需要使用日历控件则需要在Jsp的<head>部分引入：
 * <script type="text/javascript" src="${ctx}/js/ykcalendar.js"></script>
 * 2、日历控件通过获取页面标签id为page_esid获取门店id的值（必须提供），通过获取页面标签id为pid获取产品id的值（根据功能需求选择提供）
 * 3、根据使用场景的不同提供两种方式初始化日历
 * (1)根据产品查看与产品相关各门店的预约日历通过调用方法ykrili.init()完成日历初始化
 * (2)查看门店的预约日历通过调用ykrili.getjiazairili()完成日历初始化
 * 4、提供日历可选中功能，需向初始化方法传值1，如ykrili.getjiazairili(1)
 */
function ykcalendar(){
  var stylenum=0;
  this.init=function(type){
		  stylenum++;
		  if(stylenum<2 && type==1){
			  $("head").append("<style type='text/css'>.yue_rili .yue_day li.future{cursor:pointer;} .yue_rili .yue_day li.future:hover{background:#eee;}</style>");
		  }
		  $(".lookrili").click(function(){
		    var url = "/core/product.findAllMedicalByPid.do",
			data = {"product.pid" : $("#pid").val()},
			pid = $("#pid").val();
		
		$.getMyJSON(url,data,function(data){
			$(".mdxzan .mdcitylist a").remove()
			var obj = data.data[0];
			var objName = data.data[0][0].CITY;
			var objId = data.data[0][0].ID;
			//alert(objName)
			$(".mdxzan .mdcitylist").append("<a onclick='ykrili.getentityshop("+objId+","+pid+")' class='active' href='javascript:void(0)'>"+objName+"</a>")
			for(var i = 1; i < obj.length; i++){
				var CITY = obj[i].CITY;
				$(".mdxzan .mdcitylist").append("<a onclick='ykrili.getentityshop("+obj[i].ID+","+pid+")' href='javascript:void(0)'>"+CITY+"</a>")
			}
			$(".fixrilitmbg .mdxzan .mdcitylist a").click(function(){
				$(this).addClass("active").siblings("a").removeClass("active");
			});
			getEntityshop(objId,pid);
		});
	});
  }
  this.getjiazairili=function(type){
	  stylenum++;
	  if(stylenum<2 && type==1){
		  $("head").append("<style type='text/css'>.yue_rili .yue_day li.future{cursor:pointer;} .yue_rili .yue_day li.future:hover{background:#eee;}</style>");
	  }
      $(".riliqiean1").remove();
	  $(".riliqiean2").remove();
	  $(".riliyear").before("<div class='riliqiean1'></div>");
	  $(".riliyear").after("<div class='riliqiean2'></div>");
	  jiazhairili();
  }
  var getEntityshop=function(id,pid){
	  //alert(id+","+pid)
	var url = "/core/product.findMedicalList.do",
		data = {"cgVariable.pid" : pid, "cgVariable.id" : id};
	
	//return false;
	$.getMyJSON(url,data,function(data){
		$(".mdlist div").remove();
		var obj = data.data[0];

		for(var i = 0; i < obj.length; i++){
			var temp = obj[i].BUSINESS_TIME == null ? "" : "　("+obj[i].BUSINESS_TIME+")";
			var tel = obj[i].TEL == null ? "" : "　联系电话："+obj[i].TEL;
			var teltemp = tel.split("（");

			var text = "";
			text += '<div><table style="width: 100%;" class="list">';
			text += '<tr>';
			text += '<td class="ename">【' + obj[i].BNAME + '】' + obj[i].NAME + temp + '</td>';
			text += '<td rowspan="3" style="width: 120px; align=right;"><span esid='+obj[i].ESID+' class="checkrili">查看可预约日期 </span></td>';
			text += '</tr>';
			text += '<tr>';
			text += '<td>&nbsp;&nbsp;&nbsp;地址：' + obj[i].ADDRESS + '</td>';
			text += '</tr>';
			text += '<tr>';
			text += '<td>&nbsp;&nbsp;&nbsp;' + teltemp[0].trim() + '</td>';

			if (teltemp.length == 2) {
				text += '<tr>';
				text += '<td>&nbsp;&nbsp;&nbsp;' + teltemp[1].substr(0, teltemp[1].length - 1) + '</td>';
				text += '</tr>';
			}

			text += '</tr>';
			text += '</table></div>';
			$(".mdlist").append(text);
			//$(".mdlist").append("<div class='list'><p><a target='_blank' href='/core/product.findMedicalinstitutionProduct.do?cgVariable.esid="+data[i].ESID+"'>【"+data[i].BNAME+"】"+data[i].NAME+"</a></p><p>地址："+data[i].ADDRESS+"</p><span esid="+data[i].ESID+" class='checkrili'>查看可预约日期</span></div>");
			//$(".mdlist").append("<div class='list'><p>【"+obj[i].BNAME+"】"+obj[i].NAME + temp +"</p><p>地址："+obj[i].ADDRESS+tel+"</p><span esid="+obj[i].ESID+" class='checkrili'>查看可预约日期</span></div>");
		}

		  if($(".mdxzan .mdcitylist a").length < 10){
			  $(".mdxzan span").remove()
		  }
		//jiazhairili();
			$(".fixrilitmbg .mdlist .list .checkrili").click(function(){
				
			$(".fixrilitmbg .fenyuantit").text($(this).parents(".list").find(".ename").text());
			$(".fixrilitmbg .fixmddiv").fadeOut();
			$(".fixrilitmbg .rilidiv").fadeIn();
			//alert($(this).attr("esid"))
           $("#page_esid").val($(this).attr("esid"));
           $(".rilidiv").addClass("unclick");
            $(".riliqiean1").remove();
	   		$(".riliqiean2").remove();
	   		$(".riliyear").before("<div class='riliqiean1'></div>");
	   		$(".riliyear").after("<div class='riliqiean2'></div>");
           jiazhairili();
		});
	});
  }
  this.getentityshop=function(id,pid){
	  getEntityshop(id,pid);
  }	
  var jiazhairili=function(){
	  var today = new Date();
	  year = today.getFullYear();
	  month = today.getMonth()+1;
	  thisDay = today.getDate();
	  firstDay = today;
	  firstDay.setDate(1);
	  startDay = firstDay.getDay();
	  oldyear= today.getFullYear();
	  oldmonth= today.getMonth()+1;
	  //$("#xlyear").empty();
	  /*for(var nian=year;nian<2100;nian++){
		  $("#xlyear").append("<option id='y_"+nian+"' value='"+nian+"'>"+nian+"</option>");
	  }*/
	  //$("#xlmonth").empty();
	  /*for(var yuexl=1;yuexl<13;yuexl++){
		  $("#xlmonth").append("<option id='m_"+yuexl+"' value='"+yuexl+"'>"+yuexl+"</option>");
	  }*/
	  var xyyue=oldmonth-1;
	  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
	  chushirili(today,year,month,thisDay,startDay);
	  var today2,year2,month2,thisDay2,startDay2,today3,year3,month3,thisDay3,startDay3;
	
	  $(".riliqiean1").click(function(){
		  var xlnian2=$("#xlyear").html();
		  var xlyue2=$("#xlmonth").html();
		  $("#xlmonth option").removeAttr("disabled");
		  month3=Number(xlyue2)-1;
		  if(month3==0){
			  if(xlnian2==oldyear){
				  month=month3=xlyue2;
				  year=year3=xlnian2;
				  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
			  }else{
				  month=month3=12;
				  year=year3=Number(xlnian2)-1;
				  if(year3==oldyear){
					  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
				  }
			  }
		  }else{
			  if(xlnian2==oldyear){
				  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
				  if(xlyue2==oldmonth){
					  month=month3=Number(xlyue2);
				  }else{
					  month=month3=Number(xlyue2)-1;
				  }
			  }else{
				  month=month3=Number(xlyue2)-1;
			  }
			  year=year3=Number(xlnian2);
		  }
		  $("#xlyear").html(year3);
		  $("#xlmonth").html(month3);
		  today3 = new Date(year3+"/"+month3+"/01");
		  startDay3 = today3.getDay();
		  if(year3==oldyear && month3==oldmonth){
		  	  chushirili(today3,year3,month3,thisDay,startDay3);
		  }else{
			  chushirili(today3,year3,month3,0,startDay3);
		  }
	  })
		
	  $(".riliqiean2").click(function(){
		  var xlnian2=$("#xlyear").html();
		  var xlyue2=$("#xlmonth").html();
		  $("#xlmonth option").removeAttr("disabled");
		  month3=Number(xlyue2)+1;
		  if(month3==13){
			  month=month3=1;
			  year=year3=Number(xlnian2)+1;
		  }else{
			  month=month3=Number(xlyue2)+1;
			  year=year3=xlnian2;
			  if(year==oldyear){
				  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
			  }
		  }
		  $("#xlyear").html(year3);
		  //alert(month3)
		  $("#xlmonth").val(month3);
		  today3 = new Date(year3+"/"+month3+"/01");
		  startDay3 = today3.getDay();
		  chushirili(today3,year3,month3,0,startDay3);
	  })
	
	  $("#xlyear").change(function(){
		  $("#xlmonth option").removeAttr("disabled");
		  var xlnian=$("#xlyear").val();
		  var xlyue=$("#xlmonth").val();
		  if(xlnian==oldyear){
			  if(xlyue<=oldmonth){
				  xlyue=oldmonth;
				  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
				  today2 = new Date(xlnian+"/"+xlyue+"/01");
				  month=month2=xlyue;
				  year=year2=xlnian;
				  startDay2 = today2.getDay();
				  chushirili(today2,year2,month2,thisDay,startDay2);
			  }else{
				  today2 = new Date(xlnian+"/"+xlyue+"/01");
				  month=month2=xlyue;
				  year=year2=xlnian;
				  startDay2 = today2.getDay();
				  chushirili(today2,year2,month2,0,startDay2);
			  }
		  }else{
			  today2 = new Date(xlnian+"/"+xlyue+"/01");
			  month=month2=xlyue;
			  year=year2=xlnian;
			  startDay2 = today2.getDay();
			  chushirili(today2,year2,month2,0,startDay2);
		  }
	  })
	
	  $("#xlmonth").change(function(){
		  var xlnian=$("#xlyear").val();
		  var xlyue=$("#xlmonth").val();
		  today2 = new Date(xlnian+"/"+xlyue+"/01");
		  month=month2=xlyue;
		  year=year2=xlnian;
		  if(xlnian==oldyear){
			  if(xlyue<=oldmonth){
				  xlyue=oldmonth;
				  $("#xlmonth option:lt("+xyyue+")").attr("disabled",true);
				  today2 = new Date(xlnian+"/"+xlyue+"/01");
				  month=month2=xlyue;
				  year=year2=xlnian;
				  startDay2 = today2.getDay();
				  chushirili(today2,year2,month2,thisDay,startDay2);
			  }else{
				  today2 = new Date(xlnian+"/"+xlyue+"/01");
				  month=month2=xlyue;
				  year=year2=xlnian;
				  startDay2 = today2.getDay();
				  chushirili(today2,year2,month2,0,startDay2);
			  }
		  }else{
			  today2 = new Date(xlnian+"/"+xlyue+"/01");
			  month=month2=xlyue;
			  year=year2=xlnian;
			  startDay2 = today2.getDay();
			  chushirili(today2,year2,month2,0,startDay2);
		  }
	  });
	
	  for(i=0;i<24;i++){
		  $(".zttimes").append("<option value='"+i+"'>"+i+"</option>");
	  };
	  for(i=0;i<60;i++){
		  $(".zttimef").append("<option value='"+i+"'>"+i+"</option>");
		  $(".zttimem").append("<option value='"+i+"'>"+i+"</option>");
	  };
	}
	
  var initRiData =function(year,month,thisDay,startDay){
	 //beg 通过json改变日历
	var currDate=year+"-"+month+"-"+thisDay;
	//alert(currDate)
	var esid=$('#page_esid').val();
	jQuery.post("/core/product.getRiDataByEsid.do",
			{
			   "esid":esid,"date":currDate
			}, function (data) {
				 //alert(data[0]["args2"])
				 DATEINFO=data[0]["args1"];
				 DATEREVINFO=data[0]["args2"];
				 // alert(DATEINFO+"\n"+DATEREVINFO)
									 
	                   var today2 = new Date();
						
	                   $(".yue_rili .yue_day li").remove();
	                   for (i=0; i<startDay; i++) {$(".yue_rili .yue_day").append("<li></li>");}
						for (i=1; i<=DATEINFO.length; i++) {
							if (i < thisDay){
								$(".yue_rili .yue_day").append("<li class='over'>"+ i +"</li>");
							}else if(i == thisDay&&year==today2.getFullYear()&&(month==today2.getMonth()+1)){
								$(".yue_rili .yue_day").append("<li class='today'>"+ i +"<br><span>今天</span></li>");
							}else{
								var temp=year+"-"+(month<10?'0'+month:month)+"-"+(i<10?'0'+i:i);
								//0 今天以前  1 今天以后可以约。 2今天以后可以约，但由于假日策略已经使其变为”休息“了，
								//3 默认策略的人数导致其“已满”，4 默认策略（如下班时间到18：00后）导致其“暂停预约”，5节假日策略导致
								var textIsRev=getRevDayText(DATEREVINFO[i-1],temp,i)
								 $(".yue_rili .yue_day").append(textIsRev);
								//$(".yue_rili .yue_day").append("<li class='future' date="+temp+">"+ i +"<br>"+textIsRev+"</li>");
								//$(".yue_rili .yue_day").append("<li class='future' date="+year+"-"+month+"-"+i+">"+ i +"<br><span class='zt1'>可约</span><a href='#tanbianjidiv'><em>编辑</em></a></li>");
								//$(".yue_rili .yue_day").append("<li class='disfuture' date="+year+"-"+month+"-"+i+">"+ i +"<br><span class='zt2'>已满,假日,休息,暂停预约<span><a href='#tanbianjidiv'><em>编辑</em></a></li>");
							
								}
						}
						$(".yue_rili").each(function(){
							var geshu=$(this).find(".yue_day li").length;
							if(28<geshu &&　geshu<35){
								for(var k=geshu;k<35;k++)
								{$(this).find(".yue_day").append("<li class='disabled'></li>");};
							}else if(35<geshu &&　geshu<42){
								for(var m=geshu;m<42;m++)
								{$(this).find(".yue_day").append("<li class='disabled'></li>");}
							}
						})
						/*if($(".banduansfy").hasClass("checklookrili")){
							$(".yue_rili .yue_day li.future").click(function(){
								$(".checklookrili").val($(this).attr("date"));
								$(".fixrilitmbg").fadeOut();
								$(".checklookrili").show();
							})
						}*/
						if(!$(".rilidiv").hasClass("unclick")){
							$(".yue_rili .yue_day li.future").click(function(){
								$("#cal").val($(this).attr("date"));
								$(".calenderbox").fadeOut();
							})
						}
						
						//$(".yue_rili .yue_day li a").fancybox({'autoScale':false});
				} 
	        );
	//end   通过json改变日历
  }

  var getRevDayText =function(text,temp,tempData,count){
     var textIsRev="可约";
	 
	 textIsRev= "<li style='cursor: pointer;' class='future' date="+temp+">"+ i +"<br>"+" <span id=ykbackrl_"+temp+"  class='zt1'>"+textIsRev+"</span>"+"</li>";
	 // " <span id=ykbackrl_"+temp+"  class='zt1'>"+textIsRev+"</span>";
	if(text=='2'){
		 	 
		 textIsRev="休息";
		 textIsRev= "<li class='disfuture' date="+temp+">"+ i +"<br>"+"<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>"+"</li>";
		 // "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";
		
	}
	else if(text=='3'){
		 
		 textIsRev="已满";
		 textIsRev= "<li class='disfuture' date="+temp+">"+ i +"<br>"+"<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>"+"</li>";
		// textIsRev= "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";	
		 
	}
	else if(text=='4'){
		 
		 textIsRev="暂停";
		 textIsRev= "<li class='disfuture' date="+temp+">"+ i +"<br>"+"<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>"+"</li>";
		// textIsRev= "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";
	}
	else if(text=='5'){
		 
		 textIsRev="假日";
		 textIsRev= "<li class='disfuture' date="+temp+">"+ i +"<br>"+"<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>"+"</li>";
		// textIsRev= "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";
	}

	return textIsRev;
  }
  
  var chushirili =function(today,year,month,thisDay,startDay){
	  //$(".shuchu").text(today+","+year+","+month+","+thisDay+","+startDay);
	//$("#xlyear option").attr("selected",false);
	//$("#xlyear option[id=y_"+year+"]").attr("selected",true);
	//$("#xlmonth option").attr("selected",false);
	//$("#xlmonth option[id=m_"+month+"]").attr("selected",true);
	  $("#xlyear").html(year);
	  $("#xlmonth").html(month);
	
	var monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monthDays[1] = 29;
	daysOfCurrentMonth = monthDays[today.getMonth()];
	$(".yue_rili .yue_day li").remove();
	for (i=0; i<startDay; i++) {$(".yue_rili .yue_day").append("<li></li>");}
	/*for (i=1; i<=daysOfCurrentMonth; i++) {
		if (i < thisDay){
			$(".yue_rili .yue_day").append("<li class='over'>"+ i +"</li>");
		}else if(i == thisDay){
			$(".yue_rili .yue_day").append("<li class='today'>"+ i +"<br><span>今天<span></li>");
		}else{
			$(".yue_rili .yue_day").append("<li class='future' date="+year+"-"+month+"-"+i+">"+ i +"<br><span class='zt1'>可约</span></li>");
			
		}
	}*/
	//beg
	//var currDate=year+"-"+month+"-"+thisDay;
	initRiData(year,month,thisDay,startDay);
	//end
	
  }  
}
var ykrili=new ykcalendar();

