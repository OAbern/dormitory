package com.cqupt.dormitory.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.utils.JSONUtils;

/**
 * 管理员信息的控制器
 * @author Bern
 *
 */
@Controller
@RequestMapping("/adminInfo")
public class AdminInfoController {
	
	/**
	 * 查找已经登录的管理员信息
	 * @param request
	 * @param response
	 */
	public void findAdmin(HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		JSONUtils.toJSON(admin, response);
	}
	
	/**
	 * 校验密码是否正确
	 * @param pw 密码
	 * @param request
	 * @param response
	 */
	public void checkPw(String pw, HttpServletRequest request, HttpServletResponse response) {
		String pwInSession = (String) request.getSession().getAttribute("adminPw");
		
	}
}
