package com.cqupt.dormitory.service;

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
}
