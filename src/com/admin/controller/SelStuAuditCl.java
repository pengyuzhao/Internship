package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.SelStuAuditService;

// �������ѧ����ҳ��Ĳ�ѯ
public class SelStuAuditCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		// ����service
		SelStuAuditService selStuAuditService = new SelStuAuditService();
		
		// �����ҳ��Ҫ�ı���		
		int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
		int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
		
		// �����û�����Ĳ�ѯ���� 
		String term = request.getParameter("term");
		
		
		// -----------------------------------------------------------------------------------------
		// Ĭ������ʾ��δ��˵�ѧ��
		if("selAll".equals(type)){
			
			selAll(request, response);
		}
		// ��ʾĬ���б�ʱ�ķ�ҳ����
		else if("selAllpg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCount(pageSize);
			ArrayList<StudentResume> al = selStuAuditService.getStusByPage(pageNow, pageSize);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selAllpg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// ��ʾȫ���б�ʱ
		else if("selAllAudit".equals(type)){
			
			int pageNow = 1;
			
			pageCount = selStuAuditService.getPageCountAll(pageSize);
			
			// ��Ϊ��ʾȫ�����Ͱ���ѧ��˳����ʾ����
			ArrayList<StudentResume> al = selStuAuditService.getStusByPageAll(pageNow, pageSize);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("type", "selAllAuditpg");
			request.setAttribute("pgNow", pageNow);			
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// ��ʾȫ���б�ʱ�ķ�ҳ
		else if("selAllAuditpg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCountAll(pageSize);
			ArrayList<StudentResume> al = selStuAuditService.getStusByPageAll(pageNow, pageSize);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selAllAuditpg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// -------------select�ؼ���ģ����ѯ--------------------------------------------------------------
		else if("selOp".equals(type)){
			
			// ���յ�select�ؼ��ľ���optionֵ
			String option = request.getParameter("option");
			
			// -------������-----------------------------------------
			if("name".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuAuditService.getPageCountByName(pageSize,term);
					
					ArrayList<StudentResume> al = selStuAuditService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ�����ʾĬ������µ�ȫ���б�
				else{
					
					selAll(request, response);
				}
			}
			
			// ------��ѧ�Ų�-------------------------------------
			else if("stuId".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuAuditService.getPageCountById(pageSize,term);
					
					ArrayList<StudentResume> al = selStuAuditService.getStusPageById(pageNow, pageSize,term);
				
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selIdPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ��ͻ�����ʾĬ������µ�
				else{
					
					selAll(request, response);
				}
			}
			
			// ----------�ؼ��ֲ�ѯ(������ѧ�š��Ա�רҵ�������ޡ����ᡢϣ������������ϣ����λ)----------------------------------
			else if("all".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuAuditService.getPageCountByAll(pageSize,term);
					
					ArrayList<StudentResume> al = selStuAuditService.getStusPageByAll(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selLargePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ��ͻ�����ʾĬ������µ�
				else{
					
					selAll(request, response);
				}
			}
			
			// ------���ͨ�������£��ɰ�������ѧ�š�רҵ�����--------------------------------------------------------------
			else if("approved".equals(option)){
			
				// ����û�����Ϊ�գ��Ͳ�ѯ���������ͨ���ģ�
				// ��Ϊ�գ��Ͱ�����������������ѧ�š�רҵ�����в�
				
				int pageNow = 1;	
				
				pageCount = selStuAuditService.getPageCountByOk(pageSize,term);
				
				ArrayList<StudentResume> al = selStuAuditService.getStusPageByOk(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "��ʾ��δ�ҵ����ͨ����ѧ����");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
		
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selApproPg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
				}
			}
			
			// ------���δͨ�������£��ɰ�������ѧ�š�רҵ�����--------------------------------------------------------------
			else if("noApproved".equals(option)){
			
				// ����û�����Ϊ�գ��Ͳ�ѯ���������ͨ���ģ�
				// ��Ϊ�գ��Ͱ�����������������ѧ�š�רҵ�����в�
				
				int pageNow = 1;	
				
				pageCount = selStuAuditService.getPageCountByNoOk(pageSize,term);

				ArrayList<StudentResume> al = selStuAuditService.getStusPageByNoOk(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "��ʾ��δ�ҵ���˲�ͨ����ѧ����");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
		
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selNoApproPg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
				}
				
			}
		}
		
		
		// ������ѯ�µķ�ҳ����
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCountByName(pageSize,term);
			ArrayList<StudentResume> al = selStuAuditService.getStusPageByName(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selNamePg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// ѧ�Ų�ѯ�µķ�ҳ����
		else if("selIdPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCountById(pageSize,term);
			ArrayList<StudentResume> al = selStuAuditService.getStusPageById(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selIdPg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// �ؼ��ֲ�ѯ�ķ�ҳ����
		else if("selLargePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCountByAll(pageSize,term);
			ArrayList<StudentResume> al = selStuAuditService.getStusPageByAll(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selLargePg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// ��ѯ���ͨ���ĵķ�ҳ����
		else if("selApproPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCountByOk(pageSize,term);
			ArrayList<StudentResume> al = selStuAuditService.getStusPageByOk(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selApproPg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// ��ѯδ���ͨ���ĵķ�ҳ����
		else if("selNoApproPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuAuditService.getPageCountByNoOk(pageSize,term);
			ArrayList<StudentResume> al = selStuAuditService.getStusPageByNoOk(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selNoApproPg");
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
	}
	
	
	
	//----------------��ʾȫ���б�ĺ�����װ----------------------------
	public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		// ����service
		SelStuAuditService selStuAuditService = new SelStuAuditService();
		
		// �����ҳ��Ҫ�ı���		
		int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
		int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
		int pageNow = 1;
		
		pageCount = selStuAuditService.getPageCount(pageSize);
		
		// ��Ϊ��ʾȫ�����Ͱ���ѧ��˳����ʾ����
		ArrayList<StudentResume> al = selStuAuditService.getStusByPage(pageNow, pageSize);
		
		request.setAttribute("stu", al);
		request.setAttribute("pgcount", pageCount);
		request.setAttribute("type", "selAllpg");
		request.setAttribute("pgNow", pageNow);			
		request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
