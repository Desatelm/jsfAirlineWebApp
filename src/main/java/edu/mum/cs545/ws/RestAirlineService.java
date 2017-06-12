package edu.mum.cs545.ws;


import java.io.Serializable;
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
public class RestAirlineService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	List<Airline> airlines ;
	@Inject
	private AirlineService airlineService;
	

	public List<Airline> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<Airline> airlines) {
		this.airlines = airlines;
	}
	
	
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public String  createAirline() {
		Airline airline = new Airline();
		airline.setName(this.name);
		airlineService.create(airline);
		airlines = airlineService.findAll();
		return "airlines";
	}

	 {}
	@Path("delete")	
	@Consumes(MediaType.APPLICATION_JSON)
	@DELETE
	public String  deleteAirline(String name) {
		
		try{
		Airline airline = airlineService.findByName(name);		
		airlineService.delete(airline);
		airlines = airlineService.findAll();		
	   }
		catch(Exception e){
			
				e.printStackTrace();
				}
		
		return "airlines";
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
	public Airline getbyname( String name) {		 		
					
		return airlineService.findByName(name);
	}
	
	@Path("list")
	@GET
	public String findAll() {
			
		 airlines = airlineService.findAll();		
		 for(Airline airline:airlines)
		 {
			 airline.setEditable(false);
		 }
		return "airlines";
	}

}
