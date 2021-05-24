package com.ftn.uns.ac.rs.love_and_food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grade")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "grade_id")
	private Long grade_id;

	@Column(name = "location", nullable = false)
	private int location_grade;

	@Column(name = "ambient", nullable = false)
	private int ambient_grade;

	@Column(name = "atmosphere", nullable = false)
	private int atmosphere_grade;

	@Column(name = "hospitability", nullable = false)
	private int hospitability_grade;

	@Column(name = "service", nullable = false)
	private int service_grade;

	@Column(name = "overall", nullable = false)
	private int overall_grade;

	@ManyToOne
	@JoinColumn(name = "restaurant_id", nullable = false)
	private Restaurant restaurant;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Grade() {
		super();
	}

	public Grade(Long grade_id, int location_grade, int ambient_grade, int atmosphere_grade, int hospitability_grade,
			int service_grade, int overall_grade, Restaurant restaurant, User user) {
		super();
		this.grade_id = grade_id;
		this.location_grade = location_grade;
		this.ambient_grade = ambient_grade;
		this.atmosphere_grade = atmosphere_grade;
		this.hospitability_grade = hospitability_grade;
		this.service_grade = service_grade;
		this.overall_grade = overall_grade;
		this.restaurant = restaurant;
		this.user = user;
	}

	public Long getGrade_id() {
		return grade_id;
	}

	public void setGrade_id(Long grade_id) {
		this.grade_id = grade_id;
	}

	public int getLocation_grade() {
		return location_grade;
	}

	public void setLocation_grade(int location_grade) {
		this.location_grade = location_grade;
	}

	public int getAmbient_grade() {
		return ambient_grade;
	}

	public void setAmbient_grade(int ambient_grade) {
		this.ambient_grade = ambient_grade;
	}

	public int getAtmosphere_grade() {
		return atmosphere_grade;
	}

	public void setAtmosphere_grade(int atmosphere_grade) {
		this.atmosphere_grade = atmosphere_grade;
	}

	public int getHospitability_grade() {
		return hospitability_grade;
	}

	public void setHospitability_grade(int hospitability_grade) {
		this.hospitability_grade = hospitability_grade;
	}

	public int getService_grade() {
		return service_grade;
	}

	public void setService_grade(int service_grade) {
		this.service_grade = service_grade;
	}

	public int getOverall_grade() {
		return overall_grade;
	}

	public void setOverall_grade(int overall_grade) {
		this.overall_grade = overall_grade;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

}
