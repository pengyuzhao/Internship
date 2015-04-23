package com.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.StuResume;
import com.student.domain.StudentInfo;
import com.student.service.StuResumeService;
//负责页面的跳转
public class GoToPageCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		StudentInfo studentInfo=(StudentInfo)request.getSession().getAttribute("loginStudent");
		if(type.equals("upload"))
		{
			StuResumeService stuResumeService=new StuResumeService();
			StuResume stuResume=stuResumeService.selectCheckState(studentInfo);
			
			if(stuResume.getCheckState()==1)
			{
				
				request.setAttribute("checkState", stuResume);
				request.getRequestDispatcher("/WEB-INF/student/checkState.jsp").forward(request, response);
				
			}
			else{
			request.getRequestDispatcher("/WEB-INF/student/uload.jsp").forward(request, response);
			}
		}
		
		if(type.equals("updatePwd"))
		{
			request.getRequestDispatcher("/WEB-INF/student/updatepassword.jsp").forward(request, response);
		}
		if(type.equals("writeResume"))
		{
		
			request.getRequestDispatcher("/GoToWriteResume").forward(request, response);
		}
		if(type.equals("watchResume"))
		{
			request.getRequestDispatcher("/GoToWatchResume").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doGet(request, response);
	}

}
