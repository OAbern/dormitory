package com.cqupt.dormitory.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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
	
	/**
	 * 根据条件查找外出住宿的学生信息
	 * @param condition
	 * @return
	 */
	public List<Student> findStudentOutByCondition(Condition condition);
	
	/**
	 * 根据条件查找已住寝室的学生
	 * @param condition
	 * @return
	 */
	public List<Student> findStudentWithRoom(Condition condition);
	
	/**
	 * 根据条件查找退宿的学生信息
	 * @param condition
	 * @return
	 */
	public List<Student> findStudentCheckOutByCondition(Condition condition);
	
	/**
	 * updateOutOfRoom 退宿处理
	 * @param studentNum
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 * @author hhy
	 */
	public boolean updateOutOfRoom(String studentNum);
	
	/**
	 * updateOutOfRoom 退宿管理删除多个学生.
	 * @param studentNum
	 * @return 
	 *boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public boolean updateOutOfRoom(String[] studentNum);
	
	
	/**
	 * 删除校外住宿的学生（改状态）
	 * @param stuNum
	 * @return
	 */
	public boolean deleteStudentOutLiving(List<String> stuNum);
	
	/**
	 * 添加校外住宿的学生
	 * @param student
	 * @return
	 */
	public boolean addStudentOutLiving(Student student);
	
	/**
	 * 对学生做退宿处理（改状态）
	 * @param stuNum
	 * @return
	 */
	public boolean checkOutStudent(List<String> stuNum);
	
	/**
	 * 根据学号查找学生
	 * @param stuNum
	 * @return
	 */
	public Student findStudentByStuNum(String stuNum);
	
	/**
	 * updateOutOfRoom 退宿处理.主要是先清空.
	 * @param studentNums 
	 *void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateOutOfRoom(List<String> studentNums);
	
	/**
	 * 批量添加学生信息
	 * @param file
	 * @return
	 */
	public boolean addStudentByExcel(MultipartFile file);
	
	/**
	 * 查找已进行住宿分配的页面
	 * @param tecNum
	 * @return
	 */
	public List<Student> findStudentDeploy(String tecNum);
}
