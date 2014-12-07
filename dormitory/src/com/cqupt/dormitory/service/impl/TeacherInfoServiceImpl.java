package com.cqupt.dormitory.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.dao.TeacherInfoDao;
import com.cqupt.dormitory.model.Teacher;
import com.cqupt.dormitory.service.TeacherInfoService;
import com.cqupt.dormitory.utils.ExcelUtils;
/**
 * 处理教师信息类的Service实现类
 * @author Bern
 *
 */
@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {
	@Resource(name="teacherInfoDaoImpl")
	TeacherInfoDao teacherInfoDao;
	
	@Resource(name="excelUtils")
	private ExcelUtils excelUtils;
	
	@Override
	public Teacher findTeacherByNameAndAcademyId(String name, int academyId) {
		Teacher teacher = teacherInfoDao.findTeacherByNameAndAcademyId(name, academyId);
		return teacher;
	}

	@Override
	public Teacher findTeacherByTecNum(String tecNum) {
		return teacherInfoDao.findTeacherByTecNum(tecNum);
	}

	@Override
	public Teacher findTeacherByNumAndPw(Teacher teacher) {
		return teacherInfoDao.findTeacherByNumAndPw(teacher);
	}

	@Override
	public boolean updateTeacherInfo(Teacher teacher) {
		return teacherInfoDao.updateTeacherInfo(teacher);
	}

	@Override
	public List<String> findMajorByTecId(int tecId) {
		return teacherInfoDao.findMajorByTecId(tecId);
	}

	@Override
	public Teacher findTeacherByClassNum(String classNum) {
		return teacherInfoDao.findTeacherByClassNum(classNum);
	}

	@Override
	public List<Teacher> findTeacherByAcademy(int academy) {
		return teacherInfoDao.findTeacherByAcademy(academy);
	}

	@Override
	public boolean deleteTeacherByTecNum(List<String> list) {
		return teacherInfoDao.deleteTeacherByTecNum(list);
	}

	@Override
	public boolean addTeacherByExcel(MultipartFile file) {
		try {
			InputStream fs = file.getInputStream();
			String lastName= null;
			Pattern pat = Pattern.compile("\\.[\\w]+");
	 		Matcher m = pat.matcher(file.getOriginalFilename());
			while(m.find()){
				lastName = m.group();
			}
			Workbook wb = null ;
			if(lastName.equals(".xls")){
				wb = new HSSFWorkbook(fs);
			}else if(lastName.equals(".xlsx")){
				wb = new XSSFWorkbook(fs);
			}else{
				throw  new RuntimeException();
			}
			
			/*将Excel里的信息转换成对象集合*/
			Sheet sheet1 = wb.getSheetAt(0);

			List<Teacher> teachers = new ArrayList<Teacher>();
			for(int j=1; j<=sheet1.getLastRowNum(); j++) {
				Teacher teacher = excelUtils.toTeacher(sheet1.getRow(j));
				if(teacher == null) {
					return false;
				}
				teachers.add(teacher);
			}
			
			/*执行批量插入*/
			for(Teacher teacher : teachers) {
				boolean result = teacherInfoDao.addTeacher(teacher);
				if(!result) {
					return false;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}