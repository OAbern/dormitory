package com.cqupt.dormitory.controller;

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
}
