package com.ftn.uns.ac.rs.love_and_food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	public User register(User user) {
		User existingUser = (User) registeredUserRepository.findOneByEmail(user.getEmail());
		if(existingUser == null) {
			//ovde treba da se hashira lozinka i da se dodaju authorities i onda da se sacuva u bazu
		}
		
		return user;
	}

}
