package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.Student;
import com.common.util.SqlHelper;

// ��ѯѧ����Ϣ��service
public class SelStuInfoService {
	
	// Ĭ����ʾȫ��
	// ��ȡ��ʾȫ����pageCount
	public int getPageCount(int pageSize){
		
		String sql = "select count(*) from studentInfo";
		
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
	public ArrayList<Student> getStusByPage(int pageNow,int pageSize)
	{	
		// ��������
		ArrayList<Student> al = new ArrayList<Student>();
		
		//��ѯsql
		String sql = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo order by studentId) order by studentId";
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
		try 
		{
			for(int i=0;i<al2.size();i++){
				
				Object objs[] = (Object[]) al2.get(i);
				
				Student student = new Student();
				
				student.setStudentId(objs[0].toString());
				student.setStudentName(objs[1].toString());
				student.setStudentPwd(objs[2].toString());
				student.setStudentCardId(objs[3].toString());
				student.setStudentClass(objs[4].toString());
				student.setStudentTel(objs[5].toString());
				
				al.add(student);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
	
	// --------��������-----------------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {
		
		String sql2 = "select count(*) from studentInfo where studentName like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Student> getStusPageByName(int pageNow, int pageSize, String term) {
		
		// ��ѯsql
		String sql2 = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo where studentName like '%"+term+"%' order by studentId) " +
						"and studentName like '%"+term+"%' order by studentId";

		ArrayList<Student> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// --------��ѧ�Ų�-----------------------------------------------------------------------------
	public int getPageCountById(int pageSize, String term) {
		
		String sql2 = "select count(*) from studentInfo where studentId like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Student> getStusPageById(int pageNow, int pageSize, String term) {
		
		// ��ѯsql
		String sql2 = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo where studentId like '%"+term+"%' order by studentId) " +
						"and studentId like '%"+term+"%' order by studentId";

		ArrayList<Student> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// --------���༶��-----------------------------------------------------------------------------
	public int getPageCountByClass(int pageSize, String term) {
		
		String sql2 = "select count(*) from studentInfo where studentClass like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Student> getStusPageByClass(int pageNow, int pageSize, String term) {
		
		// ��ѯsql
		String sql2 = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo where studentClass like '%"+term+"%' order by studentId) " +
						"and studentClass like '%"+term+"%' order by studentId";

		ArrayList<Student> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	
	
	//----------��װ����---------------------------------------------------------------------------
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
	
	public ArrayList<Student> getStusByPage(int pageNow, int pageSize, String term,String sql2)
	{	
		// ��������
		ArrayList<Student> al = new ArrayList<Student>();
		
		//��ѯsql
		String sql = sql2;
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
		try 
		{
			for(int i=0;i<al2.size();i++){
				
				Object objs[] = (Object[]) al2.get(i);
				
				Student student = new Student();
				
				student.setStudentId(objs[0].toString());
				student.setStudentName(objs[1].toString());
				student.setStudentPwd(objs[2].toString());
				student.setStudentCardId(objs[3].toString());
				student.setStudentClass(objs[4].toString());
				student.setStudentTel(objs[5].toString());
				
				al.add(student);
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return al;
	}
}
