package com.cqupt.dormitory.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.dao.BuildingDao;
import com.cqupt.dormitory.dao.FloorDao;
import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;
import com.cqupt.dormitory.model.Floor;
import com.cqupt.dormitory.service.AreaBulidingService;
import com.cqupt.dormitory.vo.AreaBuilding;

@Service("areaBuildingService")
public class AreaBulidingServiceImpl implements AreaBulidingService {
	@Resource
	private AreaDao areaDao;
	@Resource
	private BuildingDao buildDao;
	@Resource
	private FloorDao floorDao;
	
	@Override
	public List<Area> findAllArea() {
		return areaDao.findAllArea();
	}

	@Override
	public List<Building> findBuildByAreaName(String areaName) {
		return buildDao.findBuildByAreaName(areaName);
	}

	@Override
	public List<AreaBuilding> findAllAreaAndBuilding() {
		List<AreaBuilding> list = new ArrayList<AreaBuilding>();
		AreaBuilding areaBuilding = null;
		for(Area a : this.findAllArea()){
			areaBuilding =  new AreaBuilding();
			areaBuilding.setArea(a.getName());
			List<Building> blist = this.findBuildByAreaName(a.getName());
			areaBuilding.setBuildingid(this.convertToStringList(blist));
			list.add(areaBuilding);
		}
		return list;
	}

	/**
	 * convertToStringList 简单的转为一个string的list
	 * @param list
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	private List<String> convertToStringList(List<Building> list){
		List<String> stringL = new ArrayList<String>();
		for(Building b : list){
			stringL.add(b.getBuildingNum());
		}
		return stringL;
	}

	@Override
	public List<String> findBuildingBySex(String sex) {
		return buildDao.findBuildingBySex(sex);
	}

	@Override
	public List<String> findFloorByBuildingNum(String buildingNum) {
		List<Floor> floors =  floorDao.findFloorByBuildingNum(buildingNum);
		List<String> floorString = new ArrayList<String>();
		for(Floor f : floors){
			floorString.add(f.getFloorNum());
		}
		return floorString;
	}
}
