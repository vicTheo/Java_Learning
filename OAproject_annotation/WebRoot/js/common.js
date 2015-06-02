//var common={
//	myconfig:function(message){
//		return window.confirm(message);
//	}
//};
$().ready(function(){
	$("a").each(function(){
		if($(this).text()=="删除"){
			$(this).unbind("click");
			$(this).bind("click",function(){
				//common.myconfig("确定要删除吗？");
				// $(this).confirm("确定要删除吗？");
				$.confirm("确定要删除吗");
			});
		}
	});


//$.confirm({
//	message:"确定要删除吗？",
//	callback:
//	 function(){
//		alert("aaaa");
//	}
//});

});
