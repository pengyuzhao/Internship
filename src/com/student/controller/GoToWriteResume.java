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
import com.student.service.Filepathservice;
import com.student.service.ProfessionalService;
import com.student.service.StuResumeService;
//这个控制器负责跳转到编写简历页面
public class GoToWriteResume extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		StudentInfo studentInfo=(StudentInfo)request.getSession().getAttribute("loginStudent");
		ProfessionalService ps=new ProfessionalService();
		ArrayList al=ps.getAllDirection();
		StuResumeService stuResumeService=new StuResumeService();
		Filepathservice filepathservice=new Filepathservice();
		StuResume stuResume=stuResumeService.selectCheckState(studentInfo);	
		int i=stuResume.getCheckState();
		//根据CheckState()判断需要跳转到哪个页面
		/*if(i==3){
		filepathservice.delFilepath(studentInfo);
		stuResumeService.delResume(studentInfo);
		request.setAttribute("directionInfo", al);
		request.getRequestDispatcher("/WEB-INF/student/writeResume.jsp").forward(request, response);
		}*/
		
		if(i==2){
	//	filepathservice.delFilepath(studentInfo);
	//	stuResumeService.delResume(studentInfo);
		request.setAttribute("directionInfo", al);
		request.getRequestDispatcher("/WEB-INF/student/writeResume.jsp").forward(request, response);
		}else{
			
		request.setAttribute("checkState", stuResume);
		request.getRequestDispatcher("/WEB-INF/student/checkState.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		this.doGet(request, response);
	}

}
