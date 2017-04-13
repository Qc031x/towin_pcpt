/*日历*/

function jiazhairili() {
    var today = new Date();
    year = today.getFullYear();
    month = today.getMonth() + 1;
    thisDay = today.getDate();
    firstDay = today;
    firstDay.setDate(1);
    startDay = firstDay.getDay();
    oldyear = today.getFullYear();
    oldmonth = today.getMonth() + 1;
    week = today.getDay();

    chushirili(today, year, month, thisDay, startDay,week);
    var today2, year2, month2, thisDay2, startDay2, today3, year3, month3, thisDay3, startDay3;

    $(".riliqiean1").unbind("click").click(function () {
        var xlnian2 = $("#xlyear").html();
        var xlyue2 = $("#xlmonth").html();
        month3 = Number(xlyue2) - 1;
        if (month3 == 0) {
            if (xlnian2 == oldyear) {
                month = month3 = xlyue2;
                year = year3 = xlnian2;
            } else {
                month = month3 = 12;
                year = year3 = Number(xlnian2) - 1;
            }
        } else {
            if (xlnian2 == oldyear) {
                if (xlyue2 == oldmonth) {
                    month = month3 = Number(xlyue2);
                } else {
                    month = month3 = Number(xlyue2) - 1;
                }
            } else {
                month = month3 = Number(xlyue2) - 1;
            }
            year = year3 = Number(xlnian2);
        }
        today3 = new Date(year3 + "/" + month3 + "/01");
        startDay3 = today3.getDay();
        if (year3 == oldyear && month3 == oldmonth) {
            chushirili(today3, year3, month3, thisDay, startDay3);
        } else {
            chushirili(today3, year3, month3, 0, startDay3);
        }
    });
    
    $(".riliqiean2").unbind("click").click(function () {
    	
        var xlnian2 = $("#xlyear").html();
        var xlyue2 = $("#xlmonth").html();
        month3 = Number(xlyue2)+ 1;
        if (month3 == 13) {
            month3 = 1;
            month = month3;
            year3 = Number(xlnian2) + 1;
            year =year3;
        } else {
            month3 = Number(xlyue2) + 1;
            month =month3;
            year3 = xlnian2;
            year = year3;
        }
        today3 = new Date(year3 + "/" + month3 + "/01");
        startDay3 = today3.getDay();
        //alert(today3+"\n"+year3+"\n"+month3)
        chushirili(today3, year3, month3, 0, startDay3);
    });
}

function chushirili(today, year, month, thisDay, startDay) {

    $("#xlmonth").html(month);
    $("#xlyear").html(year);
    var monthDays = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) monthDays[1] = 29;
    daysOfCurrentMonth  = monthDays[today.getMonth()];
    $(".yue_rili .yue_day li").remove();
    for (i = 0; i < startDay; i++) {
        $(".yue_rili .yue_day").append("<li></li>");
    }
    if(daysOfCurrentMonth+startDay>35){
    	$(".yuyue_rili").css({height: "330px"})
    } else {
    	$(".yuyue_rili").css({height: "290px"})
    }
    
    var esid=$('#esid').val();
    var currDate=year+"-"+month+"-"+thisDay;
    
    jQuery.post("/core/product.getRiDataByEsid.do",{"esid":esid,"date":currDate}, function (data) {
	
    	 //DATEINFO=data[0]["args1"];
		 DATEREVINFO=data[0]["args2"];
		 //alert(DATEINFO+"\n"+DATEREVINFO)
    	for (i = 1; i <= daysOfCurrentMonth; i++) {
            if (i < thisDay) {
                $(".yue_rili .yue_day").append("<li class='over'>" + i + "</li>");
            } else if (i == thisDay) {
                $(".yue_rili .yue_day").append("<li class='today'>" + i + "<br><span>今天</span></li>");
            } else {
            	var temp=year+"-"+(month<10?'0'+month:month)+"-"+(i<10?'0'+i:i);
    			var textIsRev=getRevDayText(DATEREVINFO[i-1],temp,i);
                $(".yue_rili .yue_day").append(textIsRev);
    			//$(".yue_rili .yue_day").append("<li class='future' date=" + year + "-" + month + "-" + i + ">" + i + "<br><span class='zt1'>可约</span></li>");
                //$(".yue_rili .yue_day").append("<li class='disfuture' date="+year+"-"+month+"-"+i+">"+ i +"<br><span class='zt2'>已满,假日,休息,暂停预约</span></li>");
            }
        }
	});
    
    $(".yue_rili").each(function () {
        var geshu = $(this).find(".yue_day li").length;
        if (28 < geshu && geshu < 35) {
            for (var k = geshu; k < 35; k++) {
                $(this).find(".yue_day").append("<li class='disabled'></li>");
            }
        } else if (35 < geshu && geshu < 42) {
            for (var m = geshu; m < 42; m++) {
                $(this).find(".yue_day").append("<li class='disabled'></li>");
            }
        }
    });

    $(".yue_rili .yue_day li").click(function () {
        var dayIdx = $(this).index();
        var weeks = dayIdx%7;
        var week = "";
        switch (weeks){
            case 0:
                week = "星期日";
                break;
            case 1:
                week = "星期一";
                break;
            case 2:
                week = "星期二";
                break;
            case 3:
                week = "星期三";
                break;
            case 4:
                week = "星期四";
                break;
            case 5:
                week = "星期五";
                break;
            case 6:
                week = "星期六";
                break;
        }
        if($(this).attr("date")){
            $(".input1").text($(this).attr("date")+" "+week);
            $(".calenderbox,.yuyue_rili").fadeOut();
            $(window).off("touchmove");
        }
    })
}

var getRevDayText =function(text,temp,tempData,count){
    var textIsRev="可约";
	 textIsRev= "<li class='future' date="+temp+">"+ i +"<br><span class='zt1'>"+textIsRev+"</span></li>";
	 // " <span id=ykbackrl_"+temp+"  class='zt1'>"+textIsRev+"</span>";
	if(text=='2'){
		 	 
		 textIsRev="休息";
		 textIsRev= "<li class='future' date="+temp+">"+ i +"<br><span class='zt1' style='color: #FF8F00;'>"+textIsRev+"</span></li>";
		 // "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";
		
	}
	else if(text=='3'){
		 
		 textIsRev="已满";
		 textIsRev= "<li class='future' date="+temp+">"+ i +"<br><span class='zt1' style='color: #FF8F00;'>"+textIsRev+"</span></li>";
		// textIsRev= "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";	
		 
	}
	else if(text=='4'){
		 
		 textIsRev="暂停";
		 textIsRev= "<li class='future' date="+temp+">"+ i +"<br><span class='zt1' style='color: #FF8F00;'>"+textIsRev+"</span></li>";
		// textIsRev= "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";
	}
	else if(text=='5'){
		 
		 textIsRev="假日";
		 textIsRev= "<li class='future' date="+temp+">"+ i +"<br><span class='zt1' style='color: #FF8F00;'>"+textIsRev+"</span></li>";
		// textIsRev= "<span id=ykbackrl_"+temp+"  class='zt2'>"+textIsRev+"</span>";
	}else{
		if($("#lately").find("span").length<2){
			var nyr=temp.split("-");
			$("#lately").append("<span>"+nyr[1]+"月"+nyr[2]+"日、</span>");
		}else if($("#lately").find("span").length==2){
			var nyr=temp.split("-");
			$("#lately").append("<span>"+nyr[1]+"月"+nyr[2]+"日</span>");
		}
	}
	return textIsRev;
 }
