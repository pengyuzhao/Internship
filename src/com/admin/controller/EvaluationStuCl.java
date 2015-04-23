package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.EvaluationStuService;
import com.admin.service.UpdStuInfoService;

public class EvaluationStuCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		UpdStuInfoService updStuInfoService = new UpdStuInfoService();
		EvaluationStuService evaluationStuService = new EvaluationStuService();
		
		// 去评价
		if("goEva".equals(type)){
			
			String studentId = request.getParameter("stuId");
		
			StudentResume stu = updStuInfoService.getStureByStuId(studentId);
			
			request.setAttribute("stuInfo", stu);
			request.setAttribute("stuId", studentId);
			request.getRequestDispatcher("/WEB-INF/admin/evaluate.jsp").forward(request, response);
		}
		
		// 提交
		else if("Eva".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			String evaluation = request.getParameter("evaluation");
			
			if(evaluationStuService.EvaluationStu(studentId, evaluation)){
				request.setAttribute("info", "评价成功！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，评价失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 去修改评价 
		else if("goUpdEva".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			StudentResume stu = updStuInfoService.getStureByStuId(studentId);
			
			request.setAttribute("stuInfo", stu);
			request.setAttribute("stuId", studentId);
			request.getRequestDispatcher("/WEB-INF/admin/updateEvaluate.jsp").forward(request, response);
		}
		
		// 提交
		else if("updEva".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			String evaluation = request.getParameter("evaluation");
			
			if(evaluationStuService.UpdEvaluationStu(studentId, evaluation)){
				request.setAttribute("info", "修改评价成功！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，修改评价失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
