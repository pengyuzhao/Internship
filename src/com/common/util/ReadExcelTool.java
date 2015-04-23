package com.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.admin.domain.UploadExcel;

import jxl.Sheet;
import jxl.Workbook;

// 读取excel表，然后把信息存入excel对象里，返回一个对象集合
public class ReadExcelTool {
	
	public List<UploadExcel> readExcel(ArrayList<String> al) {
		
		List<UploadExcel> ls = new ArrayList();
		
		jxl.Workbook rwb = null;
		
		int length = al.size()-1;
		String path = (String) al.get(length);
		
		File file = new File(path);
		
		try {
			
			InputStream is = new FileInputStream(file);
			rwb = Workbook.getWorkbook(is); 
			Sheet sheet = rwb.getSheet(0);
			int columnCount = sheet.getColumns();
			int rowCount = sheet.getRows();

			for (int i = 1; i < rowCount; i++) {

				UploadExcel uploadExcel = new UploadExcel();
				
				uploadExcel.setStudentId(sheet.getCell(0, i).getContents());
				uploadExcel.setStudentName(sheet.getCell(1, i).getContents());
				uploadExcel.setStudentPwd(sheet.getCell(2, i).getContents());
				uploadExcel.setStudentCardId(sheet.getCell(3, i).getContents());
				uploadExcel.setStudentClass(sheet.getCell(4, i).getContents());
				uploadExcel.setStudentTel(sheet.getCell(5, i).getContents());

				ls.add(uploadExcel);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// rwb.close();
		}
		return ls;
	}

}

