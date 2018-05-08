package com.abcbank.tokenmanage.counter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

/**
 * 
 * @author azharm
 *
 */
public class CounterFactory {
	/**
	 * Creates a counter based on the services required .
	 * 
	 * @param counterDTO
	 * @param tokenService
	 * @return
	 */

	public Receiver createCounterInstance(CounterDTO counterDTO, TokenServiceImplementation tokenService) {

		Receiver receivingCounter = null;

		String counterName = counterDTO.getCounterName();

		// Sorting the list of operations in a lexicographic manner
		List<String> operationList = new ArrayList<String>();

		operationList.addAll(counterDTO.getCounterServices());

		Collections.sort(operationList);

		String counterOperation = String.join(",", operationList);

		String counterType = counterDTO.getCounterType();

		int counterId = counterDTO.getCounterId();

		// The service types or operations specified in switch case are to be specified
		// in lexicographic manner

		switch (counterOperation) {

		case "DEPOSIT":
			receivingCounter = new DepositCounter(counterId, counterName, counterType, tokenService);
			break;

		case "WITHDRAW":
			receivingCounter = new WithdrawlCounter(counterId, counterName, counterType, tokenService);
			break;

		case "DEPOSIT,WITHDRAW":
			receivingCounter = new DepositAndWithdrawCounter(counterId, counterName, counterType, tokenService);
			break;
		}
		return receivingCounter;

	}

}
