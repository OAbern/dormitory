package com.cqupt.dormitory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.dao.BuildingDao;
import com.cqupt.dormitory.dao.FloorDao;
import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;
import com.cqupt.dormitory.model.Floor;
import com.cqupt.dormitory.service.AreaBuildingService;
import com.cqupt.dormitory.vo.AreaBuilding;
import com.cqupt.dormitory.vo.AreaBuildingEmpty;
import com.cqupt.dormitory.vo.BuildingEmptyBed;

@Service("areaBuildingService")
public class AreaBuildingServiceImpl implements AreaBuildingService {
	@Resource
	private AreaDao areaDao;
	@Resource
	private BuildingDao buildDao;
	@Resource
	private FloorDao floorDao;
	@Resource
	private RoomDao	roomDao;
	
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
		List<Building> buildings =  buildDao.findBuildingBySex(sex);
		List<String> build = new ArrayList<String>();
		for(Building b : buildings){
			build.add(b.getBuildingNum());
		}
		return build;
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

	@Override
	public Map<String, Object> findBuildingAndAreaVoBySex(String sex) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<AreaBuildingEmpty> rows = new ArrayList<AreaBuildingEmpty>();
		List<Building> buildings = buildDao.findBuildingBySex(sex);
		String oldArea = "";
		AreaBuildingEmpty e = null;
		List<BuildingEmptyBed> buildingEmptyBed = new ArrayList<BuildingEmptyBed>();
		for(int i = 0;i<buildings.size();i++){
			Building b = buildings.get(i);
			if(i==0) oldArea = b.getArea().getName();
			
			if(!oldArea.equals(b.getArea().getName()) ){
				e = new AreaBuildingEmpty();
				e.setArea(oldArea);
				e.setBuildingEmptyBeds(buildingEmptyBed);
				rows.add(e);
				
				buildingEmptyBed =  null;
				buildingEmptyBed = new ArrayList<BuildingEmptyBed>();
				
				oldArea = b.getArea().getName();
			}
			
			BuildingEmptyBed be = new BuildingEmptyBed();
			be.setBuildingNum(b.getBuildingNum());
			be.setEmptyBed(buildDao.findBuildingEmptyBed(b.getId()));
			buildingEmptyBed.add(be);
			
			
			if(i+1>=buildings.size() ){
			    e = new AreaBuildingEmpty();
				e.setArea(b.getArea().getName());
				e.setBuildingEmptyBeds(buildingEmptyBed);
				buildingEmptyBed =  null;
				buildingEmptyBed = new ArrayList<BuildingEmptyBed>();
				rows.add(e);
			}
			
		}
		map.put("total", buildings.size());
		map.put("columnMax", rows.size());
		map.put("rows", rows);
		return map;
	}

	@Override
	public boolean updateBuildingMessage(String buildingNum,String sex, String fee, String cata) {
		
		//先要判断一下有没有人住 再修改
		if(buildDao.isBuildingStayPerson(buildingNum)){
				Building b = new Building();
				b.setBuildingNum(buildingNum);
				b.setSex(sex);
				buildDao.updateBuilding(b);
				List<Floor> floor = floorDao.findFloorByBuildingNum(buildingNum);
				List<Integer> floorsId = new ArrayList<Integer>();
				for(Floor f : floor){
					floorsId.add(f.getId());
				}
				roomDao.updateRoom(floorsId, fee, cata);
				return true;
		}else{
			if(buildDao.isBuildingSexChange(buildingNum, sex)){
				List<Floor> floor = floorDao.findFloorByBuildingNum(buildingNum);
				List<Integer> floorsId = new ArrayList<Integer>();
				for(Floor f : floor){
					floorsId.add(f.getId());
				}
				roomDao.updateRoom(floorsId, fee, null);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Building> findBuildingBySexAndArea(String area, String sex) {
		return buildDao.findBuildingBySexAndArea(area,sex);
	}

	@Override
	public List<String> findBuildingByStudents(List<String> studentNums) {
		return buildDao.findBuildingByStudents(studentNums);
	}
}
