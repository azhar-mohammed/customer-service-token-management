/**
 * 
 */
package com.abcbank.tokenmanage.dto;

/**
 * @author azharm
 *
 */
public class BankDTO {

	private int bankId;

	private String bankName;

	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
