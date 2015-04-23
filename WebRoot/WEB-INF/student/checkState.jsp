<%@ page language="java" import="java.util.*,com.student.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'checkState.jsp' starting page</title>
    
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
<% 
	StuResume stuResume=(StuResume)request.getAttribute("checkState");
	//StuResume stuResume=(StuResume)al.get(0);
	int i=stuResume.getCheckState();
	if(i==1)
	{
		out.print("<center><h2>审核已经通过,不需要再上传文件或填写简历</h2></center>");
	}
	if(i==-1)
	{
		
		out.print("<center><h3><font color=red>审核不通过 原因:"+stuResume.getReason()+"</font></center></h3>");
		out.print("<a href=/Internship/GoToUpdateResume>点击重新改写</a>");	
	
	}
	if(i==0)
	{
	out.print("<center><h3>审核中</h3></center>");
	}
	
 %>

</div>
  </body>
</html>
