package com.ftn.uns.ac.rs.love_and_food.dto;

import java.time.LocalDate;

public class MatchDTO {
	
	private Long id;
	private String initiatorEmail;
	private String soulmateEmail;
	private LocalDate matchDate;
	private String coupleImage;
	private String initiatorName;
	private String soulmateName;
	private int rating;
	
	public MatchDTO() {}
	
	public MatchDTO(Long id, String initiatorEmail, String soulmateEmail, LocalDate matchDate, int rating) {
		this.id = id;
		this.initiatorEmail = initiatorEmail;
		this.soulmateEmail = soulmateEmail;
		this.matchDate = matchDate;
		this.rating = rating;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getInitiatorEmail() {
		return initiatorEmail;
	}
	public void setInitiatorEmail(String initiatorEmail) {
		this.initiatorEmail = initiatorEmail;
	}
	public String getSoulmateEmail() {
		return soulmateEmail;
	}
	public void setSoulmateEmail(String soulmateEmail) {
		this.soulmateEmail = soulmateEmail;
	}
	public LocalDate getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}
	public String getCoupleImage() {
		return coupleImage;
	}
	public void setCoupleImage(String coupleImage) {
		this.coupleImage = coupleImage;
	}
	public String getInitiatorName() {
		return initiatorName;
	}
	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}
	public String getSoulmateName() {
		return soulmateName;
	}
	public void setSoulmateName(String soulmateName) {
		this.soulmateName = soulmateName;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
}
