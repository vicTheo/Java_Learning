function ajaxFunction(){
    var xmlHttp;
    try { // Firefox, Opera 8.0+, Safari
        xmlHttp = new XMLHttpRequest();
    } 
    catch (e) {
        try {// Internet Explorer
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        } 
        catch (e) {
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            } 
            catch (e) {
            }
        }
    }
    
    return xmlHttp;
}

window.onload = function(){
	document.getElementById("ok").onclick=function(){
		ajax({
			method:'POST',
			url:'AjaxServlet?time='+new Date(),
			callback:function(data){
				alert(data);
			}
		});
	}
}

function ajax(ajaxJson){
	var xmlHttp=ajaxFunction();
	xmlHttp.onreadystatechange=function(){
		if(xmlHttp.readyState=4){
			if(xmlHttp.readyState=200){
				ajaxJson.callback(xmlHttp.responseText);
			}
		}
	}
	xmlHttp.open(ajaxJson.method,ajaxJson.url,true);
	xmlHttp.send(null);
}
