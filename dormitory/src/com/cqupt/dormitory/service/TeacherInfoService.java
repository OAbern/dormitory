package com.cqupt.dormitory.service;

import java.util.List;

import com.cqupt.dormitory.model.Teacher;

/**
 * 处理教师信息的Service接口
 * @author Bern
 *
 */
public interface TeacherInfoService {
	/**
	 * 根据教师名字和学院id查找教师
	 * @return
	 */
	public Teacher findTeacherByNameAndAcademyId(String name, int academyId);
	
	/**
	 * 根据教工号查找教师信息
	 * @param tecNum 查找的教工号
	 * @return 查找的教师信息
	 */
	public Teacher findTeacherByTecNum(String tecNum);
	
	/**
	 * 根据教工号和密码查找教师
	 * @param num 教工号
	 * @param pw 密码
	 * @return 教师信息
	 */
	public Teacher findTeacherByNumAndPw(Teacher teacher);
	
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
}
