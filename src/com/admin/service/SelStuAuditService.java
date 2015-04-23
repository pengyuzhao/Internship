package com.admin.service;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.admin.domain.StudentResume;
import com.common.util.SqlHelper;

// �������ѧ����һҳ��Ĳ�ѯservice
public class SelStuAuditService {

	// ---------------Ĭ���£�Ĭ����ʾδ��˵�----------------------------------------------------
	// ��ȡĬ��pageCount
	public int getPageCount(int pageSize) {

		String sql = "select count(*) from stuResume where checkState=0";

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
		String sql = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in " +
				"(select top "+ ((pageNow-1)*10)+" id from stuResume where checkState=0 order by id )and checkState = 0 order by id";

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// ���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
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
	
	// ------------------��������------------------------------------------------------------------
	public int getPageCountByName(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where name like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize,term,sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageByName(int pageNow, int pageSize, String term) {
	
		// ��ѯsql
		String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where name like '%"+term+"%' order by id ) " +
						"and name like '%"+term+"%' order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------��ѧ�Ų�------------------------------------------------------------------
	// ����pageCount
	public int getPageCountById(int pageSize, String term) {

		String sql2 = "select count(*) from stuResume where studentId like '%"+term+"%'";
		
		int pageCount = getPageCount(pageSize, term, sql2);
		
		return pageCount;
	}
	
	public ArrayList<StudentResume> getStusPageById(int pageNow, int pageSize, String term) {
		
		// ��ѯsql
		String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*10) +" id from stuResume where studentId like '%"+term+"%' order by id ) " +
						"and studentId like '%"+term+"%' order by id";

		ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
		
		return al;
	}
	
	// ------------------�ؼ��ֲ�ѯ(������ѧ�š��Ա�רҵ�������ޡ����ᡢϣ������������ϣ����λ)------------------------------------------------------------------
		// ����pageCount
		public int getPageCountByAll(int pageSize, String term) {

			String sql2 = "select count(*) from stuResume where studentId like '%"+term+"%' or name like '%"+term+"%' or sex like '%"+term+"%' or nativePlace like '%"+term+"%' " +
					"or hopePlace like '%"+term+"%' or major like '%"+term+"%' or hopeJob like '%"+term+"%' or direction like '%"+term+"%'";
			
			int pageCount = getPageCount(pageSize, term, sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByAll(int pageNow, int pageSize, String term) {
			
			// ��ѯsql
			String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where studentId like '%"+term+"%' or name like '%"+term+"%' or sex like '%"+term+"%' or nativePlace like '%"+term+"%' " +
					"or hopePlace like '%"+term+"%' or major like '%"+term+"%' or hopeJob like '%"+term+"%' or direction like '%"+term+"%' order by id ) " +
							"and studentId like '%"+term+"%' or name like '%"+term+"%' or sex like '%"+term+"%' or nativePlace like '%"+term+"%' " +
					"or hopePlace like '%"+term+"%' or major like '%"+term+"%' or hopeJob like '%"+term+"%' or direction like '%"+term+"%' order by id";

			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
		
	// ------------------�����ͨ���������£��ɲ�ѯ���֡�ѧ�š�רҵ����------------------------------------------------------------------
		// ����pageCount
		public int getPageCountByOk(int pageSize, String term) {
	
			String sql2 = "select count(*) from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' " +
					"or direction like '%"+term+"%') and  (checkState=1) ";
			
			int pageCount = getPageCount(pageSize, term, sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByOk(int pageNow, int pageSize, String term) {
			
			// ��ѯsql
			String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
							"and  (checkState=1) order by id ) and (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
									"and  (checkState=1) order by id";
	
			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
		
	// ------------------�����δͨ���������£��ɲ�ѯ���֡�ѧ�š�רҵ����------------------------------------------------------------------
		// ����pageCount
		public int getPageCountByNoOk(int pageSize, String term) {
		
			String sql2 = "select count(*) from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' " +
					"or direction like '%"+term+"%') and  (checkState=-1) ";
			
			int pageCount = getPageCount(pageSize, term, sql2);
			
			return pageCount;
		}
		
		public ArrayList<StudentResume> getStusPageByNoOk(int pageNow, int pageSize, String term) {
			
			// ��ѯsql
			String sql2 = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*10) +" id from stuResume where (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
							"and  (checkState=-1) order by id ) and (studentId like '%"+term+"%' or name like '%"+term+"%' or direction like '%"+term+"%') " +
									"and  (checkState=-1) order by id";
		
			ArrayList<StudentResume> al = getStusByPage(pageNow, pageSize, term, sql2);
			
			return al;
		}
		
	// ---------��ʾȫ���б�------------------------------------------------------
	// ��ȡĬ��pageCount
	public int getPageCountAll(int pageSize) {

		String sql = "select count(*) from stuResume";

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
	public ArrayList<StudentResume> getStusByPageAll(int pageNow, int pageSize) {
		// ��������
		ArrayList<StudentResume> al = new ArrayList<StudentResume>();

		// ��ѯsql
		String sql = "select top "+pageSize+" studentId,name,sex,direction,checkState from stuResume where id not in " +
				"(select top "+ ((pageNow-1)*10)+" id from stuResume order by id ) order by id";

		ArrayList al2 = SqlHelper.executeQuery3(sql, null);
		// ���η�װ ��ArrayList[��������] -> Book���� -> ArrayList(����)
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
