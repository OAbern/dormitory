package com.cqupt.dormitory.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.model.Teacher;

/**
 * 检查是否登录的拦截器(拦截*.do的请求)
 * @author Bern
 *
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object ) throws Exception {
		
		String url = request.getRequestURI();
		if(url.equals("/dormitory/loginCheck.do")) {
			return true;
		}
		
		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(teacher != null) {
			return true;
		}else if(admin != null) {
			return true;
		}else {
			response.sendRedirect("/dormitory/login.html");
			return false;
		}
	}

}
