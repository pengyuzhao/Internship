package com.student.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.common.util.SqlHelper;
import com.jspsmart.upload.SmartUpload;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUploadException;
import com.student.domain.StuResume;
import com.student.domain.StudentInfo;


public class StuResumeService {

	public void initDate(SmartUpload mySmartUpload,StudentInfo studentInfo) {

		String fileName = null;
		String sPath = "/UploadPhoto";
		StringBuffer bf = new StringBuffer();
	

		try {
			//保存照片
			File myFile = mySmartUpload.getFiles().getFile(0);
			String ext = myFile.getFileExt();
			fileName = studentInfo.getStudentId() + "." + ext;
			myFile.saveAs(sPath + "/" + fileName, mySmartUpload.SAVE_VIRTUAL);
			//得到参数
			String studentId=studentInfo.getStudentId();
			String name = mySmartUpload.getRequest().getParameter("name");
			String sex = mySmartUpload.getRequest().getParameter("sex");
			String nation = mySmartUpload.getRequest().getParameter("nation");
			String nativePlace = mySmartUpload.getRequest().getParameter(
						"nativePlace");
			String hopePlace = mySmartUpload.getRequest().getParameter(
					"hopePlace");
			String qq = mySmartUpload.getRequest().getParameter("qq");
			String poscalcode = mySmartUpload.getRequest().getParameter(
					"poscalcode");
			String birthday = mySmartUpload.getRequest().getParameter(
					"birthday");
			String graduationYear = mySmartUpload.getRequest().getParameter(
					"graduationYear");
			String education = mySmartUpload.getRequest().getParameter(
					"education");
			String hightweight = mySmartUpload.getRequest().getParameter(
					"hightweight");
			String professional = mySmartUpload.getRequest().getParameter(
					"professional");
			String healthsituation = mySmartUpload.getRequest().getParameter(
					"healthsituation");
			String graduationschool = mySmartUpload.getRequest().getParameter(
					"graduationschool");
			
			String englishClass = mySmartUpload.getRequest().getParameter(
					"englishClass");
			String[] directions = mySmartUpload.getRequest()
					.getParameterValues("direction");
			for (int i = 0; i < directions.length; i++) {

				bf.append(directions[i] + " ");
			}
			String hopeJob = mySmartUpload.getRequest().getParameter("hopeJob");
			String major = mySmartUpload.getRequest().getParameter("major");
			String personalResume = mySmartUpload.getRequest().getParameter(
					"personalResume");
			String punishsituation = mySmartUpload.getRequest().getParameter(
					"punishsituation");
			String experiences = mySmartUpload.getRequest().getParameter(
					"experiences");
			String evaluation = mySmartUpload.getRequest().getParameter(
					"evaluation");
			String imgpath=sPath + "/" + fileName;
			StuResume stuResume=selectCheckState(studentInfo);
			//StuResume stuResume=(StuResume) al.get(0);
			if(stuResume.getCheckState()==2)
			{
			//存入数据库
			String sql="insert stuResume(studentId,name,sex,nation,nativePlace,qq,poscalcode,birthday,graduationYear,education,hightweight,professional,healthsituation,graduationschool,hopePlace,englishClass,direction,hopeJob,major,personalResume,punishsituation,experiences,evaluation,imgPath) " +
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			String[] parameters={studentId,name,sex,nation,nativePlace,qq,poscalcode,birthday,graduationYear,education,hightweight,professional,healthsituation,graduationschool,hopePlace,englishClass,bf.toString(),hopeJob,major,personalResume,punishsituation,experiences,evaluation,imgpath};
			SqlHelper.executeUpdate(sql, parameters);
			}
		
			
		}

		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException();
		}

	}	
	
		public void updateResume(SmartUpload mySmartUpload,StudentInfo studentInfo)
		{

			String fileName = null;
			String sPath = "/UploadPhoto";
			StringBuffer bf = new StringBuffer();
		

			try {
				//保存照片
				File myFile = mySmartUpload.getFiles().getFile(0);
				String ext = myFile.getFileExt();
				fileName = studentInfo.getStudentId() + "." + ext;
				myFile.saveAs(sPath + "/" + fileName, mySmartUpload.SAVE_VIRTUAL);
				//得到参数
				String studentId=studentInfo.getStudentId();
				String name = mySmartUpload.getRequest().getParameter("name");
				String sex = mySmartUpload.getRequest().getParameter("sex");
				String nation = mySmartUpload.getRequest().getParameter("nation");
				String nativePlace = mySmartUpload.getRequest().getParameter(
							"nativePlace");
				String hopePlace = mySmartUpload.getRequest().getParameter(
						"hopePlace");
				String qq = mySmartUpload.getRequest().getParameter("qq");
				String poscalcode = mySmartUpload.getRequest().getParameter(
						"poscalcode");
				String birthday = mySmartUpload.getRequest().getParameter(
						"birthday");
				String graduationYear = mySmartUpload.getRequest().getParameter(
						"graduationYear");
				String education = mySmartUpload.getRequest().getParameter(
						"education");
				String hightweight = mySmartUpload.getRequest().getParameter(
						"hightweight");
				String professional = mySmartUpload.getRequest().getParameter(
						"professional");
				String healthsituation = mySmartUpload.getRequest().getParameter(
						"healthsituation");
				String graduationschool = mySmartUpload.getRequest().getParameter(
						"graduationschool");
				
				String englishClass = mySmartUpload.getRequest().getParameter(
						"englishClass");
				String[] directions = mySmartUpload.getRequest()
						.getParameterValues("direction");
				for (int i = 0; i < directions.length; i++) {

					bf.append(directions[i] + " ");
				}
				String hopeJob = mySmartUpload.getRequest().getParameter("hopeJob");
				String major = mySmartUpload.getRequest().getParameter("major");
				String personalResume = mySmartUpload.getRequest().getParameter(
						"personalResume");
				String punishsituation = mySmartUpload.getRequest().getParameter(
						"punishsituation");
				String experiences = mySmartUpload.getRequest().getParameter(
						"experiences");
				String evaluation = mySmartUpload.getRequest().getParameter(
						"evaluation");
				String imgpath=sPath + "/" + fileName;				
				String sql="update stuResume set name=?,sex=?,nation=?,nativePlace=?,qq=?,poscalcode=?," +
						"birthday=?,graduationYear=?," +
						"education=?,hightweight=?,professional=?,healthsituation=?,graduationschool=?,hopePlace=?,englishClass=?,direction=?," +
						"hopeJob=?,major=?,personalResume=?,punishsituation=?,experiences=?,evaluation=?,imgPath=?,checkState=? where studentId=? ";
				String[] parameters={name,sex,nation,nativePlace,qq,poscalcode,birthday,graduationYear,education,hightweight,professional,
						healthsituation,graduationschool,hopePlace,englishClass,bf.toString(),hopeJob,major,personalResume,
					punishsituation,experiences,evaluation,imgpath,0+"",studentInfo.getStudentId()};
				SqlHelper.executeUpdate(sql, parameters);
				
			}
				catch (Exception e) {
					// TODO: handle exception
					throw new  RuntimeException();
				}
				
			
		}
		public void delTtecherevaluation(StudentInfo studentInfo)
		{
			try {
				String sql="select techerevaluation from stuResume where studentId=?";
				String[] parameters={studentInfo.getStudentId()};
				ArrayList al=SqlHelper.executeQuery3(sql, parameters);
				if(al.size()==1){
				sql="delete techerevaluation from stuResume where studentId=?";
				SqlHelper.executeUpdate(sql, parameters);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException();
			}
		}
		public StuResume selectCheckState(StudentInfo studentInfo)
		{
			StuResume stuResume=new StuResume();
			int i = 2; //i=-1代表不通过审核 0代表正在审核中 1代表通过审核  2代表为null还未上传简历
			
			String sql="select checkState,reason from stuResume where studentId=?";
			String[] parameters={studentInfo.getStudentId()};
			ArrayList al=SqlHelper.executeQuery3(sql, parameters);
			//ArrayList al1=new ArrayList();
		
			if(al.size()==0){
				stuResume.setCheckState(i);
				}
			//	al1.add(stuResume);}
				else{
				Object[] ob=(Object[]) al.get(0);	
				i=Integer.parseInt(ob[0].toString());
					if(i==-1)
					{
						stuResume.setCheckState(i);
						try{stuResume.setReason(ob[1].toString());}
						catch (Exception e) {
							// TODO: handle exception
							stuResume.setReason("管理员没有说明原因");
						}
					//	al1.add(stuResume);
					}
					else{
						stuResume.setCheckState(i);
						//al1.add(stuResume);
					}
				}
			return stuResume;
			
			
		}
		public void delResume(StudentInfo studentInfo)
		{
			try{
			//System.out.println("ok");
			String sql="delete from stuResume where studentId=? ";
			String[] parameters={studentInfo.getStudentId()};
			System.out.println(studentInfo.getStudentId());
			SqlHelper.executeUpdate(sql, parameters);}
			catch (Exception e) {
				// TODO: handle exception
				throw new RuntimeException();
			}
		}
		public ArrayList getResumeById(String id)
		{
			String sql="select * from stuResume where studentId=? ";
			String[] parameters={id};
			ArrayList al=SqlHelper.executeQuery3(sql, parameters);
			ArrayList al1=new ArrayList();
			for(int i=0;i<al.size();i++)
			{
				StuResume stuResume=new StuResume();
				
				Object[] ob=(Object[])al.get(i);	
				stuResume.setStudentId(ob[1].toString());
				stuResume.setName(ob[2].toString());
				stuResume.setSex(ob[3].toString());
				stuResume.setNation(ob[4].toString());
				stuResume.setNativePlace(ob[5].toString());
				stuResume.setQq(ob[6].toString());
				stuResume.setPoscalcode(ob[7].toString());
				stuResume.setBirthday(ob[8].toString());
				stuResume.setGraduationYear(ob[9].toString());
				stuResume.setEducation(ob[10].toString());
				stuResume.setHightweight(ob[11].toString());
				stuResume.setProfessional(ob[12].toString());
				stuResume.setHealthsituation(ob[13].toString());
				stuResume.setGraduationschool(ob[14].toString());
				stuResume.setHopePlace(ob[15].toString());
				stuResume.setEnglishClass(ob[16].toString());
				stuResume.setDirection(ob[17].toString());
				stuResume.setHopeJob(ob[18].toString());
				stuResume.setMajor(ob[19].toString());
				stuResume.setPersonalResume(ob[20].toString());
				stuResume.setPunishsituation(ob[21].toString());
				stuResume.setExperiences(ob[22].toString());
				stuResume.setEvaluation(ob[23].toString());
				stuResume.setImgPath(ob[24].toString());

				al1.add(stuResume);
				
				
			}
			
			return al1;
		}
		
		
		public StuResume getStuInfoByoneself(StudentInfo studentInfo)
		{
			String sql="select studentId, name,choose,company from stuResume where studentId=?";
			String[] parameters={studentInfo.getStudentId()};
			ArrayList al=SqlHelper.executeQuery3(sql, parameters);
		
				StuResume stuResume=new StuResume();
				if(al.size()==0){
					return stuResume;	
				}
				else{
				Object[] ob=(Object[])al.get(0);
				stuResume.setStudentId(ob[0].toString());
				stuResume.setName(ob[1].toString());
				String choose=ob[2].toString();
				stuResume.setChoose(Integer.parseInt(choose));
				try{
				String company=ob[3].toString();
				stuResume.setCompany(company);
				System.out.println(stuResume.getName());
				}
				catch (Exception e) {
					// TODO: handle exception
					stuResume.setCompany("没有");
				}
				
				
				}
				
				
				
			return stuResume;	
		}
		public StuResume getTecherevaluation(String id)
		{
			
			String sql="select techerevaluation from dbo.stuResume where  studentId=? ";
			String[] parameters={id};
			ArrayList al=SqlHelper.executeQuery3(sql, parameters);
			StuResume stuResume=new StuResume();
			Object[] ob=(Object[])al.get(0);
			try{
			stuResume.setTecherevaluation(ob[0].toString());}
			catch (Exception e) {	
				// TODO: handle exception
				stuResume.setTecherevaluation("老师尚未评论");
			}
			return stuResume;
			
		}
}