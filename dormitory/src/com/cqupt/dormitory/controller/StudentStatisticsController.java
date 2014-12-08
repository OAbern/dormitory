package com.cqupt.dormitory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.dao.StudentStatisticsDao;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.Condition;
import com.cqupt.dormitory.vo.StudentBuildingDistribution;

/**
 * 学生信息的统计控制类
 * @author Bern
 *
 */
@RequestMapping("/StudentStatistics")
@Controller
public class StudentStatisticsController {
	@Resource(name="studentStatisticsDaoImpl")
	private StudentStatisticsDao studentStatisticsDao;
	
	/**
	 * 查询各学院学生住宿分布
	 */
	@RequestMapping("/distributionOfStudent")
	public void distributionOfStudent(@ModelAttribute Condition condition, HttpServletResponse response) {
		List<StudentBuildingDistribution> list = studentStatisticsDao.distributionOfStudent(condition);
		
		/*将非在校住宿的学生building值设为“其他”*/
		for(StudentBuildingDistribution distribution : list) {
			String building = distribution.getBuilding();
			if(building==null || "".equals(building)) {
				distribution.setBuilding("其他");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		JSONUtils.toJSON(map, response);
	}
}
