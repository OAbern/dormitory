package com.cqupt.dormitory.vo;
/**
 * 筛选学生信息的条件类VO
 * @author Bern
 *
 */
public class Condition {
	private String target;	//要筛选的字段
	private String academy;		//筛选的学院
	private String education;	//筛选的学历
	private String major;	//筛选的专业
	private String grade;	//筛选的年级
	private String sex; 	//性别
	private String classNum;	//班级
	private String buildingNum;		//楼栋号
	
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(String buildingNum) {
		this.buildingNum = buildingNum;
	}
	
}
