package com.admin.service;

import com.common.util.SqlHelper;

public class EvaluationStuService {

	// 评价学生
	public boolean EvaluationStu(String studentId,String evaluation){
		
		boolean b = true;
		
		String sql = "update stuResume set techerevaluation=? where (techerevaluation is null) and (studentId=?)";
		String parameters[] = {evaluation,studentId};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	// 评价学生
	public boolean UpdEvaluationStu(String studentId,String evaluation){
		
		boolean b = true;
		
		String sql = "update stuResume set techerevaluation=? where studentId=?";
		String parameters[] = {evaluation,studentId};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
}
