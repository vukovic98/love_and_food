package com.ftn.uns.ac.rs.love_and_food.dto;

public class SoulmateConfigDTO {
	
	private int smokingPoints;
	
	private int alcoholPoints;
	
	private int educationPoints;
	
	private int traitPoints;
	
	private int incomePoints;
	
	private int religionPoints;
	
	public SoulmateConfigDTO() {
	}
	
	public SoulmateConfigDTO(int smokingPoints, int alcoholPoints, int educationPoints, int traitPoints,
			int incomePoints, int religionPoints) {
		this.smokingPoints = smokingPoints;
		this.alcoholPoints = alcoholPoints;
		this.educationPoints = educationPoints;
		this.traitPoints = traitPoints;
		this.incomePoints = incomePoints;
		this.religionPoints = religionPoints;
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
