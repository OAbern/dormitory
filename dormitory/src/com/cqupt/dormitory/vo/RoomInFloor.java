package com.cqupt.dormitory.vo;

import java.util.List;
import java.util.Map;

/**
 * RoomInFloor
 * 
 * 这个类最恶心!尤其是那个map<String,String> 里面要放各种拼接好的字符串
 * 
 * 2014年11月26日 下午10:04:43
 */
public class RoomInFloor {
	private int total;
	private int columnMax;
	private List<Map<String,String>> rows; //这里的key 是序号.然后value是 roomid+<br>+已入住人数+/+总共人数
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getColumnMax() {
		return columnMax;
	}
	public void setColumnMax(int columnMax) {
		this.columnMax = columnMax;
	}
	public List<Map<String, String>> getRows() {
		return rows;
	}
	public void setRows(List<Map<String, String>> rows) {
		this.rows = rows;
	}
	
}
