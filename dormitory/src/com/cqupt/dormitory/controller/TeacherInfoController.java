package com.cqupt.dormitory.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.ResultType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.TeacherInfoService;
import com.cqupt.dormitory.utils.JSONUtils;
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
	 * 查找教师信息
	 */
	@RequestMapping("/findTeacher")
	public void findTeacher(HttpServletRequest request, HttpServletResponse response) {
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
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
		Teacher teacherInSession = (Teacher) request.getSession().getAttribute("teacher");
		teacher.setTecNum(teacherInSession.getTecNum());
		boolean result = teacherInfoService.updateTeacherInfo(teacher);
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("更新教师信息成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("更新教师信息失败！");
		}
	}
	
	/**
	 * 校验旧密码是否正确
	 * @param pw
	 */
	@RequestMapping("/checkPw")
	public void checkPw(String pw, HttpServletRequest request, HttpServletResponse response) {
		String pwInSession = (String) request.getSession().getAttribute("teacherPw");
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setStatus(ResultMessage.FAILED);
		resultMessage.setInfo("密码校验失败！");
		if(pwInSession!=null && pw!=null) {
			if(pw.equals(pwInSession)) {
				resultMessage.setStatus(ResultMessage.SUCCESS);
				resultMessage.setInfo("密码校验成功！");
			}
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 根据教师id查找对应所管的专业
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findMajor")
	public void findMajor(HttpServletRequest request, HttpServletResponse response) {
		Teacher teacherInSession = (Teacher) request.getSession().getAttribute("teacher");
		List<String> majors = teacherInfoService.findMajorByTecId(teacherInSession.getId());
		JSONUtils.toJSON(majors, response);
	}
	
	
	
}
