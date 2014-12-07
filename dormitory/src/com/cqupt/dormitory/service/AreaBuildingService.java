package com.cqupt.dormitory.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;
import com.cqupt.dormitory.vo.AreaBuilding;
import com.cqupt.dormitory.vo.ResultMessage;

public interface AreaBuildingService {
	
	/**
	 * findAllArea 查找所有的地区信息
	 * @return 
	 *List<Area>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	public List<Area> findAllArea();
	
	/**
	 * findBuildByAreaName 根据区域来找到下面楼栋的数据
	 * @param areaName
	 * @return 
	 *List<Building>
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	public List<Building> findBuildByAreaName(String areaName);
	
	/**
	 * findAllAreaAndBuilding 查询出vo对象 用来展示tree
	 * @return 
	 *List<AreaBuilding>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<AreaBuilding> findAllAreaAndBuilding();
	
	
	
	
	
	/**
	 * findBuildingBySex 根据性别来找到相应的楼栋号
	 * @param sex
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findBuildingBySex(String sex);
	
	/**
	 * findFloorByBuildingNum 根据楼栋号来找到所有的楼层
	 * @param buildingNum
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findFloorByBuildingNum(String buildingNum);
	
	/**
	 * findBuildingAndAreaVoBySex 根据性别来直接查询学生的信息
	 * @param sex
	 * @return 
	 *Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public Map<String,Object> findBuildingAndAreaVoBySex(String sex);
	
	/**
	 * updateBuildingMessage 修改楼栋信息 
	 * 
	 * 1-修改成功
	 * 2-修改部分成功
	 * 0-修改不成功
	 * 
	 * @param sex
	 * @param fee
	 * @param cata
	 * @return 
	 *int
	 * @exception 
	 * @since  1.0.0
	 */
	public int updateBuildingMessage(String buildingNum,String sex,String fee,String cata,String area);

	/**
	 * findBuildingBySexAndArea 级联查询
	 * @param area
	 * @param sex
	 * @return 
	 *List<Building>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Building> findBuildingBySexAndArea(String area, String sex);

	/**
	 * findBuildingByStudents 根据一大坨人名来找到他自己的楼栋号
	 * @param studentNums
	 * @return 
	 *List<String>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<String> findBuildingByStudents(List<String> studentNums);

	/**
	 * addBuildingAndFloor 导入excel表格.
	 * @param file
	 * @return 
	 *Map<String,Object>
	 * @exception 
	 * @since  1.0.0
	 */
	public ResultMessage addBuildingAndFloor(MultipartFile file);
	
	/**
	 * deleteBuilding 删除楼栋
	 * @param building
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean deleteBuilding(String building);
	
}
