package com.cqupt.dormitory.model;

/**
 * 管理员类
 * @author Bern
 *
 */
public class Admin {
	private int id;	//逻辑主键，自增id
	private int sex;	//性别：0为男，1为女
	
	private String adminNum;	//教工号（登录用户名）
	private String password;	//密码
	private String name;	//名字
	private String post;	//角色
	
	
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
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	
}
