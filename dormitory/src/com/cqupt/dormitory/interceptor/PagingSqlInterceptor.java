package com.cqupt.dormitory.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.cqupt.dormitory.utils.DBManager;
import com.cqupt.dormitory.utils.SystemContext;

/**
 * 分页处理的sql拼装的拦截器
 * 仅支持Mysql
 * @author Bern
 *
 */
@Component
@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})})
public class PagingSqlInterceptor implements Interceptor {
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		int page = SystemContext.getPage();
	    int rows = SystemContext.getRows();
	    if(page<0 || rows<0) {
	    	return invocation.proceed();
	    }
	    int offSet = computeOffset(page, rows);
	    
		/*获取sql语句，并进行拼装*/
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
	    MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory());
//	    BoundSql boundSql = statementHandler.getBoundSql();
	    String originalSql = (String)metaStatementHandler.getValue("delegate.boundSql.sql");
//	    System.out.println("原始的sql："+originalSql);
	    //如果sql不是以“SELECT”开头，则放弃执行分页sql拼装
	    if(!originalSql.startsWith("SELECT")) {
	    	return invocation.proceed();
	    }
	    
	    
	    //拼装统计所有数目的sql
	    int a = originalSql.indexOf("FROM");
	    String sqlCounting = null;
	    if(a > 0) {
	    	sqlCounting = "SELECT COUNT(*) "+originalSql.substring(a);
	    }
//	    System.out.println("sqlCounting:"+sqlCounting);
	    setCountNum(sqlCounting);	//设置total到ThreadLocal
	    
	    //拼装带分页限制的sql
	    String sqlWithPaging = originalSql + " LIMIT "+offSet+", "+rows;
	    metaStatementHandler.setValue("delegate.boundSql.sql", sqlWithPaging);
//	    System.out.println("生成分页SQL : "+ boundSql.getSql());
	    
	    return invocation.proceed();  
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 根据分页参数计算首个记录的位移量
	 * @param page 查找的页数
	 * @param rows 每页显示的记录数
	 * @return
	 */
	public int computeOffset(int page, int rows) {
		int offSet = (page-1)*rows;
		return offSet;
	}
	
	/**
	 * 将记录的总数设置在ThreadLocal中
	 * @param sql 统计记录总数的sql
	 */
	public void setCountNum(String sql) {
		SqlSession sqlSession = DBManager.getSqlSession();
		Connection conn = sqlSession.getConnection();
		PreparedStatement pStat = null;
		ResultSet resultSet = null;
		int count = -1;
		try {
			pStat = conn.prepareStatement(sql);
			resultSet = pStat.executeQuery();
			if(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeLink(conn, pStat, resultSet);
			DBManager.closeSqlSession(sqlSession);
		}
		
		
		/*将统计出来的总记录数放入ThreadLocal中*/
		if(count > 0) {
			SystemContext.setTotal(count);
		}
		
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param pStat
	 * @param rs
	 */
	public void closeLink(Connection conn, PreparedStatement pStat, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pStat != null) {
				pStat.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
