<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>personlist</title>
 
  </head>
   <script type="text/javascript">
     function del(id){
     var v=window.confirm("are you sure to delete this item?");
     if(v){
           window.location.href="<%=path%>/person/deleteById?id="+id;
          }
       
     }
     function addUI(){
       window.location.href="<%=path%>/person/addUI";
     }
     function updateUI(id){
      window.location.href="<%=path%>/person/updateUI?id="+id;
     }
     function batchDelete(){
       var ids=document.getElementsByName("ids");
        var idsValue="";
       for(var i=0;i<ids.length;i++){
       if(ids[i].checked==true){
            idsValue+=ids[i].value+",";
            }
         }
         var v=window.confirm("are you sure to delete this item?");
     if(v){
           window.location.href="<%=path%>/person/batchDelete?idsValue="+idsValue;
          }
     }
   </script>
  <body>
    <input type="button" value="batchdelete" onclick="batchDelete()">
    <input type="button" value="add" onclick="addUI()">
    <table border="1">
    <tr>
   
    <td><input type="checkbox"></td>
    <td>id</td>
    <td>name</td>
    <td>address</td>
    <td>delete</td>
    <td>update</td>
    </tr>
    <c:forEach items="${list}" var="p">
    <tr>
     
     <td><input type="checkbox" name="ids" value="${p.id}"/></td>
    <td>${p.id}</td>
    <td>${p.name}</td>
    <td>${p.address}</td>
    <td><input type="button" value="delete" onclick="del('${p.id}')"></td>
    <td><input type="button" value="update" onclick="updateUI('${p.id}')"></td>
    </tr>
    </c:forEach>
    </table>
  </body>
</html>
