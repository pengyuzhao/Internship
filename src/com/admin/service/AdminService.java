package com.admin.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admin.domain.Admin;
import com.common.util.SqlHelper;


public class AdminService {
	// 获取管理员name
		public Admin getAdminByname(String username){
			
			Admin admin = new Admin();
			String sql = "select adminName,adminAccount,adminPwd from admin where adminAccount=?";
			String parameters[] = {username};
			
			ResultSet rs = SqlHelper.executeQuery(sql, parameters);
			try {
				if(rs.next()){
					// 二次封装
					admin.setAdminName(rs.getString(1));
					admin.setAdminAccount(rs.getString(2));
					admin.setAdminPwd(rs.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
			}
			return admin;
		}
		
		// 获取公司name
		public Admin getCompanyByname(String username){
			
			Admin admin = new Admin();
			String sql = "select companyName from company where companyAccount=?";
			String parameters[] = {username};
			
			ResultSet rs = SqlHelper.executeQuery(sql, parameters);
			try {
				if(rs.next()){
					// 二次封装
					admin.setCompanyName(rs.getString(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
			}
			return admin;
		}
		public boolean checkAdmin(Admin admin )
		{	
			boolean b =false;
			
			// 使用SqlHelper来完成查询任务
			String sql = "select * from admin where adminAccount=? and adminPwd=?";
			String parameters[] ={admin.getAdminAccount(),admin.getAdminPwd()};

			ArrayList al = SqlHelper.executeQuery3(sql, parameters);
			
			// 根据rs来判断该用户是否存在
			try 
			{
				if (al.size()==1) {
					b = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return b;		
		}
}
