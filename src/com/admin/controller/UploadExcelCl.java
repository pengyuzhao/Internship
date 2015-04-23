package com.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.admin.domain.UploadExcel;
import com.admin.service.ExcelSqlService;
import com.common.util.ReadExcelTool;
import com.common.util.UploadExcelTool;
	
// 处理管理员导入excel这一功能的控制器
public class UploadExcelCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
	
		if("uploadExcel".equals(type)){
			
			// 在自己的项目中构造出一个用于存放用户excel的文件夹
			String projectpath = this.getServletContext().getRealPath("");
			// 如果此文件夹不存在，则构造此文件夹
			File f = new File(projectpath);
			if (!f.exists()) {
				f.mkdir();
			}

			// 构造出文件工厂，用于存放JSP页面中传递过来的文件
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置缓存大小，如果文件大于缓存大小时，则先把文件放到缓存中
			factory.setSizeThreshold(10 * 1024);
			// 设置上传文件的保存路径
			factory.setRepository(f);

			// 产生Servlet上传对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置可以上传文件大小的上界4MB
			upload.setSizeMax(4 * 1024 * 1024);

				
			// 取得所有上传文件的信息
			List<FileItem> list = null;
			try {
				list = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 调用上传excel表的类
			UploadExcelTool uploadExcelTool = new UploadExcelTool();
			uploadExcelTool.receive(projectpath);
			// 获取返回值，返回的是服务器的路径
			ArrayList<String> al = uploadExcelTool.uploadExcel(list);
			
			// 调用读取excel表的类
			ReadExcelTool readExcelTool = new ReadExcelTool();;
			// 获取返回值，返回的是数据对象
			List<UploadExcel> dmLs = readExcelTool.readExcel(al);
			
			// 创建session，把dmLs临时放到session里，以便下个jsp用
			request.getSession().setAttribute("dmLs",dmLs);
			
			request.setAttribute("excelDm", dmLs);
			request.getRequestDispatcher("/WEB-INF/admin/showExcelData.jsp").forward(request, response);

			Iterator iter = dmLs.iterator();
			while (iter.hasNext()) {
				UploadExcel uploadExcel = (UploadExcel) iter.next();
	
				String id = uploadExcel.getStudentId();
				String name = uploadExcel.getStudentName();
				String pwd = uploadExcel.getStudentPwd();
				}
		}

		else if("addExcel".equals(type)){
			
			// 取出上个if里的用于临时存放dmLs的session
			List<UploadExcel> dmList = (List<UploadExcel>)request.getSession().getAttribute("dmLs");		
			
			// 调用存ecxel表的对象到数据库的service
			ExcelSqlService excelSqlService = new ExcelSqlService();
			excelSqlService.addExcel(dmList);
			
			request.setAttribute("info", "您已成功把excel表导入数据库！");
			request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			
			// 用完后删除它
			HttpSession session = request.getSession();
			session.removeAttribute("dmLs");
		}
		else if("cancel".equals(type)){
			// 跳转回导入页面
			request.getRequestDispatcher("/WEB-INF/admin/uloadStudentData.jsp").forward(request, response);
		}

	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}