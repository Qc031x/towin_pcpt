<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>支付成功</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="/css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="/css/public.css" />
		<link rel="stylesheet" type="text/css" href="/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/css/list.css" />
		<link rel="stylesheet" type="text/css" href="/css/chooseTime.css" />
		<link rel="stylesheet" type="text/css" href="/css/pay_success.css" />
		<script src="/js/jquery.js" type="text/javascript"></script>
		<script src="/js/commonLib.js" type="text/javascript"></script>
	</head>
<body>
	<div class="header">
        <div class="header_left">
            <a href="javascript:history.go(-1)">返回</a>
        </div>
        <div class="header_info">支付成功页</div>
        <div class="header_right" onclick="changeMenu()">
            <div class="header_rbg"></div>
               <span>导航</span>
        </div>
    </div>
    <%@include file="/WEB-INF/content/public/top_menu.jsp" %>
	
	<div class="pay_success">
		<div class="success_tips">
				<span>恭喜您购买成功</span>
			</div>
		<div class="time-page" style="min-height: initial;">
			<ul>
				<li>订单号:${info.order.oid} </li>
				<li>订购时间:${info.order.payedTime} </li>
				<li>订购套餐:
					<c:forEach items="${info.productList }" var="productList">
						${productList.PRODUCTNAME }
					</c:forEach>
				</li>
				<li>支付金额:￥${info.order.surplus} </li>
			</ul>
			<div class="list-btn">
				<input type="button" id="" value="查看详情" class="btn btn-block" onclick="location.href='/core/order.toOrderDetail.do?oid=${info.order.oid}'"/>
			</div>
		</div>
		
	</div>
</body>
</html>