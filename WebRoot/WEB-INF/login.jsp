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

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/stu_introdution.css">
<link rel="stylesheet" type="text/css" href="css/admin_index.css">
<script type="text/javascript" src="js/stu_companyJS/login.js" charset="gbk"></script>
</head>
<body>
	<!--头部部分的照片 -->
	<div class="admin_top_img">
		<img src="img/top.jpg"></img>
	</div>
	<!--导航部分 -->
	<div class="admin_function_frame">
		
	</div>
	<!-- 上传文件部分 -->
	<div class="updatestu_table_show">
		
		<center><hr><br><br><br><br><br><br>
		<form action="LoginCl" method='post' name="form1" onsubmit="return checkLogin()">
		<table boder="0" cellpadding="0" cellspacing="0" height="200">
		<tr><th>用户名：</th><td><input type='text' name='username' ></td></tr>
		<tr><th>密　码：</th><td><input type='password' name='passwd'></td></tr>
		<tr><th>验证码：</th><td><input type='text' name='userCode'  ></td><td><img src='/Internship/CreateCode'/></td></tr>
		<tr><th>权　限：</th><td><input type="radio" name="power"  value="admin" >管理员<input type="radio" name="power" value="company">公司<input type="radio" name="power" value="student">学生</td></tr>
		<tr><td colspan="2"><span class="button_location"><input type='submit' value='登录'/></span><span><input type='reset' value='清空'/></span></td></tr>
		</table>
	
	
		
		</form><hr/>
		
		
		<%
			// 接收各种错误消息
			String errInfo=(String)request.getAttribute("err");
			if(errInfo!=null){
				%>
					<font color='red'><%=errInfo %></font>
				<%
			}
		 %>
		</center>		
	</div>

</body>
</html>