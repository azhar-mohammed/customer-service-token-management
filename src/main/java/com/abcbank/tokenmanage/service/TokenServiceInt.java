package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.model.Token;

public interface TokenServiceInt {
	public Token createTokenAndAssignToCounter(Token token);
	public List<Token> getAllTokens();
}
