package com.cqupt.dormitory.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据类型工具类
 * @author Bern
 *
 */
public class DataTypeUtils {
	
	/**
	 * 
	 * @param date
	 */
	public static String formateDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}
}
