package com.cqupt.dormitory.utils;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class DBManager {
	private static WebApplicationContext webApplicationContext;
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		sqlSessionFactory = (SqlSessionFactory )webApplicationContext.getBean("sqlSessionFactory");
	}
	
	/**
	 * 获取sqlSession
	 * @return
	 */
	public static synchronized SqlSession getSqlSession(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	/**
	 * 关闭sqlSession
	 * @param sqlSession
	 */
	public static void closeSqlSession(SqlSession sqlSession) {
		if(sqlSession != null) {
			sqlSession.close();
		}
	}
	
}
