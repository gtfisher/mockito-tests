package org.example.domain;

import java.util.ArrayList;

public class OrderManager {
	
	private ArrayList<Order> orders;

	public OrderManager() {
		super();
		orders = new ArrayList<Order>();
	}

	public void add(Order order) {
		orders.add(order);
		
	}
	
	public Order getNextOrder ()
	{
		return orders.get(0);
	}
	
	public  int getOrderCount()
	{
		return orders.size();
	}

}
