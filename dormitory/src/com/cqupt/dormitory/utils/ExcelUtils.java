package com.cqupt.dormitory.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.cqupt.dormitory.model.Academy;
import com.cqupt.dormitory.model.Student;

/**
 * excel信息转换的工具类
 * @author Bern
 *
 */
public class ExcelUtils {
	
	/**
	 * 将一行Excel数据转换为student对象
	 * @param row
	 * @return
	 */
	public static Student toStudent(Row row) {
		int i = 1;
		Student student = new Student();
		for(Cell cell : row) {
			switch (i) {
			case 1:{	//设置学号
				student.setStuNum(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));
				i++;
				break;
			}
			case 2:{	//设置名字
				student.setName(cell.getStringCellValue());
				i++;
				break;
			}
			case 3:{	//设置性别
				student.setSex(cell.getStringCellValue());
				i++;
				break;
			}
			case 4:{	//设置学院
				//TODO
				student.setAcademy(new Academy());
				i++;
				break;
			}
			case 5:{	//设置年级
				student.setGrade(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));
				i++;
				break;
			}
			case 6:{	//设置专业
				student.setMajor(cell.getStringCellValue());
				i++;
				break;
			}
			case 7:{	//设置班级
				student.setClassNum(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));
				i++;
				break;
			}
			case 8:{	//设置出生年月
				student.setBirth(cell.getDateCellValue());
				i++;
				break;
			}
			case 9:{	//设置籍贯
				student.setBirthplace(cell.getStringCellValue());
				i++;
				break;
			}
			case 10:{	//设置民族
				student.setNation(cell.getStringCellValue());
				i++;
				break;
			}
			case 11:{	//设置身份证
				student.setIdentity(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));
				i++;
				break;
			}
			case 12:{	//设置电话
				student.setPhone(String.valueOf(Math.round(Double.parseDouble(cell.toString()))));
				i++;
				break;
			}
			case 13:{	//设置学历
				student.setEducation(cell.getStringCellValue());
				i++;
				break;
			}
			
			default:
				break;
			}
		}
		return student;
	}
}
