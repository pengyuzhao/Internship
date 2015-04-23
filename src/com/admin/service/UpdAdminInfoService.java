package com.admin.service;

import com.admin.domain.Admin;
import com.common.util.SqlHelper;

public class UpdAdminInfoService {

	public boolean updAdmPwd(Admin admin,String newPwd){
		
		boolean b = true;
		
		String sql = "update admin set adminPwd=? where adminAccount=?";
		String parameters[] = {newPwd,admin.getAdminAccount()};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
}
