<%@ page language="java" import="java.util.*,com.admin.domain.*"
	pageEncoding="utf-8"%>
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

<title>My JSP 'manageStudent.jsp' starting page</title>

<script type="text/javascript" src="js/adminJS/manageStudentData.js" charset="gbk">			
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

	<!-- 查询简历表部分 -->

	<div class="select_stu_intrudution">
		<!-- 查询控件 -->
		<div class="select_button">
			<form action="SelStuAuditCl?type=selOp" method="post">
				<center>
					<select name="option">
						<option value="name">姓名</option>
						<option value="stuId">学号</option>
						<option value="approved">审核通过</option>
						<option value="noApproved">审核未通过</option>
						<option value="all">关键字查询</option>
					</select> <input type="text" name="term"/> <input type="submit" value="查询" />
					 <input	type="button" onClick="goSubmitOrder()" name="selAll" value="显示全部列表" />
				</center>
				<hr>
			</form>
		</div>
		<!-- 查询内容控件 -->
		
		<center>
			<table border="1" cellspacing="0" width="700px">

				<tr>

					<th>学号</th>
					<th>名字</th>
					<th>性别</th>
					<th>专业方向</th>
					<th>简历</th>
					<th>下载作品</th>
					<th>观看录像</th>
					<th>审核</th>
				</tr>

				<%
					// 接收不同的查询类型
					String type = (String) request.getAttribute("type");

					ArrayList<StudentResume> al = (ArrayList<StudentResume>) request.getAttribute("stu");
					
					

					// 接收分页相关信息
					int pageCount = (Integer) request.getAttribute("pgcount");
					int pageNow = (Integer) request.getAttribute("pgNow");

					for (StudentResume stu : al) {
						String checkState = stu.getCheckState()+"";
				%>
				
				

				<tr>

					<td><%=stu.getStudentId()%></td>
					<td><%=stu.getName()%></td>
					<td><%=stu.getSex()%></td>
					<td><%=stu.getDirectionid() %></td>

					<td><a href="/Internship/OperationCl?type=watchResume&id=<%=stu.getStudentId()%>" target="_blank">查看简历</a></td>
					<td><a href="/Internship/OperationCl?type=downSource&id=<%=stu.getStudentId()%>" target="_blank" >学生作品下载</a></td>
					<td><a href="/Internship/OperationCl?type=vedio&id=<%=stu.getStudentId()%>"  target="_blank">观看录像</a></td>
					
					<%
						if(checkState.equals("0")){
						%>
							<td><a href="AuditStuCl?type=auditOk&stuId=<%=stu.getStudentId() %>" onclick="return check()">通过 </a>
							<a href="AuditStuCl?type=auditNo&stuId=<%=stu.getStudentId() %>">不通过</a></td>
						<% 
						}else if(checkState.equals("1")){
						
						%>
							<td>已通过</td>
						<%
						}else if(checkState.equals("-1")){
						%>
							<td><a href="AuditStuCl?type=lookReason&stuId=<%=stu.getStudentId() %>">未通过原因</a></td>
						<%
						}
					 %>
					
				</tr>
				<%
					}
				%>

			</table>
			<br/>
			<%
			// 显示上一页
			if(pageNow!=1){
			%>
			
			<a href='/Internship/SelStuAuditCl?type=<%=type %>&pageNow=<%=(pageNow-1) %>'>上一页</a>
			<%
			}
			// 显示分页
			for(int i=1;i<=pageCount;i++){
			%>
			<a href='/Internship/SelStuAuditCl?type=<%=type %>&pageNow=<%=i %>'><<%=i %>>
			</a>
			<%
				}

			// 显示下一页
			if (pageNow != pageCount) {
			%>
			<a href='/Internship/SelStuAuditCl?type=<%=type %>&pageNow=<%=(pageNow+1) %>'>下一页</a>
			<%
				}

			// 显示当前分页信息
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;当前页<%=pageNow%>/总页数<%=pageCount%>
			</center>

			<center>
				跳转到:<input type='text' id='pageNow' name='pageNow' /> 
				<input type='button' onClick='gotoPageNow()' value='go' />
			</center>
			
</body>
</html>
