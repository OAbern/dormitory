package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Building;

public interface BuildingDao {

	/**
	 * findBuildByAreaName 根据区域的名字来寻找楼栋的信息
	 * @param areaName
	 * @return 
	 *List<Building>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	List<Building> findBuildByAreaName(String areaName);
}
