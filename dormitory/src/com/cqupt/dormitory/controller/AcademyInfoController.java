package com.cqupt.dormitory.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.dao.AcademyDao;
import com.cqupt.dormitory.model.Academy;
import com.cqupt.dormitory.utils.JSONUtils;

/**
 * 学院信息的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/academyInfo")
public class AcademyInfoController {
	@Resource(name="academyDaoImpl")
	private AcademyDao academyDao;

	/**
	 * 查找所有的学院信息
	 * @param response HttpServletResponse
	 */
	@RequestMapping("getAll")
	public void findAllAacdemy(HttpServletResponse response) {
		List<Academy> academies = academyDao.findAllAcademy();
		JSONUtils.toJSON(academies, response);
	}

	


}
