package com.abcbank.tokenmanage.consumer;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abcbank.tokenmanage.counter.CounterFactory;
import com.abcbank.tokenmanage.counter.Receiver;
import com.abcbank.tokenmanage.service.TokenService;

@Component
public class ConsumerBuilder {

	@Autowired
	ConnectionFactory connectionFactory;

	public Consumer build(String counterName, String counterOperation, String counterType,TokenService tokenService) {

		Receiver receiver = null;

		CounterFactory factory = new CounterFactory();

		receiver = factory.createCounterInstance(counterName, counterOperation, counterType,tokenService);
	
		if(counterOperation.contains("AND"))
		{
		String operations[]=counterOperation.split("AND");
		for(String operation:operations)
		{
			operation = operation.trim();
			System.out.println(operation);
			 new Consumer(counterName, operation + "-" + counterType + "-key",
					operation + "-" + counterType + "-queue", connectionFactory, receiver);
			 
			 
		}
		return null;
		}
		else
		{
		return new Consumer(counterName, counterOperation + "-" + counterType + "-key",
				counterOperation + "-" + counterType + "-queue", connectionFactory, receiver);
		
		}
	}

}
