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
	
// �������Ա����excel��һ���ܵĿ�����
public class UploadExcelCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
	
		if("uploadExcel".equals(type)){
			
			// ���Լ�����Ŀ�й����һ�����ڴ���û�excel���ļ���
			String projectpath = this.getServletContext().getRealPath("");
			// ������ļ��в����ڣ�������ļ���
			File f = new File(projectpath);
			if (!f.exists()) {
				f.mkdir();
			}

			// ������ļ����������ڴ��JSPҳ���д��ݹ������ļ�
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// ���û����С������ļ����ڻ����Сʱ�����Ȱ��ļ��ŵ�������
			factory.setSizeThreshold(10 * 1024);
			// �����ϴ��ļ��ı���·��
			factory.setRepository(f);

			// ����Servlet�ϴ�����
			ServletFileUpload upload = new ServletFileUpload(factory);
			// ���ÿ����ϴ��ļ���С���Ͻ�4MB
			upload.setSizeMax(4 * 1024 * 1024);

				
			// ȡ�������ϴ��ļ�����Ϣ
			List<FileItem> list = null;
			try {
				list = upload.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// �����ϴ�excel�����
			UploadExcelTool uploadExcelTool = new UploadExcelTool();
			uploadExcelTool.receive(projectpath);
			// ��ȡ����ֵ�����ص��Ƿ�������·��
			ArrayList<String> al = uploadExcelTool.uploadExcel(list);
			
			// ���ö�ȡexcel�����
			ReadExcelTool readExcelTool = new ReadExcelTool();;
			// ��ȡ����ֵ�����ص������ݶ���
			List<UploadExcel> dmLs = readExcelTool.readExcel(al);
			
			// ����session����dmLs��ʱ�ŵ�session��Ա��¸�jsp��
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
			
			// ȡ���ϸ�if���������ʱ���dmLs��session
			List<UploadExcel> dmList = (List<UploadExcel>)request.getSession().getAttribute("dmLs");		
			
			// ���ô�ecxel��Ķ������ݿ��service
			ExcelSqlService excelSqlService = new ExcelSqlService();
			excelSqlService.addExcel(dmList);
			
			request.setAttribute("info", "���ѳɹ���excel�������ݿ⣡");
			request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			
			// �����ɾ����
			HttpSession session = request.getSession();
			session.removeAttribute("dmLs");
		}
		else if("cancel".equals(type)){
			// ��ת�ص���ҳ��
			request.getRequestDispatcher("/WEB-INF/admin/uloadStudentData.jsp").forward(request, response);
		}

	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}