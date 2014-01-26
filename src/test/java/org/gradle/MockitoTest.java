package org.gradle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;



public class MockitoTest {
	
	@Mock
	List<Person> personList;
	Person fredSmith = new Person("Fred", "Smith");
	Person johnSmith = new Person("John", "Smith");
	
	
	@Before
	 public void setUp() {  
		MockitoAnnotations.initMocks(this);
		
	}
	
	@After
	 public void tearDown() {  
		
	}

	@Test
	public void testMockListVerify() {
		
		
		
		personList.add(fredSmith);
		personList.clear();
		
		verify(personList).add(fredSmith);
		verify(personList).clear();
	
	}

	
	@Test
	public void testMockStub() {
		
		//LinkedList mockedList = mock (LinkedList.class);
		
		//stubbing
		when(personList.get(0)).thenReturn(fredSmith);
		when(personList.get(1)).thenThrow(new RuntimeException());
		when(personList.get(2)).thenReturn(johnSmith);
		
		//following prints "first"
		assertEquals(fredSmith.toString() + " @ index 0", fredSmith, personList.get(0));
		
		//following prints "throws runtime exception"
		boolean thrown = false;
		
		try{
			
			// throws exception
			System.out.println(personList.get(1));
		}
		catch (Throwable t)
		{
			thrown = true;
		}
		assertTrue("Exception was thrown", thrown);
		
		//following prints "null" because get(999) was not stubbed
		assertEquals(johnSmith.toString() + " @ index 2", johnSmith, personList.get(2));
		
		assertNull("Mockito will return null for non stubbed index", personList.get(999));
		
		verify(personList,times(4)).get(anyInt());
	}
	
	@Test
	public void testMockArgumentMatchers() {
		
		when(personList.get(anyInt())).thenReturn(fredSmith);
		
		InOrder inOrder = inOrder(personList);
	
		for (int i = 0; i < 50; i=i+10) {
			assertEquals(fredSmith.toString() + " @ index " + i, fredSmith,
					personList.get(i));
		}
		// verify number of invocations (any int argument)
		verify(personList, times(5)).get(anyInt());
		// called once with index 0
		verify(personList, times(1)).get(0);
		// defaults to times 1
		verify(personList).get(10);
		verify(personList).get(20);
		verify(personList).get(30);
		verify(personList).get(40);
		verify(personList, never()).get(50);
		

	}
	
	@Test
	public void testThenAnswer() {
		
		
		when(personList.get(anyInt())).thenAnswer(new Answer(){
            public int count = 0;
            public Person[] persArray = {fredSmith,johnSmith,fredSmith };
			public Object answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				return persArray[count++];
			}
			
		});
		assertEquals("First Get is fredSmith",fredSmith,personList.get(0));
		assertEquals("Second Get is johnSmith",johnSmith,personList.get(1));
		assertEquals("Third Get is fredSmith",fredSmith,personList.get(1));
		
		
	}
	
	@Test
	public void testSpy() {
		
		//You can create spies of real objects. When you use the spy 
		// then the real methods are called (unless a method was stubbed).
		List<Person> persList = new ArrayList<Person>();
		List<Person> spy = Mockito.spy(persList);
		when(spy.size()).thenReturn(100);
		doReturn(fredSmith).when(spy).get(100);
		
		Person mm = new Person("Mickey","Mouse");
		spy.add(mm);
		// assert mocks
		assertEquals("Size is 100", 100, spy.size());
		assertEquals("Get 100 is fred", fredSmith, spy.get(100));
		assertEquals("Get 0 is mm", mm, spy.get(0));
	}
	
	
}

