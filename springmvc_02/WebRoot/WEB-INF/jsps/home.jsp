<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'home.jsp' starting page</title>

  </head>
  
  <body>
    This is my home page. <br>
    request传来的值：${requestScope.name}<br> 
    request传来的值：${name} <br>
    map集合中的值：${key.name}
  </body>
</html>
