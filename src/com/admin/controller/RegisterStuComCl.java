package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Company;
import com.admin.domain.Student;
import com.admin.service.ManageComService;
import com.admin.service.RegisterStuComService;
import com.admin.service.UpdStuInfoService;

// 添加学生实习信息的控制器
public class RegisterStuComCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");
		
		ManageComService manageComService = new ManageComService();
		UpdStuInfoService updStuInfoService = new UpdStuInfoService();
		RegisterStuComService registerStuComService = new RegisterStuComService();
		
		// 准备跳转添加实习页面
		if("registCom".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			ArrayList<Company> al = manageComService.getAllCom();
					
			request.setAttribute("stu", student);
			request.setAttribute("stuId", studentId);
			request.setAttribute("comAl", al);
			request.getRequestDispatcher("/WEB-INF/admin/registStuCom.jsp").forward(request, response);
			
		}
		
		// 接收信息，添加实习信息到数据库
		else if("registComOk".equals(type)){
			
			// 接收select控件的两个值
			String value = request.getParameter("option");
			
			String option[] = value.split(",");
			int companyId = Integer.parseInt(option[0]);
			String companyName = option[1];
			
			String studentId = request.getParameter("stuId");

			Student student = updStuInfoService.getStuByStuId(studentId);
			
			if(registerStuComService.RegisterStuCom(companyId, studentId,student)){
				
				if(registerStuComService.RegisterStuComtoResume(companyName, studentId)){
					request.setAttribute("info", "恭喜您，已成功登记该学生的实习信息！");
					request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
				}else{
				
					request.setAttribute("info", "对不起，登记该学生实习信息失败1！");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("info", "对不起，登记该学生实习信息失败2！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 跳转修改页面
		else if("goUpdCom".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			ArrayList<Company> al = manageComService.getAllCom();
					
			request.setAttribute("stu", student);
			request.setAttribute("stuId", studentId);
			request.setAttribute("comAl", al);
			request.getRequestDispatcher("/WEB-INF/admin/updateRegist.jsp").forward(request, response);
		}
		
		// 接收信息，去数据库修改
		else if("updCom".equals(type)){
			
			String value = request.getParameter("option");
			
			String option[] = value.split(",");
			int companyId = Integer.parseInt(option[0]);
			String companyName = option[1];
			
			String studentId = request.getParameter("stuId");
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			if(registerStuComService.UpdateStuCom(companyId, studentId)){
				
				if(registerStuComService.RegisterStuComtoResume(companyName, studentId)){
					request.setAttribute("info", "恭喜您，已成功修改该学生的实习信息！");
					request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
				}else{
				
					request.setAttribute("info", "对不起，修改该学生实习信息失败1！");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("info", "对不起，修改该学生实习信息失败2！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
	}

	private Object split(String value, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
