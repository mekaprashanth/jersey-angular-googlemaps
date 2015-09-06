/**
 * 
 */
package com.prash.rest.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.ResourceUtils;

/**
 * @author prashanth_meka
 *
 */
public class Country {
	
	private String isoCode;
	
	private String name;
	
	public Country(String isoCode, String name) {
		this.isoCode = isoCode.toUpperCase();
		this.name = name.toUpperCase();
	}

	public String getIsoCode() {
		return isoCode;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.toUpperCase();
	}
	
	
	
	@Override
	public String toString() {
		return "Country [isoCode=" + isoCode + ", name=" + name + "]";
	}

	public static void main(String[] args) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Country> countryList = getAllCountries();
		String jsonStr = mapper.writeValueAsString(countryList);
		System.out.println(jsonStr);
	}

	public static List<Country> getAllCountries() throws FileNotFoundException, IOException {
		File file = ResourceUtils.getFile("classpath:ISOCountry.txt");
		BufferedReader br = null;
		List<Country> countryList=null;
		try	{
			br = new BufferedReader(new FileReader(file));
			countryList = new ArrayList<Country>();
			
			String temp;
			String[] arr;
			while((temp=br.readLine()) != null)	{
				arr = temp.split(",");
				countryList.add(new Country(arr[0],arr[1]));
			}
		}catch(Exception e)	{
			e.printStackTrace();
			br.close();
		}
		
		return countryList;
	}
	
	

}
