package com.abcbank.tokenmanage.exception;


import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler({CounterException.class,CustomerException.class,TokenException.class,TokenCounterMappingException.class})
	public @ResponseBody ExceptionJSONWrapper handleCounterException(HttpServletRequest request, Exception ex) 
	{
		ExceptionJSONWrapper response = new ExceptionJSONWrapper();
		response.setUrl(request.getRequestURL().toString());
		response.setExceptionMessage(ex.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return response;

	}
	
}
