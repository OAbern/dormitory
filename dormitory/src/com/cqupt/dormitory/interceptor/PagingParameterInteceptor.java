package com.cqupt.dormitory.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cqupt.dormitory.utils.SystemContext;

/**
 * 分页处理的参数封装拦截器
 * @author Bern
 *
 */
public class PagingParameterInteceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
		
		/* 移除ThreadLocal中的分页参数（必须执行）*/
		SystemContext.removeAll();
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		
		int page, rows;

		/*获取分页参数*/
		String pageString = request.getParameter("page");
		String rowsString = request.getParameter("rows");
		if(pageString==null || rowsString==null) {
			return true;
		}
		
		try{
			page = Integer.parseInt(pageString);
			rows = Integer.parseInt(rowsString);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return true;
		}
		
		if(page<=0 || rows<=0) {
			return true;
		}
		
		/*向 ThreadLocal 中设置参数*/
		SystemContext.setPage(page);
		SystemContext.setRows(rows);
		return true;
	}
	
}
