package com.ftn.uns.ac.rs.love_and_food.model;

import java.util.ArrayList;
import java.util.List;

import com.ftn.uns.ac.rs.love_and_food.model.enums.Age;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;

public class PartnerRequirements {
	
	private Gender prefferedGender;
	private Age idealAge;
	private List<Age> suitableAge;
	private DesiredRelationship desiredRelationship;
	private Children children;
	
	private long userId;	

	public PartnerRequirements(long userId) {
		this.userId = userId;
		this.suitableAge = new ArrayList<Age>();
	}

	public Gender getPrefferedGender() {
		return prefferedGender;
	}

	public void setPrefferedGender(Gender prefferedGender) {
		this.prefferedGender = prefferedGender;
	}

	public Age getIdealAge() {
		return idealAge;
	}

	public void setIdealAge(Age idealAge) {
		this.idealAge = idealAge;
	}

	public List<Age> getSuitableAge() {
		return suitableAge;
	}

	public void setSuitableAge(List<Age> suitableAge) {
		this.suitableAge = suitableAge;
	}

	public DesiredRelationship getDesiredRelationship() {
		return desiredRelationship;
	}

	public void setDesiredRelationship(DesiredRelationship desiredRelationship) {
		this.desiredRelationship = desiredRelationship;
	}

	public Children getChildren() {
		return children;
	}

	public void setChildren(Children children) {
		this.children = children;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	

}
