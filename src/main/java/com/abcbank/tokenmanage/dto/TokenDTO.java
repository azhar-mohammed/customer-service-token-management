/**
 * 
 */
package com.abcbank.tokenmanage.dto;

import java.io.Serializable;
import java.util.List;

import com.abcbank.tokenmanage.model.Customer;
import com.abcbank.tokenmanage.model.TokenStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author azharm
 *
 */

public class TokenDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1230006601776461668L;

	private int tokenId;

	private String tokenType;

	@JsonInclude(Include.NON_DEFAULT)
	private List<String> requiredServices;

	private Customer customer;

	@JsonInclude(Include.NON_NULL)
	private String comments;

	private TokenStatus tokenStatus;

	private int nextStep;

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public TokenStatus getTokenStatus() {
		return tokenStatus;
	}

	public void setTokenStatus(TokenStatus tokenStatus) {
		this.tokenStatus = tokenStatus;
	}

	public int getNextStep() {
		return nextStep;
	}

	public void setNextStep(int nextStep) {
		this.nextStep = nextStep;
	}

	public List<String> getRequiredServices() {
		return requiredServices;
	}

	public void setRequiredServices(List<String> requiredServices) {
		this.requiredServices = requiredServices;
	}

	@Override
	public String toString() {
		return "TokenDTO [tokenId=" + tokenId + ", tokenType=" + tokenType + ", requiredServices=" + requiredServices
				+ ", customer=" + customer + ", comments=" + comments + ", tokenStatus=" + tokenStatus + ", nextStep="
				+ nextStep + "]";
	}

	public boolean isFurtherProcessingRequired() {
		if (requiredServices.size() > nextStep)
			return true;
		else
			return false;
	}

}
