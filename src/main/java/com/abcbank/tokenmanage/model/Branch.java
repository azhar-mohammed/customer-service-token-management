/**
 * 
 */
package com.abcbank.tokenmanage.model;

/**
 * This class represents a branch of a bank
 * 
 * @author azharm
 *
 */
public class Branch {

	private long branchId;
	private String branchName;
	private String IFSCCode;
	private String location;

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIFSCCode() {
		return IFSCCode;
	}

	public void setIFSCCode(String iFSCCode) {
		IFSCCode = iFSCCode;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
