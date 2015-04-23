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

<title>My JSP 'updatapassword.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/comm.css">
<script type="text/javascript" src="js/stu_companyJS/updatepassword.js" charset="gbk">
</script>

</head>

<body>
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
	<div class="stu_table_show">
		<form action="/Internship/ExcuteCl?type=updatePwd" method="post">
			<table border="1">
				<tr>
					<td>请输入原密码:</td>
					<td><input type="password" name="prePwd" id="pre" >
					</td>
				</tr>
				<tr>
					<td>请输入新密码:</td>
					<td><input type="password" name="nowPwd" id="now">
					</td>
				</tr>
				<tr>
					<td>请重新输入新密码:</td>
					<td><input type="password" name="comfirmPwd" id="comfirm" >
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<center> <input
						type="submit" value="确定" onclick=" return checkPwd()"/>
					 <input
						type="reset" value="重设" /></center></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
