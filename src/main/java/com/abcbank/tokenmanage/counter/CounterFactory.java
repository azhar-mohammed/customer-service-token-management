package com.abcbank.tokenmanage.counter;

/**
 * 
 * @author azharm
 *
 */
public class CounterFactory {
	
	public Receiver createCounterInstance(String counterName,String counterOperation,String counterType) {

		Receiver receiver = null;

		switch (counterOperation) {
		case "DEPOSIT":
			receiver = new DepositCounter(counterName,counterType);
			break;

		case "WITHDRAW":
			receiver = new WithdrawlCounter(counterName,counterType);
			break;
			
		case "DEPOSITANDWITHDRAW":
			receiver = new DepositAndWithdrawCounter(counterName, counterType);				
		}
		return receiver;

	}

}
