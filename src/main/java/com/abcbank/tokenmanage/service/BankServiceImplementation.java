/**
 * 
 */
package com.abcbank.tokenmanage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.dto.BankDTO;
import com.abcbank.tokenmanage.exception.BankException;
import com.abcbank.tokenmanage.model.Bank;
import com.abcbank.tokenmanage.repository.BankRepository;

/**
 * @author azharm
 *
 */
@Service
public class BankServiceImplementation implements BankService {

	@Autowired
	BankRepository bankRepo;

	@Autowired
	ModelMapper modelMapper;

	private Bank convertToEntity(BankDTO bankDTO) {
		return modelMapper.map(bankDTO, Bank.class);

	}

	private BankDTO convertToDTO(Bank bank) {
		return modelMapper.map(bank, BankDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abcbank.tokenmanage.service.BankService#saveBank(com.abcbank.tokenmanage.
	 * model.Bank)
	 */
	@Override
	public BankDTO saveBank(BankDTO bankDTO) {

		validateBankDTO(bankDTO);

		Bank bank = convertToEntity(bankDTO);

		return convertToDTO(bankRepo.saveAndFlush(bank));

	}

	private void validateBankDTO(BankDTO bankDTO) {

		if (bankDTO.getBankName() == null) {
			throw new BankException("Bank name is null ,please provide name of the bank");
		} else if (bankDTO.getLocation() == null) {
			throw new BankException("Bank location is null,please provide the location");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abcbank.tokenmanage.service.BankService#getAllBanks()
	 */
	@Override
	public List<BankDTO> getAllBanks() {
		
		List<Bank> bankList = bankRepo.findAll();
		
		List<BankDTO> bankDTOList = bankList.stream().map(bank->convertToDTO(bank)).collect(Collectors.toList());
		
		return bankDTOList;
	}

}
