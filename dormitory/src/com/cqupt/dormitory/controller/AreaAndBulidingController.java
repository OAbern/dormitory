package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Building;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.AreaBuildingService;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.Condition;


@Controller
@RequestMapping("/areaInfo")
public class AreaAndBulidingController {
	@Resource(name="areaBuildingService")
	private AreaBuildingService areaBuildingService;
	
	@Resource(name="studentInfoServiceImpl")
	private StudentInfoService studentInfoService;
	
	/**
	 * findAreaAndBuilding 查询出vo对象 用来展示tree
	 * @param response 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/findAreaAndBuilding")
	public void findAreaAndBuilding(HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", areaBuildingService.findAllAreaAndBuilding());
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/findAllArea")
	public void findAllArea(HttpServletResponse response){
		List<Area> areaList = areaBuildingService.findAllArea();
		List<String> s = new ArrayList<String>();
		for(Area a : areaList){
			s.add(a.getName());
		}
		JSONUtils.toJSON(s, response);
	}
	
	@RequestMapping("/findBuildingByManyField")
	public void findBuilding(@ModelAttribute Condition condition,HttpServletResponse response){
		List<Student> students = studentInfoService.findStudentByCondition(condition);
		List<String> studentNums = new ArrayList<String>();
		for(Student s:students){
			studentNums.add(s.getStuNum());
		}
		if(studentNums.size()>0){
			List<String> buildingNums = areaBuildingService.findBuildingByStudents(studentNums);
			JSONUtils.toJSON(buildingNums, response);
		}
		return ;
	}
	
	
	
	
	@RequestMapping("/findBuildingNumBySex")
	public void findBuildingNumBySex(String sex,String area,HttpServletResponse response){
		if(area==null || "".equals(area)){
			area = "%";
		}
		if(sex==null || "".equals(sex)){
			sex = "%";
		}
		
		List<Building> bList = areaBuildingService.findBuildingBySexAndArea(area,sex);
		List<String> s = new ArrayList<String>();
		for(Building b : bList){
			s.add(b.getBuildingNum());
		}
		JSONUtils.toJSON(s, response);
	}
	
	/**
	 * 通过Excel批量添加宿舍信息 
	 */
	@RequestMapping("/addBuildingByExcel")
	public void addBuildingByExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) { 
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getContentType());
		JSONUtils.toJSON(areaBuildingService.addBuildingAndFloor(file), response);
	}

	
	/**
	 * updateBuilding 修改整栋楼的资费
	 * @param buildingId
	 * @param cata
	 * @param fee
	 * @param sex
	 * @param response 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/updateBuilding")
	public void updateBuilding(String area,String buildingId,String cata,String fee,String sex,HttpServletResponse response){
		int  result = areaBuildingService.updateBuildingMessage(buildingId, sex, fee, cata,area);
		Map<String,Object> map = new HashMap<String,Object>();
		if(result==1){
			map.put("status", 1);
		}else if(result ==2){
			map.put("status", 2);
		}else{
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	
	@RequestMapping("/findBuildingEmptyBed")
	public void findBuildingEmptyBed(String sex,HttpServletResponse response){
		Map<String,Object> map  = areaBuildingService.findBuildingAndAreaVoBySex(sex);
		JSONUtils.toJSON(map, response);
	}

	@RequestMapping("/delBuilding")
	public void delBuilding(String buildingId,HttpServletResponse response){
		boolean b = areaBuildingService.deleteBuilding(buildingId);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/getBuildingTotalBed")
	public void getBuildingTotalBed(String area,HttpServletResponse response){
		if(area==null || "".equals(area)){
			area = "%";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", areaBuildingService.getBuildingBed(area, false));
		JSONUtils.toJSON(map, response);
	}
	
	
	@RequestMapping("/getBuildingEmptyBed")
	public void getBuildingEmptyBed(String area,HttpServletResponse response){
		if(area==null || "".equals(area)){
			area = "%";
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("data", areaBuildingService.getBuildingBed(area, true));
		JSONUtils.toJSON(map, response);
	}
	
}
