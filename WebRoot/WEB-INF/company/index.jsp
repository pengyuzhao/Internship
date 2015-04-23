<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
 	<!--功能信息展示栏 -->
 	<div class="company_function_choose1">
	<a href="/Internship/GotoCompanyIndexCl"><font class="font_style1">|首页|</font></a>
	</div>
	<div class="company_function_choose2">
	<a href="/Internship/GoToSelectResumeCl"><font class="font_style1">|查询所有学生的简历|</font></a>
	</div>
	<div class="company_function_choose3">
	<a href="/Internship/GoToCompanyPageCl?type=updatePassword"><font class="font_style1">|修改密码|</font></a>
	</div>
	<div class="company_function_choose4">
	<a href="#"><font class="font_style1">|退出|</font></a>
	</div>
	
</div>
<!-- 网页首页主体部分 -->
<div class="stu_index_main">
<center><h1>简历系统</h1></center><br>
公司登录该系统之后<font color="red">需先修改自己的个人密码</font>，然后可以查看所有已被审核通过的同学的信息，公司将能
查看同学的简历 ，下载同学的代码和录像。
管理

</body>
  

 
  
 
</html>
