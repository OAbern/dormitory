package com.cqupt.dormitory.service;

import com.cqupt.dormitory.model.Teacher;

/**
 * 处理教师信息的Service接口
 * @author Bern
 *
 */
public interface TeacherInfoService {
	/**
	 * 根据教师名字查找教师
	 * @return
	 */
	public Teacher findTeacherByNameAndAcademyId(String name, int academyId);
}
