package com.cqupt.dormitory.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.dao.AdminDao;
import com.cqupt.dormitory.dao.TeacherInfoDao;
import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.ResultMessage;

/**
 * 处理登录的控制器
 * @author Bern
 *
 */
@Controller
public class LoginController {
	@Resource(name="teacherInfoDaoImpl")
	private TeacherInfoDao teacherInfoDao;
	
	@Resource(name="adminDaoImpl")
	private AdminDao adminDao;
	
	/**
	 * 登录校验
	 * @param name 用户名
	 * @param pw 用户密码
	 */
	@RequestMapping("loginCheck")
	public void loginCheck(String num, String pw, int level, HttpServletRequest request, HttpServletResponse response) {
		ResultMessage resultMessage = new ResultMessage();
		if(level == 0) {	//普通教师
			Teacher t = new Teacher();
			t.setTecNum(num);
			t.setPassword(pw);
			Teacher teacher = teacherInfoDao.findTeacherByNumAndPw(t);
			if(teacher == null) {
				resultMessage.setStatus(ResultMessage.FAILED);
				resultMessage.setInfo("用户名或密码错误！");
			}else {
				resultMessage.setStatus(ResultMessage.SUCCESS);
				request.getSession().setAttribute("teacherPw", teacher.getPassword());
				teacher.setPassword("");	//置空密码
				request.getSession().setAttribute("teacher", teacher);
			}
		}else if(level == 1) {	//管理员
			Admin a = new Admin();
			a.setAdminNum(num);
			a.setPassword(pw);
			Admin admin = adminDao.findAdminByNumAndPw(a);
			if(admin == null) {
				resultMessage.setStatus(ResultMessage.FAILED);
				resultMessage.setInfo("用户名或密码错误！");
			}else {
				resultMessage.setStatus(ResultMessage.SUCCESS);
				request.getSession().setAttribute("adminPw", admin.getPassword());
				admin.setPassword("");		//置空密码
				request.getSession().setAttribute("admin", admin);
			}
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("logOut")
	public ModelAndView logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:login.html");
		return modelAndView;
	}

	public void setTeacherInfoDao(TeacherInfoDao teacherInfoDao) {
		this.teacherInfoDao = teacherInfoDao;
	}

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
}
