package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.vo.Factor;
import com.cqupt.dormitory.vo.ClassAndMajor;
import com.cqupt.dormitory.vo.Condition;

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
	
	/**
	 * 更新学生信息
	 * @param student
	 * @return 返回操作结果
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
	 * 校验学号是否存在
	 * @param stuNum 学号
	 * @return 存在返回true，否则返回false
	 */
	public Student findStudentByStuNum(String stuNum);
	
	/**
	 * 根据学号和姓名查找学生信息
	 * @param stuNum
	 * @param name
	 * @return
	 */
	public Student findStudentByStuNumAndName(String stuNum, String name);

	/**
	 * 改变学生的住宿状态
	 * @param stuNum
	 * @return
	 */
	public boolean changeLivingStatus(List<String> stuNum, int status);
	
	/**
	 * 添加校外住宿的学生
	 * @param student
	 * @return
	 */
	public boolean addStudentOutLiving(Student student);

	/**
	 * updateOutRoom 退宿处理
	 * @param studentNum 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateOutRoom(String[] studentNum);
	
	/**
	 * updateOutRoom 退宿处理单个
	 * @param studentNum 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateOutRoom(String studentNum);
	
	/**
	 * 根据条件查找学生（按academy和class排序）
	 * @param condition
	 * @return
	 */
	public List<Student> findStudentOrdered(Condition condition);
	
	/**
	 * 根据条件查找学生
	 * @param condition
	 * @return
	 */
	public List<Student> findStudentByCondition(Condition condition);

}
