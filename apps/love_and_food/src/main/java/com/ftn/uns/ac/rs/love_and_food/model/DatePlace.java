package com.ftn.uns.ac.rs.love_and_food.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "date_place")
public class DatePlace {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "date_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "initiator_id", nullable = false)
	private User initiator;

	@ManyToOne
	@JoinColumn(name = "soulmate_id", nullable = false)
	private User soulmate;

	@Column(name = "date", nullable = false)
	private Date date;

	public DatePlace() {
		super();
	}

	public DatePlace(Long id, Restaurant restaurant, User initiator, User soulmate, java.util.Date date) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.initiator = initiator;
		this.soulmate = soulmate;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public User getInitiator() {
		return initiator;
	}

	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	public User getSoulmate() {
		return soulmate;
	}

	public void setSoulmate(User soulmate) {
		this.soulmate = soulmate;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

}
