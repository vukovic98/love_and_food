package com.ftn.uns.ac.rs.love_and_food.dto;

public class GradeDTO {

	private long restaurantID;
	private int ambient;
	private int atmosphere;
	private int service;
	private int location;
	private int overall;
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
