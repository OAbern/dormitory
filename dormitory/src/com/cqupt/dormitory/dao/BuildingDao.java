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

	/**
	 * findBuildingBySexAndArea 级联查询
	 * @param area
	 * @param sex
	 * @return 
	 *List<Building>
	 * @exception 
	 * @since  1.0.0
	 */
	List<Building> findBuildingBySexAndArea(String area, String sex);

	/**
	 * findBuildingByStudents 根据一大坨人名来找到他自己的楼栋号
	 * @param studentNums
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	List<String> findBuildingByStudents(List<String> studentNums);
	
	/**
	 * addBuilding 添加一个building的基础类.
	 * 		如果不存在就直接添加,否则就直接查找相应的类返回.
	 * 		总之就是会返回一个相应的类
	 * @param b
	 * @return 
	 *Building
	 * @exception 
	 * @since  1.0.0
	 */
	Building addBuilding(Building b);
	
	/**
	 * isBuildingExist 判断是否存在building 根据楼栋的号码
	 * @param buildingNum
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	boolean isBuildingExist(String buildingNum);
	
	/**
	 * findBuildingByNum 查找building 根据楼栋的号码
	 * @param buildingNum
	 * @return 
	 *Building
	 * @exception 
	 * @since  1.0.0
	 */
	Building findBuildingByNum(String buildingNum);

	/**
	 * deleteBuilding 删除楼栋
	 * @param building 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	void deleteBuilding(String building);
}
