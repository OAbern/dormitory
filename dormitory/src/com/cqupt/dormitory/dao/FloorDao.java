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
	
	/**
	 * findFloorId 根据楼层号,根据楼栋信息来找到相应的楼层外键
	 * @param buildingNum
	 * @param floorNum
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 */
	public int findFloorId(String buildingNum,String floorNum);

	/**
	 * delFloor 根据id删除楼层
	 * @param floorId 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delFloor(int floorId);
	
	/**
	 * isPeopleInFloor 判断是否有人存在.
	 * @param floorId
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean isPeopleInFloor(int floorId);
	
	/**
	 * addFloor 增加一个楼层
	 * @param f
	 * @return 
	 *Floor
	 * @exception 
	 */
	void addFloor(Floor f);
	
	/**
	 * isFloorExist 判断这个楼层是否中存在
	 * @param floorNum
	 * @param buildingNum
	 * @return 
	 *boolean
	 * @exception 
	 */
	boolean isFloorExist(String floorNum,String buildingNum);
	
	/**
	 * findFloorByNum  
	 * 			根据条件查找
	 * @param floorNum
	 * @param buildingNum
	 * @return 
	 *Floor
	 * @exception
	 */
	Floor findFloorByNum(String floorNum,String buildingNum);
}
