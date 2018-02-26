package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.model.Token;

public interface TokenServiceInt {
	public Token createTokenAndAssignToQueue(Token token);
	public List<Token> getAllTokens();
	public void requeToken(Token token);
	public Token updateToken(Token token);
}
