package com.cqupt.dormitory.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.ResultMessage;

/**
 * 前台表单校验控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/vaildate")
public class VaildateController {
	@Resource(name="studentInfoDaoImpl")
	private StudentInfoDao studentInfoDao;
	
	/**
	 * 校验学号是否存在
	 * @param stuNum 需要进行校验的学号
	 * @param response
	 */
	@RequestMapping("/stuNumIsExist")
	public void vaildateStuNumExist(String stuNum, HttpServletResponse response) {
		Student student = studentInfoDao.findStudentByStuNum(stuNum);
		ResultMessage resultMessage = new ResultMessage();
		if(student != null) {	//学号已存在，返回失败
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("学号已存在！");
		}else {		//学号没有，返回成功
			resultMessage.setStatus(ResultMessage.SUCCESS);
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	
}
