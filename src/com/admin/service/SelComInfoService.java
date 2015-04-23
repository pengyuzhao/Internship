package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.Company;
import com.common.util.SqlHelper;

// ��ѯ��˾��Ϣ��service
public class SelComInfoService {

	// Ĭ����ʾȫ��
	// ��ȡ��ʾȫ����pageCount
	public int getPageCount(int pageSize){
		
		String sql = "select count(*) from company";
		
		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1);	// �õ�idֵ����֪���м�������
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount-1)/pageSize+1;		
	}
	
	// ���շ�ҳ����ȡѧ���б�
	// ��ResultSet->����->ArrayList(����)
	// ��ȡ��ʾȫ����al
	public ArrayList<Company> getStusByPage(int pageNow,int pageSize)
	{	
		// ��������
		ArrayList<Company> al = new ArrayList<Company>();
		
		//��ѯsql
		String sql = "select top "+pageSize+"id,companyName,companyAccount,companyPwd,companyAddress from company where " +
				"id not in (select top "+ ((pageNow-1)*10) +" id from company order by id) order by id";
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
		try 
		{
			for(int i=0;i<al2.size();i++){
				
				Object objs[] = (Object[]) al2.get(i);
				
				Company company = new Company();
				
				company.setId(Integer.parseInt(objs[0].toString()));
				company.setCompanyName(objs[1].toString());
				company.setCompanyAccount(objs[2].toString());
				company.setCompanyPwd(objs[3].toString());
				company.setCompanyAddress(objs[4].toString());
				
				al.add(company);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
	
	// --------����˾����-----------------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {
		
		String sql2 = "select count(*) from company where companyName like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Company> getStusPageByName(int pageNow, int pageSize, String term) {
		
		// ��ѯsql
		String sql2 = "select top "+pageSize+"id,companyName,companyAccount,companyPwd,companyAddress from company where " +
				"id not in (select top "+ ((pageNow-1)*10) +" id from company where companyName like '%"+term+"%' order by id) " +
						"and companyName like '%"+term+"%' order by id";

		ArrayList<Company> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// --------����˾���ڵز�-----------------------------------------------------------------------------
	public int getPageCountByAddress(int pageSize, String term) {
		
		String sql2 = "select count(*) from company where companyAddress like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Company> getStusPageByAddress(int pageNow, int pageSize, String term) {
		
		// ��ѯsql
		String sql2 = "select top "+pageSize+"id,companyName,companyAccount,companyPwd,companyAddress from company where " +
				"id not in (select top "+ ((pageNow-1)*10) +" id from company where companyAddress like '%"+term+"%' order by id) " +
						"and companyAddress like '%"+term+"%' order by id";

		ArrayList<Company> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	
	// --------------��װ����-----------------------------------------------------------------------
	public int getPageCount(int pageSize,String term,String sql2){
		
		String sql = sql2;
		
		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1);	// �õ�idֵ����֪���м�������
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount-1)/pageSize+1;		
	}
	
	// ���շ�ҳ����ȡѧ���б�
	// ��ResultSet->����->ArrayList(����)
	// ��ȡ��ʾȫ����al
	public ArrayList<Company> getStusByPage(int pageNow, int pageSize, String term,String sql2)
	{	
		// ��������
		ArrayList<Company> al = new ArrayList<Company>();
		
		String sql = sql2;
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
		try 
		{
			for(int i=0;i<al2.size();i++){
				
				Object objs[] = (Object[]) al2.get(i);
				
				Company company = new Company();
				
				company.setId(Integer.parseInt(objs[0].toString()));
				company.setCompanyName(objs[1].toString());
				company.setCompanyAccount(objs[2].toString());
				company.setCompanyPwd(objs[3].toString());
				company.setCompanyAddress(objs[4].toString());
				
				al.add(company);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
}
