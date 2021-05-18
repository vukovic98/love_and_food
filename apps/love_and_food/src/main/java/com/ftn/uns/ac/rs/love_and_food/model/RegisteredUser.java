package com.ftn.uns.ac.rs.love_and_food.model;

import java.util.Date;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class RegisteredUser {

	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Date dateOfBirth;
	private double income;
	
	private Gender gender;
	private SexualOrientation sexualOrientation;
	private Education education;
	private Religion religion;
	private Children children;
	private DesiredRelationship desiredRelationship;
	private Location location;
	
	private boolean alchocol;
	private boolean smoking;
	
	public RegisteredUser(String name, String surname, String email, String password, Date dateOfBirth, double income,
			Gender gender, SexualOrientation sexualOrientation, Education education, Religion religion,
			Children children, DesiredRelationship desiredRelationship, Location location, boolean alchocol,
			boolean smoking) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.income = income;
		this.gender = gender;
		this.sexualOrientation = sexualOrientation;
		this.education = education;
		this.religion = religion;
		this.children = children;
		this.desiredRelationship = desiredRelationship;
		this.location = location;
		this.alchocol = alchocol;
		this.smoking = smoking;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	public Religion getReligion() {
		return religion;
	}
	public void setReligion(Religion religion) {
		this.religion = religion;
	}
	public Children getChildren() {
		return children;
	}
	public void setChildren(Children children) {
		this.children = children;
	}
	public DesiredRelationship getDesiredRelationship() {
		return desiredRelationship;
	}
	public void setDesiredRelationship(DesiredRelationship desiredRelationship) {
		this.desiredRelationship = desiredRelationship;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public boolean isAlchocol() {
		return alchocol;
	}
	public void setAlchocol(boolean alchocol) {
		this.alchocol = alchocol;
	}
	public boolean isSmoking() {
		return smoking;
	}
	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}
	public SexualOrientation getSexualOrientation() {
		return sexualOrientation;
	}
	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}

}
