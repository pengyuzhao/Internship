<%@ page language="java" import="java.util.*,com.company.domain.*,com.student.domain.*" pageEncoding="utf-8"%>
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

<title>My JSP 'selectResume.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/stu_introdution.css">
<link rel="stylesheet" type="text/css" href="css/company.css">

</head>

<body>
	<div class="stu_top_img">
		<img src="img/top.jpg"></img>
	</div>
	<!--导航部分 -->
	<div class="stu_function_frame">
		<hr>
			<!--功能信息展示栏 -->
 	<div class="company_function_choose1">
	<a href="#"><font class="font_style1">|首页|</font></a>
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
	<!-- 查询简历表部分 -->

	<div class="select_stu_intrudution">
		<!-- 查询控件 -->
		<div class="select_button">
			<form action="/Internship/GoToSelectResumeCl?" method="post">
				<center>
					<select name="require">
						<option value="name">姓名</option>
						<option value="notChoose">未选中</option>
						<option value="choose">选中</option>
						<option value="direction">方向</option>
					</select> <input type="text" name="key"/> <input type="submit" value="查询" />
				</center>
				<hr>
			</form>
		</div>
		<!-- 查询内容控件 -->
		<div class="select_stu_content">
			
			<table border="1" cellspacing="0">
				
				<tr>
				
					
					<th>姓名</th>
					<th>性别</th>
					<th>方向</th>
					<th>简历</th>
					<th>下载作品</th>
					<th>观看录像</th>
					<th>查看评价</th>
					<th>是否被公司选上</th>
				
				</tr>
			
				<%ArrayList al=(ArrayList)request.getAttribute("stuPartInfo");
					
					for(int i=0;i<al.size();i++)
					{
						int n=i+1;
						StuResume stuResume=(StuResume)al.get(i);
						
				
				 %>			
				<tr>
				
				
					<td><%=stuResume.getName()%></td>
					<td><%=stuResume.getSex()%></td>
					<td><%=stuResume.getDirection()%></td>
					<td><a href="/Internship/OperationCl?type=watchResume&id=<%=stuResume.getStudentId()%>" target="_blank">我的简历</a>
					</td>
					<td><a href="/Internship/OperationCl?type=downSource&id=<%=stuResume.getStudentId()%>" target="_blank">我的作品下载</a>
					</td>
					<td><a href="/Internship/OperationCl?type=vedio&id=<%=stuResume.getStudentId()%>" target="_blank">观看录像</a>
					</td>
					<td><a href="/Internship/OperationCl?type=techerevaluation&id=<%=stuResume.getStudentId()%>" target="_blank">查看评价</a>
					</td>
					<% if(stuResume.getChoose()==1)%><%{ %>
					<td><input type="checkbox" disabled="disabled" checked="checked">
					</td><%} %>
					<% if(stuResume.getChoose()==0)%><%{ %>
					<td><input type="checkbox" disabled="disabled" >
					</td><%} %>
					
						
				</tr>
				<%} %>
			</table><span>
			<% 
			Page p=new Page();
			p=(Page)request.getAttribute("page");
			for(int i=1;i<=p.getPageCount();i++){
			%>
			<%
			if(request.getAttribute("require")!=null)
			{
				String require=(String)request.getAttribute("require");
				String key=(String)request.getAttribute("key");
			%>
				
				<a href="/Internship/GoToSelectResumeCl?pageNow=<%=i%>&require=<%=require %>&key=<%=key %>"><<%=i%>></a>
			<%} %>
			<% if(request.getAttribute("require")==null) {%>
			<a href="/Internship/GoToSelectResumeCl?pageNow=<%=i %>"><<%=i%>></a>
			<%} }%>
			当前为<%=p.getPageNow() %>页/共有<%=p.getPageCount() %> 页</span> 
		</div>

	</div>

</body>
</html>
