package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;
import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
public class DepositCounter extends AbstractCounter implements Receiver {

	public DepositCounter(int counterId, String counterName, String counterType, TokenService tokenService) {
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

		updateTokenComments(tokenDTO, "Performed Deposit operation.");

		updateTokenStatusAsCompleted(tokenDTO);

	}

	private void serveToken() throws InterruptedException {

		TimeUnit.MILLISECONDS.sleep(6000);

	}

}
