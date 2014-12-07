package com.cqupt.dormitory.dao;

import com.cqupt.dormitory.model.Admin;

/**
 * 处理管理员数据的DAO层接口
 * @author Bern
 *
 */
public interface AdminDao {
	
	/**
	 * 根据管理员的账号和密码查找管理员
	 * @param admin 管理元的name和pw不能为空
	 * @return 符合条件的管理员信息
	 */
	public Admin findAdminByNumAndPw(Admin admin);
	
	/**
	 * 更新管理员信息
	 * @param admin 
	 * @return
	 */
	public boolean updateAdminInfo(Admin admin);
	
	/**
	 * 根据辅导员id查找辅导员
	 * @param id
	 * @return
	 */
	public Admin findAdminById(int id);
}
