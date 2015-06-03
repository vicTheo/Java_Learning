$.ready(function(){
	$("input[type='image']").unbind("click");
	$("input[type='image']").bind("click",function(){
		if($("select[name='did' option:selected]").attr("value")==""){
			alert("部门不能为空！");
			return false;
		}else if($("select[name='did' option:selected]").attr("value")){
			alert("岗位不能为空！");
		     return false;
		}
		return true;
	});
});
