package com.ftn.uns.ac.rs.love_and_food.model;

import com.ftn.uns.ac.rs.love_and_food.model.enums.QuestionGroup;

public class PersonalityAnswer {

	private QuestionGroup group;
	private int answer;
	
	public PersonalityAnswer() {}
	
	public QuestionGroup getGroup() {
		return group;
	}
	public void setGroup(QuestionGroup group) {
		this.group = group;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
}
