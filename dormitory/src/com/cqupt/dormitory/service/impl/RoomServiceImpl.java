package com.cqupt.dormitory.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Floor;
import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.vo.RoomInFloor;

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	@Resource
	private RoomDao roomDao;

	@Override
	public RoomInFloor findRoomInFloorForView(String buildingNum) {
		List<Room> rooms = roomDao.findAllRoomByBuildingNum(buildingNum);
		return this.roomHandler(rooms,true);
	}

	@Override
	public RoomInFloor findEmptyRoomInFloorForView(String buildingNum) {
		List<Room> rooms = roomDao.findAllRoomByBuildingNum(buildingNum);
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
			}
			this.putIntoMapString(everyFloorRoom,i,r,emtpyStatus);
			i++;
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
			i++;
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
	
	
}
