package com.abcbank.tokenmanage.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.EnumUtils;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.exception.CustomerException;
import com.abcbank.tokenmanage.exception.TokenCounterMappingException;
import com.abcbank.tokenmanage.exception.TokenException;
import com.abcbank.tokenmanage.model.Branch;
import com.abcbank.tokenmanage.model.Customer;
import com.abcbank.tokenmanage.model.CustomerType;
import com.abcbank.tokenmanage.model.ServiceType;
import com.abcbank.tokenmanage.model.Token;
import com.abcbank.tokenmanage.model.TokenCounterMapping;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.abcbank.tokenmanage.repository.BranchRepository;
import com.abcbank.tokenmanage.repository.CustomerRepository;
import com.abcbank.tokenmanage.repository.TokenCounterRepository;
import com.abcbank.tokenmanage.repository.TokenRepository;

/**
 * 
 * @author azharm
 *
 */
@Service
public class TokenServiceImplementation implements TokenService {

	@Autowired
	TokenRepository tokenRepo;

	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	TokenCounterRepository tokenCounterRepo;

	@Autowired
	BranchRepository branchRepo;

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	ModelMapper modelMapper;

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
		return rabbitTemplate;
	}

	private Token convertToEntity(TokenDTO tokenDTO) {
		return modelMapper.map(tokenDTO, Token.class);

	}

	private TokenDTO convertToDTO(Token token) {
		return modelMapper.map(token, TokenDTO.class);
	}

	@Override
	public TokenDTO createTokenAndAssignToQueue(TokenDTO tokenDTO) {

		validateRequiredServices(tokenDTO);

		saveCustomer(tokenDTO);

		TokenDTO createdTokenDTO = createToken(tokenDTO);

		queueToken(createdTokenDTO);

		return createdTokenDTO;
	}

	private void validateRequiredServices(TokenDTO tokenDTO) {

		if (tokenDTO.getRequiredServices() == null || tokenDTO.getRequiredServices().isEmpty())
			throw new TokenException("Token creation failed.Required services is null or empty");

		if (tokenDTO.getTokenType() == null)
			throw new TokenException("Token creation failed specified token type is null");

		for (String service : tokenDTO.getRequiredServices()) {
			if (!EnumUtils.isValidEnum(ServiceType.class, service)) {
				throw new TokenException("Token creation failed invalid service " + service + " provided");
			}
		}
	}

	private TokenDTO createToken(TokenDTO tokenDTO) {

		tokenDTO.setTokenStatus(TokenStatus.CREATED);

		tokenDTO.setComments("Token created.");

		Token token = tokenRepo.saveAndFlush(convertToEntity(tokenDTO));

		return convertToDTO(token);
	}

	private void saveCustomer(TokenDTO tokenDTO) {

		if (tokenDTO.getCustomer() == null)
			throw new TokenException("Token creation failed customer information is not specified");

		validateBranch(tokenDTO.getCustomer().getBranchId());

		if (tokenDTO.getCustomer().getCustomerId() == 0) {
			validateCustomer(tokenDTO.getCustomer());
			Customer customer = customerRepo.save(tokenDTO.getCustomer());
			tokenDTO.setCustomer(customer);
		} else {
			tokenDTO.setCustomer(customerRepo.findOne(tokenDTO.getCustomer().getCustomerId()));
		}
	}

	private void validateBranch(int branchId) {

		if (branchId == 0)
			throw new CustomerException("Token creation failed branch id specified is not specified");

		Branch branch = branchRepo.findOne(branchId);

		if (branch == null) {
			throw new CustomerException("token creation failed invalid branch id specified");
		}

	}

	private void validateCustomer(Customer customer) {

		if (customer.getName() == null) {
			throw new CustomerException("token creation failed name of customer is null");
		} else if (customer.getCustomerType() == null) {
			throw new CustomerException("Token creation failed customer type is null ");
		}

	}

	@Override
	public List<TokenDTO> getAllTokens() {

		List<Token> tokenList = tokenRepo.findAll();

		return tokenList.stream().map(token -> convertToDTO(token)).collect(Collectors.toList());
	}

	@Override
	public void queueToken(TokenDTO tokenDTO) {

		int nextStep = tokenDTO.getNextStep();

		if (tokenDTO.getRequiredServices().size() > nextStep) {

			tokenDTO.setNextStep(nextStep + 1);

			if (EnumUtils.isValidEnum(CustomerType.class, tokenDTO.getTokenType()))
				amqpTemplate.convertAndSend("tokens-exchange",
						tokenDTO.getRequiredServices().get(nextStep) + "-" + tokenDTO.getTokenType() + "-key"+"-"+tokenDTO.getCustomer().getBranchId(),
						tokenDTO);

		}

	}

	@Override
	public TokenDTO updateToken(TokenDTO tokenDTO) {

		Token updatedToken = tokenRepo.saveAndFlush(convertToEntity(tokenDTO));
		TokenDTO updatedDTO = convertToDTO(updatedToken);
		updatedDTO.setNextStep(tokenDTO.getNextStep());

		return tokenDTO;
	}

	@Override
	public TokenCounterMapping saveTokenCounterMapping(TokenCounterMapping tokenCounterMapping) {

		validateTokenCounterMapping(tokenCounterMapping);

		return tokenCounterRepo.saveAndFlush(tokenCounterMapping);

	}

	private void validateTokenCounterMapping(TokenCounterMapping tokenCounterMapping) {

		if (tokenCounterMapping.getCounterId() == 0) {
			throw new TokenCounterMappingException("Token Counter Mapping exception counter id is zero");
		} else if (tokenCounterMapping.getTokenId() == 0) {
			throw new TokenCounterMappingException("Token Counter Mapping exception token id is zero");
		}

	}

	@Override
	public void deleteTokenCounterMapping(Integer id) {
		if (id == null)
			throw new TokenCounterMappingException("Delete Token Counter Mapping failed id is null");

		tokenCounterRepo.delete(id);
	}

	@Override
	public TokenDTO updateTokenStatus(TokenDTO tokenDTO) {

		Token foundToken = tokenRepo.findOne(tokenDTO.getTokenId());
		foundToken.setTokenStatus(tokenDTO.getTokenStatus());
		return convertToDTO(tokenRepo.saveAndFlush(foundToken));

	}

}
