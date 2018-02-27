package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;

import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenCounter;
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
     int counterId;
     private Lock lock= new ReentrantLock();
	
	
	public DepositCounter(String counterName,String counterType,TokenService tokenService,int counterId)
	{
		this.counterType = counterType;
		this.counterName = counterName;
		this.tokenService = tokenService;
		this.counterId = counterId;
	}
	
	@Override
	public void receiveMessage(Token token) throws Exception {
		
		System.out.println("received message "+token+" at counter"+counterName);
		
		TokenCounter tokenCounter = new TokenCounter();
		tokenCounter.setCounterId(counterId);
		tokenCounter.setTokenId(token.getTokenId());
		TokenCounter savedTokenCounter = tokenService.saveTokenCounterMapping(tokenCounter);
		
		token.setTokenStatus(TokenStatus.INPROGRESS);
		tokenService.updateToken(token);
		
		lock.lock();
		TimeUnit.MILLISECONDS.sleep(6000);
		lock.unlock();
		
		token.setComments(token.getComments()+" Performed Deposit operation.");
		tokenService.updateToken(token);
		
		tokenService.deleteTokenCounterMapping(savedTokenCounter.getId());
		
		if(token.isFurtherProcessingRequired())
		{
			tokenService.requeToken(token);
		}

	}

}
