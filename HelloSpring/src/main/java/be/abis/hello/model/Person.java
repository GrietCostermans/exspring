package be.abis.hello.model;

public class Person {
	private String firstName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Person(String firstName) {
		super();
		this.firstName = firstName;
	}
	
	public Person() {} 
	
}
