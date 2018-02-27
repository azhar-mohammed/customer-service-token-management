package com.abcbank.tokenmanage.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
	@Column
	private ServiceType counterServiceType;
	@Column
	private String counterType;
	@Transient
	private Token token;

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

	public ServiceType getCounterServiceType() {
		return counterServiceType;
	}

	public void setCounterServiceType(ServiceType counterServiceType) {
		this.counterServiceType = counterServiceType;
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

}
