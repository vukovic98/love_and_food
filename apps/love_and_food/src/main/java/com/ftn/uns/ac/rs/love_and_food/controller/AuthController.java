package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.AuthService;
import com.ftn.uns.ac.rs.love_and_food.service.RegisteredUserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping( value = "/register")
	public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {

		User user = new User(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(),
				userDTO.getDateOfBirth(), userDTO.getIncome(), userDTO.getGender(), userDTO.getSexualOrientation(),
				userDTO.getEducation(), userDTO.getReligion(), userDTO.getChildren(), userDTO.getDesiredRelationship(),
				userDTO.getLocation(), userDTO.isAlchocol(), userDTO.isSmoking());
		
		User createdUser = this.authService.register(user);
		
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

}
