package com.cqupt.dormitory.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.vo.Factor;
import com.cqupt.dormitory.vo.ClassAndMajor;
import com.cqupt.dormitory.vo.Condition;

/**
 * 处理学生信息业务的实现类
 * @author Bern
 *
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	@Resource(name="studentInfoDaoImpl")
	StudentInfoDao studentInfoDao;
	
	public void setStudentInfoDao(StudentInfoDao studentInfoDao) {
		this.studentInfoDao = studentInfoDao;
	}
	
	@Override
	public boolean addStudent(Student student) {
		boolean result = studentInfoDao.addStudent(student);
		return result;
	}

	@Override
	public List<Student> findStudentByFactor(List<Factor> factors) {
		return studentInfoDao.findStudentByFactor(factors);
	}

	@Override
	public boolean updateStudent(Student student) {
		boolean result = studentInfoDao.updateStudent(student);
		return result;
	}

	@Override
	public boolean deleteStudentByStuId(List<String> idList) {
		boolean result = studentInfoDao.deleteStudentByStuId(idList);
		return result;
	}

	@Override
	public List<ClassAndMajor> findClassAndMajor() {	
		return studentInfoDao.findClassAndMajor();
	}

	@Override
	public List<String> findCascadingInfo(Condition condition) {
		return studentInfoDao.findCascadingInfo(condition);
	}

	@Override
	public List<Student> findStudentByCondition(Condition condition) {
		/* 将Condition对象封装成Factor对象集合  */
		List<Factor> factors = new ArrayList<Factor>();
		if(condition.getAcademy()!=null && !"".equals(condition.getAcademy())) {
			Factor factor = new Factor("academy_id",condition.getAcademy());
			factors.add(factor);
		}
		if(condition.getClassNum()!=null && !"".equals(condition.getClassNum())) {
			Factor factor = new Factor("class", condition.getClassNum());
			factors.add(factor);
		}
		if(condition.getEducation()!=null && !"".equals(condition.getEducation())) {
			Factor factor = new Factor("education", condition.getEducation());
			factors.add(factor);	
		}
		if(condition.getGrade()!=null && !"".equals(condition.getGrade())) {
			Factor factor = new Factor("grade", condition.getGrade());
			factors.add(factor);
		}
		if(condition.getMajor()!=null && !"".equals(condition.getMajor())) {
			Factor factor = new Factor("major", condition.getMajor());
			factors.add(factor);
		}
		if(condition.getSex()!=null && !"".equals(condition.getSex())) {
			Factor factor = new Factor("stu_sex", condition.getSex());
			factors.add(factor);
		}
		return studentInfoDao.findStudentByFactor(factors);
	}

	@Override
	public List<Student> findStudentOutByCondition(Condition condition) {
		return studentInfoDao.findStudentOutByCondition(condition);
	}

	@Override
	public List<Student> findStudentWithRoom(Condition condition) {
		return studentInfoDao.findStudentWithRoom(condition);
	}

	@Override
	public List<Student> findStudentCheckOutByCondition(Condition condition) {
		return studentInfoDao.findStudentCheckOutByCondition(condition);
	}

	@Override
	public boolean deleteStudentOutLiving(List<String> stuNum) {
		return studentInfoDao.changeLivingStatus(stuNum, 2);
	}

	@Override
	public boolean addStudentOutLiving(Student student) {
		return studentInfoDao.addStudentOutLiving(student);
	}

	@Override
	public boolean checkOutStudent(List<String> stuNum) {
		return studentInfoDao.changeLivingStatus(stuNum, 1);
	}

	@Override
	public Student findStudentByStuNum(String stuNum) {
		return studentInfoDao.findStudentByStuNum(stuNum);
	}

}
