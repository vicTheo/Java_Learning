<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${topic.title}</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/main.css"> 
</head>
<body>

<%-- 简单搜索表单 --%>
<%@include file="/jsp/search/simpleSearchUI.jsp" %>

<%-- 菜单 --%>
<div class="menubar">
	<s:a action="articleAction_listTopic.action">主题列表</s:a>
</div>

<%--显示主题--%>
<table class="postList" cellspacing="0">
	<s:iterator value="#articleList">
	    <tr class="title">
	        <td width="20" class="num"><s:property value="id"/></td>
	        <td><s:property value="title"/></td>
	    </tr>
	    <tr class="content">
	        <td/>
	        <td><s:property value="content"/></td>
	    </tr>
    </s:iterator>
</table>

<div style="margin-bottom: 20px"></div>

</body>
</html>
