package com.cqupt.dormitory.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.service.TeacherInfoService;
import com.cqupt.dormitory.vo.Factor;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.utils.SystemContext;
import com.cqupt.dormitory.vo.Condition;
import com.cqupt.dormitory.vo.ResultMessage;

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
	@RequestMapping("/addStudent")
	public ModelAndView addStudent(@ModelAttribute("student")Student student) {
		boolean result = false;
		ResultMessage resultMessage = new ResultMessage();
		/*检查辅导员是否存在*/
		ModelAndView modelAndView = new ModelAndView();
		//TODO 根据班级加载辅导员信息
		Teacher teacher = teacherInfoService.findTeacherByClassNum(student.getClassNum());
		if(teacher == null) {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("添加学生失败！");
			resultMessage.setError("辅导员不存在！");
			modelAndView.addObject(ERRORINFO, resultMessage);
			modelAndView.setViewName("failed");
			return modelAndView;
		}
		result = studentInfoService.addStudent(student);
		
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("添加学生成功！");
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
	public void findByfactor(HttpServletRequest request, HttpServletResponse response) {
		String academyId = request.getParameter("academy");
		String grade = request.getParameter("grade");
		String education = request.getParameter("education");
		List<Factor> factorLists = new ArrayList<Factor>();
		if(academyId != null && !"".equals(academyId)) {
			Factor factor = new Factor();
			factor.setName("academy_id");
			factor.setValue(academyId);
			factorLists.add(factor);
		}
		if(grade != null && !"".equals(grade)) {
			Factor factor = new Factor();
			factor.setName("grade");
			factor.setValue(grade);
			factorLists.add(factor);
		}
		if(education != null && !"".equals(education)) {
			Factor factor = new Factor();
			factor.setName("education");
			factor.setValue(education);
			factorLists.add(factor);
		}
		List<Student> studentList = studentInfoService.findStudentByFactor(factorLists);
		Map<String, Object> map = new HashMap<String, Object>();
		int total = SystemContext.getTotal();
		if(total <= 0) {
			total = studentList.size();
		}
		map.put("total", total);
		map.put("rows", studentList);
		JSONUtils.toJSON(map, response);
		System.out.println("size:"+studentList.size());
	}
	
	
	
	/**
	 * 更新学生信息
	 */
	@RequestMapping("/updateStudent")
	public void updateStudent(@RequestBody Student student, HttpServletResponse response) {
		boolean result = false;
		ResultMessage resultMessage = new ResultMessage();
		/*检查辅导员是否存在*/
		//TODO
		Teacher teacher = teacherInfoService.findTeacherByClassNum(student.getClassNum());
		if(teacher == null) {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("更新学生信息失败！");
			resultMessage.setError("辅导员不存在！");
			JSONUtils.toJSON(resultMessage, response);
			return;
		}
		result = studentInfoService.updateStudent(student);
		
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("更新学生信息成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("更新学生信息失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 删除学生信息
	 */
	@RequestMapping("/deleteStudent")
	public void deleteStudent(@RequestParam("delRowsIdArray[]") String []delRowsIdArray, HttpServletResponse response) {
		List<String> idList = new ArrayList<String>();
		ResultMessage resultMessage = new ResultMessage();
		for(String id : delRowsIdArray){
			idList.add(id);
		}
		boolean result = studentInfoService.deleteStudentByStuId(null);
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("删除学生信息成功！"); 
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("删除学生信息失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 根据条件查找学生
	 * @param json 前台传入factor的json数组
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/findStudentByFactor")
	public void findByFactor(String json, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		List<Factor> factors = (List<Factor>) JSONUtils.json2Obj(json, Factor.class);
		List<Student> studentList = studentInfoService.findStudentByFactor(factors);
		Map<String, Object> map = new HashMap<String, Object>();
		int total = SystemContext.getTotal();
		if(total <= 0) {
			total = studentList.size();
		}
		map.put("total", total);
		map.put("rows", studentList);
		JSONUtils.toJSON(map, response);
	}
	
	/**
	 * caozuo 查找级联信息
	 * @param Condition 筛选条件（视图对象）
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/findCascadingInfo")
	public void findByCondition(@ModelAttribute Condition condition, HttpServletResponse response) {
		if(condition.getTarget() == null) {
			JSONUtils.toJSON(null, response);
			return;
		}
		List<String> list = studentInfoService.findCascadingInfo(condition);
		JSONUtils.toJSON(list, response);
	}

	/**
	 * 根据条件查找学生信息
	 * @param Condition 筛选条件（视图对象）
	 * @param response HttpServletResponse
	 */
	@RequestMapping("/findStudentByCondition")
	public void findStudentByCondition(@ModelAttribute Condition condition, HttpServletResponse response) {
		List<Student> students = studentInfoService.findStudentByCondition(condition);
		Map<String, Object> map = new HashMap<String, Object>();
		int total = SystemContext.getTotal();
		if(total <= 0) {
			total = students.size();
		}
		map.put("total", total);
		map.put("rows", students);
		JSONUtils.toJSON(map, response);
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
