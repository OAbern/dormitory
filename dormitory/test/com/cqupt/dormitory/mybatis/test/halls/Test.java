package com.cqupt.dormitory.mybatis.test.halls;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqupt.dormitory.dao.AreaDao;
import com.cqupt.dormitory.model.Area;

import junit.framework.TestCase;

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
}
