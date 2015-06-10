<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

  </head>
  
  <body>
  <form action="<%=path%>/mywizardform.action" method="post">
     id:<input type="text" name="id"/ value="${requestScope.person.id}"><br>
     <input type="submit" name="_cancel"value="取消">
     <input type="submit" name="_target1"value="下一页">
  </form>
   
  </body>
</html>
