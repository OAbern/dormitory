package com.cqupt.dormitory.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.cqupt.dormitory.model.Admin;
import com.cqupt.dormitory.model.Teacher;

/**
 * 检查是否登录的拦截器(拦截直接请求html页面)
 * @author Bern
 *
 */
public class LoginCheckFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
//		Teacher teacher = (Teacher) request.getSession().getAttribute("teacher");
//		Admin admin = (Admin) request.getSession().getAttribute("admin");
//		if(teacher != null) {
//			chain.doFilter(request, response);
//		}else if(admin != null) {
//			chain.doFilter(request, response);
//		}else {
//			
//		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
