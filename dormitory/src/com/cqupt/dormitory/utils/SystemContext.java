package com.cqupt.dormitory.utils;

/**
 * 封装分页参数的类
 * 使用ThreadLocal来实现减少分页参数的传递
 * @author Bern
 *
 */
public class SystemContext {
	private static ThreadLocal<Integer> page = new ThreadLocal<Integer>();	//要查询的页数
	private static ThreadLocal<Integer> rows = new ThreadLocal<Integer>();	//每页显示的条数
	private static ThreadLocal<Integer> total = new ThreadLocal<Integer>(); //总记录数
	
	/*page: get set remove*/
	public static int getPage() {
		Integer temp = page.get();
		if(temp == null) {
			return -1;
		}else {
			return temp;
		}
		
	}
	public static void setPage(int pageValue) {
		page.set(pageValue);
	}
	public static void removePage() {
		page.remove();
	}
	
	/* rows:get set remove*/
	public static int getRows() {
		Integer temp = rows.get();
		if(temp == null) {
			return -1;
		}else {
			return temp;
		}
	}
	public static void setRows(int rowsValue) {
		rows.set(rowsValue);
	}
	public static void removeRows() {
		rows.remove();
	}
	
	/* total: get set remove */
	public static int getTotal() {
		Integer temp = total.get();
		if(temp == null) {
			return -1;
		}else {
			return temp;
		}
	}
	public static void setTotal(int totalValue) {
		total.set(totalValue);
	}
	public static void removeTotal() {
		total.remove();
	}
	
	/**
	 * 移除所有的变量
	 */
	public static void removeAll() {
		rows.remove();
		page.remove();
		total.remove();
	}
	
}
