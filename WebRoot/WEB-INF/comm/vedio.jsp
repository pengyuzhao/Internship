<%@ page language="java" import="java.util.*,com.student.domain.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'vedio.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <% Filepath fp=(Filepath)request.getAttribute("filepath");
  	 String vedio=fp.getVedioFilePath().substring(1);
  
   %>
  <body><%if(fp.getVedioFilePath()!=null) {%>
      <center> <embed src=<%=vedio %> autostart="true" loop="true" width="550" height="550" ></center>
<%} %>
  </body>
</html>
