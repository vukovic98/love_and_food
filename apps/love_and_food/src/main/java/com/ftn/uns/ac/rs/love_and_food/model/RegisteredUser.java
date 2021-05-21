package com.ftn.uns.ac.rs.love_and_food.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

@Entity
@Table(name = "registered_user")
public class RegisteredUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "surname", nullable = false)
	private String surname;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "income", nullable = false)
	private double income;

	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "sexual_orientation", nullable = false)
	private SexualOrientation sexualOrientation;

	@Column(name = "education", nullable = false)
	private Education education;

	@Column(name = "religion", nullable = false)
	private Religion religion;

	@Column(name = "children", nullable = false)
	private Children children;

	@Column(name = "desired_relationship", nullable = false)
	private DesiredRelationship desiredRelationship;

	@ManyToOne
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;

	@Column(name = "alchocol", nullable = false)
	private boolean alchocol;

	@Column(name = "smoking", nullable = false)
	private boolean smoking;

	public RegisteredUser() {
		super();
	}

	public RegisteredUser(String name, String surname, Date dateOfBirth, double income, Gender gender,
			SexualOrientation sexualOrientation, Education education, Religion religion, Children children,
			DesiredRelationship desiredRelationship, Location location, boolean alchocol, boolean smoking) {
		super();
		this.name = name;
		this.surname = surname;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public SexualOrientation getSexualOrientation() {
		return sexualOrientation;
	}

	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;

		RegisteredUser other = (RegisteredUser) obj;

		if (other.getId() == null || this.getId() == null) {
			if (other.getEmail().equals(getEmail())) {
				return true;
			}
			return false;
		}
		return Objects.equals(this.getId(), other.getId());

	}

}
