package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;

import com.abcbank.tokenmanage.model.Token;

/**
 * 
 * @author azharm
 *
 */
public class DepositAndWithdrawCounter implements Receiver {

	String counterName;
	String counterType;

	public DepositAndWithdrawCounter(String counterName, String counterType) {
		this.counterName = counterName;
		this.counterType = counterType;
	}

	public void performDeposit() {
		System.out.println();
	}

	public void performWithdrawl()

	{
		System.out.println();
	}

	@Override
	public void receiveMessage(Token token) throws Exception {
		
		if(token.getRequiredServices().equals("DEPOSIT"))
		{
			performDeposit();
		}
		else if(token.getRequiredServices().equals("WITHDRAWL"))
		{
			performWithdrawl();
		}
		System.out.println("received message "+token+" at counter"+counterName);
		TimeUnit.MINUTES.sleep(2);
	}

}
