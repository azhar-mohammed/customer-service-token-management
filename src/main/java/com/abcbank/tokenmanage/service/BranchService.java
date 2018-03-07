/**
 * 
 */
package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.dto.BranchDTO;

/**
 * @author azharm
 *
 */
public interface BranchService {
	
	public BranchDTO saveBranch(BranchDTO branchDTO);
	
	public List<BranchDTO> getAllBranch();

}
