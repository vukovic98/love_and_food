package com.ftn.uns.ac.rs.love_and_food.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ftn.uns.ac.rs.love_and_food.model.enums.QuestionGroup;

public class PersonalityAnswer {

	@NotBlank(message = "Question group cannot be empty.")
	@Pattern(regexp = "EI|SN|TF|JP")
	private QuestionGroup group;
	
	@NotNull(message = "Answer cannot be emtpy.")
	@Min( value = 0, message = "Invalid answer value.")
	@Min( value = 1, message = "Invalid answer value.")
	private int answer;
	
	private Long userId;
	
	public PersonalityAnswer() {}
	
	public PersonalityAnswer(
			@NotBlank(message = "Question group cannot be empty.") @Pattern(regexp = "EI|SN|TF|JP") QuestionGroup group,
			@NotNull(message = "Answer cannot be emtpy.") @Min(value = 0, message = "Invalid answer value.") @Min(value = 1, message = "Invalid answer value.") int answer) {
		this.group = group;
		this.answer = answer;
	}
	
	public PersonalityAnswer(
			@NotBlank(message = "Question group cannot be empty.") @Pattern(regexp = "EI|SN|TF|JP") QuestionGroup group,
			@NotNull(message = "Answer cannot be emtpy.") @Min(value = 0, message = "Invalid answer value.") @Min(value = 1, message = "Invalid answer value.") int answer,
			Long userId) {
		this.group = group;
		this.answer = answer;
		this.userId = userId;
	}

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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
