package org.example.domain;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {
	
	OrderService orderService;
	
	@Mock
	OrderManager orderManager;
	
	@Mock
	Order order;
	
	@Mock
	Customer customer;
	
	SimpleDateFormat simpleDateFormat =
	        new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	Order expectedOrder; 

	@Before  
    public void setUp() {  
		
		MockitoAnnotations.initMocks(this);
		orderService = new OrderService(orderManager); 
    }  
  
    @After  
    public void tearDown() {  
    	orderService = null;
    	orderManager = null;
    	order = null;
    }  

	@Test
	public void testAddOrder() {

		// add an order to the order service
		orderService.addOrder(order);
		// verify that the orderManager add order method has been called
		verify(orderManager).add(order);
		//with any order class
		verify(orderManager).add(any(Order.class));
		// getNextOrder never called
		verify(orderManager, never() ).getNextOrder();
		// verifyNoMoreInteractions 
		verifyNoMoreInteractions(orderManager);	
	}
	
	@Test
	public void testGetOrderCount() {
		
		// setup mock for get order count
		when(orderManager.getOrderCount()).thenReturn(1);
		assertEquals("Order count is 1", 1, orderService.getOrderCount());
		verify(orderManager, times(1)).getOrderCount();
	}
	
	@Test
	public void testGetNextOrder()  throws ParseException{
		
		Date dateTimeofsubmission = simpleDateFormat.parse("25-01-2014 18:30:00");
		
		expectedOrder= new Order(dateTimeofsubmission,customer);
		
		when(orderManager.getNextOrder())
			.thenThrow(new RuntimeException())
			.thenReturn(expectedOrder);
		
		Order nextOrder = orderService.getNextOrder();
		assertNull("Null returned" , nextOrder);
		nextOrder = orderService.getNextOrder();
		assertEquals("Got Expected Order", expectedOrder, nextOrder);
		verify(orderManager, times(2)).getNextOrder();
		verify(orderManager, never()).getOrderCount();
		
		
	}
	

}
