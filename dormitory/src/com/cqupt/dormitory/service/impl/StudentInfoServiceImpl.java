package com.cqupt.dormitory.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.utils.Factor;
import com.cqupt.dormitory.vo.ClassAndMajor;

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
		List<ClassAndMajor> majors = studentInfoDao.findClassAndMajor();
		Map<String,ClassAndMajor> map = new HashMap<String, ClassAndMajor>();
		for(ClassAndMajor classAndMajor : majors) {
			
		}
		
		return studentInfoDao.findClassAndMajor();
	}

}
