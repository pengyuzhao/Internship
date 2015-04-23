package com.admin.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admin.domain.Company;
import com.common.util.SqlHelper;

// ����˾��service����ӡ�ɾ�����޸ĵ� 
public class ManageComService {

	// ��ӹ�˾��Ϣ
	public boolean addCompany(Company company){
		
		boolean b = true;
		String sql ="insert into company(companyName,companyAccount,companyPwd,companyAddress) values(?,?,?,?)";
		String parameters[] = 
			{company.getCompanyName(),company.getCompanyAccount(),company.getCompanyPwd(),company.getCompanyAddress()};
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			// TODO: handle exception
			b=false;
		}
		return b;
	}
	
	// ����id�Ż�ȡ��˾��Ϣ
	public Company getComById(String companyId){
		
		Company company = new Company();
		
		String sql = "select companyName,companyAccount,companyPwd,companyAddress from company where id=?";
		String parameters[] = {companyId};
		
		
		ResultSet rs = SqlHelper.executeQuery(sql, parameters);
		try {
			if(rs.next()){
				// ���η�װ
				company.setCompanyName(rs.getString(1));
				company.setCompanyAccount(rs.getString(2));
				company.setCompanyPwd(rs.getString(3));
				company.setCompanyAddress(rs.getString(4));;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs,SqlHelper.getPs(),SqlHelper.getCt());
		}
		return company;
	}
	
	// �õ����е��б�
	public ArrayList<Company> getAllCom(){
		
		// ��������
		ArrayList<Company> al = new ArrayList<Company>();
		
		String sql = "select id,companyName,companyAddress from company";
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
		try 
		{
			for(int i=0;i<al2.size();i++){
				
				Object objs[] = (Object[]) al2.get(i);
				
				Company company = new Company();
				
				company.setId(Integer.parseInt(objs[0].toString()));
				company.setCompanyName(objs[1].toString());
				company.setCompanyAddress(objs[2].toString());
				
				al.add(company);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
	
	// �鿴��˾ʵϰ���
	public ArrayList<Company> getAllInternship(String studentId){
		// ��������
		ArrayList<Company> al = new ArrayList<Company>();
		
		String sql = "select companyId from companyInternship where stuId=";
		String parameters[] = {studentId};
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);

		//���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
		try 
		{
			for(int i=0;i<al2.size();i++){
				
				Object objs[] = (Object[]) al2.get(i);
				
				Company company = new Company();
				
				company.setId(Integer.parseInt(objs[0].toString()));
				
				al.add(company);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
	
	// �޸Ĺ�˾��Ϣ
	public boolean updCom(Company company){
		
		boolean b = true;
		
		String sql = "update company set companyName=?, companyAccount=?, companyPwd=?, companyAddress=? where id=?";
		String parameters[] = {company.getCompanyName(),company.getCompanyAccount(),company.getCompanyPwd(),company.getCompanyAddress(),
				company.getId()+""};
		
		try {
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}
	
	// ɾ��ѧ����Ϣ
	public boolean delCom(String id)
	{
		boolean b = true;
		
		String sql = "delete from company where id=?";
		String parameters[] = {id};
		
		try 
		{
			SqlHelper.executeUpdate(sql, parameters);
		} catch (Exception e) {
			b=false;
		}
		return b;
	}	
	
}
