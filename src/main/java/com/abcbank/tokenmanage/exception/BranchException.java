/**
 * 
 */
package com.abcbank.tokenmanage.exception;

/**
 * @author azharm
 *
 */
public class BranchException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1177307919791445843L;

	public BranchException(String errMessage) {
		super(errMessage);
	}
}
