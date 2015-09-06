/**
 * 
 */
package com.prash.rest.client;

/**
 * @author prashanth_meka
 *
 */
public interface FlightTrackService {
	
	public Object getFlightArrivalsByAirport(String airportCode);
	
	public Object getFlightDeparturesByAirport(String airportCode);

}
