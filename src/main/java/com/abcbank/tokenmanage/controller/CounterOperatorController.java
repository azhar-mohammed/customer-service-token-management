package com.abcbank.tokenmanage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.repository.TokenRepository;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * 
 * @author azharm
 *
 */
@RestController
public class CounterOperatorController {

	@Autowired
	TokenService tokenService;

	@PostMapping("api/operator")
	public Token markToken(@RequestBody Token token) {

		
		return tokenService.updateTokenStatus(token);


	}

}