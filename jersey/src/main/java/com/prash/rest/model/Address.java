/**
 * 
 */
package com.prash.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author prashanth_meka
 *
 */
@XmlRootElement
public class Address {
	
	public Address() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Address(String city2, String state2) {
		this.city = city2;
		this.state = state2;
	}



	private String city;
	private String state;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public static Address createAddress(String city, String state)	{
		return new Address(city, state);
	}

}
