package com.cqupt.dormitory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;

@Repository
public class AreaDaoImpl extends BaseDaoSupport implements AreaDao {

	@Override
	public List<Area> findAllArea() {
		return getSqlSession().selectList(Area.class.getName()+".find_all_area");
	}

	@Override
	public Area addArea(Area a) {
		if(!isAreaNameExist(a.getName())){
			getSqlSession().insert(Area.class.getName()+".add",a);
		}
		return findAreaByAreaName(a.getName());
	}

	@Override
	public boolean isAreaNameExist(String areaName) {
		Area a = getSqlSession().selectOne(Area.class.getName()+".find_area_by_area_name",areaName);
		if(a != null){
			return true;
		}
		return false;
	}

	@Override
	public Area findAreaByAreaName(String areaName) {
		return getSqlSession().selectOne(Area.class.getName()+".find_area_by_area_name",areaName);
	}
	
	
	
}
