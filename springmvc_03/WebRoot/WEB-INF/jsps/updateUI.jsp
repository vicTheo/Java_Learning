<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>updateUI</title>
 
  </head>
  
  <body>
     <form action="<%=path%>/person/update">
     <input type="hidden" name="id" value="${person.id}">
     name:<input type="text" name="name" value="${person.name}">
     address:<input type="text" name="address"value="${person.address}">
     <input type="submit" value="submit">
     </form>
  </body>
</html>
