package com.cqupt.dormitory.dao;

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
	
}
