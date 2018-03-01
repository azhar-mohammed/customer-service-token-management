package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

/**
 * 
 * @author azharm
 *
 */
public class CounterFactory {

	public Receiver createCounterInstance(CounterDTO counterDTO, TokenServiceImplementation tokenService) {

		Receiver receiver = null;

		String counterName = counterDTO.getCounterName();

		String counterOperation = counterDTO.getCounterService();

		String counterType = counterDTO.getCounterType();

		int counterId = counterDTO.getCounterId();

		switch (counterOperation) {

		case "DEPOSIT":
			receiver = new DepositCounter(counterId,counterName, counterType, tokenService );
			break;

		case "WITHDRAW":
			receiver = new WithdrawlCounter(counterId,counterName, counterType, tokenService);
			break;

		case "DEPOSIT,WITHDRAW":
		case "WITHDRAW,DEPOSIT":
			receiver = new DepositAndWithdrawCounter(counterId,counterName, counterType, tokenService);
			break;
		}
		return receiver;

	}

}
