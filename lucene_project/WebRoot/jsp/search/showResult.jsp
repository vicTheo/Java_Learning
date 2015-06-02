<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>显示搜索结果</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/main.css"> 
</head>
<body>

<%-- 简单搜索表单 --%>
<%@include file="/jsp/search/simpleSearchUI.jsp" %>

<%-- 菜单 --%>
<div class="menubar">
	<s:a action="articleAction_listTopic.action">主题列表</s:a>
</div>



<%--显示搜索结果--%>
<s:iterator value="articleList">
    <table class="postList searchArticleResultList" cellspacing="0">
        <tr class="title">
            <td width="20">${title}</td>
        </tr>
        <tr class="content">
            <td>${content}</td>
        </tr>
    </table>
</s:iterator>

</body>
</html>
