package com.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Company;
import com.admin.service.ManageComService;

// 管理公司的控制器，添加、删除、修改
public class ManageComCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		String type = request.getParameter("type");
		
		ManageComService manageComService = new ManageComService();
		
		if("addCom".equals(type)){
			
			// 接收
			String companyName = request.getParameter("companyName");
			String companyAccount = request.getParameter("companyAccount");
			String companyPwd = request.getParameter("companyPwd");
			String companyAddress = request.getParameter("companyAddress");
			
			// 封装对象 
			Company company = new Company();
			
			company.setCompanyName(companyName);
			company.setCompanyAccount(companyAccount);
			company.setCompanyPwd(companyPwd);
			company.setCompanyAddress(companyAddress);
			
			// 调用service类，存入数据库
			if(manageComService.addCompany(company)){
				request.setAttribute("info", "恭喜您，成功把此公司的信息添加到数据库！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，添加信息失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 跳转查看实习情况
		else if("goSeeCom".equals(type)){
			
			String companyId = request.getParameter("comId");
			Company company = manageComService.getComById(companyId);
		}
		
		else if("gotoUpCom".equals(type)){
			
			// 根据id查信息
			String companyId = request.getParameter("comId");
			Company company = manageComService.getComById(companyId);
			
			request.setAttribute("comInfo", company);
			request.setAttribute("comId", companyId);
			// 接收修改请求，去修改页面
			request.getRequestDispatcher("/WEB-INF/admin/updateComInfo.jsp").forward(request, response);
		}
		
		// 修改 
		else if("updCom".equals(type)){
			
			String id = request.getParameter("id");
			String companyName = request.getParameter("companyName");
			String companyAccount = request.getParameter("companyAccount");
			String companyPwd = request.getParameter("companyPwd");
			String companyAddress = request.getParameter("companyAddress");
			
			// 把接收到的信息，封装成一个com对象
			Company company = new Company();
			company.setId(Integer.parseInt(id));
			company.setCompanyName(companyName);
			company.setCompanyAccount(companyAccount);
			company.setCompanyPwd(companyPwd);
			company.setCompanyAddress(companyAddress);
			
			// 调用
			if(manageComService.updCom(company)){
				request.setAttribute("info", "恭喜您，修改此公司信息成功！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，修改此公司信息失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 删除
		else if("delCom".equals(type)){
			
			// 接收要删除的id
			String id = request.getParameter("comId");

			// 调用
			if(manageComService.delCom(id)){
				request.setAttribute("info", "恭喜您，成功把此公司所有数据删除！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，删除失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
