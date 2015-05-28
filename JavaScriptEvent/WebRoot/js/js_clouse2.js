$().ready(function(){
	for(var i=0;i<3;i++){
		//声明在for循环中的变量只有一个 一定要放在函数中
		(function(){
			var item = i;
			var $input = $("<input/>");
			$input.attr("type", "button");
			$input.val("button");
			$input.unbind("click");
			$input.bind("click", function(){
				alert(item);
			});
			$('#aa').append($input);
		})();
		
	}
	
}
);
