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
	
	
	public boolean isPeopleInFloor(int floorId){
		int result = getSqlSession().selectOne(Floor.class.getName()+".find_floor_people",floorId);
		if(result<1){
			return true;
		}
		return false;
	}

	@Override
	public void addFloor(Floor f) {
		getSqlSession().insert(Floor.class.getName()+".add",f);
	}

	@Override
	public boolean isFloorExist(String floorNum, String buildingNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("floorNum", floorNum);
		map.put("buildingNum", buildingNum);
		Floor f = getSqlSession().selectOne(Floor.class.getName()+".find_floor_by_floornum_buildingnum",map);
		if(f!=null){
			return true;
		}
		return false;
	}

	@Override
	public Floor findFloorByNum(String floorNum, String buildingNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("floorNum", floorNum);
		map.put("buildingNum", buildingNum);
		return getSqlSession().selectOne(Floor.class.getName()+".find_floor_by_floornum_buildingnum",map);
	}

}
