<%@ page language="java" import="java.util.*,com.student.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'watchResume.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/stu_introdution.css">
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
	<!-- 查询简历表部分 -->

	<div class="select_stu_intrudution">
		<!-- 查询控件 -->
		<div class="select_button">
			
				<hr>
			
		</div>
		<!-- 查询内容控件 -->
		<div class="select_stu_content">
			
			<table border="1" cellspacing="0">
				
				<tr>
					
					<th>学号</th>
					<th>姓名</th>
					<th>简历</th>
					<th>下载作品</th>
					<th>观看录像</th>
					<th>查看评价</th>
					<th>是否已被选到</th>
					<th>选到的公司</th>
				</tr>
			
				<%
					
					
					
						StuResume stuResume=(StuResume)request.getAttribute("stuPartInfo");
						
				
				 %>			
				<tr>
					<%
						if(stuResume.getStudentId()!=null)
								{			
					 %>
					<td><%=stuResume.getStudentId()%></td>
					<td><%=stuResume.getName()%></td>
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
					<td><input type="text" disabled="disabled" value=<%=stuResume.getCompany() %> >
					</td>
					<%} %>
					
					
				</tr>
			
			</table>
			<span><a href="#"><1></a>当前为X页/共有X页</span>
		</div>

	</div>
  </body>
</html>
