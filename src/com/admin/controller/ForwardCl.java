package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ´¦ÀíÌø×ª
public class ForwardCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		if("addExcel".equals(type)){
		
			request.getRequestDispatcher("/WEB-INF/admin/uloadStudentData.jsp").forward(request, response);
		}
		
		else if("data".equals(type)){
			
			request.getRequestDispatcher("/WEB-INF/admin/importData.jsp").forward(request, response);
		}
		
		else if("addCom".equals(type)){
			
			request.getRequestDispatcher("/WEB-INF/admin/addCompany.jsp").forward(request, response);
		}
		
		else if("exit".equals(type)){
			
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
		else if("index".equals(type)){
			
			
			request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
