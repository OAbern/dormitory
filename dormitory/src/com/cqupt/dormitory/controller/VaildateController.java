package com.cqupt.dormitory.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.service.StudentInfoService;

/**
 * 前台表单校验控制器
 * @author Bern
 *
 */
@Controller
public class VaildateController {
	@Resource(name="studentInfoServiceImpl")
	private StudentInfoService studentInfoService;
	
	
	
	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}
	
}
