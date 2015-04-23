package com.student.service;

import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.util.SqlHelper;
import com.jspsmart.upload.SmartUpload;


import com.jspsmart.upload.File;
import com.student.domain.Filepath;
import com.student.domain.StudentInfo;


public class Filepathservice {

	Filepath filepath = new Filepath();
	public void saveFile(ServletConfig config, HttpServletRequest request,
			HttpServletResponse response, StudentInfo studentInfo) {

		String FileName = null;
		String sPath = "/UploadPhoto";
		SmartUpload mySmartUpload = new SmartUpload();
		
		try {
			mySmartUpload.initialize(config, request, response);
			mySmartUpload.setAllowedFilesList("rar,wmv,avi,zip");// 限制文件类型
			mySmartUpload.setMaxFileSize(1024*1024*1024*15);// 限制大小在30M
			mySmartUpload.upload();
			for (int i = 0; i < mySmartUpload.getFiles().getCount();i++) {
				File myFile = mySmartUpload.getFiles().getFile(i);
					String ext = myFile.getFileExt();
					filepath.setStudentId(studentInfo.getStudentId());
					if (ext.equals("rar") || ext.equals("zip")) {
						FileName = filepath.getStudentId()+ "." + ext;
						filepath.setSourceFilePath(sPath + "/" + FileName);
						myFile.saveAs(sPath + "/" + FileName,
								mySmartUpload.SAVE_VIRTUAL);
					} else if (ext.equals("wmv") || ext.equals("avi")) {
						FileName = studentInfo.getStudentId() + "." + ext;
						filepath.setVedioFilePath(sPath + "/" + FileName);
						myFile.saveAs(sPath + "/" + FileName,
						mySmartUpload.SAVE_VIRTUAL);
					}
					
					// mySmartUpload.setMaxFileSize(1024*1024*1024*30);// 限制大小类型
			}
			insertDatebase(filepath);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new RuntimeException();
		}
	}
	public void insertDatebase(Filepath filepath)
	{
		
		String sql=null;
		String studentId=filepath.getStudentId();
		String sourceFilePath= filepath.getSourceFilePath();
		String vedioFilePath= filepath.getVedioFilePath();
		sql="select * from filepath where studentId =? ";
		String[] parameters={studentId};
		ArrayList al=SqlHelper.executeQuery3(sql, parameters);
		if(al.size()==0)
		{
			sql="insert filepath(studentId ,sourceFilePath ,vedioFilePath ) values(?,?,?)";
			String[] parameters1={studentId,sourceFilePath,vedioFilePath};
			SqlHelper.executeUpdate(sql, parameters1);
		
		}
		else{
			sql="update filepath set sourceFilePath=?, vedioFilePath=? where studentId=?";
			String[] parameters2={sourceFilePath,vedioFilePath,studentId};
			SqlHelper.executeUpdate(sql, parameters2);	
			
		}
		
	}
	public Filepath getPathById(String studentId)
	{
		Filepath filepath=new Filepath();
		String sql="select sourceFilePath,vedioFilePath from filepath where studentId=?";
		String[] parameters={studentId};
		ArrayList al=SqlHelper.executeQuery3(sql, parameters);
		if(al.size()==1)
		{
		Object[] ob=(Object[]) al.get(0);
		filepath.setSourceFilePath(ob[0].toString());
		filepath.setVedioFilePath(ob[1].toString());}
		
		return filepath;		
	}
	
	public void delFilepath(StudentInfo studentInfo)
	{
		try{
		//System.out.println("ok");
		String sql="delete from filepath where studentId=? ";
		String[] parameters={studentInfo.getStudentId()};
		System.out.println(studentInfo.getStudentId());
		SqlHelper.executeUpdate(sql, parameters);}
		catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}
	
}

