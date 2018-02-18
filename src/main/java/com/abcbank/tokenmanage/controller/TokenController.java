package com.abcbank.tokenmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.TokenService;

@RestController
@RequestMapping("/api")
public class TokenController {

	@Autowired
	TokenService tokenService;
	
	@PostMapping("/token/create")
	public Token createToken(@RequestBody Token token) {
		return tokenService.createTokenAndAssignToQueue(token);
	}
	
	@GetMapping("/token/get")
	public List<Token>  getTokens( ) {
		return tokenService.getAllTokens();
	}

}
