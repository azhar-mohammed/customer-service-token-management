package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public Token createTokenAndAssignToCounter(Token token) {

		int customerId = token.getCustomer().getCustomerId();
		if (customerId == 0) {
			Customer customer = customerRepo.save(token.getCustomer());
			token.setCustomer(customer);
		}
		token.setTokenStatus(TokenStatus.CREATED);
		token.setComments("Token created");
		return tokenRepo.save(token);
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
