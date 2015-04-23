package com.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Company;
import com.admin.service.ManageComService;

// ����˾�Ŀ���������ӡ�ɾ�����޸�
public class ManageComCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		String type = request.getParameter("type");
		
		ManageComService manageComService = new ManageComService();
		
		if("addCom".equals(type)){
			
			// ����
			String companyName = request.getParameter("companyName");
			String companyAccount = request.getParameter("companyAccount");
			String companyPwd = request.getParameter("companyPwd");
			String companyAddress = request.getParameter("companyAddress");
			
			// ��װ���� 
			Company company = new Company();
			
			company.setCompanyName(companyName);
			company.setCompanyAccount(companyAccount);
			company.setCompanyPwd(companyPwd);
			company.setCompanyAddress(companyAddress);
			
			// ����service�࣬�������ݿ�
			if(manageComService.addCompany(company)){
				request.setAttribute("info", "��ϲ�����ɹ��Ѵ˹�˾����Ϣ��ӵ����ݿ⣡");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "�Բ��������Ϣʧ�ܣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// ��ת�鿴ʵϰ���
		else if("goSeeCom".equals(type)){
			
			String companyId = request.getParameter("comId");
			Company company = manageComService.getComById(companyId);
		}
		
		else if("gotoUpCom".equals(type)){
			
			// ����id����Ϣ
			String companyId = request.getParameter("comId");
			Company company = manageComService.getComById(companyId);
			
			request.setAttribute("comInfo", company);
			request.setAttribute("comId", companyId);
			// �����޸�����ȥ�޸�ҳ��
			request.getRequestDispatcher("/WEB-INF/admin/updateComInfo.jsp").forward(request, response);
		}
		
		// �޸� 
		else if("updCom".equals(type)){
			
			String id = request.getParameter("id");
			String companyName = request.getParameter("companyName");
			String companyAccount = request.getParameter("companyAccount");
			String companyPwd = request.getParameter("companyPwd");
			String companyAddress = request.getParameter("companyAddress");
			
			// �ѽ��յ�����Ϣ����װ��һ��com����
			Company company = new Company();
			company.setId(Integer.parseInt(id));
			company.setCompanyName(companyName);
			company.setCompanyAccount(companyAccount);
			company.setCompanyPwd(companyPwd);
			company.setCompanyAddress(companyAddress);
			
			// ����
			if(manageComService.updCom(company)){
				request.setAttribute("info", "��ϲ�����޸Ĵ˹�˾��Ϣ�ɹ���");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "�Բ����޸Ĵ˹�˾��Ϣʧ�ܣ�");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// ɾ��
		else if("delCom".equals(type)){
			
			// ����Ҫɾ����id
			String id = request.getParameter("comId");

			// ����
			if(manageComService.delCom(id)){
				request.setAttribute("info", "��ϲ�����ɹ��Ѵ˹�˾��������ɾ����");
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
