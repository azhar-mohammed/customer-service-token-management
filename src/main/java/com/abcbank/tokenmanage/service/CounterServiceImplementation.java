package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.exception.CounterException;
import com.abcbank.tokenmanage.exception.TokenException;
import com.abcbank.tokenmanage.model.Branch;
import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.CustomerType;
import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.model.TokenCounterMapping;
import com.abcbank.tokenmanage.repository.BranchRepository;
import com.abcbank.tokenmanage.repository.CounterRepository;
import com.abcbank.tokenmanage.repository.TokenCounterRepository;
import com.abcbank.tokenmanage.repository.TokenRepository;

/**
 * 
 * @author azharm
 *
 */
@Service
public class CounterServiceImplementation implements CounterService {

	@Autowired
	CounterRepository counterRepo;

	@Autowired
	TokenCounterRepository tokenCounterRepo;

	@Autowired
	TokenRepository tokenRepo;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	BranchRepository branchRepo;

	private Counter convertToEntity(CounterDTO counterDTO) {
		return modelMapper.map(counterDTO, Counter.class);

	}

	private CounterDTO convertToDTO(Counter counter) {
		return modelMapper.map(counter, CounterDTO.class);
	}

	@Override
	public CounterDTO saveCounter(CounterDTO counterDTO) {
		validateCounterDTO(counterDTO);
		Counter counter = convertToEntity(counterDTO);
		Counter savedCounter = counterRepo.saveAndFlush(counter);
		return convertToDTO(savedCounter);
	}

	private void validateCounterDTO(CounterDTO counterDTO) {

		fieldsAreNotNullValidation(counterDTO);
		validateRequiredServices(counterDTO);
		validateCounterType(counterDTO);
		validateCounterBranchId(counterDTO);

	}

	private void validateCounterBranchId(CounterDTO counterDTO) {

		Branch branch = branchRepo.findOne(counterDTO.getBranchId());

		if (branch == null) {
			throw new CounterException("Counter Creation failed the provided branch id does not exists");
		}


	}

	private void validateRequiredServices(CounterDTO counterDTO) {

		for (String service : counterDTO.getCounterServices()) {
			if (!EnumUtils.isValidEnum(ServiceType.class, service)) {
				throw new TokenException("Counter creation failed invalid service " + service + " provided");
			}
		}
	}

	private void fieldsAreNotNullValidation(CounterDTO counterDTO) {
		if (counterDTO.getCounterName() == null) {
			throw new CounterException("counter creation failed counter name is null");
		} else if (counterDTO.getCounterServices() == null) {
			throw new CounterException("counter creation failed counter service is null");
		} else if (counterDTO.getCounterType() == null) {
			throw new CounterException("counter creation failed counter type is null");
		} else if (counterDTO.getCounterServices().isEmpty()) {
			throw new CounterException("Counter creation failed empty counter services list specified");
		} else if (counterNameExistsCheck(counterDTO)) {
			throw new CounterException(
					"A counter is already present with the same name please provide a different name");
		} else if (counterDTO.getBranchId() == 0) {
			throw new CounterException("Counter creation failed empty branch id provided");
		}
	}

	private void validateCounterType(CounterDTO counterDTO) {

		if (!EnumUtils.isValidEnum(CustomerType.class, counterDTO.getCounterType()))
			throw new CounterException(
					"Counter creation failed invalid counter type " + counterDTO.getCounterType() + " specified");

	}

	private boolean counterNameExistsCheck(CounterDTO counterDTO) {

		if (counterRepo.findCounterByCounterName(counterDTO.getCounterName()) == null) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public List<CounterDTO> getAllCounters() {

		List<Counter> counterList = counterRepo.findAll();

		List<CounterDTO> counterDTOList = counterList.stream().map(counter -> convertToDTO(counter))
				.collect(Collectors.toList());

		for (CounterDTO counterDTO : counterDTOList) {

			List<TokenCounterMapping> tokenCounterMapList = tokenCounterRepo
					.findAllByCounterId(counterDTO.getCounterId());
			if (tokenCounterMapList != null)
				counterDTO.setTokens(getListOfTokens(tokenCounterMapList));
		}
		return counterDTOList;

	}

	public List<CounterDTO> mockupGetService() {
		List<CounterDTO> counterDTOList = new ArrayList<CounterDTO>();
		CounterDTO counter = new CounterDTO();
		counter.setCounterId(1);
		counter.setCounterName("DEPOSIT COUNTER");
		List<String> service = new ArrayList<String>();
		service.add("DEPOSIT");
		service.add("WITHDRAW");
		counter.setCounterServices(service);
		counterDTOList.add(counter);
		return counterDTOList;
	}

	private List<TokenDTO> getListOfTokens(List<TokenCounterMapping> tokenCounterMapList) {

		List<TokenDTO> tokenDTOList = new ArrayList<>();

		if (!tokenCounterMapList.isEmpty()) {
			for (TokenCounterMapping tokenCounterMapping : tokenCounterMapList) {
				TokenDTO tokenDTO = modelMapper.map(tokenRepo.findOne(tokenCounterMapping.getTokenId()),
						TokenDTO.class);
				tokenDTOList.add(tokenDTO);
			}

		}
		return tokenDTOList;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {

	}
}
