package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.SelStuEvaService;

// ��������ѧ��ҳ��Ĳ�ѯ
public class SelStuEvaCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		SelStuEvaService selStuEvaService = new SelStuEvaService();
		
		// �����ҳ��Ҫ�ı���		
		int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
		int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
		
		// �����û�����Ĳ�ѯ���� 
		String term = request.getParameter("term");
		
		// Ĭ����
		if("selAll".equals(type)){
			
			selAll(request, response);
		}
		// Ĭ���´����ҳ
		// ��ʾȫ���б�ʱ�ķ�ҳ����
		else if("selAllpg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuEvaService.getPageCount(pageSize);
			ArrayList<StudentResume> al = selStuEvaService.getStusByPage(pageNow, pageSize);
			
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selAllpg");
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}
		
		// -------------select�ؼ���ģ����ѯ--------------------------------------------------------------
		else if("selOp".equals(type)){
			
			// ���յ�select�ؼ��ľ���optionֵ
			String option = request.getParameter("option");
			
			// -------������-----------------------------------------
			if("name".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuEvaService.getPageCountByName(pageSize,term);
					
					ArrayList<StudentResume> al = selStuEvaService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
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
					
					pageCount = selStuEvaService.getPageCountById(pageSize,term);
					
					ArrayList<StudentResume> al = selStuEvaService.getStusPageById(pageNow, pageSize,term);
				
					if(al.size()==0){
						request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selIdPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
					}
				}
				// ����û���������Ϊ�գ��ͻ�����ʾĬ������µ�
				else{
					
					selAll(request, response);
				}
			}
			
			// ------����˾��-------------------------------------
			// ����û���������Ϊ�գ���ʾ��������ʵϰ��˾��
			else if("company".equals(option)){
							
				int pageNow = 1;	
				
				pageCount = selStuEvaService.getPageCountByCom(pageSize,term);
				
				ArrayList<StudentResume> al = selStuEvaService.getStusPageByCom(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "��ʾ��δ�ҵ����ϴ������Ĺ�˾��");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
			
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selComPg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
				}
				
		
			}
			
			// ------��ѯ�Ѿ�ʵϰ��ѧ���б�-------------------------------------
			else if("choose".equals(option)){

				//����û�����Ϊ�գ�����ʾ�����Ѿ�ʵϰ��
				
				int pageNow = 1;	
				
				pageCount = selStuEvaService.getPageCountByChoose(pageSize,term);
				
				ArrayList<StudentResume> al = selStuEvaService.getStusPageByChoose(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
			
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selChoosePg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
				}
			}
			
			// ------��ѯδʵϰ��ѧ���б�-------------------------------------
			else if("noChoose".equals(option)){
			
				//����û�����Ϊ�գ�����ʾ�����Ѿ�ʵϰ��
				
				int pageNow = 1;	
				
				pageCount = selStuEvaService.getPageCountByNoChoose(pageSize,term);
				
				ArrayList<StudentResume> al = selStuEvaService.getStusPageByNoChoose(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "��ʾ��δ�ҵ����ϴ�������ѧ����");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
			
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selNoChoosePg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
				}
			}
		}
		
		// ������ѯ��ҳ
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuEvaService.getPageCountByName(pageSize,term);
			ArrayList<StudentResume> al = selStuEvaService.getStusPageByName(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selNamePg");
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}
		
		// ѧ�Ų�ѯ��ҳ
		else if("selIdPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuEvaService.getPageCountById(pageSize,term);
			ArrayList<StudentResume> al = selStuEvaService.getStusPageById(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selIdPg");
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}
		
		// ��˾��ѯ��ҳ
		else if("selComPg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuEvaService.getPageCountByCom(pageSize,term);
			ArrayList<StudentResume> al = selStuEvaService.getStusPageByCom(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selComPg");
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}
		
		// �Ѿ�ʵϰ�б��ѯ��ҳ
		else if("selChoosePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuEvaService.getPageCountByChoose(pageSize,term);
			ArrayList<StudentResume> al = selStuEvaService.getStusPageByChoose(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selChoosePg");
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}
		
		// δʵϰ�б��ѯ��ҳ
		else if("selNoChoosePg".equals(type)){
			
			int pageNow;
			
			// �����û���������pageNow
			String sPageNow = request.getParameter("pageNow");
			pageNow = Integer.parseInt(sPageNow);
			
			pageCount = selStuEvaService.getPageCountByNoChoose(pageSize,term);
			ArrayList<StudentResume> al = selStuEvaService.getStusPageByNoChoose(pageNow, pageSize,term);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("pgNow", pageNow);
			request.setAttribute("type", "selNoChoosePg");
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}
	}
	
	
	
	//----------------��ʾȫ���б�ĺ�����װ----------------------------
		public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			// ����service
			SelStuEvaService selStuEvaService = new SelStuEvaService();
			
			// �����ҳ��Ҫ�ı���		
			int pageSize = 10;	// ָ��ÿҳ��ʾ5����¼	
			int pageCount = 0;	// ������ҳ����ֵ�Ǽ������	
			int pageNow = 1;
			
			pageCount = selStuEvaService.getPageCount(pageSize);
			
			// ��Ϊ��ʾȫ�����Ͱ���ѧ��˳����ʾ����
			ArrayList<StudentResume> al = selStuEvaService.getStusByPage(pageNow, pageSize);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("type", "selAllpg");
			request.setAttribute("pgNow", pageNow);			
			request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
		}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
