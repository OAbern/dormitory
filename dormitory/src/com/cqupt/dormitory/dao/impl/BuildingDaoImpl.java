package com.cqupt.dormitory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.BuildingDao;
import com.cqupt.dormitory.model.Building;

@Repository
public class BuildingDaoImpl extends BaseDaoSupport implements BuildingDao {

	@Override
	public List<Building> findBuildByAreaName(String areaName) {
		return getSqlSession().selectList(Building.class.getName()+".find_building_by_areaname",areaName);
	}

}
