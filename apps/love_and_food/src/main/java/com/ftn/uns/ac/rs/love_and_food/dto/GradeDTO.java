package com.ftn.uns.ac.rs.love_and_food.dto;

import javax.validation.constraints.NotNull;

public class GradeDTO {

	@NotNull(message = "Restaurant ID cannot be empty!")
	private long restaurantID;
	
	@NotNull(message = "Ambient cannot be empty!")
	private int ambient;
	
	@NotNull(message = "Atmosphere cannot be empty!")
	private int atmosphere;
	
	@NotNull(message = "Service cannot be empty!")
	private int service;
	
	@NotNull(message = "Location cannot be empty!")
	private int location;
	
	@NotNull(message = "Overall cannot be empty!")
	private int overall;
	
	@NotNull(message = "Hospitability cannot be empty!")
	private int hospitability;

	public GradeDTO() {
		super();
	}

	public GradeDTO(long restaurantID, int ambient, int atmosphere, int service, int location, int overall,
			int hospitability) {
		super();
		this.restaurantID = restaurantID;
		this.ambient = ambient;
		this.atmosphere = atmosphere;
		this.service = service;
		this.location = location;
		this.overall = overall;
		this.hospitability = hospitability;
	}

	public long getRestaurantID() {
		return restaurantID;
	}

	public void setRestaurantID(long restaurantID) {
		this.restaurantID = restaurantID;
	}

	public int getAmbient() {
		return ambient;
	}

	public void setAmbient(int ambient) {
		this.ambient = ambient;
	}

	public int getAtmosphere() {
		return atmosphere;
	}

	public void setAtmosphere(int atmosphere) {
		this.atmosphere = atmosphere;
	}

	public int getService() {
		return service;
	}

	public void setService(int service) {
		this.service = service;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getOverall() {
		return overall;
	}

	public void setOverall(int overall) {
		this.overall = overall;
	}

	public int getHospitability() {
		return hospitability;
	}

	public void setHospitability(int hospitability) {
		this.hospitability = hospitability;
	}

}
