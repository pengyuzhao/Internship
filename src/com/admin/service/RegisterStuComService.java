package com.admin.service;

import com.admin.domain.Student;
import com.common.util.SqlHelper;

// ���ѧ��ʵϰ��Ϣ��service
public class RegisterStuComService {

	
	// �Ǽ�ѧ��ʵϰ��ʵϰ��
	public boolean RegisterStuCom(int companyId,String studentId,Student student){
		
		boolean b = true;
		String sql ="insert into companyInternship(companyId,stuId,stuName) values(?,?,?)";
		String parameters[] = 
			{companyId+"",studentId,student.getStudentName()};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}
	
	// �Ǽ�ѧ��ʵϰ��������
	public boolean RegisterStuComtoResume(String companyName,String studentId){
		
		boolean b = true;
		String sql ="update stuResume set company=?,choose=? where studentId=?";
		String parameters[] = 
			{companyName,1+"",studentId};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}
	
	// �޸�ѧ��ʵϰ��ʵϰ��
	public boolean UpdateStuCom(int companyId,String studentId){
		
		boolean b = true;
		String sql ="update companyInternship set companyId=? where stuId=?";
		String parameters[] = 
			{companyId+"",studentId};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}
}
