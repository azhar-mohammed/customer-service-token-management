package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;

import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.abcbank.tokenmanage.service.TokenService;
/**
 * 
 * @author azharm
 *
 */
public class DepositCounter implements Receiver {
	
	TokenService tokenService;
	
	String counterName;
	String counterType;
	
	
	public DepositCounter(String counterName,String counterType,TokenService tokenService)
	{
		this.counterType = counterType;
		this.counterName = counterName;
		this.tokenService = tokenService;
	}
	
	@Override
	public void receiveMessage(Token token) throws Exception {
		
		System.out.println("received message "+token+" at counter"+counterName);
		token.setTokenStatus(TokenStatus.INPROGRESS);
		tokenService.updateToken(token);
		TimeUnit.MINUTES.sleep(1);
		if(token.isFurtherProcessingRequired())
		{
			tokenService.requeToken(token);
		}

	}

}
