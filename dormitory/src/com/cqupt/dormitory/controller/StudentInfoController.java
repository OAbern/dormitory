package com.cqupt.dormitory.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.service.TeacherInfoService;
import com.cqupt.dormitory.utils.ErrorInfo;
import com.cqupt.dormitory.utils.Factor;
import com.cqupt.dormitory.utils.JSONUtils;

/**
 * 处理学生信息的控制器 
 * @author Bern
 *
 */
@Controller
@RequestMapping("/studentInfo")
public class StudentInfoController {
	private static String ERRORINFO = "errorInfo";
	
	@Resource(name="studentInfoServiceImpl")
	private StudentInfoService studentInfoService;
	
	@Resource(name="teacherInfoServiceImpl")
	private TeacherInfoService teacherInfoService;
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/addStudent")
	public ModelAndView addStudent(@ModelAttribute("student")Student student) {
		boolean result = false;
		/*检查辅导员是否存在*/
		ModelAndView modelAndView = new ModelAndView();
		Teacher teacher = teacherInfoService.findTeacherByNameAndAcademyId(student.getName(), student.getAcademyId());
		if(teacher == null) {
			ErrorInfo errorInfo = new ErrorInfo();
			errorInfo.setErrorInfo("辅导员不存在！");
			modelAndView.addObject(ERRORINFO, errorInfo);
			modelAndView.setViewName("failed");
			return modelAndView;
		}
		result = studentInfoService.addStudent(student);
		
		if(result) {
			modelAndView.setViewName("success");
		} else {
			modelAndView.setViewName("failed");
		}
		return modelAndView;
	}
	
	/**
	 * 根据条件查找学生
	 * @param request
	 * @return 
	 */
	@RequestMapping("/findStudent")
	public ModelAndView findByfactory(HttpServletRequest request, HttpServletResponse response) {
		String academyId = request.getParameter("academy");
		String grade = request.getParameter("grade");
		String education = request.getParameter("education");
		List<Factor> factorLists = new ArrayList<Factor>();
		if(academyId != null) {
			Factor factor = new Factor();
			factor.setName("academy_id");
			factor.setValue(academyId);
			factorLists.add(factor);
		}
		if(grade != null) {
			Factor factor = new Factor();
			factor.setName("grade");
			factor.setValue(grade);
			factorLists.add(factor);
		}
		if(education != null) {
			Factor factor = new Factor();
			factor.setName("education");
			factor.setValue(education);
//			factorLists.add(factor);
		}
		List<Student> studentList = studentInfoService.findStudentByFactor(factorLists);
		String json = JSONUtils.toJSONString(studentList);
		System.out.println(json);
		JSONUtils.toJSON(studentList, response);
		return null;
	}
	
	/**
	 * 表单提交的时间转换器
	 */
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
	}

	public void setStudentInfoService(StudentInfoService studentInfoService) {
		this.studentInfoService = studentInfoService;
	}
	
}
