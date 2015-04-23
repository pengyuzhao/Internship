<%@ page language="java" import="java.util.*,com.student.domain.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updateResume.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/comm.css">
<link rel="stylesheet" type="text/css" href="css/writeResume.css">
 <script type="text/javascript" src="js/stu_companyJS/writeResume.js" charset="gbk"></script> 

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
	<%
			ArrayList al=(ArrayList)request.getAttribute("updateResume");
			StuResume stuResume=(StuResume)al.get(0);
			
			
	
	 %>
	 <!-- 简历编写部分 -->
	<div class="stu_write_resume">
		<!-- 个人基本信息 -->
		<div class="stu_person_info">
			<form action="/Internship/ExcuteCl?type=updateResume" method="post"
				enctype="multipart/form-data" name="form1" onsubmit="return checkData()">
				<table border="1" cellspacing="0">
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" value=<%=stuResume.getName() %>>
						</td>
						<td>性别</td>
						<td><input type="radio" name="sex" value="男">男<input type="radio" name="sex"  value="女">女
						</td>
						<td rowspan="6"><center>
						<div id="preview">
								 <img id="imghead" width=100 height=100 border=0 
								 src='<%=request.getContextPath()%><%=stuResume.getImgPath()%>'>
							</div>	
							</center>
						</td>
					</tr>
					<tr>
						<td>民族</td>
						<td><input type="text" name="nation" value=<%=stuResume.getNation() %> >
						</td>
						<td>籍贯</td>
						<td><input type="text" name="nativePlace" value=<%=stuResume.getNativePlace()%> >
						</td>
					</tr>
					<tr>
						<td>出生年月</td>
						<td><input type="text"  name="birthday" value=<%=stuResume.getBirthday() %> >
						</td>
						<td>毕业年份</td>
						<td><input type="text" name="graduationYear" value=<%=stuResume.getGraduationYear() %>>
						</td>
					</tr>
					<tr>
						<td>学历</td>
						<td><input type="text" name="education" value=<%=stuResume.getEducation() %> >
						</td>
						<td>体重身高</td>
						<td><input type="text" name="hightweight" value=<%=stuResume.getHealthsituation() %>>
						</td>
					</tr>
					<tr>
						<td>毕业学校</td>
						<td><input type="text" name="graduationschool" value=<%=stuResume.getGraduationschool() %>>
						</td>
						<td>希望工作地区</td>
						<td><input type="text" name="hopePlace" value=<%=stuResume.getHopePlace() %>>
						</td>
					</tr>
					<tr>
						<td>专业</td>
						<td><input type="text" name="professional" value=<%=stuResume.getProfessional() %>>
						</td>
						<td>健康状况</td>
						<td><input type="text" name="healthsituation" value=<%=stuResume.getHealthsituation() %>>
						</td>
					</tr>
					<tr>
						<td>QQ</td>
						<td><input type="text" name="qq" value=<%=stuResume.getQq() %>>
						</td>
						<td>邮箱</td>
						<td><input type="text" name="poscalcode" value=<%=stuResume.getPoscalcode() %>>
						</td>
						<td><input type="file" name="picture" id="picture"  onchange="previewImage(this)">
						</td>
					</tr>
					<tr>
						<td>英语等级</td>
						<td><input type="text" name="englishClass" value=<%=stuResume.getEnglishClass() %>>
						</td>
						<td>精通方向</td>
						<td colspan="2">
						<%
							ArrayList al1=(ArrayList)request.getAttribute("directionInfo");
							for(int i=0;i<al1.size();i++)
							{
									Professionaldirection pr=(Professionaldirection)al1.get(i);
						 %>
						<input type="checkbox" name="direction" value=<%=pr.getDirection()%>><%=pr.getDirection()%>
						
							<%} %></td>
					</tr>
					<tr>
						<td>期望岗位</td>
						<td colspan="3"><textarea cols="72" rows="1" name="hopeJob"><%=stuResume.getHopeJob() %></textarea>
						</td>
					</tr>
					<tr>
						<td height=50 colspan="1">主修课程</td>
						<td colspan="4"><textarea cols="72" rows="4" name="major"><%=stuResume.getMajor() %></textarea>
						</td>
					</tr>
					<tr>
					<tr>
						<td height=180 colspan="1">个人简历</td>
						<td colspan="4"><textarea cols="72" rows="8"
								name="personalResume" ><%=stuResume.getPersonalResume() %></textarea>
						</td>
					</tr>
					<tr>
						<td height=70 colspan="1">奖惩情况</td>
						<td colspan="4"><textarea cols="72" rows="4"
								name="punishsituation"><%=stuResume.getPunishsituation() %></textarea>
						</td>
					</tr>
					<tr>
						<td height=150 colspan="1">项目经验</td>
						<td colspan="4"><textarea cols="72" rows="8"
								name="experiences" ><%= stuResume.getExperiences()%></textarea>
						</td>
					</tr>
					<tr>
						<td height=150 colspan="1">自我评价</td>
						<td colspan="4"><textarea cols="72" rows="8"
								name="evaluation" ><%=stuResume.getEvaluation() %></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="5"><center>
								<input type="submit" value="			更  		 新			">
							</center>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
  </body>
</html>
