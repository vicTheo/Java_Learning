<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div style="margin: 15px auto; ">
<%-- 搜索表单 --%>
<s:form action="/articleAction_search.action" cssClass="simpleSearchForm">
    <font class="logoLabel">贴吧</font>
    <s:textfield name="queryString" cssClass="queryString"></s:textfield>
	<s:submit value="搜索"></s:submit>
</s:form>
</div>