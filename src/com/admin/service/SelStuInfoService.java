package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.Student;
import com.common.util.SqlHelper;

// 查询学生信息的service
public class SelStuInfoService {
	
	// 默认显示全部
	// 获取显示全部的pageCount
	public int getPageCount(int pageSize){
		
		String sql = "select count(*) from studentInfo";
		
		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1);	// 拿到id值，就知道有几行数据
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount-1)/pageSize+1;		
	}
	
	// 按照分页来获取学生列表
	// 把ResultSet->对象->ArrayList(集合)
	// 获取显示全部的al
	public ArrayList<Student> getStusByPage(int pageNow,int pageSize)
	{	
		// 创建对象
		ArrayList<Student> al = new ArrayList<Student>();
		
		//查询sql
		String sql = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo order by studentId) order by studentId";
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//二次封装 把ArrayList[对象数组] -> Book对象 -> ArrayList(集合)
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
	
	// --------按姓名查-----------------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {
		
		String sql2 = "select count(*) from studentInfo where studentName like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Student> getStusPageByName(int pageNow, int pageSize, String term) {
		
		// 查询sql
		String sql2 = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo where studentName like '%"+term+"%' order by studentId) " +
						"and studentName like '%"+term+"%' order by studentId";

		ArrayList<Student> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// --------按学号查-----------------------------------------------------------------------------
	public int getPageCountById(int pageSize, String term) {
		
		String sql2 = "select count(*) from studentInfo where studentId like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Student> getStusPageById(int pageNow, int pageSize, String term) {
		
		// 查询sql
		String sql2 = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo where studentId like '%"+term+"%' order by studentId) " +
						"and studentId like '%"+term+"%' order by studentId";

		ArrayList<Student> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// --------按班级查-----------------------------------------------------------------------------
	public int getPageCountByClass(int pageSize, String term) {
		
		String sql2 = "select count(*) from studentInfo where studentClass like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		return pageCount;
	}
	
	public ArrayList<Student> getStusPageByClass(int pageNow, int pageSize, String term) {
		
		// 查询sql
		String sql2 = "select top "+pageSize+"studentId,studentName,studentPwd,studentCardId,studentClass,studentTel from studentInfo where " +
				"studentId not in (select top "+ ((pageNow-1)*10) +" studentId from studentInfo where studentClass like '%"+term+"%' order by studentId) " +
						"and studentClass like '%"+term+"%' order by studentId";

		ArrayList<Student> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	
	
	//----------封装函数---------------------------------------------------------------------------
	public int getPageCount(int pageSize,String term,String sql2){
		
		String sql = sql2;
		
		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1);	// 拿到id值，就知道有几行数据
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount-1)/pageSize+1;		
	}
	
	public ArrayList<Student> getStusByPage(int pageNow, int pageSize, String term,String sql2)
	{	
		// 创建对象
		ArrayList<Student> al = new ArrayList<Student>();
		
		//查询sql
		String sql = sql2;
		
		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		//二次封装 把ArrayList[对象数组] -> Book对象 -> ArrayList(集合)
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
