package com.cqupt.dormitory.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.dao.ExcelInfoDao;
import com.cqupt.dormitory.model.ExcelInfo;
import com.cqupt.dormitory.service.ExcelInfoService;
import com.cqupt.dormitory.utils.UploadFileUtils;

/**
 * 处理宿舍分配Excel信息的Service层实现
 * @author Bern
 *
 */
@Service
public class ExcelInfoServiceImpl implements ExcelInfoService {
	@Resource(name="excelInfoDaoImpl")
	private ExcelInfoDao excelInfoDao;
	
	@Override
	public boolean addExcel(ExcelInfo excelInfo) {
		return excelInfoDao.addExcel(excelInfo);
	}

	@Override
	public boolean deleteExcelById(List<Integer> ids) {
		return excelInfoDao.deleteExcelById(ids);
	}

	@Override
	public List<ExcelInfo> findAllExcel() {
		return excelInfoDao.findAllExcel();
	}

	@Override
	public List<ExcelInfo> findExcelByTecNum(String tecNum) {
		return excelInfoDao.findExcelByTecNum(tecNum);
	}

	@Override
	public boolean changeExcelStatus(List<Integer> ids, int approvedAdminId,
			int status) {
		
		return excelInfoDao.changeExcelStatus(ids, approvedAdminId, status);
	}

	@Override
	public ExcelInfo findExcelById(int id) {
		List<ExcelInfo> excels = this.findAllExcel();
		ExcelInfo excel = null;
		for(ExcelInfo e: excels){
			if(e.getId() == id){
				excel = e;
			}
		}
		return excel;
	}	

}
