package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
public class CounterFactory {
	
	public Receiver createCounterInstance(String counterName,String counterOperation,String counterType,TokenService tokenService) {

		Receiver receiver = null;

		switch (counterOperation) {
		case "DEPOSIT":
			receiver = new DepositCounter(counterName,counterType,tokenService);
			break;

		case "WITHDRAW":
			receiver = new WithdrawlCounter(counterName,counterType,tokenService);
			break;
			
		case "DEPOSITANDWITHDRAW":
			receiver = new DepositAndWithdrawCounter(counterName, counterType,tokenService);				
		}
		return receiver;

	}

}
