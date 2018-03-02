package com.abcbank.tokenmanage.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class represents a customer of a bank
 * 
 * @author azharm
 *
 */
@Entity
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8806592497929560938L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int customerId;

	@Column
	@JsonInclude(Include.NON_NULL)
	private String name;

	@Column
	@JsonInclude(Include.NON_NULL)
	private String phoneNumber;

	@Column
	@JsonInclude(Include.NON_NULL)
	private String address;

	@Enumerated(EnumType.STRING)
	@Column
	private CustomerType customerType;

	public String getCustomerName() {
		return name;
	}

	public void setCustomerName(String customerName) {
		this.name = customerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", customerType=" + customerType + "]";
	}

}
