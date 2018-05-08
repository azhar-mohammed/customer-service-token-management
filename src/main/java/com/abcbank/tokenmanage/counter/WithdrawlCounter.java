package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

public class WithdrawlCounter extends AbstractCounter implements Receiver {

	public WithdrawlCounter(int counterId, String counterName, String counterType,
			TokenServiceImplementation tokenService) {
		this.counterId = counterId;
		this.counterType = counterType;
		this.counterName = counterName;
		this.tokenService = tokenService;

	}

	@Override
	public void receiveToken(TokenDTO tokenDTO) throws Exception {

		System.out.println("received message " + tokenDTO + " at counter " + counterName);

		mapTokenToCounter(tokenDTO);

		updateTokenStatusAsInProgress(tokenDTO);

		serveToken();

		updateTokenComments(tokenDTO, "Performed Withdrawl operation");

		updateTokenStatusAsCompleted(tokenDTO);

	}

	private void serveToken() throws InterruptedException {

		TimeUnit.MILLISECONDS.sleep(6000);

	}

}
