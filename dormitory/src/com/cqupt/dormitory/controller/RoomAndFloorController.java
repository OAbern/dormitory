package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.RoomInFloor;

@Controller
@RequestMapping("/roomInfo")
public class RoomAndFloorController {
	@Resource
	private RoomService roomService;
	
	@RequestMapping("/findAllRoom")
	public void findAllRoom(String buildingNum,HttpServletResponse response){
		RoomInFloor r = roomService.findEmptyRoomInFloorForView(buildingNum);
		JSONUtils.toJSON(r, response);
	}
	

	
	
	@RequestMapping("/findFloorNumByBuildingNum")
	public void findFloorNumByBuildingNum(HttpServletResponse response){
		List<String> shit = new ArrayList<String>();
		shit.add("1");
		shit.add("2");
		shit.add("3");
		JSONUtils.toJSON(shit, response);
	}
	
	@RequestMapping("/findRoomNumByBuildingNumAndFloor")
	public void findRoomNumByBuildingNumAndFloor(HttpServletResponse response){
		List<String> shit = new ArrayList<String>();
		shit.add("190101");
		shit.add("190102");
		shit.add("190103");
		JSONUtils.toJSON(shit, response);
	}
	
}
