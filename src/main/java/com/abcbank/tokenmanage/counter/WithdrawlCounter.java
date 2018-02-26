package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.TokenService;

public class WithdrawlCounter implements Receiver {

	String counterType;
	String counterName;
	TokenService tokenService;
	
	public WithdrawlCounter(String counterName,String counterType,TokenService tokenService)
	{
		this.counterType = counterType;
		this.counterName = counterName;
		this.tokenService = tokenService;
	}
	
	
	@Override
	public void receiveMessage(Token token) throws Exception {
		
		System.out.println("received message "+token+" at counter"+counterName);

	}

}
