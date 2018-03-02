package com.abcbank.tokenmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.dto.TokenDTO;
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
	/**
	 * Creates Token, If the customer not exist then will create new customer before creating token
	 * @param tokenDTO
	 * @return
	 */
	@PostMapping("/api/token")
	public ResponseEntity<TokenDTO> createToken(@RequestBody TokenDTO tokenDTO) {
		
		return new ResponseEntity<TokenDTO>(tokenService.createTokenAndAssignToQueue(tokenDTO),HttpStatus.OK);
	}
	
	/**
	 * Returns the list of tokens
	 * @return
	 */
	
	@GetMapping("/api/token")
	public ResponseEntity<List<TokenDTO>>  getTokens( ) {
		
		return new ResponseEntity<List<TokenDTO>>(tokenService.getAllTokens(),HttpStatus.OK);
	}
	
}
