package com.cqupt.dormitory.vo;

import java.util.List;

/**
 * AreaBuilding
 * 
 * 加载楼栋信息的树结构   
 *
 * 2014年11月24日 下午9:01:28
 * 
 * @version 1.0.0
 * @author hhy
 */
public class AreaBuilding {
	public String area; 
	public List<String> buildingid;
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public List<String> getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(List<String> buildingid) {
		this.buildingid = buildingid;
	}
	
}
