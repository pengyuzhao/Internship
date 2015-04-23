<%@ page language="java" import="java.util.*,com.student.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'watchEvaluate.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link  rel="stylesheet" type="text/css" href="css/comm.css">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	<link rel="stylesheet" type="text/css" href="css/company.css">
  </head>
  
  <body>
   <!--头部部分的照片 -->
    <div class="stu_top_img">
    <img src="img/top.jpg"></img>
    </div>
  <!--导航部分 -->
  <div class="stu_function_frame">
  <hr>
</div>
<!-- 网页首页主体部分 -->
<div class="stu_index_main">
  <%StuResume stuResume= (StuResume)request.getAttribute("stuResume"); %>
 <table width="487" height="179">
 <tr><th>老师评价</th><td><textarea rows="6" cols="80" readonly="readonly"><%=stuResume.getTecherevaluation() %></textarea></td></tr>
 </table>
 
 </div>
  </body>
</html>
