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
		  		$.post("userAjaxAction_ajax.action",params, function(data){
				$("#message").text(data);
				if(data=="该用户名可以使用"){
					$("#message").css("color","blue");
				}else{
					$("#message").css("color","blue");
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
