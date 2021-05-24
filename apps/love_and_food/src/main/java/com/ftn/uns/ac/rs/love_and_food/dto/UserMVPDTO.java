package com.ftn.uns.ac.rs.love_and_food.dto;

public class UserMVPDTO {
	
	private UserDTO userDTO;
	private double averageRating;
	
	public UserMVPDTO() { }
	
	public UserMVPDTO(UserDTO userDTO, double averageRating) {
		this.userDTO = userDTO;
		this.averageRating = averageRating;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	public double getRating() {
		return averageRating;
	}
	public void setRating(double rating) {
		this.averageRating = rating;
	}
}
