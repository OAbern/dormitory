package com.cqupt.dormitory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;

@Repository("areaDao")
public class AreaDaoImpl extends BaseDaoSupport implements AreaDao {

	@Override
	public List<Area> findAllArea() {
		return getSqlSession().selectList(Area.class.getName()+".find_all_area");
	}

	

}
