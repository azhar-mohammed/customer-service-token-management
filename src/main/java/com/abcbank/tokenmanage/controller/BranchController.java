package com.abcbank.tokenmanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.abcbank.tokenmanage.dto.BranchDTO;
import com.abcbank.tokenmanage.service.BranchService;

/**
 * 
 * @author azharm
 *
 */

@RestController
public class BranchController {

	@Autowired
	BranchService branchService;
	
	/**
	 * Returns a list of branches
	 * @return
	 */
	
	@GetMapping("/api/branch")
	public ResponseEntity<List<BranchDTO>> getBranches() {
		
		return new ResponseEntity<List<BranchDTO>>(branchService.getAllBranch(),HttpStatus.OK);
	}
	
	/**
	 * Creates a branch based on the information specified
	 * @param branchDTO
	 * @return
	 */
	@PostMapping("/api/branch")
	public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO){
		
		return new ResponseEntity<BranchDTO>(branchService.saveBranch(branchDTO),HttpStatus.OK);
		
	}
}
