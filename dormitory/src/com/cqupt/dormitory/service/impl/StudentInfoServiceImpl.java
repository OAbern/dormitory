package com.cqupt.dormitory.service.impl;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cqupt.dormitory.dao.StudentInfoDao;
import com.cqupt.dormitory.model.Student;
import com.cqupt.dormitory.service.StudentInfoService;
import com.cqupt.dormitory.vo.Factor;
import com.cqupt.dormitory.vo.ClassAndMajor;
import com.cqupt.dormitory.vo.Condition;

/**
 * 处理学生信息业务的实现类
 * @author Bern
 *
 */
@Service
public class StudentInfoServiceImpl implements StudentInfoService {
	@Resource(name="studentInfoDaoImpl")
	StudentInfoDao studentInfoDao;
	
	public void setStudentInfoDao(StudentInfoDao studentInfoDao) {
		this.studentInfoDao = studentInfoDao;
	}
	
	@Override
	public boolean addStudent(Student student) {
		boolean result = studentInfoDao.addStudent(student);
		return result;
	}

	@Override
	public List<Student> findStudentByFactor(List<Factor> factors) {
		return studentInfoDao.findStudentByFactor(factors);
	}

	@Override
	public boolean updateStudent(Student student) {
		boolean result = studentInfoDao.updateStudent(student);
		return result;
	}

	@Override
	public boolean deleteStudentByStuId(List<String> idList) {
		boolean result = studentInfoDao.deleteStudentByStuId(idList);
		return result;
	}

	@Override
	public List<ClassAndMajor> findClassAndMajor() {	
		return studentInfoDao.findClassAndMajor();
	}

	@Override
	public List<String> findCascadingInfo(Condition condition) {
		return studentInfoDao.findCascadingInfo(condition);
	}

	@Override
	public List<Student> findStudentByCondition(Condition condition) {
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public List<Student> findStudentOutByCondition(Condition condition) {
		condition.setLivingStatus(2);
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public List<Student> findStudentWithRoom(Condition condition) {
		condition.setLivingStatus(4);
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public List<Student> findStudentCheckOutByCondition(Condition condition) {
		condition.setLivingStatus(1);
		return studentInfoDao.findStudentByCondition(condition);
	}

	@Override
	public boolean updateOutOfRoom(String studentNum) {
		String[] studentNums = {studentNum};
		return this.updateOutOfRoom(studentNums);
	}

	@Override
	public boolean updateOutOfRoom(String[] studentNum) {
		try {
			studentInfoDao.updateOutRoom(studentNum);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteStudentOutLiving(List<String> stuNum) {
		return studentInfoDao.changeLivingStatus(stuNum, 2);
	}

	@Override
	public boolean addStudentOutLiving(Student student) {
		return studentInfoDao.addStudentOutLiving(student);
	}

	@Override
	public boolean checkOutStudent(List<String> stuNum) {
		return studentInfoDao.changeLivingStatus(stuNum, 1);
	}

	@Override
	public Student findStudentByStuNum(String stuNum) {
		return studentInfoDao.findStudentByStuNum(stuNum);
	}

	@Override
	public void updateOutOfRoom(List<String> studentNums) {
		String[] a = new String[studentNums.size()];
		studentNums.toArray(a);
		if(a.length<1){
			a[0] = "null";
		}
		this.updateOutOfRoom(a);
	}

	@Override
	public boolean addStudentByExcel(MultipartFile file) {
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
			
			Sheet sheet1 = wb.getSheetAt(0);
			for(Row row : sheet1) {
				for(Cell cell : row) {
//					cell.get
					cell.getStringCellValue();
				}
			}
			//TODO
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Student> findStudentDeploy(String tecNum) {
		return studentInfoDao.findStudentDeploy(tecNum);
	}

}
