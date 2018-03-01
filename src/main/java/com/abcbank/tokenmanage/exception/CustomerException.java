package com.abcbank.tokenmanage.exception;
/**
 * 
 * @author azharm
 *
 */
public class CustomerException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5582407202152136789L;

	public CustomerException(String errorMessage)
	{
		super(errorMessage);
	}

}
