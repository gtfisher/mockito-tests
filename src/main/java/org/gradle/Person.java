package org.gradle;


public class Person {
   

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", surname=" + surname + "]";
	}

	private final String firstName;
    
    private final String surname;
    
    

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

  
}
