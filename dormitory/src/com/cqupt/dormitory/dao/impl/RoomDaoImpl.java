package com.cqupt.dormitory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Room;
/**
 * 处理房间信息的DAO层实现类
 * @author Bern
 *
 */
@Repository
public class RoomDaoImpl extends BaseDaoSupport implements RoomDao {

	@Override
	public Room findByRoomId(String roomId) {
		Room room = getSqlSession().selectOne("com.cqupt.dormitory.model.Room.findByRoomId", roomId);
		return room;
	}

	@Override
	public List<Room> findAllRoomByBuildingNum(String buildingNum) {
		buildingNum += "%";
		return getSqlSession().selectList(Room.class.getName()+".find_all_room_by_buildingnum",buildingNum);
	}

	@Override
	public List<String> findAllCategoryOfRoom() {
		return getSqlSession().selectList(Room.class.getName()+".find_all_category_room");
	}

	@Override
	public List<String> findAllCostOfRoom() {
		return getSqlSession().selectList(Room.class.getName()+".find_all_cost_room");
	}

}
