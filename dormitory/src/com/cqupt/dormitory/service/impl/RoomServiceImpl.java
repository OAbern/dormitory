package com.cqupt.dormitory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.FloorDao;
import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Floor;
import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.vo.RoomInFloor;

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	@Resource
	private RoomDao roomDao;
	@Resource
	private FloorDao floorDao;
	
	
	@Override
	public RoomInFloor findRoomInFloorForView(String buildingNum) {
		List<Room> rooms = roomDao.findAllRoomByBuildingNumAndFloor(buildingNum,"%");
		return this.roomHandler(rooms,true);
	}

	
	@Override
	public RoomInFloor findEmptyRoomInFloorForView(String buildingNum) {
		List<Room> rooms = roomDao.findAllRoomByBuildingNumAndFloor(buildingNum,"%");
		return this.roomHandler(rooms,false);
	}
	
	
	
	/**
	 * RoomHandler 创建相应的vo 需要的方法  
	 * @param rooms 默认拿出来就是按照floorNum 来排序的.
	 * @param emtpyStatus 是否展示空寝室, true是所有寝室.false是排除空寝室.
	 * @return 
	 *RoomInFloor
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	private RoomInFloor roomHandler(List<Room> rooms ,boolean emtpyStatus){
		RoomInFloor roomInFloor = new RoomInFloor();
		List<Map<String,String>> rows = new ArrayList<Map<String,String>>();
		String oldFloorNum =null;
		Map<String,String> everyFloorRoom = null;
		int i = 0; //计数器
		int columnMax = 0;
		for(Room r : rooms){
			Floor thisFloor = r.getFloor();
			
			if(!thisFloor.getFloorNum().equals(oldFloorNum)){
				//如果不相等就要开始创建Map,将原来的map 放进rows里面
				if(columnMax < i){
					columnMax = i;
				}
				i = 1;
				oldFloorNum = thisFloor.getFloorNum();
				if(everyFloorRoom != null){
					rows.add(everyFloorRoom);
				}
				everyFloorRoom = new HashMap<String,String>();
				everyFloorRoom.put("item1", thisFloor.getFloorNum());
			}
			//this.putIntoMapString(everyFloorRoom,i,r,emtpyStatus);
			
			if(r.getRoomNum().equals("0") && !emtpyStatus){
				//不需要展示.直接返回 不需要展示空寝室 
			}else{
				//需要展示空寝室
				i++;
				everyFloorRoom.put("item"+i, this.roomNumHandler(r));
			}
			
		}
		rows.add(everyFloorRoom);//最后一大堆.
		
		
		roomInFloor.setColumnMax(columnMax);
		roomInFloor.setRows(rows);
		roomInFloor.setTotal(rooms.size());
		return roomInFloor;
	}
	
	private void putIntoMapString(Map<String,String> map ,int i,Room r,boolean emptyStatus){
		if(r.getRoomNum().equals("0") && !emptyStatus){
			//不需要展示.直接返回 不需要展示空寝室 
			return;
		}else{
			//需要展示空寝室
			map.put(""+i, this.roomNumHandler(r));
		}
	}
	
	
	
	/**
	 * roomNumHandler 专业生产超级字符串300年! 
	 * 拼凑 190101<br>1/2
	 * @param r
	 * @return 
	 *String
	 * @exception 
	 * @since  1.0.0
	 */
	private  String roomNumHandler(Room r){
		StringBuffer s = new StringBuffer();
		s.append(r.getRoomNum());
		s.append("<br/>");
		s.append(r.getAlreadyStay());
		s.append("/");
		s.append(r.getTotalBed());
		return s.toString();
	}

	@Override
	public List<String> findAllCategoryOfRoom() {
		return roomDao.findAllCategoryOfRoom();
	}

	@Override
	public List<String> findAllCostOfRoom() {
		return roomDao.findAllCostOfRoom();
	}

	@Override
	public boolean updateCategoryAndCost(int totalBed, int cost,
			String buildingNum, String floorNum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delRoomNumOfStudent(String[] studentIds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delRoomNumOfStudent(String studentId) {
		String[] students = {studentId};
		return this.delRoomNumOfStudent(students);
	}

	@Override
	public boolean udpateRoom(Room r) {
		try {
			roomDao.updateRoom(r);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addRoom(String buildingNum, String floorNum, int cata,int fee) {
		try {
			int floorId = floorDao.findFloorId(buildingNum, floorNum);
			int roomNum = roomDao.findCurrentFloorNum(floorId)+1;

			Floor f = new Floor();
			f.setId(floorId);

			Room r = new Room();
			r.setAlreadyStay(0);
			r.setCost(fee);
			r.setFloor(f);
			r.setRoomNum(this.spellTheRoomNum(buildingNum,floorNum,roomNum));
			r.setTotalBed(cata);
			roomDao.add(r);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			return true;
		}
	}
	
	
	/**
	 * spellTheRoomNum 拼接字符串的.
	 * @param buidingNum
	 * @param floorNum
	 * @param roomNum
	 * @return 
	 *String
	 * @exception 
	 * @since  1.0.0
	 */
	private String spellTheRoomNum(String buidingNum,String floorNum,int roomNum){
		StringBuffer strRoomNum = new StringBuffer();
		if(buidingNum.length()<2){
			strRoomNum.append("0");
		}
		strRoomNum.append(buidingNum);
		if(floorNum.length()<2){
			strRoomNum.append("0");
		}
		strRoomNum.append(floorNum);
		if(roomNum<10){
			strRoomNum.append("0");
		}
		strRoomNum.append(""+roomNum);
		return strRoomNum.toString();
	}

	@Override
	public boolean delRoom(String roomNum) {
		
		try {
			roomDao.delRoom(roomNum);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<Student> findAllPersonInRoom(String roomNum) {
		return roomDao.findAllPersonInRoom(roomNum);
	}


	@Override
	public boolean updateChangeRoom(String studentNum, String roomNum) {
		Room r = roomDao.findByRoomNum(roomNum);
		return this.updateChangeRoom(studentNum, r.getId());
	}
	
	public boolean updateChangeRoom(String studentNum,int roomId){
		try {
			roomDao.updateStudentRoom(studentNum,roomId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateDistributeRoom(List<String> studentNums,String[] buildingNum) {
		try {
			int j = 0;
			int k = 0;
			List<Room> allRoomList = new ArrayList<Room>(4000);
			
			for(String b : buildingNum){
				allRoomList.addAll(roomDao.findAllRoomByBuildingNumAndFloor(b, "%"));
			}
			
			Room r = allRoomList.get(k);
			
			for(int i = 0;i<studentNums.size() ;i++){
				String studentNum = studentNums.get(i);
				if(r.getTotalBed()<j){
					k++;
					r = allRoomList.get(k);
				}
				roomDao.updateStudentRoom(studentNum, r.getId());
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
}
