<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.jasig.cas.client.util.AbstractCasFilter"%>
<%@page import="java.security.Principal"%>
<%@page import="org.jasig.cas.client.util.AssertionHolder"%>
<%@page import="org.jasig.cas.client.validation.Assertion"%>
<%@page import="domain.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%=request.getClass().getName() %><br>
    <h2>获取登录名</h2>
    1.通过request.getRemoteUser()获得
    <%=request.getRemoteUser() %><br>
    2.通过request.getUserPrincipal()获得用户名
    <%=request.getUserPrincipal() %><br>
    3.通过Assertion获得用户名
    <%  Assertion as=AssertionHolder.getAssertion();
         Principal p=as.getPrincipal();
         out.print(p.getName());
     %><br>
     4.通过session对象获取
     <%
          as=(Assertion)session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
          p=as.getPrincipal();
          out.print(p.getName());
      %>
      <h2>获取更多数据</h2>
      <%
           Map map=as.getPrincipal().getAttributes();
           out.print(map);
           String address=(String)map.get("address");
           out.print("<br>address:"+URLDecoder.decode(address,"utf-8"));
       %>
       <br>
                   从session中获取user实体
       <%
          User user=(User)session.getAttribute("user");
          out.print(user);
        %>
  </body>
</html>
