package com.cqupt.dormitory.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.BuildingDao;
import com.cqupt.dormitory.model.Building;

@Repository
public class BuildingDaoImpl extends BaseDaoSupport implements BuildingDao {

	@Override
	public List<Building> findBuildByAreaName(String areaName) {
		return getSqlSession().selectList(Building.class.getName()+".find_building_by_areaname",areaName);
	}

	@Override
	public List<Building> findBuildingBySex(String sex) {
		return getSqlSession().selectList(Building.class.getName()+".find_building_by_sex",sex);
	}

	@Override
	public int findBuildingEmptyBed(int buildingId) {
		Integer totalBed = getSqlSession().selectOne(Building.class.getName()+".find_all_building_totalbed",buildingId);
		Integer emptyBed =  getSqlSession().selectOne(Building.class.getName()+".find_building_emptybed",buildingId);
		if(totalBed==null || emptyBed==null){
			return 0;
		}
		return totalBed-emptyBed;
	}

	@Override
	public void updateBuilding(Building b) {
		getSqlSession().update(Building.class.getName()+".update",b);
	}

	@Override
	public boolean isBuildingStayPerson(String buildingNum) {
		if(buildingNum.length()<2){
			buildingNum = "0"+buildingNum;
		}
		buildingNum += "%";
		Integer num = getSqlSession().selectOne(Building.class.getName()+".find_building_person",buildingNum);
		if(num<1){
			return true;
		}
		return false;
	}

	@Override
	public boolean isBuildingSexChange(String buildingNum,String sex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sex", sex);
		map.put("buildingNum", buildingNum);
		Integer num = getSqlSession().selectOne(Building.class.getName()+".is_change_sex",map);
		if(num<1){
			return false;
		}
		return true;
	}

	@Override
	public List<Building> findBuildingBySexAndArea(String area, String sex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sex", sex);
		map.put("area", area);
		return getSqlSession().selectList(Building.class.getName()+".find_building_by_sex_area",map);
	}

	@Override
	public List<String> findBuildingByStudents(List<String> studentNums) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("studentNums", studentNums);
		return getSqlSession().selectList(Building.class.getName()+".find_building_student_numbers",map);
	}

	@Override
	public Building addBuilding(Building b) {
		if(!isBuildingExist(b.getBuildingNum())){
			getSqlSession().insert(Building.class.getName()+".add",b);
		}
		//throw new RuntimeException("有重复的building");//添加
		return this.findBuildingByNum(b.getBuildingNum());
	}

	@Override
	public boolean isBuildingExist(String buildingNum) {
		Building b = getSqlSession().selectOne(Building.class.getName()+".find_building_by_building_name",buildingNum);
		if(b!= null){
			return true;
		}
		return false;
	}

	@Override
	public Building findBuildingByNum(String buildingNum) {
		return getSqlSession().selectOne(Building.class.getName()+".find_building_by_building_name",buildingNum);
	}

	@Override
	public void deleteBuilding(String building) {
		getSqlSession().delete(Building.class.getName()+".delete_building_by_buildingnum",building);
	}
	
}
