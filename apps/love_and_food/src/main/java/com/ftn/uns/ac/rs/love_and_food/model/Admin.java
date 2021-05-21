package com.ftn.uns.ac.rs.love_and_food.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

	public Admin() {
		super();
	}

	public Admin(String email, String password) {
		super(email, password);
	}

}
