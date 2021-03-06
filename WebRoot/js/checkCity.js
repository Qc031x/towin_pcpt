
$(document).ready(function(){
	citylist();
	cityInit();
})

function cityInit(){
	
	var numbers = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25];
    var zimu = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];

    //城市首字母点击切换
    $('.initials_title').click(function(){
        $(this).removeClass().addClass('initials_title_');//样式切换
        var bro = $(this).parent('li').siblings('li').children('a');//获得同级标签
        var bro_ = $(this).parent('li').parent('ul.initials').siblings('ul.initials').children('li').children('a');//获得父级标签
        bro.removeClass().addClass('initials_title');//同级其他元素恢复样式
        bro_.removeClass().addClass('initials_title');//父级其他元素恢复样式
        
        //tab切换
        var thisID = $(this).attr('id').split('_')//分割当前ID
        var flag = thisID.length>0?thisID[2]:'';
        if(flag.length>0){
            var content =$('#showinitials_' +zimu[flag-1])
            content.show();
            content.siblings('.city').hide();
            content.find('a').eq(0).mouseover();//默认选中第一项
        }
    });
    
    $('.all_city').find('a').eq(0).click();
    $(".select_city_list").on("click","ul li a",function(){
        if($(this).hasClass("active")){
            $(this).removeClass("active");
        }else{
            $(this).addClass("active").parent().siblings().find("a").removeClass("active");
        }
        
    })
}

function citylist(){
	var numbers = [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25];
	var zimu = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
	for(var i = 0;i<zimu.length;i++){
		var boolean = false;
		for(var j = 0;j<allcity.length;j++){
			if(allcity[j].pinyin.substr(0,1) == zimu[i]){
				boolean = true;
				$("#showinitials_"+zimu[i]+"").append("<li><a href='javascript:void(0);' onclick='updateCity("+allcity[j].ID+",this.text)'>"+allcity[j].TITLE+"</a></li>");
			}
		}
		
		if(boolean == false){
			var a = $("#sub_initials_"+numbers[i+1]+"").text();
			$("#sub_initials_"+numbers[i+1]+"").parent().text(a);
		}
		
	}
	
} 

function updateCity(id, cityName){
	if(id == null || cityName == null)return;
	var data = {"city":id, "cityName":cityName};
	var url = "/core/login.updateCity.do";
	$.getMyJSON(url, data, function(data){
		if(data=='true'){
			window.location.href = document.referrer
		}else{
			alert(data.errmsg);	
		}
	});
}