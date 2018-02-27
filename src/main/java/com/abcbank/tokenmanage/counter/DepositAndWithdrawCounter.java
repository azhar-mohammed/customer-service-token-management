package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenCounter;
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
	int counterId;
	private Lock lock= new ReentrantLock();

	public DepositAndWithdrawCounter(String counterName, String counterType, TokenService tokenService, int counterId) {
		this.counterName = counterName;
		this.counterType = counterType;
		this.tokenService = tokenService;
		this.counterId = counterId;
	}

	public void performDeposit() {
	
	}

	public void performWithdrawl()

	{
		
	}

	@Override
	public void receiveMessage(Token token) throws Exception {
		
		
		System.out.println("received message " + token + " at counter" + counterName);
		

		TokenCounter tokenCounter = new TokenCounter();
		tokenCounter.setCounterId(counterId);
		tokenCounter.setTokenId(token.getTokenId());
		TokenCounter savedTokenCounter = tokenService.saveTokenCounterMapping(tokenCounter);
	
	
		token.setTokenStatus(TokenStatus.INPROGRESS);
		tokenService.updateToken(token);
		lock.lock();
		TimeUnit.MINUTES.sleep(2);
		lock.unlock();
		
		String requiredServices[] = token.getRequiredServices().split(",");

		// Add validation and throw exception
		if (requiredServices[token.getNextStep()-1].equals(ServiceType.DEPOSIT.toString())) {
			performDeposit();
			token.setComments(token.getComments()+" Performed Deposit operation.");
			tokenService.updateToken(token);
			
		} else if (requiredServices[token.getNextStep()-1].equals(ServiceType.WITHDRAW.toString())) {
			performWithdrawl();
			token.setComments(token.getComments()+" Performed Withdrawl operation.");
			tokenService.updateToken(token);
		}
		
		tokenService.deleteTokenCounterMapping(savedTokenCounter.getId());

		if (token.isFurtherProcessingRequired()) {
			tokenService.requeToken(token);
		}

	}

}
