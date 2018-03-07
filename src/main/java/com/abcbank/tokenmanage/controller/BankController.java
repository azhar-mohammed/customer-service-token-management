/**
 * 
 */
package com.abcbank.tokenmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abcbank.tokenmanage.dto.BankDTO;
import com.abcbank.tokenmanage.service.BankService;

/**
 * @author azharm
 *
 */
@RestController
public class BankController {
	
	@Autowired
	BankService bankService;
	
	/**
	 * Returns the list of banks
	 * @return
	 */
	
	@GetMapping("/api/bank")
	public ResponseEntity<List<BankDTO>> getBanks() {
		
		return new ResponseEntity<List<BankDTO>>(bankService.getAllBanks(),HttpStatus.OK);
	}
	/**
	 * Creates a bank based on the provided information
	 * @param bankDTO
	 * @return
	 */
	
	@PostMapping("/api/bank")
	public ResponseEntity<BankDTO> createBank(@RequestBody BankDTO bankDTO){
		
		return new ResponseEntity<BankDTO>(bankService.saveBank(bankDTO),HttpStatus.OK);
		
	}
	

}
