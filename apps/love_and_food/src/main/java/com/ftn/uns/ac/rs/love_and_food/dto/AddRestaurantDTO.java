package com.ftn.uns.ac.rs.love_and_food.dto;

import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;


public class AddRestaurantDTO {

	private Long restaurant_id;
	
	@NotBlank(message = "Name cannot be empty.")
	@Pattern(regexp = "[A-Z][a-z]+", message = "Name must start with capital letter and can contain only letters.")
	private String name;
	
	@NotNull(message = "Location cannot be empty.")
	private Location location;
	
	@NotNull(message = "Starting hours cannot be empty.")
	private LocalTime startingHours;
	
	@NotNull(message = "Ending hours cannot be empty.")
	private LocalTime endingHours;
	
	private List<Grade> grades;
	
	@NotNull(message = "Ambient cannot be empty.")
	private Ambient ambient;
	
	@NotNull(message = "Music cannot be empty.")
	private Music music;
	
	@NotEmpty(message = "Cuisine cannot be empty.")
	private List<Cuisine> cuisine;
	
	@NotNull(message = "Price Range cannot be empty.")
	private PriceRange priceRange;
	
	@NotNull(message = "Garden cannot be empty.")
	private boolean garden;
	
	@NotNull(message = "Wifi cannot be empty.")
	private boolean wifi;
	
	@NotNull(message = "TV cannot be empty.")
	private boolean tv;
	
	@NotNull(message = "Live music cannot be empty.")
	private boolean liveMusic;
	
	@NotNull(message = "Alcohol cannot be empty.")
	private boolean alcohol;
	
	@NotNull(message = "Parking cannot be empty.")
	private boolean parking;
	
	@NotNull(message = "Smokin area cannot be empty.")
	private boolean smokingArea;

	public AddRestaurantDTO() {
		super();
	}

	public AddRestaurantDTO(Long restaurant_id, String name, Location location, LocalTime startingHours,
			LocalTime endingHours, List<Grade> grades, Ambient ambient, Music music,List<Cuisine> cuisine,
			PriceRange priceRange, boolean garden, boolean wifi, boolean tv, boolean liveMusic, boolean alcohol,
			boolean parking, boolean smokingArea) {
		super();
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.location = location;
		this.startingHours = startingHours;
		this.endingHours = endingHours;
		this.grades = grades;
		this.ambient = ambient;
		this.music = music;
		this.cuisine = cuisine;
		this.priceRange = priceRange;
		this.garden = garden;
		this.wifi = wifi;
		this.tv = tv;
		this.liveMusic = liveMusic;
		this.alcohol = alcohol;
		this.parking = parking;
		this.smokingArea = smokingArea;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public LocalTime getStartingHours() {
		return startingHours;
	}

	public void setStartingHours(LocalTime startingHours) {
		this.startingHours = startingHours;
	}

	public LocalTime getEndingHours() {
		return endingHours;
	}

	public void setEndingHours(LocalTime endingHours) {
		this.endingHours = endingHours;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Ambient getAmbient() {
		return ambient;
	}

	public void setAmbient(Ambient ambient) {
		this.ambient = ambient;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public List<Cuisine> getCuisine() {
		return cuisine;
	}

	public void setCuisine(List<Cuisine> cuisine) {
		this.cuisine = cuisine;
	}

	public PriceRange getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(PriceRange priceRange) {
		this.priceRange = priceRange;
	}

	public boolean isGarden() {
		return garden;
	}

	public void setGarden(boolean garden) {
		this.garden = garden;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isLiveMusic() {
		return liveMusic;
	}

	public void setLiveMusic(boolean liveMusic) {
		this.liveMusic = liveMusic;
	}

	public boolean isAlcohol() {
		return alcohol;
	}

	public void setAlcohol(boolean alcohol) {
		this.alcohol = alcohol;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isSmokingArea() {
		return smokingArea;
	}

	public void setSmokingArea(boolean smokingArea) {
		this.smokingArea = smokingArea;
	}

}
