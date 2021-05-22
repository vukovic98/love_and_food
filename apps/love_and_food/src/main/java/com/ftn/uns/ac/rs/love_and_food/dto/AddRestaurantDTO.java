package com.ftn.uns.ac.rs.love_and_food.dto;

import java.time.LocalTime;
import java.util.List;

import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;


public class AddRestaurantDTO {

	private Long restaurant_id;
	private String name;
	private Location location;
	private LocalTime startingHours;
	private LocalTime endingHours;
	private List<Grade> grades;
	private Ambient ambient;
	private Music music;
	private Cuisine cuisine;
	private PriceRange priceRange;
	private boolean garden;
	private boolean wifi;
	private boolean tv;
	private boolean liveMusic;
	private boolean alcohol;
	private boolean parking;
	private boolean smokingArea;

	public AddRestaurantDTO() {
		super();
	}

	public AddRestaurantDTO(Long restaurant_id, String name, Location location, LocalTime startingHours,
			LocalTime endingHours, List<Grade> grades, Ambient ambient, Music music, Cuisine cuisine,
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

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
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
