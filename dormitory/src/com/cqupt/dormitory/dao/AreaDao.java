package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;

public interface AreaDao {
	/**
	 * findAllArea 查找所有的区域的信息
	 * @return 
	 *List<Area>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Area> findAllArea();
		
	/**
	 * isAreaNameExist 判断是否存在相同的名称 如果有的话就不让添加.直接使用.
	 * @param areaName
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean isAreaNameExist(String areaName);
	
	/**
	 * addArea 先判断是否存在在添加
	 * @param a
	 * @return 
	 *Area
	 * @exception 
	 */
	public Area addArea(Area a);
	
	/**
	 * findAreaByAreaName 根据名称查找
	 * @param areaName
	 * @return 
	 *Area
	 * @exception 
	 * @since  1.0.0
	 */
	public Area findAreaByAreaName(String areaName);
}
