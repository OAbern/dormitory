package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.Academy;

/**
 * 学院信息的DAO层接口
 * @author Bern
 *
 */
public interface AcademyDao {
	/**
	 * 查找所有的学院信息
	 * @return
	 */
	public List<Academy> findAllAcademy();
	
	/**
	 * 根据辅导员名字查找辅导员
	 * @param name
	 * @return
	 */
	public Academy findAcademyByName(String name);
}
