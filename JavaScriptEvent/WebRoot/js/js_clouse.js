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
 * 上述属性和方法是公开的 容易造成安全性问题
 */

/*
 *闭包
 在函数内部定义的函数或变量在外部没有办法直接访问
 在外部访问函数内部的函数或变量称为闭包
 用闭包很耗内存，因为变量不会因为函数的销毁而销毁
 闭包使函数和变量具有了私有性
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
 * 闭包范式
 * (function(形参){})(实参)
 * 在匿名函数中所有的方法都可以用形参
 *    在外怎么访问匿名函数的私有函数
 *       可以把其中的一个函数作为浏览器内置对象的一个属性
 *       可以让函数返回一个json格式的对象，而该对象中的内容就是公开的内容
 */
(function(a){
	var i;
	a.aa=function(){
	 alert(a);
	}
	
	a.openMethod=function(){
		//封装在json中的内容被公开了
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
