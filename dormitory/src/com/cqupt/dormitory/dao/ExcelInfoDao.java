package com.cqupt.dormitory.dao;

import java.util.List;

import com.cqupt.dormitory.model.ExcelInfo;

/**
 * 处理寝室调换的Excel的DAO层接口
 * @author Bern
 *
 */
public interface ExcelInfoDao {
	/**
	 * 添加excel信息
	 * @param excelInfo
	 */
	public boolean addExcel(ExcelInfo excelInfo);
	
	/**
	 * 根据id删除excel信息
	 * @param excelInfo
	 */
	public boolean deleteExcelById(List<Integer> ids);
	
	/**
	 * 查找所有的excel信息
	 * @return
	 */
	public List<ExcelInfo> findAllExcel();
	
	/**
	 * 根据教工号查找相关的提交的excel信息
	 * @param tecNum
	 * @return
	 */
	public List<ExcelInfo> findExcelByTecNum(String tecNum);
}
