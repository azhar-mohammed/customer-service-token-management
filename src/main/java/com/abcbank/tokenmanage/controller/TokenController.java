package com.abcbank.tokenmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.service.TokenServiceImplementation;
/**
 * 
 * @author azharm
 *
 */
@RestController
public class TokenController {

	@Autowired
	TokenServiceImplementation tokenService;
	
	@PostMapping("/api/token")
	public TokenDTO createToken(@RequestBody TokenDTO tokenDTO) {
		return tokenService.createTokenAndAssignToQueue(tokenDTO);
	}
	
	@GetMapping("/api/token")
	public List<TokenDTO>  getTokens( ) {
		return tokenService.getAllTokens();
	}
	
}
