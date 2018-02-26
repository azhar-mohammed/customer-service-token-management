package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;
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

		Token tok = null;
		int customerId = token.getCustomer().getCustomerId();
		if (customerId == 0) {
			Customer customer = customerRepo.save(token.getCustomer());
			token.setCustomer(customer);
		} else {
			token.setCustomer(customerRepo.findOne(token.getCustomer().getCustomerId()));
		}

		token.setTokenStatus(TokenStatus.CREATED);
		token.setComments("Token created");
		String requiredServices[] = token.getRequiredServices().split(",");
		System.out.println("required services lenght is " + requiredServices.length);
		int nextStep = token.getNextStep();
		if (requiredServices.length > nextStep) {
			if (EnumUtils.isValidEnum(ServiceType.class, requiredServices[nextStep])) {
				tok = tokenRepo.saveAndFlush(token);
				if (tok.getTokenType().equalsIgnoreCase("PREMIUM")) {
					amqpTemplate.convertAndSend("tokens-exchange",
							requiredServices[nextStep] + "-" + "PREMIUM" + "-key", tok);

				} else {
					amqpTemplate.convertAndSend("tokens-exchange",
							requiredServices[nextStep] + "-" + "REGULAR" + "-key", tok);
				}
				tok.setNextStep(nextStep + 1);
			} else {
				// TODO: throw exception
				System.out.println("Invalid service specified");
				return null;

			}

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
		tok.setRequiredServices(ServiceType.DEPOSIT.toString());
		tok.setTokenId(1);
		tok.setTokenStatus(TokenStatus.CREATED);
		tok.setTokenType(CustomerType.REGULAR.toString());

		tokenList.add(tok);
		return tokenList;
		// return tokenRepo.findAll();
	}

	@Override
	public void requeToken(Token tok) {
		String requiredServices[] = tok.getRequiredServices().split(",");
		int nextStep = tok.getNextStep();
		if (requiredServices.length > nextStep) {
			if (EnumUtils.isValidEnum(ServiceType.class, requiredServices[nextStep])) {
				if (tok.getTokenType().equalsIgnoreCase("PREMIUM")) {
					amqpTemplate.convertAndSend("tokens-exchange",
							requiredServices[nextStep] + "-" + "PREMIUM" + "-key", tok);

				} else {
					amqpTemplate.convertAndSend("tokens-exchange",
							requiredServices[nextStep] + "-" + "REGULAR" + "-key", tok);
				}
				tok.setNextStep(nextStep + 1);
			} else {
				// TODO: throw exception
				System.out.println("Invalid service specified");
			}

		}

	}

	@Override
	public Token updateToken(Token token) {

		Token updatedToken = tokenRepo.saveAndFlush(token);
		updatedToken.setNextStep(token.getNextStep());

		return updatedToken;
	}

}
