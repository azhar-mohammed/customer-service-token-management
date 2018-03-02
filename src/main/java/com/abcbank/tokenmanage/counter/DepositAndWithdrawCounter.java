package com.abcbank.tokenmanage.counter;

import java.util.concurrent.TimeUnit;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
public class DepositAndWithdrawCounter extends AbstractCounter implements Receiver {

	public DepositAndWithdrawCounter(int counterId, String counterName, String counterType, TokenService tokenService) {
		this.counterId = counterId;
		this.counterName = counterName;
		this.counterType = counterType;
		this.tokenService = tokenService;

	}

	@Override
	public void receiveToken(TokenDTO tokenDTO) throws Exception {

		System.out.println("received message " + tokenDTO + " at counter" + counterName);

		mapTokenToCounter(tokenDTO);

		updateTokenStatusAsInProgress(tokenDTO);

		serveToken(tokenDTO);

		updateTokenStatusAsCompleted(tokenDTO);

	}

	private void serveToken(TokenDTO tokenDTO) throws InterruptedException {

		TimeUnit.MINUTES.sleep(1);

		if (tokenDTO.getRequiredServices().get(tokenDTO.getNextStep() - 1).equals(ServiceType.DEPOSIT.toString())) {
			performDeposit();
			tokenDTO.setComments(tokenDTO.getComments() + " Performed Deposit operation.");
			tokenService.updateToken(tokenDTO);

		} else if (tokenDTO.getRequiredServices().get(tokenDTO.getNextStep() - 1)
				.equals(ServiceType.WITHDRAW.toString())) {
			performWithdrawl();
			tokenDTO.setComments(tokenDTO.getComments() + " Performed Withdrawl operation.");
			tokenService.updateToken(tokenDTO);
		}

	}

	public void performDeposit() {

	}

	public void performWithdrawl()

	{

	}

}
