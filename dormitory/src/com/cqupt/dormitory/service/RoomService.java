package com.cqupt.dormitory.service;

import java.util.List;

import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.vo.RoomInFloor;

public interface RoomService {
	/**
	 * findRoomInFloorForView 
	 * 	查询出VO 直接展示所有楼层和房间的所有信息.
	 * @param buildingNum
	 * @return 
	 *RoomInFloor
	 * @exception 
	 * @since  1.0.0
	 */
	public RoomInFloor findRoomInFloorForView(String buildingNum);
	
	/**
	 * findEmptyRoomInFloorForView 
	 * 		根据楼栋信息来查找空寝室号.
	 * @param buildingNum
	 * @return 
	 *RoomInFloor
	 * @exception 
	 * @since  1.0.0
	 */
	public RoomInFloor findEmptyRoomInFloorForView(String buildingNum);
	
	/**
	 * findAllCategoryOfRoom 查找现成的所有寝室类型 (数据库里面已有的)
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findAllCategoryOfRoom();
	
	/**
	 * findAllCostOfRoom 查找所有的资费类型 (数据库里面已有的)
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findAllCostOfRoom();
	
	
	/**
	 * updateCategoryAndCost 更改类型 还有收费标准
	 * @param totalBed
	 * @param cost
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean updateCategoryAndCost(int totalBed,int cost,String buildingNum,String floorNum);
	
	/**
	 * udpateRoom 更新还有修改寝室信息.
	 * 		但是先要检验已经入住的人数是否比现在修改的要小.如果不是的话不能修改.
	 * @param r
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean udpateRoom(Room r);
	
	/**
	 * delRoomNumOfStudent 退宿
	 * 		根据多个学号将学生的room_id设为空
	 * @param studentIds
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean delRoomNumOfStudent(String studentIds[]);
	
	/**
	 * delRoomNumOfStudent 退宿
	 * 			根据一个学号将学生的room_id设为空
	 * @param studentId
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean delRoomNumOfStudent(String studentId);

	/**
	 * addRoom 添加房间
	 * @param buildingNum
	 * @param floorNum
	 * @param cata
	 * @param fee
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean addRoom(String buildingNum, String floorNum, int cata,int fee);

	/**
	 * delRoom 根据房间号查询
	 * @param roomNum
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean delRoom(String roomNum);

	/**
	 * findAllPersonInRoom 查找住在一个寝室的学生
	 * @param roomNum
	 * @return 
	 *List<Student>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Student> findAllPersonInRoom(String roomNum);

}
