package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.admin.domain.Company;
import com.admin.service.SelComInfoService;

// �鿴��˾��Ϣ�Ŀ����� 
public class SelComInfoCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");


		String type = request.getParameter("type");
		
		SelComInfoService selComInfoService = new SelComInfoService();
		
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
			
			pageCount = selComInfoService.getPageCount(pageSize);
			ArrayList<Company> al = selComInfoService.getStusByPage(pageNow, pageSize);
			
			request.setAttribute("com", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selAllpg");
			request.getRequestDispatcher("/WEB-INF/admin/selComInfo.jsp").forward(request, response);
		}
		
		//----------select�ؼ��в�ѯ---------------------------------------------------------
		else if("selOp".equals(type)){
			
			// ���յ�select�ؼ��ľ���optionֵ
			String option = request.getParameter("option");
			
			//-----����˾����--------------------------------------------------
			if("companyName".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selComInfoService.getPageCountByName(pageSize,term);
					
					ArrayList<Company> al = selComInfoService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ������Ĺ�˾��");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("com", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selComInfo.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ�����ʾĬ������µ�ȫ���б�
				else{
					
					selAll(request, response);
				}
			}
			
			//-----����˾���ڵز�--------------------------------------------------
			if("companyAddress".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selComInfoService.getPageCountByAddress(pageSize,term);
					
					ArrayList<Company> al = selComInfoService.getStusPageByAddress(pageNow, pageSize, term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ������Ĺ�˾��");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("com", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selAddressPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selComInfo.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ�����ʾĬ������µ�ȫ���б�
				else{
					
					selAll(request, response);
				}
			}
		}
		
		// ����˾���ֲ�ѯ�µķ�ҳ����
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selComInfoService.getPageCountByName(pageSize,term);
			ArrayList<Company> al = selComInfoService.getStusPageByName(pageNow, pageSize,term);
			
			request.setAttribute("com", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selNamePg");
			request.getRequestDispatcher("/WEB-INF/admin/selComInfo.jsp").forward(request, response);
		}
		
		// ����˾���ڵز�ѯ�µķ�ҳ����
		else if("selAddressPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selComInfoService.getPageCountByAddress(pageSize, term);
			ArrayList<Company> al = selComInfoService.getStusPageByAddress(pageNow, pageSize, term);
			
			request.setAttribute("com", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selAddressPg");
			request.getRequestDispatcher("/WEB-INF/admin/selComInfo.jsp").forward(request, response);
		}
	}
	
	//----------------��ʾȫ���б�ĺ�����װ----------------------------
	public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		// ����service
		SelComInfoService selComInfoService = new SelComInfoService();
		
		// �����ҳ��Ҫ�ı���		
		int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
		int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
		int pageNow = 1;
		
		pageCount = selComInfoService.getPageCount(pageSize);
		
		// ��Ϊ��ʾȫ�����Ͱ���ѧ��˳����ʾ����
		ArrayList<Company> al = selComInfoService.getStusByPage(pageNow, pageSize);
		
		request.setAttribute("com", al);
		request.setAttribute("pgcount", pageCount);
		request.setAttribute("type", "selAllpg");
		request.setAttribute("pgNow", pageNow);
		request.getRequestDispatcher("/WEB-INF/admin/selComInfo.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
