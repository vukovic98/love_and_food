package com.ftn.uns.ac.rs.love_and_food.model;

import java.time.LocalTime;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

public class RestaurantRequirements {

	private Ambient ambient;
	private boolean alcohol;
	private boolean smokingArea;

	private LocalTime dateTime;
	private boolean onFoot;
	private PriceRange priceRange;
	private Music music;
	private Cuisine cuisine;

	private boolean garden;
	private boolean tv = true;
	private boolean wifi = true;
	private boolean parking;

	public RestaurantRequirements() {
		super();
	}

	public RestaurantRequirements(Ambient ambient, boolean alcohol, boolean smokingArea, LocalTime dateTime,
			boolean onFoot, PriceRange priceRange, Music music, Cuisine cuisine, boolean tv, boolean wifi, boolean g,
			boolean p) {
		super();
		this.parking = p;
		this.garden = g;
		this.ambient = ambient;
		this.alcohol = alcohol;
		this.smokingArea = smokingArea;
		this.dateTime = dateTime;
		this.onFoot = onFoot;
		this.priceRange = priceRange;
		this.music = music;
		this.cuisine = cuisine;
		this.wifi = wifi;
		this.tv = tv;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isGarden() {
		return garden;
	}

	public void setGarden(boolean garden) {
		this.garden = garden;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public Ambient getAmbient() {
		return ambient;
	}

	public void setAmbient(Ambient ambient) {
		this.ambient = ambient;
	}

	public boolean isAlcohol() {
		return alcohol;
	}

	public void setAlcohol(boolean alcohol) {
		this.alcohol = alcohol;
	}

	public boolean isSmokingArea() {
		return smokingArea;
	}

	public void setSmokingArea(boolean smokingArea) {
		this.smokingArea = smokingArea;
	}

	public LocalTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalTime dateTime) {
		this.dateTime = dateTime;
	}

	public boolean isOnFoot() {
		return onFoot;
	}

	public void setOnFoot(boolean onFoot) {
		this.onFoot = onFoot;
	}

	public PriceRange getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(PriceRange priceRange) {
		this.priceRange = priceRange;
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

}
