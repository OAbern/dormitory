package com.cqupt.dormitory.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.utils.Factor;

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
			result = getSqlSession().insert("addStudent", student);
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
			students = getSqlSession().selectList("findStudentByFactor", factors);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return students;
	}
	
}
