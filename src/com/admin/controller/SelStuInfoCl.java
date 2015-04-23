package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Student;
import com.admin.service.SelStuInfoService;

// 处理修改学生个人信息页面的查询
public class SelStuInfoCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		// 创建service对象
		SelStuInfoService selStuInfoService = new SelStuInfoService();
		
		// 定义分页需要的变量		
		int pageSize = 10;	// 指定每页显示5条记录	
		int pageCount = 0;	// 共多少页，该值是计算出来	
		
		// 接收用户输入的查询条件 
		String term = request.getParameter("term");
		
		// 显示全部列表
		if("selAll".equals(type)){
			
			selAll(request, response);
			
		}
		// 显示全部列表时的分页处理
		else if("selAllpg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		//----------select控件中查询---------------------------------------------------------
		else if("selOp".equals(type)){
			
			// 接收到select控件的具体option值
			String option = request.getParameter("option");
			
			//-----按姓名查--------------------------------------------------
			if("name".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuInfoService.getPageCountByName(pageSize,term);
					
					ArrayList<Student> al = selStuInfoService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
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
					
					pageCount = selStuInfoService.getPageCountById(pageSize,term);

					ArrayList<Student> al = selStuInfoService.getStusPageById(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selIdPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
					}
				}
				// 如果用户输入条件为空，就还是显示默认情况下的
				else{
					
					selAll(request, response);
				}
			}
			
			// ------按班级查-------------------------------------
			else if("class".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuInfoService.getPageCountByClass(pageSize,term);
					
					ArrayList<Student> al = selStuInfoService.getStusPageByClass(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
						
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selClassPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/selStudentInfoRs.jsp").forward(request, response);
					}
				}
				// 如果用户输入条件为空，就还是显示默认情况下的
				else{
					
					selAll(request, response);
				}
			}
		}
		
		// 姓名查询下的分页处理
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 学号查询下的分页处理
		else if("selIdPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 班级查询下的分页处理
		else if("selClassPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
	
	
	//----------------显示全部列表的函数封装----------------------------
	public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		// 创建service
		SelStuInfoService selStuInfoService = new SelStuInfoService();
		
		// 定义分页需要的变量		
		int pageSize = 10;	// 指定每页显示5条记录	
		int pageCount = 0;	// 共多少页，该值是计算出来	
		int pageNow = 1;
		
		pageCount = selStuInfoService.getPageCount(pageSize);
		
		// 因为显示全部，就按照学号顺序显示出来
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
