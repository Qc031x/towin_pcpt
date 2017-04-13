<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="UTF-8"%>
<div class="page-sidebar nav-collapse collapse">
	<div class="row" style="padding-left: 10px;display:none" id="hospitalList">
			<select id="switchHospital" style="margin-top: 7px;margin-left: 17px;width:200px;"></select>
			<button class="btn btn-primary btn-sm" onclick="switchHospital()" style="background: #00b0ea;border-color: #00b0ea;padding: 3.5px 11px;">确定</button>
	 </div>
      <ul class="page-sidebar-menu">
          <li>
              <a class="sideblk blk" id="index" href="${ctx }/contentRoot/index.do">
                  <i class="home"><img src="/images/index-info.png"/></i>
                  <span class="title">首页</span>
                  <span class="selected"></span>
              </a>
          </li>

          <li>
              <a class="sideblk blk titactive" href="javascript:void(0);">
                  <i class="record"><img src="/images/hos-info.png"/></i>
                  <span class="title">医院（机构）管理</span>
                  <span class="arrowicon"><img src="/images/arrowup.png"/></span>
              </a>
              <ul class="sub-menu subactive">
                  <li id="jglb">
                      <a href="${ctx}/core/shop.toShopList.do">&nbsp;&nbsp;机构列表</a>
                  </li>
                  <li id="xzjg">
                      <a href="${ctx}/core/shop.toAddShop.do">&nbsp;&nbsp;新增分院（机构）</a>
                  </li>
                  <%--
                  <li id="ztjg">
                      <a href="${ctx}/core/shop.toPauseShopList.do">&nbsp;&nbsp;暂停分院（机构）列表</a>
                  </li>
                   --%>
              </ul>
          </li>


          <li>
            <a class="sideblk blk titactive" href="javascript:void(0);">
                <i class="record"><img src="/images/batch-info.png"/></i>
                <span class="title">团检套餐管理</span>
                <span class="arrowicon"><img src="/images/arrowup.png"/></span>
            </a>
            <ul class="sub-menu subactive">
              <li id="tclb">
                <a href="${ctx }/core/product.toSingleProductList.do">&nbsp;&nbsp;套餐列表</a>
                     <span class="listCount" id="left_pro_count"></span>
              </li>

              <%--
          
           <li>
            <a class="sideblk blk titactive" href="javascript:void(0);">
                <i class="record"><img src="/images/cogs.png"/></i>
                <span class="title">团检套餐管理</span>
                <span class="arrowicon"><img src="/images/arrowdown.png"/></span>
            </a>
            <ul class="sub-menu subactive">
              <li id="tclb">
                <a href="javascript:void(0);" onclick="go('productList.html?tclb')">&nbsp;&nbsp;普通套餐列表</a>
                              <span class="listCount">(34)</span>

              </li>

               --%>
              <li id="diyList">
                    <a href="${ctx }/core/product.diyProductList.do">&nbsp;&nbsp;自定义套餐列表</a>
              </li>
				<%--
              <li id="diy">
                <a href="${ctx }/core/product.toAddDiyProduct.do">&nbsp;&nbsp;新增自定义套餐</a>
              </li>
				 --%>

             <!--  <li id="tcts">
                <a href="javascript:void(0)">&nbsp;&nbsp;套餐推送</a>
              </li> -->
            </ul>
          </li>
          
          <li>
              <a class="sideblk blk titactive" href="javascript:void(0);">
                  <i class="record"><img src="/images/base-info.png"/></i>
                  <span class="title">基础信息管理</span>
                  <span class="arrowicon"><img src="/images/arrowup.png"/></span>
              </a>
              <ul class="sub-menu subactive">
                  <li id="tjpq">
                      <a href="${ctx }/core/cal.calendarSchedule.do">&nbsp;&nbsp;体检排期</a>
                  </li>
 				  <li id="mbgl">
                      <a href="${ctx }/core/template.toManageTemplate.do">&nbsp;&nbsp;模板管理</a>
                  </li>
                  <li id="ygcx">
                      <a href="${ctx }/core/employee.checkEmployeeInfo.do">&nbsp;&nbsp;员工信息查询</a>
                  </li>
                  <li id="bgsc">
                      <a href="${ctx }/core/report.toReportList.do">&nbsp;&nbsp;体检报告上传</a>
                  </li>
              </ul>
          </li>
          
          <li>
              <a class="sideblk blk titactive" href="javascript:void(0);">
                  <i class="record"><img src="/images/rev-info.png"/></i>
                  <span class="title">预约管理</span>
                  <span class="arrowicon"><img src="/images/arrowup.png"/></span>
              </a>
              <ul class="sub-menu subactive">
                  <li id="tjyy">
                      <a href="/core/reservation.findNoReservedList.do">&nbsp;&nbsp;团检预约</a>
                  </li>
              </ul>
          </li>
          
      </ul>
  </div>
<script type="text/javascript" src="/js/leftside.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	var currentLabel = '${currentLabel}';
	//高亮当前所在的标签 
	$("#" + currentLabel).addClass("titactive").siblings().removeClass();
	
	$.getMyJSON('${ctx}/core/product.getSingleProductCount.do',{},function(data){
		if (data.returncode == 0){
			$('#left_pro_count').text('['+data.data[0]+']');
		}
	});	
	var loginName = '${hsLoginUserInfo.loginName}';
	if(loginName.indexOf("tw_")!=-1){
		$.getMyJSON('${ctx}/core/login.findtwHospital.do',{},function(data){
			for(var i = 0;i<data.list.length;i++){
				var optionObject = document.createElement('option');
				optionObject.innerHTML = data.list[i].NAME;
				optionObject.setAttribute("value",data.list[i].CSID);
				$("#switchHospital").append(optionObject);
			}
			$("#switchHospital").val(data.csid);
		});
		$("#hospitalList").show();
	}
	
});

function switchHospital(){
	var csid = $("#switchHospital").val();
	window.location.href="/core/login.switchHospital.do?csid="+csid;
}
</script>