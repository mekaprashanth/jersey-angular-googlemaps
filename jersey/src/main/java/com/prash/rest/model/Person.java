/**
 * 
 */
package com.prash.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * @author prashanth_meka
 *
 */
@XmlRootElement
public class Person {
	
	@JsonProperty(value="fName")
	private String firstName;
	
	private String lastName;
	
	@JsonProperty(value="personalAddress")
	private Address homeAddress;

	public Person() {
		
	}
	public Person(String firstName2, String lastName2, Address homeAddress) {
		this.firstName = firstName2;
		this.lastName = lastName2;
		this.homeAddress = homeAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public static Person createPerson(String firstName, String lastName)	{
		Address addr = Address.createAddress("Chennai", "Taminadu");
		return new Person(firstName, lastName,  addr);
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	

}
