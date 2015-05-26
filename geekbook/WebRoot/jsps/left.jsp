<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
	<link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/css/left.css'/>">
	<script type="text/javascript"src="<c:url value='/jsps/Standard_slides/js/jquery-2.1.3.min.js'/>"></script>
	<script type="text/javascript"src="<c:url value='/jsps/Standard_slides/js/slides.min.jquery.js'/>"></script>
	<link rel="stylesheet" href="/jsps/Standard_slides/css/left_slides.css">
<script language="javascript">
/*
 * 1. 对象名必须与第一个参数相同！
   2. 第二个参数是显示在菜单上的大标题
 */
var bar = new Q6MenuBar("bar", "极客网上书城");
$(function() {
	bar.colorStyle = 0;//指定配色样式，一共0,1,2,3,4
	bar.config.imgDir = "<c:url value='/menu/img/'/>";//小工具所需图片的路径
	bar.config.radioButton=true;//是否排斥，多个一级分类是否排斥

	/*
	1. 程序设计：一级分类名称
	2. Java Javascript：二级分类名称
	3. /geekbook/jsps/book/list.jsp：点击二级分类后链接到的URL
	4. body:链接的内容在哪个框架页中显示
	*/
<c:forEach items="${parents}" var="parent">
  <c:forEach items="${parent.children}" var="child">
	bar.add("${parent.cname}", "${child.cname}", "/geekbook/BookServlet?method=findByCategory&cid=${child.cid}", "body");
  </c:forEach>
</c:forEach>
	
	$("#menu").html(bar.toString());
	
	$('#products').slides({
		preload: true,
		
		
		play: 2500,
		pause:1000,
		hoverPause: true
		
		
		
	});
});

</script>
</head>
  
<body>  
  <div id="menu"></div><br>
  <div><div id="container">
		<div id="products_example">
			<div id="products">
				<div class="slides_container">
					<a href="" target=""><img src="/jsps/Standard_slides/img/1.PNG" width="" alt=""></a>
					<a href="" target=""><img src="/jsps/Standard_slides/img/2.PNG" width="" alt=""></a>
					<a href="" target=""><img src="/jsps/Standard_slides/img/3.PNG" width="" alt=""></a>					
			
				</div>
				
			</div>
		</div>
		<div id="footer">
		
		</div>
	</div></div>
</body>
</html>
