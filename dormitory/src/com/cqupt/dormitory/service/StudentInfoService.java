package com.cqupt.dormitory.service;

import java.util.List;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.utils.Factor;

/**
 * 处理学生信息业务的接口
 * @author Bern
 *
 */
public interface StudentInfoService {
	/**
	 * 添加学生信息
	 * @return	添加操作成败
	 */
	public boolean addStudent(Student student);
	
	/**
	 * 根据条件查找学生信息
	 * @param map 条件集合
	 * @return 符合条件的学生集合
	 */
	public List<Student> findStudentByFactor(List<Factor> factors);
}
