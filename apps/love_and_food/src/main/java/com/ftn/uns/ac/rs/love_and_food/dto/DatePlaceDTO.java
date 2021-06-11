package com.ftn.uns.ac.rs.love_and_food.dto;

public class DatePlaceDTO {
	private RestaurantDTO restaurant;
	private String date;
	private String initiator;
	private String soulmate;

	public DatePlaceDTO() {
		super();
	}

	public DatePlaceDTO(RestaurantDTO restaurant, String date, String initiator, String soulmate) {
		super();
		this.restaurant = restaurant;
		this.date = date;
		this.initiator = initiator;
		this.soulmate = soulmate;
	}

	public RestaurantDTO getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDTO restaurant) {
		this.restaurant = restaurant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public String getSoulmate() {
		return soulmate;
	}

	public void setSoulmate(String soulmate) {
		this.soulmate = soulmate;
	}

}
