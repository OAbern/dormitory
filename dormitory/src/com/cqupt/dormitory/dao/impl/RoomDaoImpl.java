package com.cqupt.dormitory.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.model.Student;
/**
 * 处理房间信息的DAO层实现类
 * @author Bern
 *
 */
@Repository
public class RoomDaoImpl extends BaseDaoSupport implements RoomDao {

	@Override
	public Room findByRoomNum(String roomId) {
		Room room = getSqlSession().selectOne("com.cqupt.dormitory.model.Room.findByRoomId", roomId);
		return room;
	}

	@Override
	public List<Room> findAllRoomByBuildingNumAndFloor(String buildingNum,String floorNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		buildingNum += "%";
		map.put("buildingNum", buildingNum);
		map.put("floorNum", floorNum);
		return getSqlSession().selectList(Room.class.getName()+".find_all_room_by_buildingnum",map);
	}

	@Override
	public List<String> findAllCategoryOfRoom() {
		return getSqlSession().selectList(Room.class.getName()+".find_all_category_room");
	}

	@Override
	public List<String> findAllCostOfRoom() {
		return getSqlSession().selectList(Room.class.getName()+".find_all_cost_room");
	}

	@Override
	public void updateRoom(List<Integer> floorIds, String fee, String cata) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("floorIds", floorIds);
		map.put("fee", fee);
		map.put("cata", cata);
		getSqlSession().update(Room.class.getName()+".update_room_by_floorids",map);
	}	

	@Override
	public void updateRoom(Room r) {
		getSqlSession().update(Room.class.getName()+".update",r);
	}

	@Override
	public int findCurrentFloorNum(int floorId) {
		return getSqlSession().selectOne(Room.class.getName()+".find_current_room",floorId);
	}

	@Override
	public void add(Room r) {
		getSqlSession().insert(Room.class.getName()+".add_room",r);
	}

	@Override
	public void delRoom(String roomNum) {
		getSqlSession().delete(Room.class.getName()+".delete_room",roomNum);
	}

	@Override
	public List<Student> findAllPersonInRoom(String roomNum) {
		return getSqlSession().selectList(Room.class.getName()+".find_all_person_room",roomNum);
	}

	@Override
	public void updateStudentRoom(String studentNum, int roomId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("studentNum", studentNum);
		map.put("roomId", roomId);
		getSqlSession().update(Room.class.getName()+".update_student_room_id",map);
	}

}
