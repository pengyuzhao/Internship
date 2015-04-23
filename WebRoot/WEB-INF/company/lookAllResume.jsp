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
			<form action="/Internship/GoToSelectResumeCl?" method="post" name="form1">
				<center>
					<select name="require"  onchange="remind()">
						<option value="name">姓名</option>
						<option value="notChoose">未选中</option>
						<option value="choose">选中</option>
						<option value="direction">方向</option>
					</select> <input type="text" name="key" id="key"/><input type="submit" value="查询" /> <input type="submit" value="显示所有列" />
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
					<th>专业方向</th>
					<th>简历</th>
					<th>下载作品</th>
					<th>观看录像</th>
					<th>查看评价</th>
					<th>是否被公司选上</th>
				
				</tr>
			
				<%ArrayList al=(ArrayList)request.getAttribute("stuPartInfo");//取到所以符合条件学生的集合

					Page p=new Page();
					p=(Page)request.getAttribute("page");//取到分页的对象
					int n=p.getPageNow();
					String require;
					String key;
					if(al.size()==0)
					{%>
					<script type="text/javascript">	
					alert("找不到符合条件的学生");</script>
				<% 	}
					for(int i=0;i<al.size();i++)
					{
					
						StuResume stuResume=(StuResume)al.get(i);	//取出学生对象			
				 %>			
				<tr>
				<%-- 下面<%=  %>中的都是取得对象属性                                   --%>
					<td><%=stuResume.getName()%></td>
					<td><%=stuResume.getSex()%></td>
					<td><%=stuResume.getDirection()%></td>
					<td><a href="/Internship/OperationCl?type=watchResume&id=<%=stuResume.getStudentId()%>" target="_blank">查看简历</a>
					</td>
					<td><a href="/Internship/OperationCl?type=downSource&id=<%=stuResume.getStudentId()%>" target="_blank">作品下载</a>
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

			/*-----------------是否显示上一页----------------------*/
			if(n!=1){
				if(request.getAttribute("require")!=null)
				{
					 require=(String)request.getAttribute("require");
					 key=(String)request.getAttribute("key");//取到查询的参数，如果有就在上一页的超链接里带上参数
			%>
			<a href="/Internship/GoToSelectResumeCl?pageNow=<%=n-1%>&require=<%=require %>&key=<%=key %>"><上一页></a>
			<%}
				if(request.getAttribute("require")==null) {//如果没有就不带参数 pageNow-1	%>
			<a href="/Internship/GoToSelectResumeCl?pageNow=<%=n-1%>"><上一页></a>
			<%}}/*-----------------是否显示上一页结束----------------------*/%>
		
			<%
			/*-----------------实现页面跳转----------------------*/
			for(int i=1;i<=p.getPageCount();i++){
			if(request.getAttribute("require")!=null)
			{
				 require=(String)request.getAttribute("require");
				 key=(String)request.getAttribute("key");
			%>
			<a href="/Internship/GoToSelectResumeCl?pageNow=<%=i%>&require=<%=require %>&key=<%=key %>"><<%=i%>></a>
			<%}%>
			<% if(request.getAttribute("require")==null) {
			%>
			<a href="/Internship/GoToSelectResumeCl?pageNow=<%=i%>"><<%=i%>></a>
			<%}} /*-----------------实现页面结束----------------------*/%>
			<%
			/*-----------------实现下一页的跳转----------------------*/
			if(n!=p.getPageCount()){
				if(request.getAttribute("require")!=null)
				{
				 require=(String)request.getAttribute("require");
				 key=(String)request.getAttribute("key"); //取到查询的参数，如果有就在下一页的超链接里带上参数 %>
				<a href="/Internship/GoToSelectResumeCl?pageNow=<%=n+1%>&require=<%=require %>&key=<%=key %>"><下一页></a>
				<%}	if(request.getAttribute("require")==null){ //如果没有就不带参数 pageNow+1%>
			
				<a href="/Internship/GoToSelectResumeCl?pageNow=<%=n+1%>"><下一页></a>
				<%}}/*-----------------实现下一页的跳转结束----------------------*/%>
			当前为<%=p.getPageNow() %>页/共有<%=p.getPageCount()%>页</span>
		
		</div>

	</div>

</body>
</html>
