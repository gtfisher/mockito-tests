package org.example.domain;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class Order {
	
	 private final Date dateTimeOfSubmission;
	  private Map<String, Integer> orderItems;
	  private final UUID key;
	  private Customer customer;
	  
	  
	public Order(Date dateTimeOfSubmission, org.example.domain.Customer customer) {
		this.key = UUID.randomUUID();
		this.dateTimeOfSubmission = dateTimeOfSubmission;
		this.customer = customer;
	}


	public Map<String, Integer> getOrderItems() {
		return orderItems;
	}


	public void setOrderItems(Map<String, Integer> orderItems) {
		this.orderItems = orderItems;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Date getDateTimeOfSubmission() {
		return dateTimeOfSubmission;
	}


	public UUID getKey() {
		return key;
	}
	
}
