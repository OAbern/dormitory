package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Floor;

public interface FloorDao {
	/**
	 * findFloorByBuildingNum 根据楼栋的编号来查找所有的相应楼层的信息.
	 * @param buildingNum
	 * @return 
	 *List<Floor>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Floor> findFloorByBuildingNum(String buildingNum);
}
