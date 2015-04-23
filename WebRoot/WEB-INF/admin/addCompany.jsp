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

<title>My JSP 'addCompany.jsp' starting page</title>

<script type="text/javascript" src="js/adminJS/remind.js" charset="gbk">			
</script>


<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/stu_introdution.css">
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
			<a href="/Internship/ForwardCl?type=index"><font class="font_style1">|首页|</font> </a>
		</div>
		<div class="admin_function_choose2">
			<a href="/Internship/ForwardCl?type=data"><font class="font_style1">|管理数据信息|</font></a>
		</div>
		<div class="admin_function_choose3">
			<a href="/Internship/SelStuAuditCl?type=selAll"><font class="font_style1">|审核学生简历|</font> </a>
		</div>
		<div class="admin_function_choose4">
			<a href="/Internship/UpdateAdminInfoCl?type=goUpd"><font class="font_style1">|修改个人密码|</font> </a>
		</div>
		<div class="admin_function_choose5">
			<a href="/Internship/SelStuInfoCl?type=selAll"><font class="font_style1">|管理学生个人信息|</font> </a>
		</div>
		<div class="admin_function_choose6">
			<a href="/Internship/SelStuEvaCl?type=selAll"><font class="font_style1">|登记评价学生|</font> </a>
		</div>
		<div class="admin_function_choose7">
			<a href="/Internship/ForwardCl?type=exit"><font class="font_style1">|退出|</font> </a>
		</div>
	</div>
	<!-- 上传文件部分 -->
	<div class="updatestu_table_show">
		
		<center><hr>
		<form action="ManageComCl?type=addCom" method='post' >
			<table border="1">
				<tr>
					<td colspan="3">添加公司信息</td>
				</tr>
				<tr>
					<td>公司名</td>
					<td colspan="2"><input type="text" name="companyName"></td>
				</tr>
				<tr>
					<td>公司登陆账号</td>
					<td colspan="2"><input type="text" name="companyAccount"></td>
				</tr>
				<tr>
					<td>公司登陆密码</td>
					<td colspan="2"><input type="text" name="companyPwd"></td>
				</tr>
				<tr>
					<td>公司所在地</td>
					<td colspan="2"><input type="text" name="companyAddress"></td>
				</tr>
		
				
				<tr>
					<td colspan="3"><span class="location">
					<input type="submit" onClick="return confirmOper()" value="提交"> &nbsp; <input type="reset" value="重设">
					</td>
				</tr>

			</table>
			
			</center>
			
		</form>
</body>
</html>