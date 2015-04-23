package com.student.controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.jspsmart.upload.SmartUpload;
import com.student.domain.StuResume;
import com.student.domain.StudentInfo;
import com.student.service.Filepathservice;
import com.student.service.StuResumeService;
import com.student.service.StudentInfoService;

//�˿�����������ɾ��
public class ExcuteCl extends HttpServlet {

	ServletConfig config=null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		StudentInfo studentInfo=(StudentInfo)request.getSession().getAttribute("loginStudent");
		String type=request.getParameter("type");
		StuResumeService stuResumeService=new StuResumeService();
		if(type.equals("updatePwd"))
		{
			String prePwd=request.getParameter("prePwd");
			String nowPwd=request.getParameter("nowPwd");
			StudentInfoService studentInfoService=new StudentInfoService();
			if(studentInfoService.updatePassword(studentInfo, prePwd, nowPwd))
			{		
				out.println("�����ɹ�������ת");response.setHeader("refresh", "2;URL=/Internship/GoToIndexCl");
				//request.setAttribute("updateInfo", "ok");
				//request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			}
			else{
	 			out.println("ԭ����������ڷ�����ҳ��");response.setHeader("refresh", "2;URL=/Internship/GoToPageCl?type=updatePwd");	

			//	request.setAttribute("updateInfo", "err");
			//	request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			}
		}
		else if(type.equals("upload"))
		{
			
			Filepathservice filepathservice=new Filepathservice();
			try{
				filepathservice.saveFile(config, request, response, studentInfo);
	 			out.println("�ϴ��ɹ�������ת");response.setHeader("refresh", "2;URL=/Internship/GoToIndexCl");
				//request.setAttribute("uploadInfo","ok");
				//request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			}catch (Exception e) {
				// TODO: handle exception
	 			out.println("�ϴ�ʧ��<font color='red'>�ļ��ܴ�С����30M</font>������ҳ��");response.setHeader("refresh", "2;URL=/Internship/GoToPageCl?type=upload");	
				//request.setAttribute("uploadInfo","err");
				//request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			}
			
		}
		else if(type.equals("sumbitResume")){
			 
	
		
			try {
				SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
				mySmartUpload.initialize(config, request, response);
				mySmartUpload.setAllowedFilesList("jpg,PNG");// �����ļ�����
				mySmartUpload.upload();
				stuResumeService.initDate(mySmartUpload,studentInfo);
	 			out.println("�ϴ��ɹ�������ת");response.setHeader("refresh", "2;URL=/Internship/GoToIndexCl");

				//request.setAttribute("writeResume","ok");
				//request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
	 			out.println("�ϴ�ʧ�����ڷ�����ҳ��");response.setHeader("refresh", "2;URL=/Internship/GoToWriteResume");	

				//request.setAttribute("writeResume","err");
				//request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			}
		}
		else if(type.equals("updateResume")){
			 
			
		
			try {
				SmartUpload mySmartUpload=new com.jspsmart.upload.SmartUpload();
				mySmartUpload.initialize(config, request, response);
				mySmartUpload.setAllowedFilesList("jpg,PNG");// �����ļ�����
				mySmartUpload.upload();
				stuResumeService.updateResume(mySmartUpload, studentInfo);
				//stuResumeService.delResume(studentInfo);
				out.println("�ϴ��ɹ�������ת");response.setHeader("refresh", "2;URL=/Internship/GoToIndexCl");

			//request.setAttribute("writeResume","ok");
			//	request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			} catch (Exception e) {
				// TODO: handle exception
	 			out.println("�ϴ�ʧ�����ڷ�����ҳ��");response.setHeader("refresh", "2;URL=/Internship/GoToWriteResume");	
				//request.setAttribute("writeResume","err");
				//request.getRequestDispatcher("/CommandInfoCl").forward(request, response);
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		this.doGet(request, response);
	}
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

}