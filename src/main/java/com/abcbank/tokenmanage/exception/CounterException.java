/**
 * 
 */
package com.abcbank.tokenmanage.exception;

/**
 * @author azharm
 *
 */
public class CounterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8320005887722695048L;

	public CounterException(String errorMessage)
	{
	   super(errorMessage);
	}
}
