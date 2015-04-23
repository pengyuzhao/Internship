package com.admin.service;

import java.util.Iterator;
import java.util.List;
import com.admin.domain.UploadExcel;
import com.common.util.SqlHelper;

// 导入excel表时存入数据库的service
public class ExcelSqlService {
	
	public boolean addExcel(List<UploadExcel> dmList){	
		
		List<UploadExcel> list = dmList;
		Iterator iter = list.iterator();
		boolean b = true;

		while (iter.hasNext()) {
			
			UploadExcel uploadExcel = (UploadExcel) iter.next();
			
			String sql = "insert into studentInfo(studentId,studentName,studentPwd,studentCardId,studentClass,studentTel) values(?,?,?,?,?,?)";
			
			String parameters[] = {uploadExcel.getStudentId(),uploadExcel.getStudentName(),uploadExcel.getStudentPwd(),uploadExcel.getStudentCardId(),
					uploadExcel.getStudentClass(),uploadExcel.getStudentTel()};

			
			try {
				
				SqlHelper.executeUpdate(sql, parameters);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				b=false;
			}
		}
		
		return b;
			
	}
}
			

