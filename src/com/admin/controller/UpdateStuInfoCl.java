package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Student;
import com.admin.service.UpdStuInfoService;

// ���ڴ������ѧ����Ϣ�Ŀ�����
public class UpdateStuInfoCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		// ����service����
		UpdStuInfoService updStuInfoService = new UpdStuInfoService();
		
		// ����µ�ѧ����Ϣ
		if("addStu".equals(type)){
			//������Ϣ
			String studentId = request.getParameter("studentId");
			String studentName = request.getParameter("studentName");
			String studentPwd = request.getParameter("studentPwd");
			String studentClass =request.getParameter("studentClass");
			String studentCardId =request.getParameter("studentCardId");
			String studentTel =request.getParameter("studentTel");
			
			// �ѽ��յ�����Ϣ����װ��һ��stu����
			Student student = new Student();
			
			student.setStudentId(studentId);
			student.setStudentName(studentName);
			student.setStudentPwd(studentPwd);
			student.setStudentClass(studentClass);
			student.setStudentCardId(studentCardId);
			student.setStudentTel(studentTel);
			
			// ����service�࣬�������ݿ�
			if(updStuInfoService.addStudent(student)){
				request.setAttribute("info", "��ϲ�����ɹ��Ѵ�ѧ������Ϣ��ӵ����ݿ⣡");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "�Բ��������Ϣʧ�ܣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// �޸�ѧ����Ϣ
		else if("gotoUpStu".equals(type)){
			
			// ����ѧ�Ų���Ϣ
			String studentId = request.getParameter("stuId");
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			request.setAttribute("stuInfo", student);
			request.setAttribute("stuId", studentId);
			// �����޸�����ȥ�޸�ҳ��
			request.getRequestDispatcher("/WEB-INF/admin/updateStudentInfo.jsp").forward(request, response);
		}
		// ����ѧ���޸ĵ���Ϣ������service�������ݿ�
		else if("updStu".equals(type)){
			
			String studentId = request.getParameter("studentId");
			String studentName = request.getParameter("studentName");
			String studentPwd = request.getParameter("studentPwd");
			String studentCardId = request.getParameter("studentCardId");
			String studentClass = request.getParameter("studentClass");
			String studentTel = request.getParameter("studentTel");
			
			// �ѽ��յ�����Ϣ����װ��һ��stu����
			Student student = new Student(studentId,studentName,studentPwd,studentCardId,studentClass,studentTel);
			
			// ����
			if(updStuInfoService.updStu(student)){
				request.setAttribute("info", "��ϲ�����޸Ĵ�ѧ����Ϣ�ɹ���");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "�Բ����޸Ĵ�ѧ����Ϣʧ�ܣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// ɾ��ѧ����Ϣ
		else if("delStu".equals(type)){
			
			// ����Ҫɾ����id
			String studentId = request.getParameter("stuId");

			// ����
			if(updStuInfoService.delStu(studentId)){
				request.setAttribute("info", "��ϲ�����ɹ��Ѵ�ѧ����������ɾ����");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "�Բ���ɾ��ʧ�ܣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
