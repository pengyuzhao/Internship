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

public class GoToWatchResume extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		StudentInfo studentInfo=(StudentInfo)request.getSession().getAttribute("loginStudent");
		StuResumeService stuResumeService=new StuResumeService();
		StuResume stuResume=stuResumeService.getStuInfoByoneself(studentInfo);
		request.setAttribute("stuPartInfo", stuResume);
		request.getRequestDispatcher("/WEB-INF/student/watchResume.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		this.doGet(request, response);
	}

}
