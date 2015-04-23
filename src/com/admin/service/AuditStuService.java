package com.admin.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.admin.domain.StudentResume;
import com.common.util.SqlHelper;

// ���ѧ��service
public class AuditStuService {

	// ���ͨ��
	public boolean approved(String studentId){
			
		boolean b = true;
		
		String sql = "update stuResume set checkState=1 where studentId=?";
		String parameters[] = {studentId};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	// ��˲�ͨ��
	public boolean noApproved(String studentId){
		
		boolean b = true;
		
		String sql = "update stuResume set checkState=-1 where studentId=?";
		String parameters[] = {studentId};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	// ��˲�ͨ��ԭ��
	public boolean noApprovedReason(String studentId,String reason){
		
		boolean b = true;
		
		String sql = "update stuResume set reason=? where studentId=?";
		String parameters[] = {reason,studentId};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	// �鿴ԭ��
	public StudentResume lookReason(String studentId){
		
		StudentResume studentResume = new StudentResume();
		
		String sql = "select name,sex,direction,reason from stuResume where studentId=?";
		String parameters[] = {studentId};
		
		
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()){
				// ���η�װ				
				studentResume.setName(rs.getString(1));
				studentResume.setSex(rs.getString(2));
				studentResume.setDirectionid(rs.getString(3));
				studentResume.setReason(rs.getString(4));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
		}
		return studentResume;
	}
}
