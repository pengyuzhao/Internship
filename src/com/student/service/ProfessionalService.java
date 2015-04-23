package com.student.service;

import java.util.ArrayList;

import com.common.util.SqlHelper;
import com.student.domain.Professionaldirection;


public class ProfessionalService {

public ArrayList getAllDirection()
{	
	
	String sql="select * from Professionaldirection where id>?";
	String[] parameters={0+""};
	ArrayList al=SqlHelper.executeQuery3(sql, parameters);
	
	ArrayList dirctonInfo=new ArrayList();

	for(int i=0;i<al.size();i++)
	{	
		Professionaldirection pr=new Professionaldirection();
		Object[] ob=(Object[]) al.get(i);
		String id=ob[0].toString();
		pr.setId(Integer.parseInt(id));
		pr.setDirection(ob[1].toString());
		dirctonInfo.add(pr);
		}
	
		return dirctonInfo;
	}
	
		
}
