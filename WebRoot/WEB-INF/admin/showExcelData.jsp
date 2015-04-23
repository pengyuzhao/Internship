<%@ page language="java" import="java.util.*,com.admin.domain.*" pageEncoding="utf-8"%>
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

<title>My JSP 'showExcelData.jsp' starting page</title>

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


<center><hr><br><br><br><br><br>
			<table border="1" cellspacing="0" width="800px">

				<tr>

					<th>学号</th>
					<th>姓名</th>
					<th>初始密码</th>
					<th>身份证</th>
					<th>班级</th>
					<th>联系电话</th>
				</tr>

				<%
					// 从request中取出要显示的信息
							List<UploadExcel> dmLs = (List) request.getAttribute("excelDm");
							Iterator iter = dmLs.iterator();
							while (iter.hasNext()) {
								UploadExcel uploadExcel = (UploadExcel) iter.next();
				%>
				<tr>
					<td><%=uploadExcel.getStudentId()%></td>
					<td><%=uploadExcel.getStudentName()%></td>
					<td><%=uploadExcel.getStudentPwd()%></td>
					<td><%=uploadExcel.getStudentCardId()%></td>
					<td><%=uploadExcel.getStudentClass()%></td>
					<td><%=uploadExcel.getStudentTel()%></td>
					<%
						}
					%>
			</table>
		<center>
		<br><br><a onClick="return confirmOper4()" href='/Internship/UploadExcelCl?type=addExcel'>确定导入</a> &nbsp;&nbsp;&nbsp;
    	&nbsp;&nbsp;<a href='/Internship/UploadExcelCl?type=cancel'>返回重新导入</a>
    	</center>
    		
</body>
</html>
