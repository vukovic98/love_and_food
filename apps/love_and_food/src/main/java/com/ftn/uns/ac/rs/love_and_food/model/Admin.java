package com.ftn.uns.ac.rs.love_and_food.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}

	public Admin(String email, String password) {
		super(email, password);
	}

}
