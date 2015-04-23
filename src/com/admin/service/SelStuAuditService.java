package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.StudentResume;
import com.common.util.SqlHelper;

// 用于审核学生那一页面的查询service
public class SelStuAuditService {

	// ---------------默认下，默认显示未审核的----------------------------------------------------
	// 获取默认pageCount
	public int getPageCount(int pageSize) {

		String sql = "select count(*) from stuResume where checkState=0";

		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1); // 拿到id值，就知道有几行数据

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount - 1) / pageSize + 1; 
	}

	// 按照分页来获取学生列表
	// 把ResultSet->对象->ArrayList(集合)
	public ArrayList<StudentResume> getStusByPage(int pageNow, int pageSize) {
		// 创建对象
		ArrayList<StudentResume> al = new ArrayList<StudentResume>();

		// 查询sql
		String sql = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in " +
				"(select top "+ ((pageNow-1)*10)+" id from stuResume where checkState=0 order by id )and checkState = 0 order by id";

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// 二次封装 把ArrayList[对象数组] -> Book对象 -> ArrayList(集合)
		try {
			for (int i = 0; i < al2.size(); i++) {

				Object objs[] = (Object[]) al2.get(i);

				StudentResume studentResume = new StudentResume();

				studentResume.setStudentId(objs[0].toString());
				studentResume.setName(objs[1].toString());
				studentResume.setSex(objs[2].toString());
				studentResume.setDirectionid(objs[3].toString());
				studentResume.setCheckState(Integer.parseInt(objs[4].toString()));

				al.add(studentResume);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	// ------------------按姓名查------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where name like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByName(int pageNow, int pageSize, String term) {
	
		// 查询sql
		String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where name like '%"+term+"%' order by id ) " +
						"and name like '%"+term+"%' order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------按学号查------------------------------------------------------------------
	// 姓名pageCount
	public int getPageCountById(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where studentId like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize, term, sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageById(int pageNow, int pageSize, String term) {
		
		// 查询sql
		String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where studentId like '%"+term+"%' order by id ) " +
						"and studentId like '%"+term+"%' order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------关键字查询(姓名、学号、性别、专业方向、主修、籍贯、希望工作地区、希望岗位)------------------------------------------------------------------
		// 姓名pageCount
		public int getPageCountByAll(int pageSize, String term) {

			String sql2 = "select count(*) from stuResume where studentId like '%"+term+"%' or name like '%"+term+"%' or sex like '%"+term+"%' or nativePlace like '%"+term+"%' " +
					"or hopePlace like '%"+term+"%' or major like '%"+term+"%' or hopeJob like '%"+term+"%' or direction like '%"+term+"%'";
			
			int pageCount = getPageCount(pageSize, term, sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByAll(int pageNow, int pageSize, String term) {
			
			// 查询sql
			String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where studentId like '%"+term+"%' or name like '%"+term+"%' or sex like '%"+term+"%' or nativePlace like '%"+term+"%' " +
					"or hopePlace like '%"+term+"%' or major like '%"+term+"%' or hopeJob like '%"+term+"%' or direction like '%"+term+"%' order by id ) " +
							"and studentId like '%"+term+"%' or name like '%"+term+"%' or sex like '%"+term+"%' or nativePlace like '%"+term+"%' " +
					"or hopePlace like '%"+term+"%' or major like '%"+term+"%' or hopeJob like '%"+term+"%' or direction like '%"+term+"%' order by id";

			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
		
	// ------------------在审核通过的条件下，可查询名字、学号、专业方向------------------------------------------------------------------
		// 姓名pageCount
		public int getPageCountByOk(int pageSize, String term) {
	
			String sql2 = "select count(*) from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' " +
					"or direction like '%"+term+"%') and  (checkState=1) ";
			
			int pageCount = getPageCount(pageSize, term, sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByOk(int pageNow, int pageSize, String term) {
			
			// 查询sql
			String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
							"and  (checkState=1) order by id ) and (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
									"and  (checkState=1) order by id";
	
			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
		
	// ------------------在审核未通过的条件下，可查询名字、学号、专业方向------------------------------------------------------------------
		// 姓名pageCount
		public int getPageCountByNoOk(int pageSize, String term) {
		
			String sql2 = "select count(*) from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' " +
					"or direction like '%"+term+"%') and  (checkState=-1) ";
			
			int pageCount = getPageCount(pageSize, term, sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByNoOk(int pageNow, int pageSize, String term) {
			
			// 查询sql
			String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
							"and  (checkState=-1) order by id ) and (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
									"and  (checkState=-1) order by id";
		
			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
		
	// ---------显示全部列表------------------------------------------------------
	// 获取默认pageCount
	public int getPageCountAll(int pageSize) {

		String sql = "select count(*) from stuResume";

		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1); // 拿到id值，就知道有几行数据

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount - 1) / pageSize + 1; 
	}

	// 按照分页来获取学生列表
	// 把ResultSet->对象->ArrayList(集合)
	public ArrayList<StudentResume> getStusByPageAll(int pageNow, int pageSize) {
		// 创建对象
		ArrayList<StudentResume> al = new ArrayList<StudentResume>();

		// 查询sql
		String sql = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in " +
				"(select top "+ ((pageNow-1)*10)+" id from stuResume order by id ) order by id";

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// 二次封装 把ArrayList[对象数组] -> Book对象 -> ArrayList(集合)
		try {
			for (int i = 0; i < al2.size(); i++) {

				Object objs[] = (Object[]) al2.get(i);

				StudentResume studentResume = new StudentResume();

				studentResume.setStudentId(objs[0].toString());
				studentResume.setName(objs[1].toString());
				studentResume.setSex(objs[2].toString());
				studentResume.setDirectionid(objs[3].toString());
				studentResume.setCheckState(Integer.parseInt(objs[4].toString()));

				al.add(studentResume);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
		
		
	// -------------封装函数--------------------------------------------------------------
		// 取得pageCount
		public int getPageCount(int pageSize, String term,String sql2) {
			
			String sql = sql2;
			
			int rowCount = 0;
			ResultSet rs = SqlHelper.executeQuery(sql, null);
			try {
				rs.next();
				rowCount = rs.getInt(1); // 拿到id值，就知道有几行数据

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
			}
			return (rowCount - 1) / pageSize + 1; 
		}
		
		// 取得al
		public ArrayList<StudentResume> getStusByPage(int pageNow, int pageSize, String term,String sql2) {
			// 创建对象
			ArrayList<StudentResume> al = new ArrayList<StudentResume>();
			
			String sql = sql2;

			ArrayList al2 = SqlHelper.executeQuery3(sql, null);
			// 二次封装 把ArrayList[对象数组] -> Book对象 -> ArrayList(集合)
			try {
				for (int i = 0; i < al2.size(); i++) {

					Object objs[] = (Object[]) al2.get(i);

					StudentResume studentResume = new StudentResume();

					studentResume.setStudentId(objs[0].toString());
					studentResume.setName(objs[1].toString());
					studentResume.setSex(objs[2].toString());
					studentResume.setDirectionid(objs[3].toString());
					studentResume.setCheckState(Integer.parseInt(objs[4].toString()));

					al.add(studentResume);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return al;
		}
}
