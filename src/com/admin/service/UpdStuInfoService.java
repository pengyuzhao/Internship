package com.admin.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.admin.domain.Student;
import com.admin.domain.StudentResume;
import com.common.util.SqlHelper;

// ר�Ŵ������ѧ����Ϣ��service
public class UpdStuInfoService {

	// ���ѧ����Ϣ
	public boolean addStudent(Student student){
		
		boolean b = true;
		String sql ="insert into studentInfo(studentId,studentName,studentPwd,studentCardId,studentClass,studentTel) values(?,?,?,?,?,?)";
		String parameters[] = 
			{student.getStudentId(),student.getStudentName(),student.getStudentPwd(),student.getStudentCardId(),student.getStudentClass(),student.getStudentTel()};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}

	
	// ͨ��ѧ�Ż�ȡ�û�����
	public Student getStuByStuId(String studentId){
		
		Student student = new Student();
		
		String sql = "select studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where studentId=?";
		String parameters[] = {studentId};
		
		
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()){
				// ���η�װ
				student.setStudentName(rs.getString(1));
				student.setStudentPwd(rs.getString(2));
				student.setStudentCardId(rs.getString(3));
				student.setStudentClass(rs.getString(4));
				student.setStudentTel(rs.getString(5));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
		}
		return student;
	}
	
	// ͨ��ѧ�Ż�ȡ�û�����
	public StudentResume getStureByStuId(String studentId){
		
		StudentResume studentResume = new StudentResume();
		
		String sql = "select name,sex,direction,techerevaluation from stuResume where studentId=?";
		String parameters[] = {studentId};
		
		
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()){
				// ���η�װ				
				studentResume.setName(rs.getString(1));
				studentResume.setSex(rs.getString(2));
				studentResume.setDirectionid(rs.getString(3));
				studentResume.setTecherevaluation(rs.getString(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
		}
		return studentResume;
	}
	
	// �޸�ѧ����Ϣ
	public boolean updStu(Student student){
		
		boolean b = true;
		
		String sql = "update studentInfo set studentName=?, studentPwd=?, studentCardId=?, studentClass=? ,studentTel=? where studentId=?";
		String parameters[] = {student.getStudentName(),student.getStudentPwd(),student.getStudentCardId(),student.getStudentClass(),
				student.getStudentTel(),student.getStudentId()};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	// ɾ��ѧ����Ϣ
	public boolean delStu(String studentId)
	{
		boolean b = true;
		
		String sql = "delete from studentInfo where studentId=?";
		String parameters[] = {studentId};
		
		try 
		{
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}	
}
