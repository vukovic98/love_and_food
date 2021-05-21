package com.ftn.uns.ac.rs.love_and_food.dto;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

public class RestaurantEntryDTO {

	private Music music;
	private Cuisine cuisine;
	private PriceRange priceRange;
	private boolean onFoot;
	private boolean hasGarden;

	public RestaurantEntryDTO() {
		super();
	}

	public RestaurantEntryDTO(Music music, Cuisine cuisine, PriceRange priceRange, boolean onFoot, boolean hasGarden) {
		super();
		this.music = music;
		this.cuisine = cuisine;
		this.priceRange = priceRange;
		this.onFoot = onFoot;
		this.hasGarden = hasGarden;
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
