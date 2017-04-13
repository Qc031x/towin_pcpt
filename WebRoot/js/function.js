$(document).ready(function(){
	$(".preview").change(function(){
		var j = $(this).attr("url-data");
		var v = $(this).val();
		var filepath = "fileName" + j;
		var imgpath = "previewImg" + j;
		var img = document.getElementById(imgpath);
		var file = document.getElementById("fileName"+j).files[0];
		var suffix = file.name.substring(file.name.lastIndexOf(".")+1,file.name.length);
		if (!/png|jpeg|jpg|gif|bmp/i.test(suffix)){
			alert("请上传格式正确的图片!!");
			document.getElementById('previewImg'+j).src = '';
			return false;
		}
		var flag = checkfile(filepath, imgpath);
		if(flag){
			$("#changeImg"+j).val(v);
			$("#imgUrl"+j).val(v);
			var src = window.URL.createObjectURL(file);
			img.src = src;
		}
	});	
});

var maxsize = 1*1024*512;//500K
var errMsg = "上传图片大小要求不大于500k，请选择其他图片或进行裁剪！";
var tipMsg = "您的浏览器暂不支持计算上传文件的大小，确保上传文件不要超过500k，建议使用IE、FireFox、Chrome浏览器。";
var browserCfg = {};
var ua = window.navigator.userAgent;
if (ua.indexOf("MSIE") >= 1){
	browserCfg.ie = true;
}else if(ua.indexOf("Firefox") >= 1){
	browserCfg.firefox = true;
}else if(ua.indexOf("Chrome") >= 1){
	browserCfg.chrome = true;
}

//file 文件域ID  img 预览图片img标签ID （可选） 
function checkfile(file, img){
	try{
	 	
		var obj_file = document.getElementById(file);
	 	if(obj_file.value==""){
	 		alert("请先选择上传文件");
	 		return false;
	 	}
	 	var filesize = 0;
	 	if(browserCfg.firefox || browserCfg.chrome ){
	 		filesize = obj_file.files[0].size;
	 	}else if(browserCfg.ie){
	 		var obj_img = document.getElementById(img);
	 		obj_img.dynsrc=obj_file.value;
	 		filesize = obj_img.fileSize;
	 	}else{
	 		alert(tipMsg);
	 		return false;
	 	}
	 	if(filesize==-1){
	 		alert(tipMsg);
	 		return false;
	 	}else if(filesize > maxsize){
	 		alert(errMsg);
	 		return false;
		}
	 	return true;
	}catch(e){
		alert(e);
	}
}