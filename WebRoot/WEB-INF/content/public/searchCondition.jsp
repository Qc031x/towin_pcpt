<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>

$(document).ready(function(){
	$("#condition").val('${condition }');
	$("#dqssxl").val('${type }');
})

function goSearch(){
	var searchType = $("#dqssxl").val();
	var condition = $("#condition").val().replace(/(^\s*)|(\s*$)/g, "");
	if(condition=="请输入关键词"){
		condition="";
	}
	if(searchType == "1"){
		window.location.href="/core/product.toMallProductSearch.do?cgVariable.condition="+condition;
	}else if(searchType == "2"){
		window.location.href="/core/product.toProductSearch.do?cgVariable.condition="+condition;
	}else if(searchType == "3"){
		window.location.href="/core/product.toBranchSearch.do?cgVariable.condition="+condition;
	}	
	
}   
</script>
<div class="header1">
    <a href="javascript:history.go(-1);"></a>
    <div class="searchBox">
        <select id="dqssxl">
            <option value="1">体检卡</option>
            <option value="2">套&nbsp;&nbsp;&nbsp;餐</option>
            <option value="3">机&nbsp;&nbsp;&nbsp;构</option>
        </select>
        <div class="inputBox">
            <input id="condition" class="headsosok" type="text" value="请输入关键词" onfocus="if( this.value=='请输入关键词'){this.value='';}" onblur="if( this.value==''){ this.value='请输入关键词';}" />
            <span onclick="goSearch();"></span>
        </div>
    </div>
</div>
