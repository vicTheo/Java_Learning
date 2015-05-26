<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>管理员登陆界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="/adminjsps/login/css/lanrenzhijia.css" media="all">
<script src="/adminjsps/login/js/jquery.min.js"></script>
<script>
jQuery(document).ready(function($) {
	$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})
	function checkForm() {
			if(!$("#adminname").val()) {
				alert("管理员名称不能为空！");
				return false;
			}
			if(!$("#adminpwd").val()) {
				alert("管理员密码不能为空！");
				return false;
			}
			return true;
		}
</script>
  </head>
  
 <body>
 
<div class="theme-buy">
 <p style="font-weight: 900; color: red;" >${msg }</p><br>
<a class="btn btn-primary btn-large theme-login" href="javascript:;">管理员登陆&gt;&gt;</a>
</div>
<div class="theme-popover">
     <div class="theme-poptit">
          <a href="javascript:;" title="关闭" class="close">×</a>
          <h3>登录 是一种态度</h3>
     </div>
       
     <div class="theme-popbod dform">
           <form class="theme-signin" name="loginform" action="/AdminServlet" method="post" onsubmit="return checkForm()" target="_top">
               <input type="hidden" name="method" value="login"/>
                <ol>
                     <li><h4>你必须先登录！</h4></li>
                     <li><strong>账户：</strong><input class="ipt" type="text" name="adminname" id="adminname"value="" size="20" /></li>
                     <li><strong>密码：</strong><input class="ipt" type="password" name="adminpwd"id="adminpwd" value="" size="20" /></li>
                     <li><input class="btn btn-primary" type="submit" name="submit" value="进入后台 " /></li>
                </ol>
           </form>
     </div>
</div>
<div class="theme-popover-mask"></div>

</body>
</html>
