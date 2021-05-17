package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.RegisteredUserDTO;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	private TestService testService;

	@PostMapping
	public ResponseEntity<RegisteredUser> test(@RequestBody RegisteredUserDTO registeredUserDto) {

		RegisteredUser freshUser = new RegisteredUser(registeredUserDto.getName(), registeredUserDto.getSurname(), registeredUserDto.getEmail(),
				registeredUserDto.getPassword(), registeredUserDto.getDateOfBirth(), registeredUserDto.getGender(), registeredUserDto.getSexualOrientation());
		
		RegisteredUser newUser = this.testService.getUser(freshUser);
		
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
}
