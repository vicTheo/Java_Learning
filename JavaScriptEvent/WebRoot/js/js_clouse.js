/**
 * @author theo
 */
function Person(){
	
}
Person.prototype.setName=function(name){
	this.name=name;
}
Person.prototype.setAge=function(age){
	this.age=age;
}
Person.prototype.name="dd";

/**
 * �������Ժͷ����ǹ����� ������ɰ�ȫ������
 */

/*
 *�հ�
 �ں����ڲ�����ĺ�����������ⲿû�а취ֱ�ӷ���
 ���ⲿ���ʺ����ڲ��ĺ����������Ϊ�հ�
 �ñհ��ܺ��ڴ棬��Ϊ����������Ϊ���������ٶ�����
 �հ�ʹ�����ͱ���������˽����
 */
function Student(){
	var i=0;
	function SuperStudent(){
		i++;
		
		return i;
	}
	return SuperStudent;
}

var sFunction=Student();
alert(sFunction());

/**
 * �հ���ʽ
 * (function(�β�){})(ʵ��)
 * ���������������еķ������������β�
 *    ������ô��������������˽�к���
 *       ���԰����е�һ��������Ϊ��������ö����һ������
 *       �����ú�������һ��json��ʽ�Ķ��󣬶��ö����е����ݾ��ǹ���������
 */
(function(a){
	var i;
	a.aa=function(){
	 alert(a);
	}
	
	a.openMethod=function(){
		//��װ��json�е����ݱ�������
		return {
			b:b,
			c:c
		};
	}
	function b(){
		alert("b");
	}
	function c(){
		alert("c")
	}
	
})(window);
window.aa();
var jsonObject=window.openMethod();
jsonObject.b();
jsonObject.c();
