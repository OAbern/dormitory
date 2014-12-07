package com.cqupt.dormitory.dao.impl;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.AdminDao;
import com.cqupt.dormitory.model.Admin;

/**
 * 处理管理员数据的DAO实现类
 * @author Bern
 *
 */
@Repository
public class AdminDaoImpl extends BaseDaoSupport implements AdminDao {

	@Override
	public Admin findAdminByNumAndPw(Admin a) {
		Admin admin = null;
		try {
			admin = getSqlSession().selectOne("com.cqupt.dormitory.model.Admin.findAdminByNumAndPw", a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

	@Override
	public boolean updateAdminInfo(Admin admin) {
		int result = -1;
		try{
			result = getSqlSession().update("com.cqupt.dormitory.model.Admin.updateAdminInfo", admin);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(result > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Admin findAdminById(int id) {
		Admin admin = null;
		try {
			admin = getSqlSession().selectOne("com.cqupt.dormitory.model.Admin.findAdminById", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return admin;
	}

}
