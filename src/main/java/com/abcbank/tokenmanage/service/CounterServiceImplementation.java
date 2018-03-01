package com.abcbank.tokenmanage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.dto.CounterDTO;
import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.exception.CounterException;
import com.abcbank.tokenmanage.model.Counter;
import com.abcbank.tokenmanage.model.TokenCounterMapping;
import com.abcbank.tokenmanage.repository.CounterRepository;
import com.abcbank.tokenmanage.repository.TokenCounterRepository;
import com.abcbank.tokenmanage.repository.TokenRepository;

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

	@Override
	public List<CounterDTO> getAllCounters() {

		List<Counter> counterList = counterRepo.findAll();

		List<CounterDTO> counterDTOList = counterList.stream().map(counter -> convertToDTO(counter))
				.collect(Collectors.toList());

		for (CounterDTO counterDTO : counterDTOList) {

			List<TokenCounterMapping> tokenCounterMapList = tokenCounterRepo
					.findAllByCounterId(counterDTO.getCounterId());

			counterDTO.setTokens(getListOfTokens(tokenCounterMapList));
		}
		return counterDTOList;

	}

	private List<TokenDTO> getListOfTokens(List<TokenCounterMapping> tokenCounterMapList) {

		List<TokenDTO> tokenDTOList = new ArrayList<TokenDTO>();

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

		if (counterDTO.getCounterName() == null) {
			throw new CounterException("counter creation failed counter name is null");
		} else if (counterDTO.getCounterService() == null) {
			throw new CounterException("counter creation failed counter service is null");
		} else if (counterDTO.getCounterType() == null) {
			throw new CounterException("counter creation failed counter type is null");
		} else if (counterNameExistsCheck(counterDTO)) {
			throw new CounterException(
					"A counter is already present with the same name please provide a different name");
		}

	}

	private boolean counterNameExistsCheck(CounterDTO counterDTO) {

		if (counterRepo.findCounterByCounterName(counterDTO.getCounterName()) == null) {
			return false;
		} else {
			return true;
		}

	}
}
