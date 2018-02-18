package com.abcbank.tokenmanage.model;

import java.util.List;

/**
 * This class represents a counter which serves a particular request in a bank.
 * @author azharm
 *
 */
public class Counter {
	
	private Long counterId;
	private ServiceType counterServiceType;
	private String counterType;
	private List<Token> tokens;
	
	
	public Long getCounterId() {
		return counterId;
	}
	public void setCounterId(Long counterId) {
		this.counterId = counterId;
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
	public List<Token> getTokens() {
		return tokens;
	}
	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	

}
