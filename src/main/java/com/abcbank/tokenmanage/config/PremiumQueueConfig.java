package com.abcbank.tokenmanage.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PremiumQueueConfig {
	@Value("${abcbank.rabbitmq.exchange}")
	String exchange;

	@Value("${abcbank.rabbitmq.premiumqueue}")
	String queueName1;

	@Value("${abcbank.rabbitmq.premiumroutingkey}")
	private String routingkey1;


	 @Bean
	    DirectExchange exchange() {
	    	return new DirectExchange(exchange);
	    }
	 
	 
	@Bean
	Binding binding1(Queue queue1, DirectExchange exchange) {
		return BindingBuilder.bind(queue1).to(exchange).with(routingkey1);
	}

	@Bean
	public MessageConverter jsonMessageConverter2() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	Queue queue1() {
		return new Queue(queueName1, false);
	}
	

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter2());
		return rabbitTemplate;
	}
}
