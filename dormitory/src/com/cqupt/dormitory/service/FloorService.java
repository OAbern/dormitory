package com.cqupt.dormitory.service;

public interface FloorService {
	/**
	 * updateFloor 更新一层楼下面的所有房间信息
	 * @param buildingNum
	 * @param floorNum
	 * @param cata
	 * @param fee
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean updateFloor(String buildingNum,String floorNum,int cata,int fee);

	/**
	 * delFloor数据库中要设置级联删除
	 * @param buildingNum
	 * @param floorNum
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean delFloor(String buildingNum, String[] floorNum);
}
