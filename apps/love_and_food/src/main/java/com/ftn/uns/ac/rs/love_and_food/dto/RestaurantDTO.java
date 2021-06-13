package com.ftn.uns.ac.rs.love_and_food.dto;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Set;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

public class RestaurantDTO {

	private Long restaurant_id;
	private String name;
	private Location location;
	private LocalTime startingHours;
	private LocalTime endingHours;
	private Ambient ambient;
	private Music music;
	private Set<Cuisine> cuisine;
	private PriceRange priceRange;
	private boolean garden;
	private boolean wifi;
	private boolean tv;
	private boolean liveMusic;
	private boolean alcohol;
	private boolean parking;
	private boolean smokingArea;
	private ArrayList<GradeDTO> grades;
	private String image;

	public RestaurantDTO() {
		super();
	}

	public RestaurantDTO(Long restaurant_id, String name, Location location, LocalTime startingHours,
			LocalTime endingHours, Ambient ambient, Music music, Set<Cuisine> cuisine, PriceRange priceRange,
			boolean garden, boolean wifi, boolean tv, boolean liveMusic, boolean alcohol, boolean parking,
			boolean smokingArea, ArrayList<GradeDTO> grades, String image) {
		super();
		this.restaurant_id = restaurant_id;
		this.name = name;
		this.location = location;
		this.startingHours = startingHours;
		this.endingHours = endingHours;
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
		this.grades = grades;
		this.image = image;
	}

	public Long getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(Long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public Set<Cuisine> getCuisine() {
		return cuisine;
	}

	public void setCuisine(Set<Cuisine> cuisine) {
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

	public ArrayList<GradeDTO> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<GradeDTO> grades) {
		this.grades = grades;
	}

}
