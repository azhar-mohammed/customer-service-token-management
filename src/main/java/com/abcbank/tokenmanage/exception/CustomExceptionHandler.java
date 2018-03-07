package com.abcbank.tokenmanage.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({Exception.class})
	public @ResponseBody ExceptionWrapper handleCounterException(HttpServletRequest request, Exception ex) 
	{
		ExceptionWrapper response = new ExceptionWrapper();
		response.setUrl(request.getRequestURL().toString());
		response.setExceptionMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return response;

	}
	
}
