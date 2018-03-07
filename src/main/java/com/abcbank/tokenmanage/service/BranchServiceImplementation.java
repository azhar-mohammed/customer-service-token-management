/**
 * 
 */
package com.abcbank.tokenmanage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.dto.BranchDTO;
import com.abcbank.tokenmanage.exception.BranchException;
import com.abcbank.tokenmanage.model.Bank;
import com.abcbank.tokenmanage.model.Branch;
import com.abcbank.tokenmanage.repository.BankRepository;
import com.abcbank.tokenmanage.repository.BranchRepository;

/**
 * @author azharm
 *
 */
@Service
public class BranchServiceImplementation implements BranchService {

	@Autowired
	BranchRepository branchRepo;

	@Autowired
	BankRepository bankRepo;

	@Autowired
	ModelMapper modelMapper;

	private Branch convertToEntity(BranchDTO branchDTO) {
		return modelMapper.map(branchDTO, Branch.class);

	}

	private BranchDTO convertToDTO(Branch branch) {
		return modelMapper.map(branch, BranchDTO.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.abcbank.tokenmanage.service.BankService#saveBank(com.abcbank.tokenmanage.
	 * dto.BankDTO)
	 */
	@Override
	public BranchDTO saveBranch(BranchDTO branchDTO) {

		validateBranchDTO(branchDTO);

		Bank bank = bankRepo.findOne(branchDTO.getBankId());

		if (bank == null) {
			throw new BranchException("Branch creation failed there is no bank associated with the provided bank id");
		}

		Branch savedBranch = branchRepo.saveAndFlush(convertToEntity(branchDTO));

		savedBranch.setBank(bank);

		return convertToDTO(savedBranch);
	}

	private void validateBranchDTO(BranchDTO branchDTO) {

		if (branchDTO.getBankId() == 0) {
			throw new BranchException("Branch Creation failed bank id information is not provided");
		} else if (branchDTO.getBranchName() == null) {
			throw new BranchException("Branch Creation failed branch name is not provided");
		} else if (branchDTO.getLocation() == null) {
			throw new BranchException("Branch Creation failed location is null");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.abcbank.tokenmanage.service.BankService#getAllBanks()
	 */
	@Override
	public List<BranchDTO> getAllBranch() {

		List<Branch> branchList = branchRepo.findAll();

		List<BranchDTO> branchDTOList = branchList.stream().map(branch -> convertToDTO(branch))
				.collect(Collectors.toList());

		return branchDTOList;
	}

}
