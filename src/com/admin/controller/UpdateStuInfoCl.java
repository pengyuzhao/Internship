package com.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.domain.Student;
import com.admin.service.UpdStuInfoService;

// 用于处理更新学生信息的控制器
public class UpdateStuInfoCl extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		
		String type = request.getParameter("type");
		
		// 创建service对象
		UpdStuInfoService updStuInfoService = new UpdStuInfoService();
		
		// 添加新的学生信息
		if("addStu".equals(type)){
			//接收信息
			String studentId = request.getParameter("studentId");
			String studentName = request.getParameter("studentName");
			String studentPwd = request.getParameter("studentPwd");
			String studentClass =request.getParameter("studentClass");
			String studentCardId =request.getParameter("studentCardId");
			String studentTel =request.getParameter("studentTel");
			
			// 把接收到的信息，封装成一个stu对象
			Student student = new Student();
			
			student.setStudentId(studentId);
			student.setStudentName(studentName);
			student.setStudentPwd(studentPwd);
			student.setStudentClass(studentClass);
			student.setStudentCardId(studentCardId);
			student.setStudentTel(studentTel);
			
			// 调用service类，存入数据库
			if(updStuInfoService.addStudent(student)){
				request.setAttribute("info", "恭喜您，成功把此学生的信息添加到数据库！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，添加信息失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 修改学生信息
		else if("gotoUpStu".equals(type)){
			
			// 根据学号查信息
			String studentId = request.getParameter("stuId");
			Student student = updStuInfoService.getStuByStuId(studentId);
			
			request.setAttribute("stuInfo", student);
			request.setAttribute("stuId", studentId);
			// 接收修改请求，去修改页面
			request.getRequestDispatcher("/WEB-INF/admin/updateStudentInfo.jsp").forward(request, response);
		}
		// 接收学生修改的信息，调用service更新数据库
		else if("updStu".equals(type)){
			
			String studentId = request.getParameter("studentId");
			String studentName = request.getParameter("studentName");
			String studentPwd = request.getParameter("studentPwd");
			String studentCardId = request.getParameter("studentCardId");
			String studentClass = request.getParameter("studentClass");
			String studentTel = request.getParameter("studentTel");
			
			// 把接收到的信息，封装成一个stu对象
			Student student = new Student(studentId,studentName,studentPwd,studentCardId,studentClass,studentTel);
			
			// 调用
			if(updStuInfoService.updStu(student)){
				request.setAttribute("info", "恭喜您，修改此学生信息成功！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，修改此学生信息失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
		
		// 删除学生信息
		else if("delStu".equals(type)){
			
			// 接收要删除的id
			String studentId = request.getParameter("stuId");

			// 调用
			if(updStuInfoService.delStu(studentId)){
				request.setAttribute("info", "恭喜您，成功把此学生所有数据删除！");
				request.getRequestDispatcher("/WEB-INF/admin/okInfo.jsp").forward(request, response);
			}else{
				request.setAttribute("info", "对不起，删除失败！");
				request.getRequestDispatcher("/WEB-INF/admin/errInfo.jsp").forward(request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
