package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.service.AreaBulidingService;
import com.cqupt.dormitory.utils.JSONUtils;


@Controller
@RequestMapping("/areaInfo")
public class AreaAndBulidingController {
	@Resource(name="areaBuildingService")
	private AreaBulidingService areaBuildingService;
	
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

}
