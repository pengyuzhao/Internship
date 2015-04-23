package com.company.controller;
//只要负责company端的增删改查
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.domain.Company;
import com.company.service.CompanyService;
import com.student.service.StudentInfoService;

public class CompanyExcuteCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String type=request.getParameter("type");
		Company company=(Company) request.getSession().getAttribute("loginCompany");
		if(type.equals("updatePwd")){
			String prePwd=request.getParameter("prePwd");
			String nowPwd=request.getParameter("nowPwd");
			CompanyService companyService=new CompanyService();
			if(companyService.updatePassword(company, prePwd, nowPwd))
			{		
				out.println("操作成功正在跳转");response.setHeader("refresh", "2;URL=/Internship/GotoCompanyIndexCl");
			}
			else{
	 			out.println("原密码错误正在返回上页面");response.setHeader("refresh", "2;URL=/Internship/GoToCompanyPageCl?type=updatePassword");	
				
			}
		}
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		this.doGet(request, response);
	}

}
