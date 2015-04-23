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

// 把上传的excel从客户端路径转化为服务端路径，返回服务端路径
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

				// 如果接收到的参数不是一个普通表单(例text等)的元素，那么执行下面代码

				String fieldName = item.getFieldName();// 获得此表单元素的name属性

				String fileName = item.getName();// 获得文件的完整名称

				String contentType = item.getContentType();// 获得文件类型

				long fileSize = item.getSize();// 获得文件大小
				// 从文件的完整路径中截取出文件名
				fileName = fileName.substring(fileName.lastIndexOf("\\") + 1,
						fileName.length());

				String filepath = propath + "\\" + fieldName;

				File uf = new File(filepath);
				// 更改文件的保存路径，以防止文件重名的现象出现
				if (!uf.exists()) {
					uf.mkdir();
				}

				// 此输出路径为保存到服务器中路径
				String insertDB = filepath + "\\" + fileName;

				File uploadedFile = new File(filepath, fileName);

				// 如果在该文件夹中已经有相同的文件，那么将其删除之后再重新创建（只适用于上传一张照片的情况）
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
