package com.ftn.uns.ac.rs.love_and_food.dto;

import com.ftn.uns.ac.rs.love_and_food.model.enums.AlarmType;

public class FilterAlarmDTO {

	private AlarmType alarmType;
	
	public FilterAlarmDTO() {}
	
	public AlarmType getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(AlarmType alarmType) {
		this.alarmType = alarmType;
	}
}
