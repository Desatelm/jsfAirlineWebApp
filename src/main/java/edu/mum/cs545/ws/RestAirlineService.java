package edu.mum.cs545.ws;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@ApplicationScoped 
public class RestAirlineService {

	@Inject
	private AirlineService airlineService;
	Flight test = new Flight();

	@GET
	public String helloWorld(@DefaultValue("Gorgeous") @QueryParam("name") String name) {
		return "Hello " + name + "!";
	}
	
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String  createAirline(Airline airline) {
		airlineService.create(airline);
		return "name: "+ airline.getName()+ "\nId: "+ airline.getId() + "\nsuccessfully created";
	}
	 
	@Path("delet")	
	@DELETE
	public void  deleteAirline(Airline airport) {
		airlineService.delete(airport);
	}
	
	@Path("airline")
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	public Airline findByAirline(@QueryParam("airline") Airline airline ) {			
		return airlineService.find(airline);
		}
	
	@Path("flight")
	@GET
	public List<Airline> findByFlight(Flight flight ) {			
		return airlineService.findByFlight(flight);
		}
    
	@Path("name")
	@GET
	public String getAirlineTest(@QueryParam("name") String name) {
		String result = "Nil!";		
		Airline airline = airlineService.findByName(name);		
			result = "This is an airline: " + airline.getName();		
		return result;
	}
	
	@Path("list")
	@GET
	public List<Airline> getListAirline() {
		String result = "Nil!";
		
		List<Airline> airlines = airlineService.findAll();		
		for (Airline airline: airlines) {
			result = "This is an airline: " + airline.getName();
			System.out.println(result);
		}
		return airlines;
	}

}
