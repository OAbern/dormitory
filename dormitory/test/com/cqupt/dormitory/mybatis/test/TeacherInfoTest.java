package com.cqupt.dormitory.mybatis.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.TeacherInfoService;

public class TeacherInfoTest {
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
	public void findTeacherByNameAndAcademyIdTest() {
		TeacherInfoService teacherInfoService = (TeacherInfoService) context.getBean("teacherInfoServiceImpl");
		Teacher teacher = teacherInfoService.findTeacherByNameAndAcademyId("刘畅", 1);
		System.out.println("Teacher id:"+teacher.getId()+",	tecNum:"+teacher.getTecNum());
	}

}
