<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="">
    
    <title>body</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	
<link rel="stylesheet" href="Standard_slides/css/global.css">
	
	<script src="Standard_slides/js/jquery-2.1.3.min.js"></script>
	<script src="Standard_slides/js/jquery.easing.1.3.js"></script>
	<script src="Standard_slides/js/slides.min.jquery.js"></script>
	<script>
		$(function(){
			$('#slides').slides({
				preload: true,
				preloadImage: 'Standard_slides/img/loading.gif',
				play: 1500,
				pause: 200,
				hoverPause: true
			});
		});
	</script>
  </head>
  
  <body>
   <div id="container">
		<div id="example">
			<img src="Standard_slides/img/new-ribbon.png" width="112" height="112" alt="New Ribbon" id="ribbon">
			<div id="slides">
				<div class="slides_container">
					<a href="<c:url value='/BookServlet?method=load&bid=0EE8A0AE69154287A378FB110FF2C780'/>" title="" target=""><img src="Standard_slides/img/slide-1.jpg" width="570" height="270" alt="Slide 1"></a>
					<a href="<c:url value='/BookServlet?method=load&bid=3E1990E19989422E9DA735978CB1E4CE'/>" title="" target=""><img src="Standard_slides/img/slide-2.jpg" width="570" height="270" alt="Slide 2"></a>
					<a href="<c:url value='/BookServlet?method=load&bid=1A15DC5E8A014A58862ED741D579B530'/>" title="" target=""><img src="Standard_slides/img/slide-3.png" width="570" height="270" alt="Slide 3"></a>
					<a href="<c:url value='/BookServlet?method=load&bid=62E94258ACAB47DFB19A962FA7379B5F'/>" title="" target=""><img src="Standard_slides/img/slide-4.jpg" width="570" height="270" alt="Slide 4"></a>
					<a href="<c:url value='/BookServlet?method=load&bid=676B56A612AF4E968CF0F6FFE289269D'/>" title="" target=""><img src="Standard_slides/img/slide-5.jpg" width="570" height="270" alt="Slide 5"></a>
					<a href="<c:url value='/BookServlet?method=load&bid=9D257176A6934CB79427CEC37E69249F'/>" title="" target=""><img src="Standard_slides/img/slide-6.png" width="570" height="270" alt="Slide 6"></a>
				</div>
				<a href="#" class="prev"><img src="Standard_slides/img/arrow-prev.png" width="24" height="43" alt="Arrow Prev"></a>
				<a href="#" class="next"><img src="Standard_slides/img/arrow-next.png" width="24" height="43" alt="Arrow Next"></a>
			</div>
			<img src="Standard_slides/img/example-frame.png" width="739" height="341" alt="Example Frame" id="frame">
		</div>
		<div id="footer" >
			<p><label id="label1">友情链接：</label>&nbsp; <a href="https://github.com/" target="_blank">GitHub</a> &nbsp;
		     <a href="http://stackoverflow.com/"target="_blank">StackOverFlow</a>&nbsp;
		     <a href="http://www.quora.com/"target="_blank">Quora</a>&nbsp;
		     <a href="http://www.csdn.net/"target="_blank">CSDN</a>
			</p>
		</div>
	</div>
  </body>
</html>
