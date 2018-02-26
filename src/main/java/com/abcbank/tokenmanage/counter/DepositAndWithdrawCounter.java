package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;

import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
public class DepositAndWithdrawCounter implements Receiver {

	TokenService tokenService;
	
	String counterName;
	String counterType;

	public DepositAndWithdrawCounter(String counterName, String counterType,TokenService tokenService) {
		this.counterName = counterName;
		this.counterType = counterType;
		this.tokenService = tokenService;
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
		
		String requiredServices[] = token.getRequiredServices().split(",");
		
		
		if(requiredServices[token.getNextStep()].equals("DEPOSIT"))
		{
			performDeposit();
		}
		else if(requiredServices[token.getNextStep()].equals("WITHDRAWL"))
		{
			performWithdrawl();
		}
		System.out.println("received message "+token+" at counter"+counterName);
		token.setTokenStatus(TokenStatus.INPROGRESS);
		tokenService.updateToken(token);
		TimeUnit.MINUTES.sleep(2);
		
		if(token.isFurtherProcessingRequired())
		{
			tokenService.requeToken(token);
		}
		
	}

}
