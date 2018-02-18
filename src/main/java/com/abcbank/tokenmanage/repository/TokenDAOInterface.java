package com.abcbank.tokenmanage.repository;

import java.util.List;

import com.abcbank.tokenmanage.model.Customer;
import com.abcbank.tokenmanage.model.Token;


public interface TokenDAOInterface {
	Token createToken(Token token);
	public List<Token> getTokens();
	 

}
