package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.service.AreaBuildingService;
import com.cqupt.dormitory.utils.JSONUtils;


@Controller
@RequestMapping("/areaInfo")
public class AreaAndBulidingController {
	@Resource(name="areaBuildingService")
	private AreaBuildingService areaBuildingService;
	
	@RequestMapping("/findAreaAndBuilding")
	public void findAreaAndBuilding(HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", areaBuildingService.findAllAreaAndBuilding());
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/findBuildingByManyField")
	public void findBuilding(HttpServletResponse response){
		List<String> shit = new ArrayList<String>();
		shit.add("15");
		shit.add("1");
		shit.add("18");
		JSONUtils.toJSON(shit, response);
	}
	
	
	@RequestMapping("/findBuildingNumBySex")
	public void findBuildingNumBySex(HttpServletResponse response){
		List<String> shit = new ArrayList<String>();
		shit.add("15");
		shit.add("1");
		shit.add("18");
		JSONUtils.toJSON(shit, response);
	}
	
	/**
	 * 通过Excel批量添加宿舍信息 
	 */
	@RequestMapping("/addBuildingByExcel")
	public void addBuildingByExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) { 
		//TODO
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
	public void updateBuilding(String buildingId,String cata,String fee,String sex,HttpServletResponse response){
		boolean  result = areaBuildingService.updateBuildingMessage(buildingId, sex, fee, cata);
		Map<String,Object> map = new HashMap<String,Object>();
		if(result){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	
	@RequestMapping("/findBuildingEmptyBed")
	public void findBuildingEmptyBed(String sex,HttpServletResponse response){
		Map<String,Object> map  = areaBuildingService.findBuildingAndAreaVoBySex(sex);
		JSONUtils.toJSON(map, response);
	}

}
