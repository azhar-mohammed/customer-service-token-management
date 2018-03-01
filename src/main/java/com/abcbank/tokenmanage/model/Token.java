package com.abcbank.tokenmanage.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * This class represnts a token which would be provided to a customer
 * 
 * @author azharm
 *
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Token.class)
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6097704049487570726L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int tokenId;
	@Column
	private String tokenType;
	@Column
	private String requiredServices;
	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customer;
	@Column
	private String comments;
	@Enumerated(EnumType.STRING)
	@Column
	private TokenStatus tokenStatus = TokenStatus.CREATED;
	
	

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
   
	public String getRequiredServices() {
		return requiredServices;
	}

	public void setRequiredServices(String requiredServices) {
		this.requiredServices = requiredServices;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Token [tokenId=" + tokenId + ", tokenType=" + tokenType + ", requiredServices=" + requiredServices
				+ ", customer=" + customer + ", comments=" + comments + ", tokenStatus=" + tokenStatus + "]";
	}


	
	
	
	/*
	public boolean isFurtherProcessingRequired()
	{
		if(requiredServices.length()> nextStep)
			return true;
		else
			return false;
	}*/

}
