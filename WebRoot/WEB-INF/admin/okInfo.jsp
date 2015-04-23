<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'okInfo.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/admin_index.css">
</head>


<body>
	<!--头部部分的照片 -->
	<div class="admin_top_img">
		<img src="img/top.jpg"></img>
	</div>
	<!--导航部分 -->
	<div class="admin_function_frame">
		<hr>
		<!--功能信息展示栏 -->
		<div class="admin_function_choose1">
			<a href="/Internship/ForwardCl?type=index"><font class="font_style1">|首页|</font>
			</a>
		</div>
		<div class="admin_function_choose2">
			<a href="/Internship/ForwardCl?type=data"><font
				class="font_style1">|管理数据信息|</font>
			</a>
		</div>
		<div class="admin_function_choose3">
			<a href="/Internship/SelStuAuditCl?type=selAll"><font
				class="font_style1">|审核学生简历|</font>
			</a>
		</div>
		<div class="admin_function_choose4">
			<a href="/Internship/UpdateAdminInfoCl?type=goUpd"><font class="font_style1">|修改个人密码|</font>
			</a>
		</div>
		<div class="admin_function_choose5">
			<a href="/Internship/SelStuInfoCl?type=selAll"><font
				class="font_style1">|管理学生个人信息|</font>
			</a>
		</div>
		<div class="admin_function_choose6">
			<a href="/Internship/SelStuEvaCl?type=selAll"><font
				class="font_style1">|登记评价学生|</font> </a>
		</div>
		<div class="admin_function_choose7">
			<a href="/Internship/ForwardCl?type=exit"><font class="font_style1">|退出|</font>
			</a>
		</div>

	</div>
	<hr>
	<!-- 网页首页主体部分 -->
	<div class="stu_index_main">
		<br> <br> <br>
		<%
			String info = (String)request.getAttribute("info");	
			
			if("gotoEva".equals(info)){
			
				String stuId = (String)request.getAttribute("stuId");	
			%>
				<center>
				<h2>提示：该同学已经成功通过审核！</h2>
				
				<a href="/Internship/EvaluationStuCl?type=goEva&stuId=<%=stuId%>">去评价</a>
				</center>
				<br> <a href="#"><span>返回主页</span></a>
			<%
			}else{
		%>
			<center>
				<h2><%=request.getAttribute("info").toString()%></h2>
				</center>
				<br> <a href="#"><span>返回主页</span></a>
		<%
		}	
		%>
	</div>

</body>
</html>
