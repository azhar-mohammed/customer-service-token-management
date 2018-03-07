/**
 * 
 */
package com.abcbank.tokenmanage.service;

import java.util.List;

import com.abcbank.tokenmanage.dto.BankDTO;


/**
 * @author azharm
 *
 */
public interface BankService {
	
	public BankDTO saveBank(BankDTO bankDTO);
	
	public List<BankDTO> getAllBanks();

}
