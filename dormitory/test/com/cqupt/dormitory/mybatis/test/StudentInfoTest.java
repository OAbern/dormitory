package com.cqupt.dormitory.mybatis.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.utils.Factor;

public class StudentInfoTest {
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
	public void addStudentTest() {
		StudentInfoDao studentInfoDao = (StudentInfoDao) context.getBean("studentInfoDaoImpl");
		Student student = new Student();
		student.setAcademyId(3);
		student.setGrade("2012");
		student.setClassNum("0311201");
		student.setMajor("信息管理与信息系统");
		student.setName("冯迪");
		student.setStuNum("2012211141");
		student.setLiveSatus(0);
		boolean result = studentInfoDao.addStudent(student);
		if(result) {
			System.out.println("添加学生信息成功！");
		} else {
			System.out.println("！！！！！！！！！添加学生信息失败！");
		}
	}

	@Test
	public void findByFactorTest() {
		Factor factor1 = new Factor("academy", "经济管理学院");
		Factor factor2 = new Factor("grade", "2012");
		List<Factor> factors = new ArrayList<Factor>();
		factors.add(factor1);
		factors.add(factor2);
		//Factor factor3 = new Factor("education", "");
		StudentInfoDao studentInfoDao = (StudentInfoDao) context.getBean("studentInfoDaoImpl");
		List<Student> students = studentInfoDao.findStudentByFactor(factors);
		
		System.out.println(students.size());
		
		for(Student s : students) {
			System.out.println(s.toString());
		}
	}
}
