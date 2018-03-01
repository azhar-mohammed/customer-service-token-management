package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.model.TokenCounterMapping;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.abcbank.tokenmanage.service.TokenService;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

public class WithdrawlCounter implements Receiver {

	int counterId;
	String counterType;
	String counterName;
	TokenService tokenService;


	public WithdrawlCounter(int counterId,String counterName, String counterType, TokenServiceImplementation tokenService
			) 
	{
		this.counterId = counterId;
		this.counterType = counterType;
		this.counterName = counterName;
		this.tokenService = tokenService;
		
	}

	@Override
	public void receiveToken(TokenDTO tokenDTO) throws Exception {

		System.out.println("received message " + tokenDTO + " at counter" + counterName);

		mapTokenToCounter(tokenDTO);

		updateTokenStatusAsInProgress(tokenDTO);

		serveToken();

		updateTokenComments(tokenDTO);

		if (tokenDTO.isFurtherProcessingRequired()) {
			tokenService.queueToken(tokenDTO);
		}
		else
		{
			updateTokenStatusAsCompleted(tokenDTO);
		}
	}

	private void serveToken() throws InterruptedException {

		TimeUnit.MILLISECONDS.sleep(6000);

	}

	private void updateTokenComments(TokenDTO tokenDTO) {

		tokenDTO.setComments(tokenDTO.getComments() + " Performed Deposit operation.");
		tokenService.updateToken(tokenDTO);

	}

	private void updateTokenStatusAsInProgress(TokenDTO tokenDTO) {

		tokenDTO.setTokenStatus(TokenStatus.INPROGRESS);
		tokenService.updateToken(tokenDTO);

	}

	private void updateTokenStatusAsCompleted(TokenDTO tokenDTO) {

		tokenDTO.setTokenStatus(TokenStatus.COMPLETED);
		tokenService.updateToken(tokenDTO);

	}
	
	private TokenCounterMapping mapTokenToCounter(TokenDTO tokenDTO) {

		TokenCounterMapping tokenCounter = new TokenCounterMapping();
		tokenCounter.setCounterId(counterId);
		tokenCounter.setTokenId(tokenDTO.getTokenId());
		return tokenService.saveTokenCounterMapping(tokenCounter);

	}

}
