package com.cqupt.dormitory.utils;
/**
 * 错误信息类
 * @author Bern
 *
 */
public class ErrorInfo {
	private String errorInfo;	//错误信息提示
	private String forwardUrl;	//转发地址
	private String redirectUrl;	//重定向地址
	
	public String getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
	
}
