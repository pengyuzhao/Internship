package com.admin.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.StudentResume;
import com.admin.service.SelStuAuditService;

// 处理审核学生的页面的查询
public class SelStuAuditCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		// 创建service
		SelStuAuditService selStuAuditService = new SelStuAuditService();
		
		// 定义分页需要的变量		
		int pageSize = 10;	// 指定每页显示5条记录	
		int pageCount = 0;	// 共多少页，该值是计算出来	
		
		// 接收用户输入的查询条件 
		String term = request.getParameter("term");
		
		
		// -----------------------------------------------------------------------------------------
		// 默认是显示还未审核的学生
		if("selAll".equals(type)){
			
			selAll(request, response);
		}
		// 显示默认列表时的分页处理
		else if("selAllpg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 显示全部列表时
		else if("selAllAudit".equals(type)){
			
			int pageNow = 1;
			
			pageCount = selStuAuditService.getPageCountAll(pageSize);
			
			// 因为显示全部，就按照学号顺序显示出来
			ArrayList<StudentResume> al = selStuAuditService.getStusByPageAll(pageNow, pageSize);
			
			request.setAttribute("stu", al);
			request.setAttribute("pgcount", pageCount);
			request.setAttribute("type", "selAllAuditpg");
			request.setAttribute("pgNow", pageNow);			
			request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
		}
		
		// 显示全部列表时的分页
		else if("selAllAuditpg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// -------------select控件中模糊查询--------------------------------------------------------------
		else if("selOp".equals(type)){
			
			// 接收到select控件的具体option值
			String option = request.getParameter("option");
			
			// -------按姓名-----------------------------------------
			if("name".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuAuditService.getPageCountByName(pageSize,term);
					
					ArrayList<StudentResume> al = selStuAuditService.getStusPageByName(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selNamePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
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
					
					pageCount = selStuAuditService.getPageCountById(pageSize,term);
					
					ArrayList<StudentResume> al = selStuAuditService.getStusPageById(pageNow, pageSize,term);
				
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selIdPg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
					}
				}
				// 如果用户输入条件为空，就还是显示默认情况下的
				else{
					
					selAll(request, response);
				}
			}
			
			// ----------关键字查询(姓名、学号、性别、专业方向、主修、籍贯、希望工作地区、希望岗位)----------------------------------
			else if("all".equals(option)){
				
				if(term!=""){
					
					int pageNow = 1;	
					
					pageCount = selStuAuditService.getPageCountByAll(pageSize,term);
					
					ArrayList<StudentResume> al = selStuAuditService.getStusPageByAll(pageNow, pageSize,term);
					
					if(al.size()==0){
						request.setAttribute("info", "提示：未找到符合此条件的学生！");
						request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
					}else{
			
						request.setAttribute("stu", al);
						request.setAttribute("pgcount", pageCount);
						request.setAttribute("type", "selLargePg");
						request.setAttribute("pgNow", pageNow);
						request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
					}
				}
				// 如果用户输入条件为空，就还是显示默认情况下的
				else{
					
					selAll(request, response);
				}
			}
			
			// ------审核通过条件下，可按姓名、学号、专业方向查--------------------------------------------------------------
			else if("approved".equals(option)){
			
				// 如果用户输入为空，就查询到所有审核通过的，
				// 不为空，就按输入条件从姓名、学号、专业方向中查
				
				int pageNow = 1;	
				
				pageCount = selStuAuditService.getPageCountByOk(pageSize,term);
				
				ArrayList<StudentResume> al = selStuAuditService.getStusPageByOk(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "提示：未找到审核通过的学生！");
					request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
				}else{
		
					request.setAttribute("stu", al);
					request.setAttribute("pgcount", pageCount);
					request.setAttribute("type", "selApproPg");
					request.setAttribute("pgNow", pageNow);
					request.getRequestDispatcher("/WEB-INF/admin/manageStudentData.jsp").forward(request, response);
				}
			}
			
			// ------审核未通过条件下，可按姓名、学号、专业方向查--------------------------------------------------------------
			else if("noApproved".equals(option)){
			
				// 如果用户输入为空，就查询到所有审核通过的，
				// 不为空，就按输入条件从姓名、学号、专业方向中查
				
				int pageNow = 1;	
				
				pageCount = selStuAuditService.getPageCountByNoOk(pageSize,term);

				ArrayList<StudentResume> al = selStuAuditService.getStusPageByNoOk(pageNow, pageSize,term);
				
				if(al.size()==0){
					request.setAttribute("info", "提示：未找到审核不通过的学生！");
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
		
		
		// 姓名查询下的分页处理
		else if("selNamePg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 学号查询下的分页处理
		else if("selIdPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 关键字查询的分页处理
		else if("selLargePg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 查询审核通过的的分页处理
		else if("selApproPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
		
		// 查询未审核通过的的分页处理
		else if("selNoApproPg".equals(type)){
			
			int pageNow;
			
			// 接收用户传过来的pageNow
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
	
	
	
	//----------------显示全部列表的函数封装----------------------------
	public void selAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		// 创建service
		SelStuAuditService selStuAuditService = new SelStuAuditService();
		
		// 定义分页需要的变量		
		int pageSize = 10;	// 指定每页显示5条记录	
		int pageCount = 0;	// 共多少页，该值是计算出来	
		int pageNow = 1;
		
		pageCount = selStuAuditService.getPageCount(pageSize);
		
		// 因为显示全部，就按照学号顺序显示出来
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
