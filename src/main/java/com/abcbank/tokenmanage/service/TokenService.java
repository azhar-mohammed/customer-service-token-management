package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.model.Customer;
import com.abcbank.tokenmanage.model.CustomerType;
import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.abcbank.tokenmanage.repository.CustomerRepository;
import com.abcbank.tokenmanage.repository.TokenRepository;

@Service
public class TokenService implements TokenServiceInt {

	@Autowired
	TokenRepository tokenRepo;

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	
	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
	    return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
	    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
	    rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
	    return rabbitTemplate;
	}
	 
	

	@Override
	public Token createTokenAndAssignToQueue(Token token) {

		int customerId = token.getCustomer().getCustomerId();
		if (customerId == 0) 
		{
			Customer customer = customerRepo.save(token.getCustomer());
			token.setCustomer(customer);
		}
		token.setCustomer(customerRepo.findOne(token.getCustomer().getCustomerId()));
		token.setTokenStatus(TokenStatus.CREATED);
		token.setComments("Token created");
		Token tok = tokenRepo.saveAndFlush(token);
		if(tok.getTokenType().equalsIgnoreCase("PREMIUM"))
		{
		amqpTemplate.convertAndSend("tokens-exchange",token.getServiceType().toString()+"-"+"PREMIUM"+"-key",tok);
		}
		else
		{
			amqpTemplate.convertAndSend("tokens-exchange",token.getServiceType().toString()+"-"+"REGULAR"+"-key",tok);
		}
		return tok;
	}

	@Override
	public List<Token> getAllTokens() {

		Customer cust = new Customer();
		cust.setAddress("santoshnagar");
		cust.setCustomerId(1);
		cust.setCustomerName("azhar");
		cust.setCustomerType(CustomerType.REGULAR);
		cust.setPhoneNumber("9494940808");
		List<Token> tokenList = new ArrayList<>();
		Token tok = new Token();
		tok.setComments("intial comments");
		tok.setCustomer(cust);
		tok.setServiceType(ServiceType.DEPOSIT);
		tok.setTokenId(1);
		tok.setTokenStatus(TokenStatus.CREATED);
		tok.setTokenType(CustomerType.REGULAR.toString());
		
		tokenList.add(tok);
		return tokenList;
		//return tokenRepo.findAll();
	}

}
