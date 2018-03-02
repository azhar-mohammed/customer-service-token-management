package com.abcbank.tokenmanage.counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		
		List<String> operationList = new ArrayList<String>();
		
		operationList.addAll(counterDTO.getCounterServices());
		
		Collections.sort(operationList);
		
		String counterOperation =  String.join(",",operationList);

		String counterType = counterDTO.getCounterType();

		int counterId = counterDTO.getCounterId();

		switch (counterOperation) {

		case "DEPOSIT":
			receiver = new DepositCounter(counterId, counterName, counterType, tokenService);
			break;

		case "WITHDRAW":
			receiver = new WithdrawlCounter(counterId, counterName, counterType, tokenService);
			break;

		case "DEPOSIT,WITHDRAW":
			receiver = new DepositAndWithdrawCounter(counterId, counterName, counterType, tokenService);
			break;
		}
		return receiver;

	}

}
