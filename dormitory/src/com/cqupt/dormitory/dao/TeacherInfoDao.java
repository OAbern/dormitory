package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Teacher;

/**
 * 教师信息的DAO接口
 * @author Bern
 *
 */
public interface TeacherInfoDao {
	/**
	 * 根据教师名字和学院id查找教师
	 * @param name 教师名字
	 * @return 教师信息
	 */
	public Teacher findTeacherByNameAndAcademyId(String name, int academyId);
	
	/**
	 * 根据教师的账号和密码查找教师
	 * @param teacher 教师的num和pw不能为空
	 * @return 教师信息
	 */
	public Teacher findTeacherByNumAndPw(Teacher teacher);
	
	/**
	 * 根据教工号查找教师信息
	 * @param tecNum
	 * @return
	 */
	public Teacher findTeacherByTecNum(String tecNum);
	
	/**
	 * 更新教师信息
	 * @param teacher
	 * @return
	 */
	public boolean updateTeacherInfo(Teacher teacher);
	
	/**
	 * 根据教师id查找专业
	 * @param tecId 教师id
	 * @return 
	 */
	public List<String> findMajorByTecId(int tecId);
	
	/**
	 * 根据班级号查找教师信息
	 * @param classNum 班级号
	 * @return 教师信息
	 */
	public Teacher findTeacherByClassNum(String classNum);
	
	/**
	 * 根据学院和年级查找教师信息
	 * @param academy
	 * @param grade
	 * @return
	 */
	public List<Teacher> findTeacherByAcademyAndGrade(String academy,String grade);
	
	/**
	 * 根据教工号删除教师信息
	 * @param list 要删除的教工号列表
	 * @return 操作结果
	 */
	public boolean deleteTeacherByTecNum(List<String> list);
	
	/**
	 * 添加教师
	 * @param teacher
	 * @return
	 */
	public boolean addTeacher(Teacher teacher);
}
