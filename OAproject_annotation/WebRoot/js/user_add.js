$.ready(function(){
	$("input[type='image']").unbind("click");
	$("input[type='image']").bind("click",function(){
		if($("select[name='did' option:selected]").attr("value")==""){
			alert("���Ų���Ϊ�գ�");
			return false;
		}else if($("select[name='did' option:selected]").attr("value")){
			alert("��λ����Ϊ�գ�");
		     return false;
		}
		return true;
	});
});
