package com.ftn.uns.ac.rs.love_and_food.model.enums;

public enum Education {
	HIGH_SCHOOL(0), 
	BSC(1), 
	PHD(2);
	
	private int value;
	
	Education(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
