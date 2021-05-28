package com.ftn.uns.ac.rs.love_and_food.event;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

import com.ftn.uns.ac.rs.love_and_food.model.Grade;

@Role(Role.Type.EVENT)
@Timestamp("timestamp")
@Expires("2h")
public class RestaurantRatingEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date timestamp;
	private Grade grade;
	private String message;

	public RestaurantRatingEvent() {
		super();
	}

	public RestaurantRatingEvent(Date timestamp, Grade grade, String m) {
		super();
		this.message = m;
		this.timestamp = timestamp;
		this.grade = grade;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
