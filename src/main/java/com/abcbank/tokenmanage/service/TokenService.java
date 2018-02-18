package com.abcbank.tokenmanage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.repository.CustomerDAO;
import com.abcbank.tokenmanage.repository.TokenDAO;

@Service
public class TokenService implements TokenServiceInt {

	@Autowired
	TokenDAO tokenDAO;

	@Autowired
	CustomerDAO customerDAO;

	@Override
	public Token createTokenAndAssignToQueue(Token token) {

		if (token.getCustomerId() == null)
			customerDAO.saveCustomer(token.getCustomer());

		token.setCustomerId(token.getCustomer().getCustomerId());
	
		return tokenDAO.createToken(token);
	}

	@Override
	public List<Token> getAllTokens() {

		return tokenDAO.getTokens();
	}

}
