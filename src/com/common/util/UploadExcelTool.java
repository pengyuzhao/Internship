package com.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.admin.domain.UploadExcel;

import jxl.Sheet;
import jxl.Workbook;

// ���ϴ���excel�ӿͻ���·��ת��Ϊ�����·�������ط����·��
public class UploadExcelTool {
	
	String propath = null;;
	
	public void receive(String projectpath){
		 propath = projectpath;
	}

	public ArrayList<String> uploadExcel(List<FileItem> list) {

		Iterator<FileItem> iter = list.iterator();
		ArrayList<String> al = new ArrayList<String>();
		try {
			while (iter.hasNext()) {
				FileItem item = iter.next();

				// ������յ��Ĳ�������һ����ͨ��(��text��)��Ԫ�أ���ôִ���������

				String fieldName = item.getFieldName();// ��ô˱�Ԫ�ص�name����

				String fileName = item.getName();// ����ļ�����������

				String contentType = item.getContentType();// ����ļ�����

				long fileSize = item.getSize();// ����ļ���С
				// ���ļ�������·���н�ȡ���ļ���
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1,
						fileName.length());

				String filepath = propath + "\\" + fieldName;

				File uf = new File(filepath);
				// �����ļ��ı���·�����Է�ֹ�ļ��������������
				if (!uf.exists()) {
					uf.mkdir();
				}

				// �����·��Ϊ���浽��������·��
				String insertDB = filepath + "\\" + fileName;

				File uploadedFile = new File(filepath, fileName);

				// ����ڸ��ļ������Ѿ�����ͬ���ļ�����ô����ɾ��֮�������´�����ֻ�������ϴ�һ����Ƭ�������
				if (uploadedFile.exists()) {
					uploadedFile.delete();
				}
				item.write(uploadedFile);

				al.add(insertDB);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		
		return al;
	}
}
