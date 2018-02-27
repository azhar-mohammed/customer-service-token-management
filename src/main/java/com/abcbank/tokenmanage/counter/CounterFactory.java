package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
public class CounterFactory {
	
	public Receiver createCounterInstance(Counter counter,TokenService tokenService) {

		Receiver receiver = null;


		String counterName = counter.getCounterName();

		String counterOperation = counter.getCounterServiceType().toString();

		String counterType = counter.getCounterType();
		
		int counterId = counter.getCounterId();
		
		switch (counterOperation) {
		case "DEPOSIT":
			receiver = new DepositCounter(counterName,counterType,tokenService,counterId);
			break;

		case "WITHDRAW":
			receiver = new WithdrawlCounter(counterName,counterType,tokenService,counterId);
			break;
			
		case "DEPOSITANDWITHDRAW":
			receiver = new DepositAndWithdrawCounter(counterName, counterType,tokenService,counterId);	
			
		case "WITHDRAWANDDEPOSIT":
			receiver = new DepositAndWithdrawCounter(counterName, counterType,tokenService,counterId);	
		}
		return receiver;

	}

}
