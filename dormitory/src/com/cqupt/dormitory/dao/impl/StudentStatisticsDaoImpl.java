package com.cqupt.dormitory.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.StudentStatisticsDao;
import com.cqupt.dormitory.vo.Condition;
import com.cqupt.dormitory.vo.StudentBuildingDistribution;

/**
 * 学生信息统计类的DAO层实现类
 * @author Bern
 *
 */
@Repository
public class StudentStatisticsDaoImpl extends BaseDaoSupport implements StudentStatisticsDao {

	@Override
	public List<StudentBuildingDistribution> distributionOfStudent(Condition condition) {
		List<StudentBuildingDistribution> list = null;
		try {
			list = getSqlSession().selectList("StudentStatistics.distributionOfStudent", condition);
		}catch(Exception e) {
				e.printStackTrace();
		}
		return list;
	}
	
}
