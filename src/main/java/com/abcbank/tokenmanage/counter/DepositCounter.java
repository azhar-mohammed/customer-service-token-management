package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.model.Token;

public class DepositCounter implements Receiver {

	String counterName;
	String counterType;
	
	
	public DepositCounter(String counterName,String counterType)
	{
		this.counterType = counterType;
		this.counterName = counterName;
	}
	
	@Override
	public void receiveMessage(Token token) throws Exception {
		
		System.out.println("received message "+token+" at counter"+counterName);

	}

}
