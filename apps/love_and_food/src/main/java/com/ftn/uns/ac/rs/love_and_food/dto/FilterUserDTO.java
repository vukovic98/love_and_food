package com.ftn.uns.ac.rs.love_and_food.dto;

public class FilterUserDTO {

	private String email;
	private String name;
	private String trait;
	private double lowerRating;
	private double upperRating;

	public FilterUserDTO() {
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}
	public double getLowerRating() {
		return lowerRating;
	}
	public void setLowerRating(double lowerRating) {
		this.lowerRating = lowerRating;
	}
	public double getUpperRating() {
		return upperRating;
	}
	public void setUpperRating(double upperRating) {
		this.upperRating = upperRating;
	}
	
}
