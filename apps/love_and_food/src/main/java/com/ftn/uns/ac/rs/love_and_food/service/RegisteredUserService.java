package com.ftn.uns.ac.rs.love_and_food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;

@Service
public class RegisteredUserService {
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	public RegisteredUser findByEmail(String email) {
		return this.registeredUserRepository.findByEmail(email);
	}
	
	public RegisteredUser save(RegisteredUser registeredUser) {
		return this.registeredUserRepository.save(registeredUser);
	}
}
