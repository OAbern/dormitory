package com.cqupt.dormitory.vo;
/**
 * 结果信息类
 * @author Bern
 *
 */
public class ResultMessage {
	public static int FAILED = 0;
	public static int SUCCESS = 1;
	
	private int status;		//操作结果状态值 0表示失败，1表示成功
	private String info;	//操作结果信息
	private String error;	//错误原因
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
