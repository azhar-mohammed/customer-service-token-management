package com.abcbank.tokenmanage.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbank.tokenmanage.counter.CounterFactory;
import com.abcbank.tokenmanage.counter.Receiver;
import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

@Component
public class ConsumerBuilder {

	@Autowired
	ConnectionFactory connectionFactory;

	public void build(CounterDTO counterDTO, TokenServiceImplementation tokenService) {

		Receiver receivingCounter = null;

		CounterFactory factory = new CounterFactory();

		String counterName = counterDTO.getCounterName();

		String counterOperation = counterDTO.getCounterService();

		String counterType = counterDTO.getCounterType();

		receivingCounter = factory.createCounterInstance(counterDTO, tokenService);

		if (counterOperation.contains(",")) {
			String[] operations = counterOperation.split(",");
			for (String operation : operations) {
				operation = operation.trim();
				System.out.println(operation);
				new Consumer(counterName, operation + "-" + counterType + "-key",
						operation + "-" + counterType + "-queue", connectionFactory, receivingCounter);
			}

		} else {
			new Consumer(counterName, counterOperation + "-" + counterType + "-key",
					counterOperation + "-" + counterType + "-queue", connectionFactory, receivingCounter);

		}
	}

}
