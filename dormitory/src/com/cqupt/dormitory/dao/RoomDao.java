package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.model.Student;

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
	public Room findByRoomNum(String roomId);
	
	
	/**
	 * findAllRoomByBuildingNum 
	 * 	根据buildingNum来查找所有的room 对象 
	 * @param buildingNum
	 * @return 
	 *List<Room>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Room> findAllRoomByBuildingNumAndFloor(String buildingNum,String floorNum);

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
	
	/**
	 * updateRoom 更新房间的基本信息根据楼层的id来更新
	 * @param floorIds
	 * @param fee
	 * @param cata 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateRoom(List<Integer> floorIds , String fee, String cata);
	
	
	/**
	 * updateRoom 更新某个寝室
	 * @param r 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateRoom(Room r);

	/**
	 * findCurrentFloorNum 查找最大的数量值
	 * @param floorId
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 */
	public int findCurrentFloorNum(int floorId);

	/**
	 * add 添加room的方法
	 * @param r 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void add(Room r);

	/**
	 * delRoom 删除房间
	 * @param roomNum 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delRoom(String roomNum);

	/**
	 * findAllPersonInRoom 查找所有的
	 * @param roomNum
	 * @return 
	 *List<Student>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Student> findAllPersonInRoom(String roomNum);

	/**
	 * updateStudentRoom 更新一下这个家伙的room_id
	 * @param studentNum
	 * @param roomId
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateStudentRoom(String studentNum, int roomId);
}
