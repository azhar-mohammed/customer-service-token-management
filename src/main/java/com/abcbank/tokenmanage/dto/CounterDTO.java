package com.abcbank.tokenmanage.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author azharm
 *
 */

public class CounterDTO {

	@JsonIgnore
	private int counterId;
	
	private String counterName;

	private String counterService;

	private String counterType;

	@JsonInclude(Include.NON_NULL)
	private List<TokenDTO> tokens;

	public String getCounterName() {
		return counterName;
	}

	public void setCounterName(String counterName) {
		this.counterName = counterName;
	}

	public String getCounterService() {
		return counterService;
	}

	public void setCounterService(String counterService) {
		this.counterService = counterService;
	}

	public String getCounterType() {
		return counterType;
	}

	public void setCounterType(String counterType) {
		this.counterType = counterType;
	}

	public List<TokenDTO> getTokens() {
		return tokens;
	}

	public void setTokens(List<TokenDTO> tokens) {
		this.tokens = tokens;
	}

	public int getCounterId() {
		return counterId;
	}

	public void setCounterId(int counterId) {
		this.counterId = counterId;
	}

}
