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

}
