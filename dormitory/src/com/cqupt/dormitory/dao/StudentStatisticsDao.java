package com.cqupt.dormitory.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.vo.Condition;
import com.cqupt.dormitory.vo.StudentBuildingDistribution;

/**
 * 学生信息统计类的DAO层接口
 * @author Bern
 *
 */
public interface StudentStatisticsDao {
	
	/**
	 * 查询各学院学生住宿分布
	 */
	public List<StudentBuildingDistribution> distributionOfStudent(Condition condition);
}
