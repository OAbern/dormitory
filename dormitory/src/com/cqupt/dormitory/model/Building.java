package com.cqupt.dormitory.model;
/**
 * 楼栋信息类
 * @author Bern
 *
 */
public class Building {
	private int id;	//逻辑主键，自增id
	private String buildingId;	//楼栋id
	private Area area;	//所属区域
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	
}
