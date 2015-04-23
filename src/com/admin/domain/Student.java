package com.admin.domain;

// 存在数据里的student对象
public class Student {

	private String studentId;
	private String studentName;
	private String studentCardId;
	private String studentClass;
	private String studentTel;
	
	public Student(){
		
	}
	
	public Student(String studentId, String studentName, String studentPwd,String studentCardId,
			String studentClass, String studentTel) {
	
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentCardId = studentCardId;
		this.studentClass = studentClass;
		this.studentTel = studentTel;
		this.studentPwd = studentPwd;
	}

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
