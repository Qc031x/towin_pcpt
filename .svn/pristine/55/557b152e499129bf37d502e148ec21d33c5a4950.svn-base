<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="/js/pagination.js" type="text/javascript "></script>
<head>
</head>
<body>
<div class="tcdPageCode paginList">
	<div id="pagination" class="pagination"></div>
</div>
<div class="pageBlank"></div>
</body>
<script type="text/javascript">
$(document).ready(function() {
	var url = window.location.href;
	initUI('${pages.pageNo}', '${pages.totalPages}',url);
});

//初始化分页
function initUI(pageNo,totalPages,url) {
	var n=new RegExp(/\d+$/).exec(url);
	var canshu = window.location.search;
	if(canshu==""){
		url = url + "?";
	}else{
		if(canshu.indexOf("page")!="-1"){
			var a = canshu.substring(canshu.indexOf("page")-1,canshu.length);
			var b = canshu.substring(0,canshu.indexOf("page")-1);
			url = url.replace(a, "");
			
			if(b.length==0){
				url = url + "?";
			}else{
				url = url + "&";
			}
		}else{
			url = url + "&";
		}
		
		
	}
    pagination({
        cur: pageNo,
        total: totalPages,
        len: 5,
        targetId: 'pagination',
        url:url,
        callback: function () {
            var me = this;
            var oPages = document.getElementsByClassName('page-index');
            for (var i = 0; i < oPages.length; i++) {
                oPages[i].onclick = function () {
                    initUI(this.getAttribute('data-index'), totalPages);
                }
            }
            $('.firstPage').click(function () {
                initUI(1, totalPages);
            });
            $('.lastPage').click(function () {
                initUI(totalPages, totalPages);
            });
        }
    });
}
</script>
</html>