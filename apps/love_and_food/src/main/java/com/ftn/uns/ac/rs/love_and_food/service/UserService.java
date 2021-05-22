package com.ftn.uns.ac.rs.love_and_food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public RegisteredUser findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
}
