package com.ftn.uns.ac.rs.love_and_food.model.enums;

public enum QuestionGroup {
	EI("EI"), 
	SN("SN"), 
	TF("TF"), 
	JP("JP");
	
	private String name;
	 
	QuestionGroup(String name) {
        this.name = name;
    }
 
    public String getName() {
        return name;
    }
}
