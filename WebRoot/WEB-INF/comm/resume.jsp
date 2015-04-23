<%@ page language="java" import="java.util.*,com.student.domain.*" pageEncoding="utf-8"%>
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

<title>My JSP 'resume.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<title>个人简历</title>
</head>
<body>
	<table width=800 height=0 border=0 align=center>
		<tr>
			<td width="800" align="center"><font size="+5" color="#AAAAAA"
				face="华文彩云">个人简历</font></td>
		</tr>
		<table cellspacing=0 cellpadding=0 border=1 width=700 height=400
			align=center>
			<% ArrayList al=(ArrayList)request.getAttribute("resumeInfo");
				StuResume stuResume=new StuResume();
				stuResume=(StuResume)al.get(0);
				String img=stuResume.getImgPath().substring(1);
				
				
			 %>
			<tr>
				<td colspan=1 height=50 align=center bgcolor=#D4D4D4>姓名
				<td align=center><%=stuResume.getName()%>
				<td colspan=1 align=center bgcolor=#D4D4D4>性别
				<td align=center><%=stuResume.getSex()%>
				<th width=130 rowspan=4><img src=<%=img%> width=130/>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>民族
				<td align=center><%=stuResume.getNation() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>籍贯
				<td align=center><%=stuResume.getNativePlace() %>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>出生日期
				<td align=center><%=stuResume.getBirthday() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>毕业年份
				<td align=center><%=stuResume.getGraduationYear() %>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>学历
				<td align=center><%=stuResume.getEducation() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>体重身高
				<td align=center><%=stuResume.getHightweight() %>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>专业
				<td align=center><%=stuResume.getProfessional() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>健康状况
				<th colspan=2 align=center><%=stuResume.getHealthsituation() %>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>毕业学校
				<td align=center><%=stuResume.getGraduationschool() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>希望工作地区
				<th colspan=2 align=center><%=stuResume.getHopePlace() %>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>qq
				<td align=center><%=stuResume.getQq() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>邮箱
				<th colspan=2 n=center><%=stuResume.getPoscalcode()%>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>英语等级
				<td align=center><%=stuResume.getEnglishClass() %>
				<td colspan=1 align=center bgcolor=#D4D4D4>精通方向
				<th colspan=2 align=center><%=stuResume.getDirection() %>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>期望岗位：
				<th colspan=8 align=left><%=stuResume.getHopeJob() %>
			<tr>
			<tr>
				<td colspan=1 height=40 align=center bgcolor=#D4D4D4>主修课程：
				<th colspan=8 align=left><%=stuResume.getMajor() %>
			<tr>
				<td colspan=1 height=150 align=center bgcolor=#D4D4D4>个人简历：
				<th colspan=8 align=left><%=stuResume.getPersonalResume() %>
			<tr>
				<td colspan=1 height=70 align=center bgcolor=#D4D4D4>奖惩情况：
				<th colspan=8 align=left><%=stuResume.getPunishsituation() %>
			<tr>
				<td colspan=1 height=150 align=center bgcolor=#D4D4D4>项目经验：
				<th colspan=8 align=left><%=stuResume.getExperiences() %>
			<tr>
				<td colspan=1 height=150 align=center bgcolor=#D4D4D4>自我评价：
				<th colspan=8 align=left><%=stuResume.getEvaluation() %>
		</table>
</body>
</html>


</html>
