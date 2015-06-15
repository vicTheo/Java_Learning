<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<div class="divOuterFrame">
	<div class="divInnerFrame">欢迎使用SurveyDoor调查系统!</div>
</div>
<div class="divWhiteLine"></div>
<div class="divNavigatorOuterFrame">
	<div class="divNavigatorInnerFrame">
		<s:a action="LoginAction_toLoginPage.action">[首页]</s:a>&nbsp;
		<s:a action="SurveyAction_newSurvey.action">[新建调查]</s:a>&nbsp;
		<s:a action="SurveyAction_getMySurveys.action">[我的调查]</s:a>&nbsp;
		<s:a action="EngageSurveyAction_toEngageSurveyList">[参与调查]</s:a>&nbsp;
		<s:a action="RegAction_toRegPage.action">[用户注册]</s:a>&nbsp;
		[用户授权管理]&nbsp;
		[角色管理]&nbsp;
		[权限管理]&nbsp;
		[日志管理]&nbsp;
	</div>
</div>
<div class="divWhiteLine"></div>