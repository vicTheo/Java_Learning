$().ready(function(){
	for(var i=0;i<10;i++){
		
//		$("input[name='mybutton']").click(function(){
//			
//			alert("sss");
//		});
		
		$("input[name='mybutton']").unbind("click");
		//����ť��click�¼�
		/*$("input[name='mybutton']").bind("click",function(){
			alert("hello");
		});*/
		$("input[name='mybutton']").bind("click",function(){
			/*//��������
			$("input[name='mybutton']").trigger("getValue");*/
			
			/*
			 * ��һ������Ϊ�¼��� �ڶ�������Ϊ����Ϊ�Զ����¼��Ĳ���
			 */
			$("input[name='mybutton']").trigger("getValue",[5,6]);
			
			
		})
	}
	
	//�Զ����¼�
	$("input[name='mybutton']").unbind("getValue");
	/*//��������
	$("input[name='mybutton']").bind("getValue",function(){
		alert("getvalue");
	});*/
	
	//������
	$("input[name='mybutton']").bind("getValue",function(event,a,b){
		alert("getvalue"+a+b);
	});
});

