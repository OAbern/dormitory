package com.cqupt.dormitory.service.impl;

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

}
