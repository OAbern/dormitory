package com.cqupt.dormitory.service;

import java.util.List;

import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;
import com.cqupt.dormitory.vo.AreaBuilding;

public interface AreaBulidingService {
	
	/**
	 * findAllArea 查找所有的地区信息
	 * @return 
	 *List<Area>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	public List<Area> findAllArea();
	
	/**
	 * findBuildByAreaName 根据区域来找到下面楼栋的数据
	 * @param areaName
	 * @return 
	 *List<Building>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	public List<Building> findBuildByAreaName(String areaName);
	
	/**
	 * findAllAreaAndBuilding 查询出vo对象
	 * @return 
	 *List<AreaBuilding>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<AreaBuilding> findAllAreaAndBuilding();
}
