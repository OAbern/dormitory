package com.cqupt.dormitory.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.dao.AdminDao;
import com.cqupt.dormitory.dao.TeacherInfoDao;
import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.model.Teacher;

/**
 * 处理登录的控制器
 * @author Bern
 *
 */
@Controller
public class LoginController {
	@Resource(name="teacherInfoDaoImpl")
	TeacherInfoDao teacherInfoDao;
	
	@Resource(name="adminDaoImpl")
	AdminDao adminDao;
	
	/**
	 * 登录校验
	 * @param name 用户名
	 * @param pw 用户密码
	 */
	@RequestMapping("loginCheck")
	public ModelAndView loginCheck(String num, String pw, int level, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if(level == 0) {	//普通教师
			Teacher t = new Teacher();
			t.setTecNum(num);
			t.setPassword(pw);
			Teacher teacher = teacherInfoDao.findTeacherByNumAndPw(t);
			if(teacher == null) {
				modelAndView.setViewName("login");
				modelAndView.addObject("error", "用户名或密码错误！");
			}else {
				modelAndView.setViewName("f_index");
				request.getSession().setAttribute("teacher", teacher);
			}
		}else if(level == 1) {	//辅导员
			Admin a = new Admin();
			a.setAdminNum(num);
			a.setPassword(pw);
			Admin admin = adminDao.findAdminByNumAndPw(a);
			if(admin == null) {
				modelAndView.setViewName("login");
				modelAndView.addObject("error", "用户名或密码错误！");
			}else {
				modelAndView.setViewName("index");
				request.getSession().setAttribute("admin", admin);
			}
		}
		
		return modelAndView;
	}

	public void setTeacherInfoDao(TeacherInfoDao teacherInfoDao) {
		this.teacherInfoDao = teacherInfoDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
}
