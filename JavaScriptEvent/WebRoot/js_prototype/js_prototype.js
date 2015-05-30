/**
 * @author theo
 */
function min(a,b){
	
	return a>b?b:a;
}
var array=new Array();
Array.prototype.min=function (a,b){
	return a>b?b:a;
}
Array.min=min;
alert(Array.min(6,7));
alert(array.min(5,6));
