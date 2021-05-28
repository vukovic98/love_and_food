package com.ftn.uns.ac.rs.love_and_food.dto;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

public class RestaurantFilterDTO {

	private String name;
	private PriceRange price;
	private Location location;
	private Cuisine cuisine;

	public RestaurantFilterDTO() {
		super();
	}

	public RestaurantFilterDTO(String name, PriceRange price, Location location, Cuisine cuisine) {
		super();
		this.name = name;
		this.price = price;
		this.location = location;
		this.cuisine = cuisine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PriceRange getPrice() {
		return price;
	}

	public void setPrice(PriceRange price) {
		this.price = price;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

}
