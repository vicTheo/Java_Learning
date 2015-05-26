<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>top</title>
    <base target="body">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {font-size: 10pt;}
	a {
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
	span{
	font:normal 82.5%/1.5 Helvetica, Arial, sans-serif;
		text-decoration:none;
		color: #000000;
		font-weight: 900;
	}
</style>
  </head>
  
  <body style="background:#efefef url(/images/top_bg2.jpg) repeat top center;">
<h1 style="text-align: center; line-height: 30px;">极客书城系统后台管理</h1>
<div style="line-height: 10px;">
	<span>管理员：${sessionScope.admin.adminname }</span>&nbsp;|
	<a target="_top" href="<c:url value='/AdminServlet?method=quit'/>">退出</a>&nbsp;|
	<span style="padding-left:50px;">
		<a href="<c:url value='/admin/AdminCategoryServlet?method=findAll'/>">分类管理</a>&nbsp;|
		<a href="<c:url value='/adminjsps/admin/book/main.jsp'/>">图书管理</a>&nbsp;|
		<a href="<c:url value='/admin/AdminOrderServlet?method=findAll'/>">订单管理</a>
	</span>
</div>
  </body>
</html>
