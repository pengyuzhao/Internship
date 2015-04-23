package com.company.service;

import java.util.ArrayList;

import com.common.util.SqlHelper;
import com.company.domain.Page;
import com.student.domain.StuResume;


public class PageService {
	public ArrayList getPage(int pageSize,int pageNow){
		
		
		String sql="select top "+pageSize+" * from stuResume where  checkState=?  and Id not in " +
			"(select top "+(pageNow-1)*pageSize+" id from stuResume order by Id )order by Id ";
		String[] parameters={1+""};
		ArrayList al=SqlHelper.executeQuery3(sql, parameters);
		ArrayList al1=new ArrayList();
		for(int i=0;i<al.size();i++)
		{
			StuResume stuResume=new StuResume();
			Object[] ob=(Object[])al.get(i);
			stuResume.setStudentId(ob[1].toString());
			stuResume.setName(ob[2].toString());
			stuResume.setSex(ob[3].toString());
			stuResume.setDirection(ob[17].toString());
			String choose=ob[26].toString();
			stuResume.setChoose(Integer.parseInt(choose));
			al1.add(stuResume);
		}		
		
		return al1;	
	}
	public Page getPageCount(int pageSize,int pageNow){
		
		String sql="select count(*) from stuResume where checkState=?";
		String[] parameters={1+""};
		ArrayList al=SqlHelper.executeQuery3(sql, parameters);
		Object[] ob=(Object[])al.get(0);
		int rowCount=Integer.parseInt(ob[0].toString());
		int pageCount=(rowCount-1)/pageSize+1;
		Page page=new Page();
		page.setPageCount(pageCount);
		page.setRowCount(rowCount);
		page.setPageSize(pageSize);
		page.setPageNow(pageNow);
		return page;	
	}
	public ArrayList selectDate(String require,String key,int pageSize,int pageNow)
	{
		String sql="";
		ArrayList al=new ArrayList();
		ArrayList al1=new ArrayList();
		if(require.equals("name"))
		{
		sql="select top "+pageSize+"* from stuResume where  name LIKE '%"+key+"%'and checkState=? and Id not in"+
				"(select top "+(pageNow-1)*pageSize+"id from stuResume order by Id )order by Id";
			String[] parameters={1+""};
			al=SqlHelper.executeQuery3(sql, parameters);
}		if(require.equals("notChoose")){
	 sql = "select top "+pageSize+"* from stuResume where id not in" +
				" (select top "+ ((pageNow-1)*pageSize) +" id from stuResume where (name like '%"+key+"%' or direction like '%"+key+"%' or " +
						" sex like '%"+key+"%') and (choose = ?)and(checkState=1) order by id ) and (name like '%"+key+"%' or " +
								"direction like '%"+key+"%' or sex like '%"+key+"%') and (choose = ?)and((checkState=1)) order by id";
				String[] parameters={0+"",0+""};
				 al=SqlHelper.executeQuery3(sql, parameters);	
		}
		if(require.equals("choose")){
		
				
			 sql = "select top "+pageSize+"* from stuResume where id not in" +
					" (select top "+ ((pageNow-1)*pageSize) +" id from stuResume where (name like '%"+key+"%' or direction like '%"+key+"%' or " +
							" sex like '%"+key+"%') and (choose = ?)and(checkState=1) order by id ) and (name like '%"+key+"%' or " +
									"direction like '%"+key+"%' or sex like '%"+key+"%') and (choose = ?)and((checkState=1)) order by id";
		
			String[] parameters={1+"",1+""};
				 al=SqlHelper.executeQuery3(sql, parameters);	
		}
		if(require.equals("direction"))
		{
		sql="select top "+pageSize+"* from stuResume where  direction LIKE '%"+key+"%'and checkState=? and Id not in"+
				"(select top "+(pageNow-1)*pageSize+"id from stuResume order by Id )order by Id";
			String[] parameters={1+""};
			al=SqlHelper.executeQuery3(sql, parameters);
		}	
		for(int i=0;i<al.size();i++)
		{
			StuResume stuResume=new StuResume();
			Object[] ob=(Object[])al.get(i);
			stuResume.setStudentId(ob[1].toString());
			stuResume.setName(ob[2].toString());
		
			stuResume.setName(ob[2].toString());
			stuResume.setSex(ob[3].toString());
			stuResume.setDirection(ob[17].toString());
			String choose=ob[26].toString();
			stuResume.setChoose(Integer.parseInt(choose));
			al1.add(stuResume);
		}		
		return al1;	
	}
	public Page getPageCount(int pageSize,int pageNow,String require,String key){
		
		ArrayList al=new ArrayList();
		if(require.equals("name")){
		String sql="select count(*) from stuResume where name LIKE '%"+key+"%' and  checkState=? ";
		String[] parameters={1+""};
		al=SqlHelper.executeQuery3(sql, parameters);
		
	}
		if(require.equals("notChoose")){
		
			String sql=	"select count(*) from stuResume where (name like '%"+key+"%' or direction like '%"+key+"%' or " +
					" sex like '%"+key+"%') and (choose = ?) and(checkstate = ?) ";
			String[] parameters={0+"",1+""};
			 al=SqlHelper.executeQuery3(sql, parameters);
			
		}
		if(require.equals("choose")){
		//	String sql="select count(*) from stuResume where choose=? and checkState=?";
			String sql=	"select count(*) from stuResume where (name like '%"+key+"%' or direction like '%"+key+"%' or " +
			" sex like '%"+key+"%') and (choose = ?) and(checkstate = ?) ";
			String[] parameters={1+"",1+""};
			al=SqlHelper.executeQuery3(sql, parameters);
		}
		if(require.equals("direction")){
			
			String sql="select count(*) from stuResume where direction LIKE '%"+key+"%' and  checkState=? ";
			String[] parameters={1+""};
			 al=SqlHelper.executeQuery3(sql, parameters);
			
		}
		Object[] ob=(Object[]) al.get(0);
		int rowCount=Integer.parseInt(ob[0].toString());
		int pageCount=(rowCount-1)/pageSize+1;
		Page page=new Page();
		page.setPageCount(pageCount);
		page.setRowCount(rowCount);
		page.setPageSize(pageSize);
		page.setPageNow(pageNow);
		return page;	
	}
}
