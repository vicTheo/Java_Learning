<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>主题列表</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $(function() {
            $("tbody.list>tr:even").css("backgroundColor", "#aaaaaa");
        });
    </script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/style/main.css">
</head>
<body>

<%-- 简单搜索表单 --%>
<%@include file="/jsp/search/simpleSearchUI.jsp" %>

<%-- 菜单 --%>
<div class="menubar">
</div>

<table cellspacing="0">
<tbody class="list topicList">
    <%--显示表头--%>
    <tr class="title">
        <td width="25">编号</td>
        <td width="500">标题</td>
    </tr>

    <%-- 显示部门列表 --%>
   <s:iterator value="#articleList">
        <tr class="data">
            <td class="num"><s:a action="articleAction_queryById?id=%{id}"><s:property value="id"/></s:a></td>
            <td class="num">${title}</td>
        </tr>
    </s:iterator>
    </tbody>
</table>

<div style="margin-bottom: 15px"></div>

<%--发表主题表单--%>
<s:form action="articleAction_saveArticle.action">
    <table class="publishArticleForm">
        <tr>
            <td>标　题:</td>
            <td><s:textfield name="title" cssClass="title"></s:textfield></td>
        </tr>
        <tr>
            <td>内　容:</td>
            <td><s:textarea name="content" cssClass="content"></s:textarea></td>
        </tr>
        <tr>
            <td/>
            <td><s:submit value="发表"></s:submit></td>
        </tr>
    </table>
</s:form>

</body>
</html>
