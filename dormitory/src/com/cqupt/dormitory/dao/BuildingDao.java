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
	List<Building> findBuildingBySex(String sex);
	
	/**
	 * findBuildingEmptyBed 根据buildingId来找到这栋寝室的空床位
	 * @param buildingId
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 */
	int findBuildingEmptyBed(int buildingId);
	
	/**
	 * updateBuilding 修改楼栋信息
	 * @param b 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void updateBuilding(Building b);
	
	/**
	 * isBuildingStayPerson 判断是否有人住
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean isBuildingStayPerson(String buildingNum);
	
	/**
	 * isBuildingSexChange 判断楼栋的性别是否改变
	 * @param sex
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean isBuildingSexChange(String buildingNum,String sex);
}
