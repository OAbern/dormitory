package com.cqupt.dormitory.dao.impl;

import java.util.HashMap;
import java.util.List;
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

	@Override
	public Teacher findTeacherByNumAndPw(Teacher t) {
		Teacher teacher = null;
		try {
			teacher = getSqlSession().selectOne("findTeacherByNumAndPw", t);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public Teacher findTeacherByTecNum(String tecNum) {
		Teacher teacher = null;
		try {
			teacher = getSqlSession().selectOne("findTeacherByTecNum", tecNum);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public boolean updateTeacherInfo(Teacher teacher) {
		int result = -1;
		try {
			result =  getSqlSession().update("updateTeacherInfo", teacher);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<String> findMajorByTecId(int tecId) {
		List<String> majors = null;
		try {
			majors = getSqlSession().selectList("findMajorByTecId", tecId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return majors;
	}

	@Override
	public Teacher findTeacherByClassNum(String classNum) {
		Teacher teacher = null;
		try {
			teacher = getSqlSession().selectOne("findTeacherByClassNum", classNum);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return teacher;
	}

	@Override
	public List<Teacher> findTeacherByAcademyAndGrade(String academy,
			String grade) {
		List<Teacher> teachers = null;
		try {
			Map<String, Object> parameter = new HashMap<String, Object>();
			parameter.put("academy", academy);
			parameter.put("grade", grade);
			teachers = getSqlSession().selectList("findTeacherByAcademyAndGrade", parameter);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

	@Override
	public boolean deleteTeacherByTecNum(List<String> list) {
		int result = -1;
		try {
			result = getSqlSession().delete("deleteTeacherByTecNum", list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	
}
