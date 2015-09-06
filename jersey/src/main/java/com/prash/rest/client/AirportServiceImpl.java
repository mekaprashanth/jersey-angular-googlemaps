/**
 * 
 */
package com.prash.rest.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Service;

import com.prash.rest.model.AirportDetail;
import com.prash.rest.model.Country;

/**
 * @author prashanthmeka
 *
 */
@Service
public class AirportServiceImpl implements AirportService {

	/* (non-Javadoc)
	 * @see com.prash.rest.client.AirportService#getMatchingCountryList(java.lang.String)
	 */
	@Override
	public Country[] getMatchingCountryList(String countryQuery) {
		List<Country> returnList = new ArrayList<Country>();
		try {
			List<Country> countryList = Country.getAllCountries();
			if(countryQuery == null || countryQuery.isEmpty())	{
				returnList.addAll(countryList);
				return returnList.toArray(new Country[0]);
			}
			countryQuery = countryQuery.toUpperCase();
			for(Country c:countryList)	{
				if(c.getName().contains(countryQuery) || c.getIsoCode().contains(countryQuery))	{
					returnList.add(c);
				}
			}
			return returnList.toArray(new Country[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.prash.rest.client.AirportService#getAirportsByCountryCode(java.lang.String)
	 */
	@Override
	public AirportDetail[] getAirportsByCountry(String country) {
		Map<String, AirportDetail> map = null;
		
		try {
			map = AirportDetail.getAllAirports();
			Collection<AirportDetail> airportDetailColl = map.values();
			Iterator<AirportDetail> iter = airportDetailColl.iterator();
			AirportDetail airportDetail = null;
			for(;iter.hasNext();)	{
				airportDetail = iter.next();
				if(!airportDetail.getCountry().contains(country))	{
					iter.remove();
				}
			}
			return airportDetailColl.toArray(new AirportDetail[0]);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
