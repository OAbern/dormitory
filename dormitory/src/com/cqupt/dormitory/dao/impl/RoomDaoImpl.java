package com.cqupt.dormitory.dao.impl;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Room;

@Repository
public class RoomDaoImpl extends BaseDaoSupport implements RoomDao {

	@Override
	public Room findByRoomId(String roomId) {
		Room room = getSqlSession().selectOne("com.cqupt.dormitory.model.Room.findByRoomId", roomId);
		return room;
	}

}
