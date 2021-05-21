package com.ftn.uns.ac.rs.love_and_food.dto;

import java.time.LocalTime;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;

public class RestaurantEntryDTO {

	private Music music;
	private Cuisine cuisine;
	private boolean onFoot;
	private boolean hasGarden;
	private LocalTime dateTime;

	public RestaurantEntryDTO() {
		super();
	}

	public RestaurantEntryDTO(Music music, Cuisine cuisine, boolean onFoot, boolean hasGarden,
			LocalTime dt) {
		super();
		this.music = music;
		this.cuisine = cuisine;
		this.onFoot = onFoot;
		this.hasGarden = hasGarden;
		this.dateTime = dt;
	}

	public LocalTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalTime dateTime) {
		this.dateTime = dateTime;
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

	public boolean isOnFoot() {
		return onFoot;
	}

	public void setOnFoot(boolean onFoot) {
		this.onFoot = onFoot;
	}

	public boolean isHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(boolean hasGarden) {
		this.hasGarden = hasGarden;
	}

}
