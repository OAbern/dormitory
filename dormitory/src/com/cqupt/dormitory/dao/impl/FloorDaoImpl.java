package com.cqupt.dormitory.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.FloorDao;
import com.cqupt.dormitory.model.Floor;

@Repository
public class FloorDaoImpl extends BaseDaoSupport implements FloorDao {
	@Override
	public List<Floor> findFloorByBuildingNum(String buildingNum) {
		return getSqlSession().selectList(Floor.class.getName()+".find_floor_by_buildingnum",buildingNum);
	}

	@Override
	public int findFloorId(String buildingNum, String floorNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bn", buildingNum);
		map.put("fn", floorNum);
		return getSqlSession().selectOne(Floor.class.getName()+".find_floorid",map);
	}

	@Override
	public void delFloor(int floorId) {
		getSqlSession().delete(Floor.class.getName()+".del_floor",floorId);
	}

}
