package com.cqupt.dormitory.dao;

import java.util.List;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.utils.Factor;

/**
 * 处理学生信息Dao的接口
 * @author Bern
 *
 */
public interface StudentInfoDao {
	/**
	 * 添加学生信息
	 * @return 返回操作结果
	 */
	public boolean addStudent(Student student);
	
	/**
	 * 根据条件查询学生信息
	 * @param 条件集合
	 * @return 返回符合条件的学生信息
	 */
	public List<Student> findStudentByFactor(List<Factor> factors);
}
