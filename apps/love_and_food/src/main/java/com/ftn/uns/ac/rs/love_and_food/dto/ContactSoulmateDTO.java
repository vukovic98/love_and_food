package com.ftn.uns.ac.rs.love_and_food.dto;

public class ContactSoulmateDTO {

	private String soulmateEmail;
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
