package com.cqupt.dormitory.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqupt.dormitory.dao.AcademyDao;
import com.cqupt.dormitory.model.Academy;

/**
 * 学院信息类的DAO层实现
 * @author Bern
 *
 */
@Repository
public class AcademyDaoImpl extends BaseDaoSupport implements AcademyDao {
	
	@Override
	public List<Academy> findAllAcademy() {
		List<Academy> list = null;
		try {
			list = getSqlSession().selectList("com.cqupt.dormitory.model.Academy.findAllAcademy");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Academy findAcademyByName(String name) {
		Academy academy = null;
		try {
			academy = getSqlSession().selectOne("com.cqupt.dormitory.model.Academy.findAcademyByName", name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return academy;
	}

}
