<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery-checkbox.js"></script>
 <script type="text/javascript" src="js/jquery-ztree-2.5.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/user_list.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/privilege.js"></script>
 <link rel="stylesheet" type="text/css" href="zTreeStyle/zTreeIcons.css">
   <link rel="stylesheet" type="text/css" href="zTreeStyle/zTreeStyle.css">
<html>
<head>
    <title>用户列表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/css/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td><s:checkbox name="allcheck">全选</s:checkbox></td>
                <td width="100">用户名</td>
                <td width="100">所属部门</td>
                <td>岗位</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        	<s:iterator >
	            <tr class="TableDetail1 template">
	                <td><s:checkbox name="usercheck"></s:checkbox> </td>
	                <s:hidden name="uid"></s:hidden>
	                <td id="username"><s:property value="username"/></td>
	                <td><s:property value="department.dname"/></td>
	                <td>
						<s:iterator value="posts">
							<s:property value="pname"/>
						</s:iterator>
					</td>
	                <td><a onClick="" href="list.html">删除</a>
	                    <a href="saveUI.html">修改</a>
						<s:a>设置权限</s:a>
	                </td>
	            </tr>
            </s:iterator>
        </tbody>
    </table>
    
     <div id="TableTail">
        <div id="TableTail_inside">
            <s:a action="userAction_addUI"><img src="${pageContext.request.contextPath}/css/images/createNew.png" /></s:a>
        </div>
    </div>
    
    <div class="ItemBlock_Title1" id="userTitle" style="display: none;"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif"/>
        	<div id="userImage"></div>
        </div>
    <div class="ItemBlock_Title1" id="privilegeTitle" style="display: none;"><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/css/blue/images/item_point.gif" />选择权限</div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder" style="display: none;" id="privilegeContent">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<!--表头-->
					<thead>
						<tr align="LEFT" valign="MIDDLE" id="TableTitle">
							<td width="300px" style="padding-left: 7px;">
								<!-- 如果把全选元素的id指定为selectAll，并且有函数selectAll()，就会有错。因为有一种用法：可以直接用id引用元素 -->
								<input type="checkbox" id="allchecked" onchange=""/>
								<label for="cbSelectAll">全选</label>
							</td>
						</tr>
					</thead>
                   
			   		<!--显示数据列表-->
					<tbody id="TableData">
						<tr class="TableDetail1">
							<!-- 显示权限树 -->
							<td>
								<ul id='privilegeTree' class="tree">
							</td>
						</tr>
					</tbody>
                </table>
            </div>
        </div>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a id="savePrivilege"><image  onclick="" src="${pageContext.request.contextPath}/css/images/save.png"/></a>
        </div>
</div>

</body>
</html>
