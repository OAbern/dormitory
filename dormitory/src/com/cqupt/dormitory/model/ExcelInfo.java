package com.cqupt.dormitory.model;

/**
 * 辅导员申请寝室调换上传的Excel表
 * @author Bern
 *
 */
public class ExcelInfo {
	private int id;		//excel的逻辑id
	private int status;		//excel审核状态，1表示未审核，2表示审核通过，3表示审核失败
	
	private String name;		//excel名字
	private String path;		//excel的存储路径
	
	private Admin approvedAdmin;		//审核的管理员
	private Teacher submitTeacher;		//提交的教师
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Admin getApprovedAdmin() {
		return approvedAdmin;
	}
	public void setApprovedAdmin(Admin approvedAdmin) {
		this.approvedAdmin = approvedAdmin;
	}
	public Teacher getSubmitTeacher() {
		return submitTeacher;
	}
	public void setSubmitTeacher(Teacher submitTeacher) {
		this.submitTeacher = submitTeacher;
	}
	
	
}
