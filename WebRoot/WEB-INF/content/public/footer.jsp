<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(document).ready(function(){
	var url = "/core/user.checkLogin.do";
	$.getMyJSON(url,null,function(data){
		if(data.returncode == 0){
			$("#isNo").hide();
			$("#loginName").text(data.data);
			$("#isYes").show();
		}else{
			$("#isYes").hide();
			$("#isNo").show();
		}
	});
	
	
});
function loginOut()
{
	var url = "${ctx}/core/user.loginOut.do";
	$.getMyJSON(url,null,function(data){
		if(data.returncode == 0){
             window.location.href = "${ctx}/";
		}
	});
}

</script>
<div class="bottom">
     <div class="footer">         
         	 <div id="isNo">
         	 <p>
         	 	 <a href="#" onclick="location.href='/core/user.toPerCenPage.do'" class="footer_login">我的</a>
             <%--  <a href="${ctx}/core/user.login.do" class="footer_login">我的</a> 	<a href="${ctx}/core/user.toRegPage.do" class="footer_reg">注册</a> --%>
             	<a href="tel:4006616669" class="footer_cus">客服</a>
             </p>
         	 </div> 
         	<!--  <div id="isYes">
         	 	<p>
         	 	<a href="/core/user.valiLoginInfo.do" class="footer_login" id="loginName" style="color:#00afc7;"></a>
         	 	<a href = "javascript:void(0)" onclick="loginOut()" class="footer_loginOut">退出</a>
         	 	<a href="tel:4008557000" class="footer_cus">客服</a>
         	 	</p>
         	 </div> -->
         <p class="exp">国内领先的体检预约平台</p>
         <p>天问（深圳）医疗服务有限公司  版权所有</p>
     </div>
</div>