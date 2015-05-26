$().ready(function(){
	for(var i=0;i<10;i++){
		
//		$("input[name='mybutton']").click(function(){
//			
//			alert("sss");
//		});
		
		$("input[name='mybutton']").unbind("click");
		//给按钮绑定click事件
		/*$("input[name='mybutton']").bind("click",function(){
			alert("hello");
		});*/
		$("input[name='mybutton']").bind("click",function(){
			/*//不带参数
			$("input[name='mybutton']").trigger("getValue");*/
			
			/*
			 * 第一个参数为事件名 第二个参数为数组为自定义事件的参数
			 */
			$("input[name='mybutton']").trigger("getValue",[5,6]);
			
			
		})
	}
	
	//自定义事件
	$("input[name='mybutton']").unbind("getValue");
	/*//不带参数
	$("input[name='mybutton']").bind("getValue",function(){
		alert("getvalue");
	});*/
	
	//带参数
	$("input[name='mybutton']").bind("getValue",function(event,a,b){
		alert("getvalue"+a+b);
	});
});

