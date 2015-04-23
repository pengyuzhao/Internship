<%@ page language="java" import="java.util.*,com.admin.domain.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'selComInfo.jsp' starting page</title>

<script type="text/javascript" src="js/adminJS/selComInfo.js" charset="gbk">			
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


	<div class="select_stu_intrudution">
		<!-- 查询控件 -->
		<div class="select_button">
			<form action="SelComInfoCl?type=selOp" method="post">
				<center>
					<select name="option">
						<option value="companyName">公司名称</option>
						<option value="companyAddress">公司所在地</option>
					</select> 
					<input type="text" name="term"/> <input type="submit" value="查询" />
					 <input	type="button" onClick="goSubmitOrder()" name="selAll" value="显示全部列表" />
				</center>
				<hr>
			</form>
		</div>
		<!-- 查询内容控件 -->
	
		<center>
			<table border="1" cellspacing="0">

				<tr>
					
					<th>公司编号</th>
					<th>公司名称</th>
					<th>公司登陆账号</th>
					<th>公司登陆密码</th>
					<th>公司所在地</th>
					<th colspan="3">请选择操作</th>

				</tr>
				<%
					// 接收不同的查询类型
					String type = (String)request.getAttribute("type");
					
					ArrayList<Company> al = (ArrayList<Company>) request.getAttribute("com");
					
					// 接收分页相关信息
					int pageCount = (Integer)request.getAttribute("pgcount");
					int pageNow = (Integer)request.getAttribute("pgNow");
				

					for (Company com : al) {
				%>
				
				<tr>
					<td><%=com.getId() %></td>
					<td><%=com.getCompanyName()%></td>
					<td><%=com.getCompanyAccount()%></td>
					<td><%=com.getCompanyPwd()%></td>
					<td><%=com.getCompanyAddress()%></td>
					
					<td><a href='ManageComCl?type=goSeeCom&comId=<%=com.getId() %>'>查看实习情况</a></td>
					<td><a href='ManageComCl?type=gotoUpCom&comId=<%=com.getId() %>'>修改公司信息</a></td>
					<td><a href='ManageComCl?type=delCom&comId=<%=com.getId() %>' onclick="return check()">删除公司信息</a></td>
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
			
			<a href='/Internship/SelStuInfoCl?type=<%=type %>&pageNow=<%=(pageNow-1) %>'>上一页</a>
			<%
			}
			// 显示分页
			for(int i=1;i<=pageCount;i++){
			%>
			<a href='/Internship/SelStuInfoCl?type=<%=type %>&pageNow=<%=i %>'><<%=i %>>
			</a>
			<%
				}

			// 显示下一页
			if (pageNow != pageCount) {
			%>
			<a href='/Internship/SelStuInfoCl?type=<%=type %>&pageNow=<%=(pageNow+1) %>'>下一页</a>
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
			</div>
</body>
</html>