$().ready(function(){
	for(var i=0;i<3;i++){
		//������forѭ���еı���ֻ��һ�� һ��Ҫ���ں�����
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
