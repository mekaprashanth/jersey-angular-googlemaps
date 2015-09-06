/**
 * 
 */
package com.prash.rest.client;

import com.prash.rest.model.AirportDetail;
import com.prash.rest.model.Country;

/**
 * @author prashanth_meka
 *
 */


public interface AirportService {
	
	public Country[] getMatchingCountryList(String countryQuery);
	
	public AirportDetail[] getAirportsByCountry(String country);
	

}
