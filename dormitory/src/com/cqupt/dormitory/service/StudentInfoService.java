package com.cqupt.dormitory.service;

import java.util.List;

import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.vo.Factor;
import com.cqupt.dormitory.vo.ClassAndMajor;
import com.cqupt.dormitory.vo.Condition;

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
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return
	 */
	public boolean updateStudent(Student student);
	
	/**
	 * 根据id删除学生信息 
	 * @param idList 要删除的id列表 
	 * @return 返回操作结果
	 */
	public boolean deleteStudentByStuId(List<String> idList);
	
	/**
	 * 查询所有的班级和专业、学院信息
	 * @return
	 */
	public List<ClassAndMajor> findClassAndMajor();
	
	/**
	 * 查找级联信息
	 * @param condition 筛选条件
	 * @return 
	 */
	public List<String> findCascadingInfo(Condition condition);
	
	/**
	 * 根据条件查找学生信息
	 * @param condition 筛选条件
	 * @return 符合条件的学生信息
	 */
	public List<Student> findStudentByCondition(Condition condition);
}
