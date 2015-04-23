package com.student.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.domain.StudentInfo;

public class GoToIndexCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		/*StudentInfo loginStudent=new StudentInfo();
		loginStudent.setStudentName("a");
		loginStudent.setStudentClass("пео╒1");
		loginStudent.setStudentTel("1830000");
		loginStudent.setStudentId("12124640131");
		loginStudent.setStudentCardId("111111111");
		loginStudent.setStudentPwd("123");
		request.getSession().setAttribute("loginStudent", loginStudent);*/
		request.getRequestDispatcher("/WEB-INF/student/index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		this.doGet(request, response);
	}

}
