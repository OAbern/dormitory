package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.FloorService;
import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.RoomInFloor;

@Controller
@RequestMapping("/roomInfo")
public class RoomAndFloorController {
	@Resource
	private RoomService roomService;
	@Resource
	private FloorService floorService;
	
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

	/**
	 * findRoomCata 查询所有住宿信息
	 * @param response 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/findRoomCata")
	public void findRoomCata(HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("cata", roomService.findAllCategoryOfRoom());
		JSONUtils.toJSON(map, response);
	}
	
	/**
	 * findRoomFee 查询所有的收费信息
	 * @param response 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/findRoomFee")
	public void findRoomFee(HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fee", roomService.findAllCostOfRoom());
		JSONUtils.toJSON(map, response);
	}
	
	/**
	 * addRoom 单独添加一个房间.
	 * @param buildingNum
	 * @param floorNum
	 * @param cata
	 * @param fee
	 * @param response 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/addRoom")
	public void addRoom(String buildingNum, String floorNum,int cata,int fee,HttpServletResponse response){
		/*System.out.println(buildingNum);
		System.out.println(floorNum);
		System.out.println(cata);
		System.out.println(fee);*/
		boolean b = roomService.addRoom(buildingNum,floorNum,cata,fee);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/updateFloor")
	public void updateFloor(String buildingNum, String floorNum,int cata,int fee,HttpServletResponse response){
		/*System.out.println(buildingNum);
		System.out.println(floorNum);
		System.out.println(cata);
		System.out.println(fee);*/
		boolean b= floorService.updateFloor(buildingNum, floorNum, cata, fee);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/updateRoom")
	public void updateRoom(String roomNum,int cata,int fee,HttpServletResponse response){
		/*System.out.println(roomNum);
		System.out.println(cata);
		System.out.println(fee);*/
		
		Room r =new Room();
		r.setRoomNum(roomNum);
		r.setCost(fee);
		r.setTotalBed(cata);
		boolean b= roomService.udpateRoom(r);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/delFloor")
	public void delFloor(String buildingNum,@RequestParam("floorNum[]")String[] floorNum,HttpServletResponse response){
		System.out.println(buildingNum);
		System.out.println(floorNum);
		boolean b = floorService.delFloor(buildingNum,floorNum);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/delRoom")
	public void delRoom(String roomNum,HttpServletResponse response){
		System.out.println(roomNum);
		boolean b = roomService.delRoom(roomNum);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/findAllPersonInRoom")
	public void findAllPersonInRoom(String roomNum,HttpServletResponse response){
		System.out.println(roomNum);
		List<Student> students = roomService.findAllPersonInRoom(roomNum);
		JSONUtils.toJSON(students, response);
	}
	
}
