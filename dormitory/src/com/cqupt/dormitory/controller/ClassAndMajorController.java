package com.cqupt.dormitory.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.vo.ClassAndMajor;

/**
 * 处理班级、专业、学院信息的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/info")
public class ClassAndMajorController {
	@Resource(name="studentInfoServiceImpl")
	private StudentInfoService studentInfoService;
	
	/**
	 * 查找所有的班级和专业信息
	 */
	@RequestMapping("/findClassAndMajor")
	public void  findClassAndMajor(HttpServletResponse response) {
		List<ClassAndMajor> list = studentInfoService.findClassAndMajor();
		if(list == null) {
			return;
		}
	}

	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}
}
