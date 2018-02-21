package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.model.Token;

public class WithdrawlCounter implements Receiver {

	String counterType;
	String counterName;
	
	public WithdrawlCounter(String counterName,String counterType)
	{
		this.counterType = counterType;
		this.counterName = counterName;
	}
	
	
	@Override
	public void receiveMessage(Token token) throws Exception {
		
		System.out.println("received message "+token+" at counter"+counterName);

	}

}
