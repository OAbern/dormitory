package com.cqupt.dormitory.service.impl;

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
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public List<Student> findStudentOutByCondition(Condition condition) {
		condition.setLivingStatus(2);
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public List<Student> findStudentWithRoom(Condition condition) {
		condition.setLivingStatus(4);
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public List<Student> findStudentCheckOutByCondition(Condition condition) {
		condition.setLivingStatus(1);
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public boolean updateOutOfRoom(String studentNum) {
		String[] studentNums = {studentNum};
		return this.updateOutOfRoom(studentNums);
	}

	@Override
	public boolean updateOutOfRoom(String[] studentNum) {
		try {
			studentInfoDao.updateOutRoom(studentNum);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
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
