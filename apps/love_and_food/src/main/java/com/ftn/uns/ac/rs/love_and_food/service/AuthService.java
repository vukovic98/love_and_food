package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private AuthorityService authorityService;
	
	public RegisteredUser register(RegisteredUser user) {
		User existingUser = (User) userRepository.findByEmail(user.getEmail());
		if(existingUser == null) {
			//ovde treba da se hashira lozinka i da se dodaju authorities i onda da se sacuva u bazu
			//enkripcija lozinke
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			//postavljanje authorities
			user.setAuthorities(new ArrayList<>(authorityService.findByName("ROLE_USER")));
			return registeredUserRepository.save(user);
		}
		
		return user;
	}

}
