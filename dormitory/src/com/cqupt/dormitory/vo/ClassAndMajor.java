package com.cqupt.dormitory.vo;
/**
 * 班级专业信息类
 * @author Bern
 *
 */
public class ClassAndMajor {
	private String classNum;	//班级代号
	private int academyId;		//学院id
	private String academyName;		//学院名称
	private String education;	//学历
	private String major;		//专业
	
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public int getAcademyId() {
		return academyId;
	}
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
}
