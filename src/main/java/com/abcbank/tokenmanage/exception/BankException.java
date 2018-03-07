package com.abcbank.tokenmanage.exception;
/**
 * 
 * @author azharm
 *
 */
public class BankException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2216338787541932451L;

	public BankException(String errMesg)
	{
		super(errMesg);
	}
}
