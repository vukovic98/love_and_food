package com.ftn.uns.ac.rs.love_and_food.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ftn.uns.ac.rs.love_and_food.model.enums.AlarmType;

@Entity
@Table(name = "alarm")
public class Alarm {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@Enumerated(value = EnumType.STRING)
	private AlarmType alarmType;

	@Column(name = "message", nullable = false)
	private String message;

	@Column(name = "date", nullable = false)
	private Date date;

	public Alarm() {
		super();
	}

	public Alarm(Long id, AlarmType alarmType, String message, Date date) {
		super();
		this.id = id;
		this.alarmType = alarmType;
		this.message = message;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AlarmType getAlarmType() {
		return alarmType;
	}

	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
