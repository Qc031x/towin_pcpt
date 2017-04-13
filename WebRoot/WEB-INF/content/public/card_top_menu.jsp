<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav class="clearfix">
			<div class="nav-left" onclick="history.go(-1);">
				<img src="/images/left.png" />
				<span>返回</span>
			</div>
			<div class="nav-mid">
				<!--<ul class="clearfix">
					<li class="myActive">体检机构</li>
					<li>体检套餐</li>
				</ul>-->
				<p>体检卡套餐</p>
			</div>
			<div class="nav-right">
				<img src="/images/list.png" />
				<span>导航</span>
			</div>
		</nav>
		<!--遮罩层-->
		<div id="model_box2">
			<div class="indexNav">
			<ul class="list clearfix">
			<li onclick="location.href='http://r.51towin.com/'">
				<img src="/images/home.png" />
				<p>首页</p>
			</li>
			<li onclick="onclickCity();">
				<img src="/images/csqh.png" />
				<p>${cityname_pcpt}<span id="qiehuan">|切换</span></p>
			</li>
			<li onclick="location.href='/core/shop.toEntityShopListPage.do'">
				<img src="/images/hospital.png" />
				<p>体检机构</p>
			</li>
			<li onclick="location.href='/core/product.toProductListPage.do'">
				<img src="/images/tjtc.png" />
				<p>体检套餐</p>
			</li>
			<li  onclick="location.href='/content/product.cardList.do'">
				<img src="/images/card.png" />
				<p>体检卡</p>
			</li>
			<li>
				<img src="/images/key.png" />
				<p>卡密预约</p>
			</li>
			<li>
				<img src="/images/report.png" />
				<p>报告查询</p>
			</li>
			
			<li onclick="location.href='/core/user.toPerCenPage.do'">
				<img src="/images/people.png" />
				<p>个人中心</p>
			</li>
				</ul>
			</div>

		</div>

<script type="text/javascript">
function onclickCity(){
	if($("#showChangeCity").val() == "true"){
		location.href='/core/login.goCity.do';
	}
}
</script>