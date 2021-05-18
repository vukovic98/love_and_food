package com.ftn.uns.ac.rs.love_and_food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long location_id;

	@Column(name = "name", nullable = false)
	private String name;

	public Location() {
		super();
	}

	public Location(Long id, String name) {
		super();
		this.location_id = id;
		this.name = name;
	}

	public Long getId() {
		return location_id;
	}

	public void setId(Long id) {
		this.location_id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
