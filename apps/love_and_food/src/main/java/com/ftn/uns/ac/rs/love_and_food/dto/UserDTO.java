package com.ftn.uns.ac.rs.love_and_food.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.ftn.uns.ac.rs.love_and_food.model.PersonalityAnswer;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Income;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class UserDTO {
	
	private Long id;
	
	@NotBlank(message = "Name cannot be empty.")
	@Pattern(regexp = "[A-Z][a-z]+", message = "Name must start with capital letter and can contain only letters.")
	private String name;
	
	@NotBlank(message = "Surname cannot be empty.")
	@Pattern(regexp = "[A-Z][a-z]+", message = "Surname must start with capital letter and can contain only letters.")
	private String surname;
	
	@NotBlank(message = "Email cannot be empty.")
	@Email(message = "Email must be in format 'example@mail.com'")
	private String email;
	
	@NotBlank(message = "Password cannot be empty.")
	private String password;
	
	@NotNull(message = "Date of birth cannot be empty.")
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	private Date dateOfBirth;
	
	@NotNull(message = "Gender cannot be empty.")
	private Gender gender;
	
	@NotNull(message = "Sexual orientation cannot be empty.")
	private SexualOrientation sexualOrientation;
	
	@NotNull(message = "Education cannot be empty.")
	private Education education;
	
	@NotNull(message = "Income cannot be empty.")
	private Income income;
	
	@NotNull(message = "Religion cannot be empty.")
	private Religion religion;
	
	@NotNull(message = "Children cannot be empty.")
	private Children children;
	
	@NotNull(message = "Desired relationship cannot be empty.")
	private DesiredRelationship desiredRelationship;
	
	@NotNull(message = "Location cannot be empty.")
	private Location location;

	@NotNull(message = "Alchocol cannot be empty.")
	private boolean alchocol;
	
	@NotNull(message = "Smoking cannot be empty.")
	private boolean smoking;
	
	@NotEmpty(message = "Test answers cannot be empty.")
	private List<PersonalityAnswer> testAnswers;
	
	@Column(name = "personality", nullable = false)
	private String personalityTraits;
	
	public UserDTO() {
	}
	
	public UserDTO(Long id,
			@NotBlank(message = "Name cannot be empty.") @Pattern(regexp = "[A-Z][a-z]+", message = "Name must start with capital letter and can contain only letters.") String name,
			@NotBlank(message = "Surname cannot be empty.") @Pattern(regexp = "[A-Z][a-z]+", message = "Surname must start with capital letter and can contain only letters.") String surname,
			@NotBlank(message = "Email cannot be empty.") @Email(message = "Email must be in format 'example@mail.com'") String email,
			@NotBlank(message = "Password cannot be empty.") String password,
			@NotNull(message = "Date of birth cannot be empty.") Date dateOfBirth,
			@NotNull(message = "Gender cannot be empty.") Gender gender,
			@NotNull(message = "Sexual orientation cannot be empty.") SexualOrientation sexualOrientation,
			@NotNull(message = "Education cannot be empty.") Education education,
			@NotNull(message = "Income cannot be empty.") Income income,
			@NotNull(message = "Religion cannot be empty.") Religion religion,
			@NotNull(message = "Children cannot be empty.") Children children,
			@NotNull(message = "Desired relationship cannot be empty.") DesiredRelationship desiredRelationship,
			@NotNull(message = "Location cannot be empty.") Location location,
			@NotNull(message = "Alchocol cannot be empty.") boolean alchocol,
			@NotNull(message = "Smoking cannot be empty.") boolean smoking,
			String personalityTraits) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.sexualOrientation = sexualOrientation;
		this.education = education;
		this.income = income;
		this.religion = religion;
		this.children = children;
		this.desiredRelationship = desiredRelationship;
		this.location = location;
		this.alchocol = alchocol;
		this.smoking = smoking;
		this.personalityTraits = personalityTraits;
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
	public SexualOrientation getSexualOrientation() {
		return sexualOrientation;
	}
	public void setSexualOrientation(SexualOrientation sexualOrientation) {
		this.sexualOrientation = sexualOrientation;
	}
	public Income getIncome() {
		return income;
	}
	public void setIncome(Income income) {
		this.income = income;
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
	public List<PersonalityAnswer> getTestAnswers() {
		return testAnswers;
	}
	public void setTestAnswers(List<PersonalityAnswer> testAnswers) {
		this.testAnswers = testAnswers;
	}
	public String getPersonalityTraits() {
		return personalityTraits;
	}
	public void setPersonalityTraits(String personalityTraits) {
		this.personalityTraits = personalityTraits;
	}
}
