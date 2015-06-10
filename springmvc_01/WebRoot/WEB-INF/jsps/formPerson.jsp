<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
   
    <title>My JSP 'formPerson.jsp' starting page</title>
    

  </head>
  
  <body >
  <form action="<%=path%>/myform.action" method="post">
    id:<input type="text" name="id"/><br>
    name:<input type="text" name="name"/><br>
    <input type="submit" value="提交"/>
    </form>
  </body>
</html>
