package com.cqupt.dormitory.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.dao.AdminDao;
import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.utils.JSONUtils;
import com.cqupt.dormitory.vo.ResultMessage;

/**
 * 管理员信息的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/adminInfo")
public class AdminInfoController {
	@Resource(name="adminDaoImpl")
	private AdminDao adminDao;
	
	/**
	 * 查找已经登录的管理员信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/findAdmin")
	public void findAdmin(HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		admin = adminDao.findAdminById(admin.getId());
		JSONUtils.toJSON(admin, response);
	}
	
	/**
	 * 校验密码是否正确
	 * @param pw 密码
	 * @param request
	 * @param response
	 */
	@RequestMapping("/checkPw")
	public void checkPw(String pw, HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		admin = adminDao.findAdminById(admin.getId());
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setStatus(ResultMessage.FAILED);
		resultMessage.setInfo("密码校验失败！");
		if(pw.equals(admin.getPassword())) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("密码校验成功！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	/**
	 * 更新管理员信息
	 * @param admin
	 */
	@RequestMapping("/updateAdmin")
	public void updateAdmin(@ModelAttribute Admin admin, HttpServletRequest request, HttpServletResponse response) {
		ResultMessage resultMessage = new ResultMessage();
		Admin adminInSession = (Admin) request.getSession().getAttribute("admin");
		admin.setAdminNum(adminInSession.getAdminNum());
		boolean result = adminDao.updateAdminInfo(admin);
		if(result) {
			resultMessage.setStatus(ResultMessage.SUCCESS);
			resultMessage.setInfo("更新管理员信息成功！");
		}else {
			resultMessage.setStatus(ResultMessage.FAILED);
			resultMessage.setInfo("更新管理员信息失败！");
		}
		JSONUtils.toJSON(resultMessage, response);
	}
	
	
	
}
