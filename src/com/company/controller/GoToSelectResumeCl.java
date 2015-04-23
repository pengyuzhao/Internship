package com.company.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.company.domain.Page;
import com.company.service.PageService;
import com.student.service.StuResumeService;

public class GoToSelectResumeCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		PageService pageService=new PageService();
		String require=request.getParameter("require");
		String key=request.getParameter("key");
		ArrayList al =new ArrayList();
		Page page =new Page();
		int pageSize=10;
		int pageNow=1;
		if(request.getParameter("pageNow")!=null)
		{
			pageNow=Integer.parseInt(request.getParameter("pageNow"));
		}
		if(require!=null)
		{
			al=pageService.selectDate(require, key,pageSize,pageNow);
			page=pageService.getPageCount(pageSize,pageNow,require,key);
			request.setAttribute("require", require);
			request.setAttribute("key", key);
		}
		else{
		al=pageService.getPage(pageSize,pageNow);
		page=pageService.getPageCount(pageSize,pageNow);
		}
		request.setAttribute("page", page);
		request.setAttribute("stuPartInfo", al);
		request.getRequestDispatcher("/WEB-INF/company/lookAllResume.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
