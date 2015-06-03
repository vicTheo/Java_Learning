var user = {
	formValidate: function(){
		$("input[type='image']").unbind("click");
		$("input[type='image']").bind("click", function(){
			if ($("select[name='did'] option:selected").attr("value") == "") {
				alert("请选择部门");
				return false;
			}
			else {
				if (!$("select[name='pids'] option:selected").attr("value")) {
					alert("请选择岗位");
					return false;
				}
				else {
					if ($("#message").text() == "该用户名不可用"|| $("#message").text() == ""||$("#message").text() == "用户名不能为空" ) {
						alert("用户名不可用");
						return false;
					}
					else {
						return true;
					}
				}
			}
			return true;
			
			
		});
	},
	initEvent: function(){
		$("input[name='username']").unbind("blur");
		$("input[name='username']").bind("blur", function(){
			user.checkUser($(this).val());
		});
	},
	checkUser: function(username){
		var params={
			username:username
		};
          if(params.username!=""){
//		  		$.post("userJsonAction_checkUserName.action",params, function(data){
//				$("#message").text(data.message);
//				if(data.message=="该用户名可以使用"){
//					$("#message").css("color","blue");
//				}else{
//					$("#message").css("color","blue");
//				}
//			});
			
			$.ajax({
				url:"userJsonAction_checkUserName.action",
				type:"POST",
				data: params,
				success: function(data){
					$("#message").text(data.message);
				if(data.message=="该用户名可以使用"){
					$("#message").css("color","blue");
				}else{
					$("#message").css("color","blue");
				}
				},
				error:function(){
					/*此处为后台错误后执行部分 与servlet结合时这部分功能可以用,servlet对异常
					 * 信息进行了定义，如405，404...而strtus2没有这样做。
					因为struts2自身设计的问题，与$.ajax结合时，这部分不能用
	                无论后台发生什么错误，都会执行success回调函数，只是struts2
	                的所有异常都会传递给一个错误模板，data只能得到这个模板，不能得到具体参数
	                解决办法：用spring的aop以service层为切点，以异常通知获取所有异常，然后把
	                异常信息放入值栈，在action层从值栈取出异常信息，赋给相应属性传给success回调函数
					*/
				}
				
				});
			
		  }else{
		  	$("#message").text("用户名不能为空");
		  }
		

	}
}
$().ready(function(){
	user.formValidate();
	user.initEvent();
	
});
