package com.abcbank.tokenmanage.counter;

import com.abcbank.tokenmanage.dto.TokenDTO;
import com.abcbank.tokenmanage.model.TokenCounterMapping;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.abcbank.tokenmanage.service.TokenService;

/**
 * This class represents a Counter
 * @author azharm
 *
 */
public class AbstractCounter {

	public int counterId;
	public String counterName;
	public String counterType;
	public TokenService tokenService;

	public void updateTokenComments(TokenDTO tokenDTO, String comments) {

		tokenDTO.setComments(tokenDTO.getComments() + " " + comments);
		tokenService.updateToken(tokenDTO);

	}

	public void updateTokenStatusAsInProgress(TokenDTO tokenDTO) {

		tokenDTO.setTokenStatus(TokenStatus.INPROGRESS);
		tokenService.updateToken(tokenDTO);

	}

	public void updateTokenStatusAsCompleted(TokenDTO tokenDTO) {

		if (tokenDTO.isFurtherProcessingRequired()) {
			tokenService.queueToken(tokenDTO);
		} else {
			tokenDTO.setTokenStatus(TokenStatus.COMPLETED);
			tokenService.updateToken(tokenDTO);
		}

	}

	public TokenCounterMapping mapTokenToCounter(TokenDTO tokenDTO) {

		TokenCounterMapping tokenCounter = new TokenCounterMapping();
		tokenCounter.setCounterId(counterId);
		tokenCounter.setTokenId(tokenDTO.getTokenId());
		return tokenService.saveTokenCounterMapping(tokenCounter);

	}

}
