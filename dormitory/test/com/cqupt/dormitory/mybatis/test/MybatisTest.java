package com.cqupt.dormitory.mybatis.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqupt.dormitory.dao.RoomDao;
import com.cqupt.dormitory.model.Room;

public class MybatisTest {
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

	@Test
	public void roomTest() {
		RoomDao roomDao = (RoomDao) context.getBean("roomDaoImpl");
		Room room = roomDao.findByRoomId("160317");
		System.out.println("roomId:"+room.getRoomId()+"Cost:"+room.getCost());
	}

}
