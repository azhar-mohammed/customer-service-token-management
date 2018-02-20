/**
 * 
 */
package com.abcbank.tokenmanage.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.abcbank.tokenmanage.model.Token;


/**
 * @author azharm
 *
 */
@Component
public class RabbitMqConsumer {
	
	@RabbitListener(queues = "regular-queue")
	public void recievedRegularMessage(Token token) {
		System.out.println("Recieved regular token: " + token);
	}
	
	@RabbitListener(queues = "premium-queue")
	public void recievedPremiumMessage(Token token) {
		System.out.println("Recieved premium token: " + token);
	}
	
	
}
