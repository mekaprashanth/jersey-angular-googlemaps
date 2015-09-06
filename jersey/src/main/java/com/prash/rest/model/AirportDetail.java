/**
 * 
 */
package com.prash.rest.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.ResourceUtils;

/**
 * @author prashanth_meka
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportDetail {
	
	private String name;
	private String city;
	private String country;
	@JsonProperty(value="iata")
	private String iataCode;
	@JsonProperty(value="icao")
	private String icaoCode;
	private String latitude;
	private String longitude;
	@JsonProperty(value="timezone")
	private String timeZone;
	
	public AirportDetail() {
		
	}

	public static ObjectMapper mapper = new ObjectMapper();

//	@JsonCreator
//	public static Airport create(String jsonStr)	{
//		
//		try {
//			System.out.println("\nattempting to convert string \n"+jsonStr);
//			Airport airport = mapper.readValue(jsonStr,\t\tAirport.class);
//			return airport;
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country.toUpperCase();
	}
	public String getIataCode() {
		return iataCode;
	}
	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}
	public String getIcaoCode() {
		return icaoCode;
	}
	public void setIcaoCode(String icaoCode) {
		this.icaoCode = icaoCode;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	@Override
	public String toString() {
		return "Airport [name=" + name + ",\t\tcity="
				+ city + ",\t\tcountry=" + country + ",\t\tiataCode=" + iataCode
				+ ",\t\ticaoCode=" + icaoCode + ",\t\tlatitude=" + latitude
				+ ",\t\tlongitude=" + longitude + ",\t\ttimeZone=" + timeZone + "]\n";
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, AirportDetail> map = getAllAirports();
		System.out.println(map);
		
	}
	public static Map<String, AirportDetail> getAllAirports()
			throws FileNotFoundException, IOException, JsonParseException, JsonMappingException {
		ObjectMapper mapper = new ObjectMapper();
		File file = ResourceUtils.getFile("classpath:airport.json");
		Map<String, AirportDetail> map = new HashMap<String, AirportDetail>();
		map = mapper.readValue(file, new TypeReference<HashMap<String,AirportDetail>>(){});
		return map;
	}
}
