package com.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.AuditStuService;
import com.admin.service.UpdStuInfoService;

// ���ѧ���Ŀ�����
public class AuditStuCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		UpdStuInfoService updStuInfoService = new UpdStuInfoService();
		
		// ����service 
		AuditStuService auditStuService = new AuditStuService();
		
		// ���ͨ��
		if("auditOk".equals(type)){
			
			// ����id
			String studentId = request.getParameter("stuId");

			if(auditStuService.approved(studentId)){
				request.setAttribute("info", "gotoEva");
				request.setAttribute("stuId", studentId);
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "��ʾ��ִ�й��̳��������ԣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// ��˲�ͨ��
		else if("auditNo".equals(type)){
			
			// ����id
			String studentId = request.getParameter("stuId");
			StudentResume stu = updStuInfoService.getStureByStuId(studentId);

			if(auditStuService.noApproved(studentId)){
				request.setAttribute("stuInfo", stu);
				request.setAttribute("stuId", studentId);
				request.getRequestDispatcher("/WEB-INF/admin/refuseReason.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "��ʾ��ִ�й��̳��������ԣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// ��˲�ͨ���ύԭ��
		else if("reason".equals(type)){
			
			String studentId = request.getParameter("stuId");
			String reason = request.getParameter("reasonText");
			
			if(auditStuService.noApprovedReason(studentId,reason)){
				request.setAttribute("info", "��ʾ����ͬѧ�ļ���û��ͨ����ˣ�");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "��ʾ��ִ�й��̳��������ԣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// �鿴ԭ�� 
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
