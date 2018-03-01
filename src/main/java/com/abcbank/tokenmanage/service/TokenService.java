package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenCounterMapping;

public interface TokenService {

	public TokenDTO createTokenAndAssignToQueue(TokenDTO tokenDTO);

	public List<TokenDTO> getAllTokens();

	public void queueToken(TokenDTO tokenDTO);

	public TokenDTO updateToken(TokenDTO tokenDTO);

	public TokenCounterMapping saveTokenCounterMapping(TokenCounterMapping tokCounterDTO);

	public void deleteTokenCounterMapping(Integer id);

	public TokenDTO updateTokenStatus(TokenDTO tokenDTO);
}
