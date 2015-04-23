package com.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.AuditStuService;
import com.admin.service.UpdStuInfoService;

// 审核学生的控制器
public class AuditStuCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		UpdStuInfoService updStuInfoService = new UpdStuInfoService();
		
		// 创建service 
		AuditStuService auditStuService = new AuditStuService();
		
		// 审核通过
		if("auditOk".equals(type)){
			
			// 接收id
			String studentId = request.getParameter("stuId");

			if(auditStuService.approved(studentId)){
				request.setAttribute("info", "gotoEva");
				request.setAttribute("stuId", studentId);
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "提示：执行过程出错，请重试！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 审核不通过
		else if("auditNo".equals(type)){
			
			// 接收id
			String studentId = request.getParameter("stuId");
			StudentResume stu = updStuInfoService.getStureByStuId(studentId);

			if(auditStuService.noApproved(studentId)){
				request.setAttribute("stuInfo", stu);
				request.setAttribute("stuId", studentId);
				request.getRequestDispatcher("/WEB-INF/admin/refuseReason.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "提示：执行过程出错，请重试！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 审核不通过提交原因
		else if("reason".equals(type)){
			
			String studentId = request.getParameter("stuId");
			String reason = request.getParameter("reasonText");
			
			if(auditStuService.noApprovedReason(studentId,reason)){
				request.setAttribute("info", "提示：该同学的简历没有通过审核！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "提示：执行过程出错，请重试！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 查看原因 
		else if("lookReason".equals(type)){
			
			String studentId = request.getParameter("stuId");
			StudentResume stu = auditStuService.lookReason(studentId);
			
			request.setAttribute("stuInfo", stu);
			request.setAttribute("stuId", studentId);
			request.getRequestDispatcher("/WEB-INF/admin/lookReason.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
