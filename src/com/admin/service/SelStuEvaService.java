package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.StudentResume;
import com.common.util.SqlHelper;

// ��������ѧ����һҳ��Ĳ�ѯservice
public class SelStuEvaService {

	// Ҫ��˹���������ۣ����Զ��ǲ�ѯ��˹�����ǲ���
	// Ĭ����
	public int getPageCount(int pageSize) {

		String sql = "select count(*) from stuResume where checkState=1";

		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1); // �õ�idֵ����֪���м�������

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount - 1) / pageSize + 1; 
	}

	// ���շ�ҳ����ȡѧ���б�
	// ��ResultSet->����->ArrayList(����)
	public ArrayList<StudentResume> getStusByPage(int pageNow, int pageSize) {
		// ��������
		ArrayList<StudentResume> al = new ArrayList<StudentResume>();

		// ��ѯsql
		String sql = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in " +
				"(select top "+ ((pageNow-1)*10)+" id from stuResume where checkState=1 order by id )and checkState = 1 order by id";

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// ���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
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
	
	// ------------------��������------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (name like '%"+term+"%') and (checkState=1)";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByName(int pageNow, int pageSize, String term) {
	
		// ��ѯsql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (name like '%"+term+"%') and (checkState=1) order by id ) " +
						"and (name like '%"+term+"%') and (checkState=1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------��ѧ�Ų�------------------------------------------------------------------
	public int getPageCountById(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (studentId like '%"+term+"%') and (checkState=1)";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageById(int pageNow, int pageSize, String term) {
	
		// ��ѯsql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (studentId like '%"+term+"%') and (checkState=1) order by id ) " +
						"and (studentId like '%"+term+"%') and (checkState=1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------����˾��------------------------------------------------------------------
	public int getPageCountByCom(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (company like '%"+term+"%') and (choose=1)";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByCom(int pageNow, int pageSize, String term) {
	
		// ��ѯsql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (company like '%"+term+"%') and (choose=1) order by id ) " +
						"and (company like '%"+term+"%') and (choose=1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------��ѯ��ʵϰ��ѧ���б�,�������������Ա�------------------------------------------------------------------
	public int getPageCountByChoose(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
				" sex like '%"+term+"%') and (choose = 1) ";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByChoose(int pageNow, int pageSize, String term) {
	
		// ��ѯsql
		String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
						" sex like '%"+term+"%') and (choose = 1) order by id ) and (name like '%"+term+"%' or " +
								"direction like '%"+term+"%' or sex like '%"+term+"%') and (choose = 1) order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------��ѯδʵϰ��ѧ���б�,�������������Ա�------------------------------------------------------------------
		public int getPageCountByNoChoose(int pageSize, String term) {

			String sql2 = "select count(*) from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
					" sex like '%"+term+"%') and (choose = 0) and (checkState=1)";
			
			int pageCount = getPageCount(pageSize,term,sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByNoChoose(int pageNow, int pageSize, String term) {
		
			// ��ѯsql
			String sql2 = "select top "+pageSize+" studentId,name,sex,company,direction,techerevaluation from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where (name like '%"+term+"%' or direction like '%"+term+"%' or " +
							" sex like '%"+term+"%') and (choose = 0) and (checkState=1) order by id ) and (name like '%"+term+"%' or " +
									"direction like '%"+term+"%' or sex like '%"+term+"%') and (choose = 0) and (checkState=1) order by id";

			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
	
	
	
	// -------------��װ����--------------------------------------------------------------
	// ȡ��pageCount
	public int getPageCount(int pageSize, String term,String sql2) {
		
		String sql = sql2;
		
		int rowCount = 0;
		ResultSet rs = SqlHelper.executeQuery(sql, null);
		try {
			rs.next();
			rowCount = rs.getInt(1); // �õ�idֵ����֪���м�������

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, SqlHelper.getPs(), SqlHelper.getCt());
		}
		return (rowCount - 1) / pageSize + 1; 
	}
	
	// ȡ��al
	public ArrayList<StudentResume> getStusByPage(int pageNow, int pageSize, String term,String sql2) {
		// ��������
		ArrayList<StudentResume> al = new ArrayList<StudentResume>();
		
		String sql = sql2;

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// ���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
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
