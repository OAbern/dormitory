package com.cqupt.dormitory.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.TeacherInfoDao;
import com.cqupt.dormitory.model.Teacher;

/**
 * 教师信息的DAO实现类
 * @author Bern
 *
 */
@Repository
public class TeacherInfoDaoImpl extends BaseDaoSupport implements TeacherInfoDao {

	@Override
	public Teacher findTeacherByNameAndAcademyId(String name, int academyId) {
		Teacher teacher = null;
		try {
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("name", name);
			paraMap.put("academyId", academyId);
			teacher = getSqlSession().selectOne("findTeacherByNameAndAcademyId", paraMap);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

	
}
