package com.abcbank.tokenmanage.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * This class represents a counter which serves a particular request in a bank.
 * 
 * @author azharm
 *
 */
@Entity
public class Counter {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int counterId;
	@Column
	private String counterName;
	@ElementCollection
	private List<String> counterServices;
	@Column
	private String counterType;


	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public List<String> getCounterServices() {
		return counterServices;
	}

	public void setCounterServices(List<String> counterServices) {
		this.counterServices = counterServices;
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}


}
