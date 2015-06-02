(function(jQuery){
//	$.fn.confirm=function(message){
//		return window.confirm(message);
//	}
	
	$.confirm=function(message){
		return window.confirm(message);
	}
	
//	$.confirm=function(messageJson){
//		$("a").each(function(){
//			if($(this).text()=="删除"){
//				$(this).unbind("click");
//				$(this).bind("click",function(){ 
//				messageJson.callback();
//				return	window.confirm(messageJson.message);
//				});
//			}
//			
//		});
//		
//	}
})(jQuery);
