package com.cqupt.dormitory.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.PUT;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.vo.Factor;
import com.cqupt.dormitory.vo.ClassAndMajor;
import com.cqupt.dormitory.vo.Condition;

/**
 * 处理学生信息的Dao实现类
 * @author Bern
 *
 */
@Repository
public class StudentInfoDaoImpl extends BaseDaoSupport implements StudentInfoDao {

	@Override
	public boolean addStudent(Student student) {
		int result = -1;
		try {
			result = getSqlSession().insert("com.cqupt.dormitory.model.Student.addStudent", student);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Student> findStudentByFactor(List<Factor> factors) {
		List<Student> students = null;
		try {
			students = getSqlSession().selectList("com.cqupt.dormitory.model.Student.findStudentByFactor", factors);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public boolean updateStudent(Student student) {
		int result = -1;
		try {
			result = getSqlSession().update("com.cqupt.dormitory.model.Student.updateStudent", student);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteStudentByStuId(List<String> idList) {
		int result = -1;
		try {
			result = getSqlSession().delete("com.cqupt.dormitory.model.Student.deleteStudentByStuId", idList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ClassAndMajor> findClassAndMajor() {
		List<ClassAndMajor> list = null;
		try {
			list = getSqlSession().selectList("com.cqupt.dormitory.model.Student.findClassAndMajor");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> findCascadingInfo(Condition condition) {
		List<String> list = null;
		try {
			list = getSqlSession().selectList("com.cqupt.dormitory.model.Student.findCascadingInfo", condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Student findStudentByStuNum(String stuNum) {
		Student student = null;
		try {
			student = getSqlSession().selectOne("stuNumIsExist", stuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public List<Student> findStudentOutByCondition(Condition condition) {
		List<Student> students = null;
		try{
			students = getSqlSession().selectList("findStutentOutByCondition", condition);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public List<Student> findStudentWithRoom(Condition condition) {
		List<Student> students = null;
		try{
			students = getSqlSession().selectList("findStudentWithRoom", condition);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public List<Student> findStudentCheckOutByCondition(Condition condition) {
		List<Student> students = null;
		try{
			students = getSqlSession().selectList("findStudentCheckOutByCondition", condition);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}

	@Override
	public void updateOutRoom(String[] studentNum) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("studentNum", studentNum);
		getSqlSession().update(Student.class.getName()+".update_out_of_room",map);
	}
	@Override
	public boolean changeLivingStatus(List<String> stuNum, int status) {
		int result = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nums", stuNum);
		map.put("status", status);
		try {
			result = getSqlSession().update("changeLivingStatus", map);
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
	public boolean addStudentOutLiving(Student student) {
		int result = -1;
		try {
			result = getSqlSession().update("addStudentOutLiving", student);
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
	public Student findStudentByStuNumAndName(String stuNum, String name) {
		Student student = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("stuNum", stuNum);
		map.put("name", name);
		try {
			student = getSqlSession().selectOne("findStudentByStuNumAndName", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}
	
}
