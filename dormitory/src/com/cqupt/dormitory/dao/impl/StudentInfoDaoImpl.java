package com.cqupt.dormitory.dao.impl;

import java.util.List;

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
	public boolean stuNumIsExist(String stuNum) {
		Student student = null;
		try {
			student = getSqlSession().selectOne("stuNumIsExist", stuNum);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(student == null) {
			return false;
		}else {
			return true;
		}
	}
	
}
