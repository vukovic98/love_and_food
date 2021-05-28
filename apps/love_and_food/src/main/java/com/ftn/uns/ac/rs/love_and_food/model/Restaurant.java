package com.ftn.uns.ac.rs.love_and_food.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "restaurant_id")
	private Long restaurant_id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "location", nullable = false)
	private Location location;

	@Column(name = "starting_hours", nullable = false)
	private LocalTime startingHours;

	@Column(name = "ending_hours", nullable = false)
	private LocalTime endingHours;

	@ElementCollection
	@CollectionTable(name = "restaurant_grade", joinColumns = @JoinColumn(name = "restaurant_id"))
	private List<Grade> grades;

	@Enumerated(value = EnumType.STRING)
	private Ambient ambient;

	@Enumerated(value = EnumType.STRING)
	private Music music;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "restaurant_cuisine", joinColumns = @JoinColumn(name = "restaurant_id"))
	private Set<Cuisine> cuisine;

	@Enumerated(value = EnumType.STRING)
	private PriceRange priceRange;

	@Column(name = "garden", nullable = false)
	private boolean garden;

	@Column(name = "wifi", nullable = false)
	private boolean wifi;

	@Column(name = "tv", nullable = false)
	private boolean tv;

	@Column(name = "live_music", nullable = false)
	private boolean liveMusic;

	@Column(name = "alcohol", nullable = false)
	private boolean alcohol;

	@Column(name = "parking", nullable = false)
	private boolean parking;

	@Column(name = "smoking_area", nullable = false)
	private boolean smokingArea;

	public Restaurant() {
		super();
	}

	public Restaurant(Long restaurant_id, String name, Location location, LocalTime startingHours,
			LocalTime endingHours, ArrayList<Grade> grades, Ambient ambient, Music music, Set<Cuisine> cuisine,
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

	public void setGrades(ArrayList<Grade> grades) {
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

	public boolean isOpen(LocalTime time) {
		if (this.startingHours.isAfter(this.endingHours)) {
			return !time.isBefore(this.startingHours) || !time.isAfter(this.endingHours);
		} else {
			return !time.isBefore(this.startingHours) && !time.isAfter(this.endingHours);
		}
	}
	
	public boolean isOpenForNext3Hours(LocalTime time) {
		time = time.plusHours(3);
		
		return this.isOpen(time);
	}

}
