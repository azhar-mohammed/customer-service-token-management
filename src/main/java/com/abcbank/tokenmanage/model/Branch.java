/**
 * 
 */
package com.abcbank.tokenmanage.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * This class represents a branch of a bank
 * 
 * @author azharm
 *
 */
@Entity
public class Branch {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int branchId;
	@Column
	private String branchName;
	@Column
	private String location;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "bankId")
	private Bank   bank;
	
	@JsonInclude(Include.NON_DEFAULT)
	@OneToMany(mappedBy = "branch")
	Set<Counter> counters;

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
	
	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Set<Counter> getCounters() {
		return counters;
	}

	public void setCounters(Set<Counter> counters) {
		this.counters = counters;
	}

	@Override
	public String toString() {
		return "Branch [branchId=" + branchId + ", branchName=" + branchName + ", location=" + location + ", bank="
				+ bank + ", counters=" + counters + "]";
	}
	
	


}
