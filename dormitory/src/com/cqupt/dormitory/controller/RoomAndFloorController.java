package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Floor;
import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.AreaBuildingService;
import com.cqupt.dormitory.service.FloorService;
import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.utils.SystemContext;
import com.cqupt.dormitory.vo.Condition;
import com.cqupt.dormitory.vo.ResultMessage;
import com.cqupt.dormitory.vo.RoomInFloor;

@Controller
@RequestMapping("/roomInfo")
public class RoomAndFloorController {
	@Resource
	private RoomService roomService;
	@Resource
	private FloorService floorService;
	@Resource
	private AreaBuildingService areaBuildingService;
	@Resource
	private RoomDao roomDao;
	@Resource
	private StudentInfoService studentInfoService;
	
	@RequestMapping("/findAllRoom")
	public void findAllRoom(String buildingNum,HttpServletResponse response){
		RoomInFloor r = roomService.findEmptyRoomInFloorForView(buildingNum);
		JSONUtils.toJSON(r, response);
	}
	
	
	/**
	 * findFloorNumByBuildingNum 查找某楼栋下面的所有floor
	 * @param response 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping("/findFloorNumByBuildingNum")
	public void findFloorNumByBuildingNum(String building,HttpServletResponse response){
		List<String> floors = areaBuildingService.findFloorByBuildingNum(building);
		JSONUtils.toJSON(floors, response);
	}
	
	@RequestMapping("/findRoomNumByBuildingNumAndFloor")
	public void findRoomNumByBuildingNumAndFloor(String building,String floor,HttpServletResponse response){
		if("".equals(floor) || floor==null){
			floor = "%";
		}
		if("".equals(building) || building == null){
			building = "%";
		}
		List<Room> rooms = roomDao.findAllRoomByBuildingNumAndFloor(building, floor);
		List<String> s = new ArrayList<String>();
		for(Room r : rooms){
			s.add(r.getRoomNum());
		}
		JSONUtils.toJSON(s, response);
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
	//	System.out.println(buildingNum);
	//	System.out.println(floorNum);
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
	//	System.out.println(roomNum);
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
	//	System.out.println(roomNum);
		List<Student> students = roomService.findAllPersonInRoom(roomNum);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", students.size());
		map.put("rows",students);
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/updateStudentRoom")
	public void updateStudentRoom(String studentNum,String roomNum,HttpServletResponse response){
		boolean b = roomService.updateChangeRoom(studentNum,roomNum);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	
	@RequestMapping("/findRoomDetail")
	public void findRoomDetail(String sex,String buildingNum,String floorNum,String roomNum,HttpServletResponse response){
		if(sex == null || "".equals(sex)){
			sex = "%";
		}
		
		if(buildingNum == null || "".equals(buildingNum)){
			buildingNum = "%";
		}
		
		if(floorNum == null || "".equals(floorNum)){
			floorNum = "%";
		}
		
		if(roomNum == null || "".equals(roomNum)){
			roomNum = "%";
		}
		
		List<Room> rooms = roomDao.findRoomByAnyField(sex,buildingNum,floorNum,roomNum);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", rooms);
		int total = SystemContext.getTotal();
		if(total <= 0) {
			total = rooms.size();
		}
		map.put("total",total);
		JSONUtils.toJSON(map, response);
	}
	
	@RequestMapping("/distributeRoom")
	public void distributeRoom(@ModelAttribute Condition condition,@RequestParam("building[]")String[] buildingNum,HttpServletResponse response){
		condition.setLivingStatus(3);
		List<Student> students = studentInfoService.findStudentByCondition(condition);
		List<String> studentNums = new ArrayList<String>();
		for(Student s:students){
			studentNums.add(s.getStuNum());
		}
		studentInfoService.updateOutOfRoom(studentNums);
		boolean b  =roomService.updateDistributeRoom(studentNums,buildingNum);
		ResultMessage rm = new ResultMessage();
		if(b){
			rm.setStatus(1);
			rm.setInfo("分配成功");
		}else {
			rm.setStatus(0);
			rm.setInfo("失败");
		}
		JSONUtils.toJSON(rm, response);
	}

	@RequestMapping("/updateRoomCostAndFee")
	public void updateRoomCostAndFee(@RequestBody Room room,HttpServletResponse response){
		boolean b= roomService.udpateRoom(room);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
	}
		
	@RequestMapping("/changeOneRoom")
	public void changeOneRoom(String stuNum,String room,HttpServletResponse response){
		
		boolean b = roomService.updateChangeRoom(stuNum,room);
		Map<String,Object> map = new HashMap<String,Object>();
		if(b){
			map.put("status", 1);
		}else {
			map.put("status", 0);
		}
		JSONUtils.toJSON(map, response);
	}
	
	public void downLoadExcel(@ModelAttribute Condition condition,HttpServletResponse response){
		
	}
}
