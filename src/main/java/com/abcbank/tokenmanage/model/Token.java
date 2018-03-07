package com.abcbank.tokenmanage.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;



/**
 * This class represnts a token which would be provided to a customer
 * 
 * @author azharm
 *
 */
@Entity
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
	@ElementCollection
	private List<String> requiredServices;
	@ManyToOne
	@JoinColumn(name = "customerId")
	@Cascade({CascadeType.SAVE_UPDATE})
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
   
	public List<String> getRequiredServices() {
		return requiredServices;
	}

	public void setRequiredServices(List<String> requiredServices) {
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
