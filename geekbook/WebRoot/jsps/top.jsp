<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>


<style type="text/css">
	body {
	background:#efefef url(../images/top_bg2.jpg) repeat top center;
		font:normal 82.5%/1.5 Helvetica, Arial, sans-serif;
		margin: 0px;
		color: #ffffff;
	}
	a {
	font:normal 82.5%/1.5 Helvetica, Arial, sans-serif;
		text-decoration:none;
		color: #000000;
		font-weight: 900;
	} 
	label{
	font:normal 82.5%/1.5 Helvetica, Arial, sans-serif;
		text-decoration:none;
		color: #000000;
		font-weight: 900;
	}
	a:hover {
		text-decoration: underline;
		color: #ffffff;
		font-weight: 900;
	}
</style>
  </head>
  
  <body >
<h1 style="text-align: center;color: #000000;">极客书城系统</h1>
<div style="font-size: 10pt; line-height: 10px;">

<%-- 根据用户是否登录，显示不同的链接 --%>
<c:choose>
	<c:when test="${empty sessionScope.sessionUser.activationCode }">
		  <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">极客会员登录</a> |&nbsp; 
		  <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">注册极客会员</a>&nbsp;&nbsp;|
		 
		  
		      
		    <label id="youke" >游客：</label>${sessionScope.sessionUser.loginname} &nbsp;&nbsp;|
		  <a href="<c:url value='/CartItemServlet?method=myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;
	</c:when>
	<c:otherwise>
		   <label> 极客会员：</label> ${sessionScope.sessionUser.loginname }&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/CartItemServlet?method=myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/jsps/user/pwd.jsp'/>" target="body">修改密码</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		  <a href="<c:url value='/jsps/contactUs.jsp'/>" target="_top">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;	
	
			
	</c:otherwise>
</c:choose>



</div>
  </body>
</html>
