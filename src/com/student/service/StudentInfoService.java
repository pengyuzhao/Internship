package com.student.service;

import java.util.ArrayList;

import com.common.util.SqlHelper;
import com.student.domain.StudentInfo;


public class StudentInfoService {
	
	
  public boolean checkName(String userName,String password){
	  
	boolean flag=false;
	String sql="select * from studentInfo where studentId=? and studentPwd=?";
	String[] parameters={userName,password};
	ArrayList al=SqlHelper.executeQuery3(sql, parameters);
	if(al.size()==1)
	{
		flag=true;	
	}
	
	return flag;  
  }
  public StudentInfo getStudentInfo(String studentId){
	
	  String sql="select * from studentInfo where studentId=?";
	  String[] parameters={studentId};
	  ArrayList al=SqlHelper.executeQuery3(sql, parameters);
	  StudentInfo studentInfo=new StudentInfo();
	  Object[] ob=(Object[])al.get(0);
	  studentInfo.setStudentId( ob[0].toString());
	  studentInfo.setStudentName(ob[1].toString());
	  studentInfo.setStudentPwd(ob[2].toString());
	  studentInfo.setStudentCardId(ob[3].toString());
	  studentInfo.setStudentClass(ob[4].toString());
	  studentInfo.setStudentTel(ob[5].toString());
	  return studentInfo;
	  
  }
  public boolean updatePassword(StudentInfo studentInfo,String prePwd,String nowPwd)
  {
	  boolean flag=false;
	  if(studentInfo.getStudentPwd().equals(prePwd))
	  {
		  String sql="update studentInfo set studentPwd =? where studentId=?" ;
		  try {
			  String[] parameters={nowPwd,studentInfo.getStudentId()};
			  SqlHelper.executeUpdate(sql, parameters);
		  } catch (Exception e) {
			 
			}
		  flag=true;  
	  }
	  return flag;
	  
	  
  }
}
