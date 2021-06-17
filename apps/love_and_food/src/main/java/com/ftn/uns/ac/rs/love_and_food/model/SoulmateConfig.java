package com.ftn.uns.ac.rs.love_and_food.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "soulmate_config")
public class SoulmateConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "soulmate_config_id")
	private Long id;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "smoking_points")
	private int smokingPoints;
	
	@Column(name = "alcohol_points")
	private int alcoholPoints;
	
	@Column(name = "education_points")
	private int educationPoints;
	
	@Column(name = "traitPoints_points")
	private int traitPoints;
	
	@Column(name = "income_points")
	private int incomePoints;
	
	@Column(name = "religion_points")
	private int religionPoints;
	
	public SoulmateConfig() {
	}

	public SoulmateConfig(Long userId) {
		this.userId = userId;
		this.alcoholPoints = 1;
		this.educationPoints = 1;
		this.incomePoints = 1;
		this.religionPoints = 1;
		this.smokingPoints = 1;
		this.traitPoints = 1;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getSmokingPoints() {
		return smokingPoints;
	}

	public void setSmokingPoints(int smokingPoints) {
		this.smokingPoints = smokingPoints;
	}

	public int getAlcoholPoints() {
		return alcoholPoints;
	}

	public void setAlcoholPoints(int alcoholPoints) {
		this.alcoholPoints = alcoholPoints;
	}

	public int getEducationPoints() {
		return educationPoints;
	}

	public void setEducationPoints(int educationPoints) {
		this.educationPoints = educationPoints;
	}

	public int getTraitPoints() {
		return traitPoints;
	}

	public void setTraitPoints(int traitPoints) {
		this.traitPoints = traitPoints;
	}

	public int getIncomePoints() {
		return incomePoints;
	}

	public void setIncomePoints(int incomePoints) {
		this.incomePoints = incomePoints;
	}

	public int getReligionPoints() {
		return religionPoints;
	}

	public void setReligionPoints(int religionPoints) {
		this.religionPoints = religionPoints;
	}
}
