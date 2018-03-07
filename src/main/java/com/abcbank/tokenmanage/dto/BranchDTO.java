/**
 * 
 */
package com.abcbank.tokenmanage.dto;

import java.util.Set;

import com.abcbank.tokenmanage.model.Counter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author azharm
 *
 */
public class BranchDTO {
	
	@JsonInclude(Include.NON_DEFAULT)
	private int branchId;
	
	private String branchName;
	
	private String location;
	
	private int   bankId;
	
	@JsonInclude(Include.NON_DEFAULT)
	private Set<Counter> counters;

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public Set<Counter> getCounters() {
		return counters;
	}

	public void setCounters(Set<Counter> counters) {
		this.counters = counters;
	}
	
	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	

}
