package com.cqupt.dormitory.model;
/**
 * 区域类
 * @author Bern
 *
 */
public class Area {
	private int id;	//逻辑主键，自增id
	private String name;	//区域名字
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
