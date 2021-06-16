package com.ftn.uns.ac.rs.love_and_food.dto;

import javax.validation.constraints.NotBlank;

public class ContactSoulmateDTO {

	@NotBlank(message = "Soulmate email cannot be empty.")
	private String soulmateEmail;
	
	@NotBlank(message = "Message cannot be empty.")
	private String message;

	public ContactSoulmateDTO() {
		super();
	}

	public ContactSoulmateDTO(String soulmateEmail, String message) {
		super();
		this.soulmateEmail = soulmateEmail;
		this.message = message;
	}

	public String getSoulmateEmail() {
		return soulmateEmail;
	}

	public void setSoulmateEmail(String soulmateEmail) {
		this.soulmateEmail = soulmateEmail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
