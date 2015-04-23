package com.admin.domain;

// excel产生的对象
public class UploadExcel {
	private int id;
	
	private String studentId;
	private String studentName;
	private String studentCardId;
	private String studentClass;
	private String studentTel;
	public String getStudentCardId() {
		return studentCardId;
	}

	public void setStudentCardId(String studentCardId) {
		this.studentCardId = studentCardId;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentTel() {
		return studentTel;
	}

	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentPwd() {
		return studentPwd;
	}

	public void setStudentPwd(String studentPwd) {
		this.studentPwd = studentPwd;
	}

	private String studentPwd;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
}
