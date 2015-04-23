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

// ���ѧ��ʵϰ��Ϣ�Ŀ�����
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
		
		// ׼����ת���ʵϰҳ��
		if("registCom".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			ArrayList<Company> al = manageComService.getAllCom();
					
			request.setAttribute("stu", student);
			request.setAttribute("stuId", studentId);
			request.setAttribute("comAl", al);
			request.getRequestDispatcher("/WEB-INF/admin/registStuCom.jsp").forward(request, response);
			
		}
		
		// ������Ϣ�����ʵϰ��Ϣ�����ݿ�
		else if("registComOk".equals(type)){
			
			// ����select�ؼ�������ֵ
			String value = request.getParameter("option");
			
			String option[] = value.split(",");
			int companyId = Integer.parseInt(option[0]);
			String companyName = option[1];
			
			String studentId = request.getParameter("stuId");

			Student student = updStuInfoService.getStuByStuId(studentId);
			
			if(registerStuComService.RegisterStuCom(companyId, studentId,student)){
				
				if(registerStuComService.RegisterStuComtoResume(companyName, studentId)){
					request.setAttribute("info", "��ϲ�����ѳɹ��ǼǸ�ѧ����ʵϰ��Ϣ��");
					request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
				}else{
				
					request.setAttribute("info", "�Բ��𣬵ǼǸ�ѧ��ʵϰ��Ϣʧ��1��");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("info", "�Բ��𣬵ǼǸ�ѧ��ʵϰ��Ϣʧ��2��");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// ��ת�޸�ҳ��
		else if("goUpdCom".equals(type)){
			
			String studentId = request.getParameter("stuId");
			
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			ArrayList<Company> al = manageComService.getAllCom();
					
			request.setAttribute("stu", student);
			request.setAttribute("stuId", studentId);
			request.setAttribute("comAl", al);
			request.getRequestDispatcher("/WEB-INF/admin/updateRegist.jsp").forward(request, response);
		}
		
		// ������Ϣ��ȥ���ݿ��޸�
		else if("updCom".equals(type)){
			
			String value = request.getParameter("option");
			
			String option[] = value.split(",");
			int companyId = Integer.parseInt(option[0]);
			String companyName = option[1];
			
			String studentId = request.getParameter("stuId");
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			if(registerStuComService.UpdateStuCom(companyId, studentId)){
				
				if(registerStuComService.RegisterStuComtoResume(companyName, studentId)){
					request.setAttribute("info", "��ϲ�����ѳɹ��޸ĸ�ѧ����ʵϰ��Ϣ��");
					request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
				}else{
				
					request.setAttribute("info", "�Բ����޸ĸ�ѧ��ʵϰ��Ϣʧ��1��");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}
			}else{
				request.setAttribute("info", "�Բ����޸ĸ�ѧ��ʵϰ��Ϣʧ��2��");
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
