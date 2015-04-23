package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Student;
import com.admin.service.SelStuInfoService;

// �����޸�ѧ��������Ϣҳ��Ĳ�ѯ
public class SelStuInfoCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		// ����service����
		SelStuInfoService selStuInfoService = new SelStuInfoService();
		
		// �����ҳ��Ҫ�ı���		
		int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
		int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
		
		// �����û�����Ĳ�ѯ���� 
		String term = request.getParameter("term");
		
		// ��ʾȫ���б�
		if("selAll".equals(type)){
			
			selAll(request, response);
			
		}
		// ��ʾȫ���б�ʱ�ķ�ҳ����
		else if("selAllpg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuInfoService.getPageCount(pageSize);
			ArrayList<Student> al = selStuInfoService.getStusByPage(pageNow, pageSize);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selAllpg");
			request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
		}
		
		//----------select�ؼ��в�ѯ---------------------------------------------------------
		else if("selOp".equals(type)){
			
			// ���յ�select�ؼ��ľ���optionֵ
			String option = request.getParameter("option");
			
			//-----��������--------------------------------------------------
			if("name".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuInfoService.getPageCountByName(pageSize,term);
					
					ArrayList<Student> al = selStuInfoService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
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
					
					pageCount = selStuInfoService.getPageCountById(pageSize,term);

					ArrayList<Student> al = selStuInfoService.getStusPageById(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selIdPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ��ͻ�����ʾĬ������µ�
				else{
					
					selAll(request, response);
				}
			}
			
			// ------���༶��-------------------------------------
			else if("class".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuInfoService.getPageCountByClass(pageSize,term);
					
					ArrayList<Student> al = selStuInfoService.getStusPageByClass(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selClassPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ��ͻ�����ʾĬ������µ�
				else{
					
					selAll(request, response);
				}
			}
		}
		
		// ������ѯ�µķ�ҳ����
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuInfoService.getPageCountByName(pageSize,term);
			ArrayList<Student> al = selStuInfoService.getStusPageByName(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selNamePg");
			request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
		}
		
		// ѧ�Ų�ѯ�µķ�ҳ����
		else if("selIdPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuInfoService.getPageCountById(pageSize,term);
			ArrayList<Student> al = selStuInfoService.getStusPageById(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selIdPg");
			request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
		}
		
		// �༶��ѯ�µķ�ҳ����
		else if("selClassPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuInfoService.getPageCountById(pageSize,term);
			ArrayList<Student> al = selStuInfoService.getStusPageById(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selClassPg");
			request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
		}
	}
	
	
	//----------------��ʾȫ���б�ĺ�����װ----------------------------
	public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		// ����service
		SelStuInfoService selStuInfoService = new SelStuInfoService();
		
		// �����ҳ��Ҫ�ı���		
		int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
		int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
		int pageNow = 1;
		
		pageCount = selStuInfoService.getPageCount(pageSize);
		
		// ��Ϊ��ʾȫ�����Ͱ���ѧ��˳����ʾ����
		ArrayList<Student> al = selStuInfoService.getStusByPage(pageNow, pageSize);
		
		request.setAttribute("stu", al);
		request.setAttribute("pgcount", pageCount);
		request.setAttribute("type", "selAllpg");
		request.setAttribute("pgNow", pageNow);
		request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
