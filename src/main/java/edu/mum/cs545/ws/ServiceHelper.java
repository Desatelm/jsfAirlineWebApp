package edu.mum.cs545.ws;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import cs545.airline.model.Airline;
import cs545.airline.service.AirlineService;

@Named
@ApplicationScoped 

public class ServiceHelper implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject 
	AirlineService airlineService;
	
	public void changeEditStatus(Airline airline){
		if(airline.isEditable()){
			airlineService.update(airline);
		}
		airline.setEditable(!airline.isEditable());
	} 

}
