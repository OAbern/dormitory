package com.cqupt.dormitory.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.FloorDao;
import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.service.FloorService;

@Service
public class FloorServiceImpl implements FloorService {
	@Resource
	private FloorDao floorDao;
	@Resource
	private RoomDao	roomDao;
	
	public boolean updateFloor(String buildingNum,String floorNum,int cata,int fee){
		try {
			int floorId = floorDao.findFloorId(buildingNum, floorNum);
			List<Integer> floorIds = new ArrayList<Integer>();
			floorIds.add(floorId);
			roomDao.updateRoom(floorIds, fee+"", cata+"");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		return true;
	}

	@Override
	public boolean delFloor(String buildingNum, String[] floorNum) {
		try {
			for(String strF : floorNum){
				int floorId = floorDao.findFloorId(buildingNum, strF);
				floorDao.delFloor(floorId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
