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

	/**
	 * findBuildingBySex 根据性别来查找相应的
	 * @param sex
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	List<String> findBuildingBySex(String sex);
}
