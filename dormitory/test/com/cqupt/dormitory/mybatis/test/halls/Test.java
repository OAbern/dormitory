package com.cqupt.dormitory.mybatis.test.halls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.dao.BuildingDao;
import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Area;
import com.cqupt.dormitory.model.Room;
import com.cqupt.dormitory.service.AreaBuildingService;
import com.cqupt.dormitory.service.RoomService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.AreaBuildingEmpty;
import com.cqupt.dormitory.vo.BuildingEmptyBed;
import com.cqupt.dormitory.vo.RoomInFloor;

public class Test extends TestCase{
ApplicationContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("config/applicationContext-commons.xml");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public void test1(){
		AreaDao areaDao = (AreaDao)context.getBean("areaDao");
		List<Area> list = areaDao.findAllArea();
		System.out.println(list.size());
	}
	
	public void test2(){
		RoomInFloor f = new RoomInFloor();
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "123213<br/>123");
		map.put("2", "123213<br/>123");
		map.put("3", "123213<br/>123");
		map.put("4", "123213<br/>123");
		map.put("5", "123213<br/>123");
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list.add(map);
		f.setRows(list);
		System.out.println(JSONUtils.toJSONString(f));
	}
	
	public void test3(){
		RoomDao room =(RoomDao)context.getBean("roomDaoImpl");
		List<Room> rooms = room.findAllRoomByBuildingNumAndFloor("19","%");
		System.out.println(rooms.size());
	}
	
	public void test4(){
		RoomService rService = (RoomService)context.getBean("roomService");
		RoomInFloor shit = rService.findRoomInFloorForView("16");
		System.out.println(JSONUtils.toJSONString(shit));
	}
	
	public void test5(){
		List<AreaBuildingEmpty> rows = new ArrayList<AreaBuildingEmpty>();
		BuildingEmptyBed shit = new BuildingEmptyBed();
		shit.setBuildingNum("12");
		shit.setEmptyBed(100);
		List<BuildingEmptyBed> fuck = new ArrayList<BuildingEmptyBed>();
		fuck.add(shit);
		fuck.add(shit);
		fuck.add(shit);
		AreaBuildingEmpty e = new AreaBuildingEmpty();
		e.setArea("A区");
		e.setBuildingEmptyBeds(fuck);
		rows.add(e);
		rows.add(e);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", "12");
		map.put("columnMax", 12);
		map.put("rows", rows);
		System.out.println(JSONUtils.toJSONString(map));
		
	}
	
	public void test6(){
		AreaBuildingService a = (AreaBuildingService)context.getBean("areaBuildingService");
		List<String> shit = a.findBuildingBySex("男");
		System.out.println(JSONUtils.toJSONString(shit));
	}
	
	public void test7(){
		AreaBuildingService a = (AreaBuildingService)context.getBean("areaBuildingService");
		Map<String,Object> map  = a.findBuildingAndAreaVoBySex("男");
		System.out.println(JSONUtils.toJSONString(map));
	}
	
	public void test8(){
		BuildingDao b = (BuildingDao)context.getBean("buildingDaoImpl");
		System.out.println(b.findBuildingEmptyBed(1));
	}
	
	
}
