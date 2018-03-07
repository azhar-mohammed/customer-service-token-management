package com.abcbank.tokenmanage.exception;
/**
 * 
 * @author azharm
 *
 */
public class ExceptionWrapper {
	private int statusCode;
	private String exceptionMessage;
	private String url;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}




}
