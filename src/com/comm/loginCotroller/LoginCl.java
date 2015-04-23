package com.comm.loginCotroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Admin;
import com.admin.service.AdminService;
import com.company.domain.Company;
import com.company.service.CompanyService;
import com.student.domain.StudentInfo;
import com.student.service.StudentInfoService;

public class LoginCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userName=request.getParameter("username");
		String password=request.getParameter("passwd");
		String code=request.getParameter("userCode");
		String power =request.getParameter("power");
		StudentInfoService studentInfoService=new StudentInfoService();
		CompanyService companyService =new CompanyService();
		String num=(String) request.getSession().getAttribute("num");
		if(!(num.equals(code)))
		{
			request.setAttribute("err", "—È÷§¬Î¥ÌŒÛ");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		else{
		if(power.equals("admin")){
			AdminService adminService = new AdminService();
			Admin admin = new Admin();
			admin.setAdminAccount(userName);
			admin.setAdminPwd(password);
			if(adminService.checkAdmin(admin)){
				Admin admin1 = adminService.getAdminByname(userName);
				request.getSession().setAttribute("adminInfo", admin1);
			request.getRequestDispatcher("/WEB-INF/admin/index.jsp").forward(request,response);
			}else{
				request.setAttribute("err", "”√ªß√˚ªÚ’ﬂ√‹¬Î¥ÌŒÛ");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		}
		if(power.equals("company")){
			if(companyService.checkName(userName,password))
			{
				Company company=new Company();
				company=companyService.getCompanyInfo(userName);
				request.getSession().setAttribute("loginCompany",company );
				request.getRequestDispatcher("/GotoCompanyIndexCl").forward(request, response);
			}
			else{
				request.setAttribute("err", "’ ∫≈ªÚ√‹¬Î¥ÌŒÛ");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
			
		}
		if(power.equals("student")){
			System.out.println("1");
			if(studentInfoService.checkName(userName,password))
			{
				StudentInfo studentInfo=new StudentInfo();
				studentInfo=studentInfoService.getStudentInfo(userName);
				request.getSession().setAttribute("loginStudent",studentInfo );
				request.getRequestDispatcher("/GoToIndexCl").forward(request, response);
			}
			else{
				request.setAttribute("err", "’ ∫≈ªÚ√‹¬Î¥ÌŒÛ");
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		}
	}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		this.doGet(request, response);
	}

}
