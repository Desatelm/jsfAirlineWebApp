package edu.mum.cs545.ws;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airplane;
import cs545.airline.model.Flight;
import cs545.airline.service.AirplaneService;

@Named
@ApplicationScoped
@Path("airplane")
public class RestAirplaneService {
	private List<Airplane> airplanes;
	public List<Airplane> getAirplanes() {
		return airplanes;
	}

	public void setAirplanes(List<Airplane> airplanes) {
		this.airplanes = airplanes;
	}
	@Inject
	private AirplaneService airplaneService;
	
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public void  createAirplane(Airplane airport) {
		airplaneService.create(airport);
	}
	 
	@Path("delet")	
	@DELETE
	public void  deleteAirplane(Airplane airport) {
		airplaneService.delete(airport);
	}
		
	@Path("findByModel")
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	public List<Airplane> findByModel(String airplane ) {			
		return airplaneService.findByModel(airplane);
		}
	
	@Path("findByflight")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Airplane> findByFlight(Flight flight ) {			
		return airplaneService.findByFlight(flight);
		}    
		
	@Path("list")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 	
	public String findAll() {				
		 airplanes = airplaneService.findAll();		
		return "airplane";
	}
	
	@Path("findBySrlnr")
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public Airplane findBySrlnr(String airplane ) {			
		return airplaneService.findBySrlnr(airplane);
		} 
	@Path("update")
	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	public Airplane update(Airplane airplane ) {			
		return airplaneService.update(airplane);
		} 
}
