package com.ftn.uns.ac.rs.love_and_food.dto;

import com.ftn.uns.ac.rs.love_and_food.model.User;

public class UserRatingDTO {
	private User user;
	private double averageRating;
	
	public UserRatingDTO() { }
	
	public UserRatingDTO(User user, double averageRating) {
		this.user = user;
		this.averageRating = averageRating;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
	
}
