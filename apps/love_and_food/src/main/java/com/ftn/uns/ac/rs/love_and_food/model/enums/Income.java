package com.ftn.uns.ac.rs.love_and_food.model.enums;

public enum Income {
	INCOME_NONE(0), 
	INCOME_0_500(1), 
	INCOME_500_1000(2), 
	INCOME_2000_PLUS(3);
	
	private int value;
	
	Income(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
}
