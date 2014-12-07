package com.cqupt.dormitory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.ResultType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.TeacherInfoService;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.utils.SystemContext;
import com.cqupt.dormitory.vo.ResultMessage;

/**
 * 处理教师信息的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/teacherInfo")
public class TeacherInfoController {
	@Resource(name="teacherInfoServiceImpl")
	private TeacherInfoService teacherInfoService;
	
	/**
	 * 查找教师信息（辅导员已登录）
	 */
	@RequestMapping("/findTeacher")
	public void findTeacher(HttpServletRequest request, HttpServletResponse response) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		teacher = teacherInfoService.findTeacherByTecNum(teacher.getTecNum());
		JSONUtils.toJSON(teacher, response);
	}
	
	/**
	 * 更新教师信息
	 * @param teacher
	 * @param response
	 */
	@RequestMapping("/updateTeacher")
	public void updateTeacherInfo(@ModelAttribute Teacher teacher,HttpServletRequest request, HttpServletResponse response) {
		ResultMessage resultMessage = new ResultMessage();
		if(teacher.getTecNum()==null || "".equals(teacher.getAcademy())) {	
			Teacher teacherInSession = (Teacher) request.getSession().getAttribute("teacher");
			teacher.setTecNum(teacherInSession.getTecNum());
		}
		boolean result = teacherInfoService.updateTeacherInfo(teacher);
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("更新教师信息成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("更新教师信息失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 校验旧密码是否正确（辅导员已登录）
	 * @param pw
	 */
	@RequestMapping("/checkPw")
	public void checkPw(String pw, HttpServletRequest request, HttpServletResponse response) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		teacher = teacherInfoService.findTeacherByTecNum(teacher.getTecNum());
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setStatus(ResultMessage.FAILED);
		resultMessage.setInfo("密码校验失败！");
		if(pw.equals(teacher.getPassword())) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("密码校验成功！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 根据教师id查找对应所管的专业（辅导员已登录）
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findMajor")
	public void findMajor(HttpServletRequest request, HttpServletResponse response) {
		Teacher teacherInSession = (Teacher) request.getSession().getAttribute("teacher");
		if(teacherInSession == null) {
			return;
		}
		List<String> majors = teacherInfoService.findMajorByTecId(teacherInSession.getId());
		JSONUtils.toJSON(majors, response);
	}
	
	/**
	 * 根据学院查找教师信息
	 * @param academy
	 * @param garde
	 * @param response
	 */
	@RequestMapping("/findTeacherByFactor")
	public void findTeacherByaAcademyAndGrade(int academy, HttpServletResponse response) {
		List<Teacher> teachers = teacherInfoService.findTeacherByAcademy(academy);
		int total = SystemContext.getTotal();
		if(total <= 0) {
			total = teachers.size();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", total);
		map.put("rows", teachers);
		JSONUtils.toJSON(map, response);
	}
	
	/**
	 * 根据教工号删除教师信息
	 */
	@RequestMapping("/deleteTeacher")
	public void deleteTeacherByTecNum(@RequestParam("delRowsIdArray[]") String []delRowsIdArray, HttpServletResponse response) {
		List<String> list = new ArrayList<String>();
		for(String tecNum : delRowsIdArray) {
			list.add(tecNum);
		}
		boolean result = teacherInfoService.deleteTeacherByTecNum(list);
		ResultMessage resultMessage = new ResultMessage();
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("删除教师信息成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("删除教师信息失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 通过Excel批量添加辅导员
	 */
	@RequestMapping("/addTeacherByExcel")
	public void addTeacherByExcel(MultipartFile file, HttpServletRequest request, HttpServletResponse response) { 
		ResultMessage resultMessage = new ResultMessage();		
		boolean result = teacherInfoService.addTeacherByExcel(file);
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("批量添加辅导员成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("批量添加辅导员失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
}
