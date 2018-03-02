package com.abcbank.tokenmanage.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbank.tokenmanage.counter.CounterFactory;
import com.abcbank.tokenmanage.counter.Receiver;
import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;

@Component
public class RabbitMQConsumerBuilder {

	@Autowired
	ConnectionFactory connectionFactory;
	/**
	 * This  creates a rabbitmq consumer and binds it to a specific queue based on operation. 
	 * @param counterDTO
	 * @param tokenService
	 */

	public void build(CounterDTO counterDTO, TokenServiceImplementation tokenService) {

		Receiver receivingCounter = null;

		CounterFactory factory = new CounterFactory();

		String counterName = counterDTO.getCounterName();

		String counterType = counterDTO.getCounterType();

		receivingCounter = factory.createCounterInstance(counterDTO, tokenService);

		for (String operation : counterDTO.getCounterServices()) {

			System.out.println(operation);
			// Queue creation and binding of the rabbitmq consumer to the specific queue
			new RabbitMQConsumer(counterName, operation + "-" + counterType + "-key",
					operation.trim() + "-" + counterType + "-queue", connectionFactory, receivingCounter);
		}

	}

}
