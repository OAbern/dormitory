package com.cqupt.dormitory.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传文件的帮助类
 * @author Bern
 *
 */
public class UploadFileUtils {
	
	public static boolean saveFile(InputStream inputStream, File file) {
		FileOutputStream outputStream = null;
		try {
			if(file.exists()) {
				return false;
			}
			file.createNewFile();
			outputStream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len=inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, len);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	/**
	 * 根据当前系统的时间生成唯一名字
	 * @param fileName
	 * @return
	 */
	public static String getUniqueName() {
		Date nowTime = new Date();
		SimpleDateFormat formatTime = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = formatTime.format(nowTime);		//利用当前系统时间作为文件名字，唯一标识
		return fileName;
	}
	
}
