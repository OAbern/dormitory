package com.cqupt.dormitory.model;

import java.util.Date;

/**
 * 学生基本类
 * @author Bern
 *
 */
public class Student {
	
	private int id;	//逻辑主键 自增id	
	private int sex;	//性别 ：0为男,1为女	
	private int liveSatus;	//住宿状态：在住:0,退宿：1，外宿：2	
	
	private String academy;	//学院	
	private String grade;	//年级	
	private String classNum;	//班级号	
	private String major;	//专业	
	private String stuNum;	//学号	
	private String name;	//姓名	
	private String birthPlace;	//籍贯	
	private String nation;	//民族	
	private String identity;	//身份证	
	private String phone;	//电话
	private String outAddress;	//外住地点
	
	private Date birth;	//生日
	private Date inTime;	//入住时间
	private Date outTime;	//外住时间
	
	private Teacher teacher;	//辅导员
	private Room room;	//所住房间
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getLiveSatus() {
		return liveSatus;
	}
	public void setLiveSatus(int liveSatus) {
		this.liveSatus = liveSatus;
	}
	public String getAcademy() {
		return academy;
	}
	public void setAcademy(String academy) {
		this.academy = academy;
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
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getStuNum() {
		return stuNum;
	}
	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOutAddress() {
		return outAddress;
	}
	public void setOutAddress(String outAddress) {
		this.outAddress = outAddress;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
}
