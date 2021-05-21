package com.ftn.uns.ac.rs.love_and_food.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_match")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_match_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "initiator_id", referencedColumnName = "user_id", nullable = false)
	private RegisteredUser initiator;
	
	@ManyToOne
	@JoinColumn(name = "soulmate_id", referencedColumnName = "user_id", nullable = false)
	private RegisteredUser soulmate;
	
	@Column(name = "match_date", nullable = false)
	private LocalDate matchDate;
	
	@Column(name = "soulmate_rating", nullable = true)
	private int rating;
	
	public Match() {}
	
	public Match(Long id, RegisteredUser initiator, RegisteredUser soulmate, LocalDate matchDate, int rating) {
		this.id = id;
		this.initiator = initiator;
		this.soulmate = soulmate;
		this.matchDate = matchDate;
		this.rating = rating;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public RegisteredUser getInitiator() {
		return initiator;
	}
	public void setInitiator(RegisteredUser initiator) {
		this.initiator = initiator;
	}
	public RegisteredUser getSoulmate() {
		return soulmate;
	}
	public void setSoulmate(RegisteredUser soulmate) {
		this.soulmate = soulmate;
	}
	public LocalDate getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(LocalDate matchDate) {
		this.matchDate = matchDate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
}
