package com.cqupt.dormitory.model;
/**
 * 楼层信息类
 * @author Bern
 *
 */
public class Floor {
	private int id;	//逻辑主键，自增id
	private String floorNum;	//楼层id，业务主键，复合形式（1901）
	private Building building;	//所属楼栋
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFloorNum() {
		return floorNum;
	}
	public void setFloorNum(String floorNum) {
		this.floorNum = floorNum;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	
}
