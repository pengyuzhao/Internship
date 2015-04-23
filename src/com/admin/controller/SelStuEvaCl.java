package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.SelStuEvaService;

// 处理评价学生页面的查询
public class SelStuEvaCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		SelStuEvaService selStuEvaService = new SelStuEvaService();
		
		// 定义分页需要的变量		
		int pageSize = 10;	// 指定每页显示5条记录	
		int pageCount = 0;	// 共多少页，该值是计算出来	
		
		// 接收用户输入的查询条件 
		String term = request.getParameter("term");
		
		// 默认下
		if("selAll".equals(type)){
			
			selAll(request, response);
		}
		// 默认下处理分页
		// 显示全部列表时的分页处理
		else if("selAllpg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// -------------select控件中模糊查询--------------------------------------------------------------
		else if("selOp".equals(type)){
			
			// 接收到select控件的具体option值
			String option = request.getParameter("option");
			
			// -------按姓名-----------------------------------------
			if("name".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuEvaService.getPageCountByName(pageSize,term);
					
					ArrayList<StudentResume> al = selStuEvaService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
					}
				}
				// 如果用户输入条件为空，就显示默认情况下的全部列表
				else{
					
					selAll(request, response);
				}
			}
			
			// ------按学号查-------------------------------------
			else if("stuId".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuEvaService.getPageCountById(pageSize,term);
					
					ArrayList<StudentResume> al = selStuEvaService.getStusPageById(pageNow, pageSize,term);
				
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selIdPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
					}
				}
				// 如果用户输入条件为空，就还是显示默认情况下的
				else{
					
					selAll(request, response);
				}
			}
			
			// ------按公司查-------------------------------------
			// 如果用户输入条件为空，显示所有已有实习公司的
			else if("company".equals(option)){
							
				int pageNow = 1;	
				
				pageCount = selStuEvaService.getPageCountByCom(pageSize,term);
				
				ArrayList<StudentResume> al = selStuEvaService.getStusPageByCom(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "提示：未找到符合此条件的公司！");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
			
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selComPg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
				}
				
		
			}
			
			// ------查询已经实习的学生列表-------------------------------------
			else if("choose".equals(option)){

				//如果用户输入为空，就显示所有已经实习的
				
				int pageNow = 1;	
				
				pageCount = selStuEvaService.getPageCountByChoose(pageSize,term);
				
				ArrayList<StudentResume> al = selStuEvaService.getStusPageByChoose(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "提示：未找到符合此条件的学生！");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
			
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selChoosePg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/examineResume.jsp").forward(request, response);
				}
			}
			
			// ------查询未实习的学生列表-------------------------------------
			else if("noChoose".equals(option)){
			
				//如果用户输入为空，就显示所有已经实习的
				
				int pageNow = 1;	
				
				pageCount = selStuEvaService.getPageCountByNoChoose(pageSize,term);
				
				ArrayList<StudentResume> al = selStuEvaService.getStusPageByNoChoose(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "提示：未找到符合此条件的学生！");
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
		
		// 姓名查询分页
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 学号查询分页
		else if("selIdPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 公司查询分页
		else if("selComPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 已经实习列表查询分页
		else if("selChoosePg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 未实习列表查询分页
		else if("selNoChoosePg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
	
	
	
	//----------------显示全部列表的函数封装----------------------------
		public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			// 创建service
			SelStuEvaService selStuEvaService = new SelStuEvaService();
			
			// 定义分页需要的变量		
			int pageSize = 10;	// 指定每页显示5条记录	
			int pageCount = 0;	// 共多少页，该值是计算出来	
			int pageNow = 1;
			
			pageCount = selStuEvaService.getPageCount(pageSize);
			
			// 因为显示全部，就按照学号顺序显示出来
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
