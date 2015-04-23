package com.student.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.StudentInfo;
import com.student.service.ProfessionalService;
import com.student.service.StuResumeService;

public class GoToUpdateResume extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		StudentInfo studentInfo=(StudentInfo)request.getSession().getAttribute("loginStudent");
		StuResumeService stuResumeService=new StuResumeService();
		ArrayList al=stuResumeService.getResumeById(studentInfo.getStudentId());
		ProfessionalService ps=new ProfessionalService();
		ArrayList al1=ps.getAllDirection();
		request.setAttribute("directionInfo", al1);
		request.setAttribute("updateResume", al);
		request.getRequestDispatcher("/WEB-INF/student/updateResume.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doGet(request, response);
	}

}
