package com.abcbank.tokenmanage.model;


/**
 * This class represents a customer of a bank  
 * @author azharm
 *
 */
public class Customer {

	private long customerId;
	
	private String name;

	private String phoneNumber;

	private String address;

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

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}



}
