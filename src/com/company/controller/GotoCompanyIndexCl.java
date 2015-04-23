package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.domain.Company;

public class GotoCompanyIndexCl extends HttpServlet {
//��¼�ɹ������ڴ�ҳ������ session
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//���˵�¼֮�� �����������ͨ����ѯ���ݿ����
	
		request.getRequestDispatcher("/WEB-INF/company/index.jsp").forward(request, response);
		
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		this.doGet(request, response);
	}

}
