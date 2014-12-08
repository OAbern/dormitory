package com.cqupt.dormitory.vo;
/**
 * 学生在各楼栋分布的视图对象
 * @author Bern
 *
 */
public class StudentBuildingDistribution {
	private String building;		//楼栋号
	private int num;	//与楼栋号对应的学生人数
	
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
