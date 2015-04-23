package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Admin;
import com.admin.service.UpdAdminInfoService;


// 处理修改管理员信息的控制器
public class UpdateAdminInfoCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		UpdAdminInfoService updAdminInfoService = new UpdAdminInfoService();
		
		if("goUpd".equals(type)){
			
			request.getRequestDispatcher("/WEB-INF/admin/updatePassword.jsp").forward(request, response);
		}
		else if("updAdm".equals(type)){
			
			Admin admin = (Admin)request.getSession().getAttribute("adminInfo");
			String pwd = request.getParameter("pwd");
			String newPwd = request.getParameter("newPwd");
			
			if(pwd.equals(admin.getAdminPwd())){
				
				if(updAdminInfoService.updAdmPwd(admin, newPwd)){
					
					request.setAttribute("info", "修改密码成功，下次登录请使用新密码登录！");
					request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
				}else{
					request.setAttribute("info", "对不起，修改密码失败！");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}
			}else
			{
				request.setAttribute("info", "请输入正确的原密码！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
			
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
