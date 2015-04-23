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
 	<div class="stu_function_choose1">
	<a href="/Internship/GoToIndexCl"><font class="font_style1">|首页|</font></a>
	</div>
	<div class="stu_function_choose2">
	<a href="/Internship/GoToPageCl?type=writeResume"><font class="font_style1">|填写简历表|</font></a>
	</div>
 	<div class="stu_function_choose3">
	<a href="/Internship/GoToPageCl?type=upload"><font class="font_style1">|上传个人作品压缩包和录像|</font></a>
	</div>
	<div class="stu_function_choose4">
	<a href="/Internship/GoToPageCl?type=watchResume"><font class="font_style1">|查看自己简历|</font></a>
	</div>
	<div class="stu_function_choose5">
	<a href="/Internship/GoToPageCl?type=updatePwd"><font class="font_style1">|修改个人的密码|</font></a>
	</div>
	<div class="stu_function_choose6">
	<a href="#"><font class="font_style1">|退出|</font></a>
	</div>

	
</div>
<!-- 网页首页主体部分 -->
<div class="stu_index_main">
<center><h1>简历系统</h1></center><br>
学生登录该系统之后<font color="red">需先修改自己的个人密码</font>，然后自行填写简历并且上传自己的作品源码和录像，
<font color="red">请别超过15MB</font>,审核成功之前你都可以继续上传你的代码压缩包等。如果审核不通过，你将可以根据原因
重新修改简历。提交完成后你可以查看你自己的简历。审核成功后，公司将能在公司端看到你的简历 ，下载你的代码和录像。管理员有权对你的资料进行
管理

</div>



</body>
  

 
  
 
</html>
