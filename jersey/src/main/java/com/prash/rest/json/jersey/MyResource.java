
package com.prash.rest.json.jersey;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.prash.rest.client.AirportServiceImpl;
import com.prash.rest.model.AirportDetail;
import com.prash.rest.model.Country;
import com.prash.rest.model.Person;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/myresource")
@Controller(value="/myresource")
public class MyResource {
	
	
	@Autowired
	private AirportServiceImpl airportService;
	
	public MyResource() {
		System.out.println("Instance "+this+" created by thread "+Thread.currentThread().getName());
	}
    
    public AirportServiceImpl getAirportService() {
		return airportService;
	}

	public void setAirportService(AirportServiceImpl airportService) {
		this.airportService = airportService;
	}

	/** Method processing HTTP GET requests, producing "text/plain" MIME media
     * type.
     * @return String that will be send back as a response of type "text/plain".
     */
    @GET 
    @Produces("text/plain")
    public String getIt() {
        return "Hi there!";
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getpersons")
    public List<Person> getPersons() {
    	
    	List<Person> persons = new ArrayList<Person>();
    	for(int i=0; i<20; i++)	{
    		persons.add(Person.createPerson("Prashanth", "Meka - "+(new Random()).nextInt(100)));
    	}
        return persons;
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getpersonsv2")
    public String getPersonsV2() throws JsonGenerationException, JsonMappingException, IOException {
    	ObjectMapper mapper = new ObjectMapper();
    	List<Person> persons = new ArrayList<Person>();
    	for(int i=0; i<20; i++)	{
    		persons.add(Person.createPerson("Prashanth", "Meka - "+(new Random()).nextInt(100)));
    	}
    	
        return mapper.writeValueAsString(persons);
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchMatchingcountries")
    public Country[] searchMatchingCountries(@QueryParam(value = "countryStr") String countryStr)	{
    	Country[] countries = airportService.getMatchingCountryList(countryStr);
    	System.out.println("Countries length "+countries.length);
		return countries;
    	
    }
    
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchcountries")
    public Country[] searchCountries()	{
    	Country[] countries = airportService.getMatchingCountryList(null);
    	System.out.println("countries length "+countries.length);
    	return countries;
    }
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/searchairportsbycountry")
    public AirportDetail[] searchAirpotsByCountry(@QueryParam(value = "countryStr") String countryStr)	{
    	AirportDetail[] airportDetails = airportService.getAirportsByCountry(countryStr);
    	System.out.println("Airports length "+airportDetails.length);
    	return airportDetails;
    }
}
