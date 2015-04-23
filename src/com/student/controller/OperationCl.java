package com.student.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.Filepath;
import com.student.domain.StuResume;
import com.student.domain.StudentInfo;
import com.student.service.Filepathservice;
import com.student.service.StuResumeService;
//此控制器用于在查询页面里面的用户操作
public class OperationCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		StudentInfo loginStudent=(StudentInfo) request.getSession().getAttribute("loginStudent");
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		Filepathservice fs=new Filepathservice();
		Filepath fp=new Filepath();
	
		fp=fs.getPathById(id);
		if(type.equals("watchResume"))
		{
			StuResumeService sr=new StuResumeService();
			ArrayList al=sr.getResumeById(id);
			request.setAttribute("resumeInfo", al);
			request.getRequestDispatcher("/WEB-INF/comm/resume.jsp").forward(request, response);
		}
		if(type.equals("vedio"))
		{
			if(fp.getVedioFilePath()!=null){
			request.setAttribute("filepath", fp);
			request.getRequestDispatcher("/WEB-INF/comm/vedio.jsp").forward(request, response);
			}
			else{
				request.setAttribute("err", "还未上传作品影像");
				request.getRequestDispatcher("/WEB-INF/comm/info.jsp").forward(request, response);
			}
		}
		if(type.equals("downSource"))
		{
			
			if(fp.getSourceFilePath()!=null){
			//String temp=java.net.URLEncoder.encode(loginStudent.getStudentName()+"源代码压缩包.rar","utf-8");
			String temp=java.net.URLEncoder.encode("源代码压缩包.rar","utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="+temp);
			String source=fp.getSourceFilePath();
			System.out.println(source);
			String path=this.getServletContext().getRealPath(source);
			FileInputStream fis=new FileInputStream(path);
			byte buff[]=new byte[1024*100];
			int len=0;
			OutputStream os=response.getOutputStream();
			while((len=fis.read(buff))>0){
				os.write(buff, 0, len);
				os.close();
				fis.close();
				}
			}
			else{
				request.setAttribute("err", "还未上传源码");
				request.getRequestDispatcher("/WEB-INF/comm/info.jsp").forward(request, response);
				
			}
		
			
		}
			
		
	
		if(type.equals("techerevaluation"))
		{
		
			StuResumeService sr=new StuResumeService();
			StuResume stuResume=sr.getTecherevaluation(id);
			request.setAttribute("stuResume",stuResume);
			request.getRequestDispatcher("/WEB-INF/comm/watchEvaluate.jsp").forward(request, response);
		}
		
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		this.doGet(request, response);
	}

}
