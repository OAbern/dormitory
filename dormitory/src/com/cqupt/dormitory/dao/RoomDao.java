package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Room;

/**
 * 处理房间信息的DAO层接口
 * @author Bern
 *
 */
public interface RoomDao {
	/**
	 * 根据房间Id查找房间
	 * @param roomId 房间id(复合型)
	 * @return	相应的房间
	 */
	public Room findByRoomId(String roomId);
	
	
	/**
	 * findAllRoomByBuildingNum 
	 * 	根据buildingNum来查找所有的room 对象 
	 * @param buildingNum
	 * @return 
	 *List<Room>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Room> findAllRoomByBuildingNum(String buildingNum);

	/**
	 * findAllCategoryOfRoom 返回所有的寝室类型
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findAllCategoryOfRoom();

	/**
	 * findAllCostOfRoom 查找所有的费用标准
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findAllCostOfRoom();
}
