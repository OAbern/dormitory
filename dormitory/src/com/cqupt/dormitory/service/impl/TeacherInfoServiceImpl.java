package com.cqupt.dormitory.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cqupt.dormitory.dao.TeacherInfoDao;
import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.TeacherInfoService;
/**
 * 处理教师信息类的Service实现类
 * @author Bern
 *
 */
@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {
	@Resource(name="teacherInfoDaoImpl")
	TeacherInfoDao teacherInfoDao;
	
	@Override
	public Teacher findTeacherByNameAndAcademyId(String name, int academyId) {
		Teacher teacher = teacherInfoDao.findTeacherByNameAndAcademyId(name, academyId);
		return teacher;
	}

	@Override
	public Teacher findTeacherByTecNum(String tecNum) {
		return teacherInfoDao.findTeacherByTecNum(tecNum);
	}

	@Override
	public Teacher findTeacherByNumAndPw(Teacher teacher) {
		return teacherInfoDao.findTeacherByNumAndPw(teacher);
	}

	@Override
	public boolean updateTeacherInfo(Teacher teacher) {
		return teacherInfoDao.updateTeacherInfo(teacher);
	}

	@Override
	public List<String> findMajorByTecId(int tecId) {
		return teacherInfoDao.findMajorByTecId(tecId);
	}

}
