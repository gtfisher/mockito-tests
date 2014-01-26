package org.example.domain;


import java.util.logging.Level;
import java.util.logging.Logger;



public class OrderService {
	
	private final static Logger LOG = Logger.getLogger(OrderService.class.getName()); 
	
	private final OrderManager orderManager;

	public OrderService(OrderManager orderManager) {
		super();
		this.orderManager = orderManager;
	}
	
	
	public void addOrder(Order order)
	{
		orderManager.add(order);
	}
	
	public int getOrderCount()
	{
		return orderManager.getOrderCount();
	}
	
	public Order getNextOrder(){
		Order result = null;
		try {
			result = orderManager.getNextOrder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOG.log(Level.SEVERE, e.getMessage());
		}
		return result;
	}
	
	

}
