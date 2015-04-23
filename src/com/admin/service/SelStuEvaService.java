package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.StudentResume;
import com.common.util.SqlHelper;

// 用于评价学生那一页面的查询service
public class SelStuEvaService {

	// 要审核过后才能评价，所以都是查询审核过后的那部分
	// 默认下
	public int getPageCount(int pageSize) {

		String sql = "select count(*) from stuResume where checkState=1";

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
		String sql = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in " +
				"(select top "+ ((pageNow-1)*10)+" id from stuResume where checkState=1 order by id )and checkState = 1 order by id";

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// 二次封装 把ArrayList[对象数组] -> Book对象 -> ArrayList(集合)
		try {
			for (int i = 0; i < al2.size(); i++) {

				Object objs[] = (Object[]) al2.get(i);

				StudentResume studentResume = new StudentResume();

				studentResume.setStudentId(objs[0].toString());
				studentResume.setName(objs[1].toString());
				studentResume.setSex(objs[2].toString());
				try {
					studentResume.setCompany(objs[3].toString());
				} catch (Exception e) {
					// TODO: handle exception
					studentResume.setCompany("");
				}
				studentResume.setDirectionid(objs[4].toString());
				try {
					studentResume.setTecherevaluation(objs[5].toString());
				} catch (Exception e) {
					studentResume.setTecherevaluation("");
				}
				al.add(studentResume);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
	
	// ------------------按姓名查------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (name like '%"+term+"%') and (checkState=1)";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByName(int pageNow, int pageSize, String term) {
	
		// 查询sql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (name like '%"+term+"%') and (checkState=1) order by id ) " +
						"and (name like '%"+term+"%') and (checkState=1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------按学号查------------------------------------------------------------------
	public int getPageCountById(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (studentId like '%"+term+"%') and (checkState=1)";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageById(int pageNow, int pageSize, String term) {
	
		// 查询sql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (studentId like '%"+term+"%') and (checkState=1) order by id ) " +
						"and (studentId like '%"+term+"%') and (checkState=1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------按公司查------------------------------------------------------------------
	public int getPageCountByCom(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (company like '%"+term+"%') and (choose=1)";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByCom(int pageNow, int pageSize, String term) {
	
		// 查询sql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (company like '%"+term+"%') and (choose=1) order by id ) " +
						"and (company like '%"+term+"%') and (choose=1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------查询已实习的学生列表,按姓名、方向、性别------------------------------------------------------------------
	public int getPageCountByChoose(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
				" sex like '%"+term+"%') and (choose = 1) ";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByChoose(int pageNow, int pageSize, String term) {
	
		// 查询sql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
						" sex like '%"+term+"%') and (choose = 1) order by id ) and (name like '%"+term+"%' or " +
								"direction like '%"+term+"%' or sex like '%"+term+"%') and (choose = 1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------查询未实习的学生列表,按姓名、方向、性别------------------------------------------------------------------
		public int getPageCountByNoChoose(int pageSize, String term) {

			String sql2 = "select count(*) from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
					" sex like '%"+term+"%') and (choose = 0) and (checkState=1)";
			
			int pageCount = getPageCount(pageSize,term,sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByNoChoose(int pageNow, int pageSize, String term) {
		
			// 查询sql
			String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
							" sex like '%"+term+"%') and (choose = 0) and (checkState=1) order by id ) and (name like '%"+term+"%' or " +
									"direction like '%"+term+"%' or sex like '%"+term+"%') and (choose = 0) and (checkState=1) order by id";

			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
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
				try {
					studentResume.setCompany(objs[3].toString());
				} catch (Exception e) {

					studentResume.setCompany("");
				}
				studentResume.setDirectionid(objs[4].toString());
				try {
					studentResume.setTecherevaluation(objs[5].toString());
				} catch (Exception e) {
					studentResume.setTecherevaluation("");
				}

				al.add(studentResume);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return al;
	}
}
