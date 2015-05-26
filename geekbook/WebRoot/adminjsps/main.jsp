<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>极客书城后台</title>
    
 <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/css/main.css'/>">
  </head>
  <body>
<table class="table" align="center">
	<tr>
		<td colspan="2" align="center" height="100px;">
			<iframe frameborder="0" src="<c:url value='/adminjsps/top.jsp'/>" name="top"></iframe>
		</td>
	</tr>
	<tr>
		<td>
		<c:choose>
		<c:when test="${empty sessionScope.admin}">
			<iframe frameborder="0" src="<c:url value='/adminjsps/login/login.jsp'/>" name="body"></iframe>
		</c:when>
		<c:otherwise>
		<iframe frameborder="0" style="background:#efefef url(/images/background.png) repeat top center;" name="body"></iframe>
		</c:otherwise>
		</c:choose>
		</td>
	</tr>
</table>
  </body>
</html>
