package com.company.service;

import java.util.ArrayList;

import com.common.util.SqlHelper;
import com.company.domain.Company;
import com.student.domain.StudentInfo;


public class CompanyService {

	public boolean checkName(String userName,String password){
		  
		boolean flag=false;
		String sql="select * from company where companyAccount=? and companyPwd=?";
		String[] parameters={userName,password};
		ArrayList al=SqlHelper.executeQuery3(sql, parameters);
		if(al.size()==1)
		{
			flag=true;	
		}
		
		return flag;  
	  }

		 public boolean updatePassword(Company company,String prePwd,String nowPwd)
		  {
			  boolean flag=false;
			  if(company.getCompanyPwd().equals(prePwd))
			  {
				  String sql="update company set companyPwd =? where companyAccount=?" ;
				  try {
					  String[] parameters={nowPwd,company.getCompanyAccount()};
					  SqlHelper.executeUpdate(sql, parameters);
				  } catch (Exception e) {
					 
					}
				  flag=true;  
			  }
			  return flag;
			 		  
		  }
		 public Company getCompanyInfo(String userName)
		 {
			 String sql="select * from company where companyAccount=?";
			  String[] parameters={userName};
			  ArrayList al=SqlHelper.executeQuery3(sql, parameters);
			  Company company=new Company();
			  Object[] ob=(Object[])al.get(0);
			  company.setCompanyName( ob[1].toString());
			  company.setCompanyAccount( ob[2].toString());
			  company.setCompanyPwd(  ob[3].toString());
			  company.setCompanyAddress( ob[4].toString());
			return company;
			 
		 }
	
}
