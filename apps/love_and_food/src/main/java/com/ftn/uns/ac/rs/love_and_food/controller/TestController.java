package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	private TestService testService;

//	@PostMapping
//	public ResponseEntity<RegisteredUser> register(@RequestBody UserDTO registeredUserDto) {
//
//		RegisteredUser freshUser = new RegisteredUser(registeredUserDto.getName(), registeredUserDto.getSurname(), registeredUserDto.getEmail(),
//				registeredUserDto.getPassword(), registeredUserDto.getDateOfBirth(),  registeredUserDto.getIncome(), registeredUserDto.getGender(),
//				registeredUserDto.getSexualOrientation(), registeredUserDto.getEducation(), registeredUserDto.getReligion(), registeredUserDto.getChildren(),
//				registeredUserDto.getDesiredRelationship(), registeredUserDto.getLocation(), registeredUserDto.isAlchocol(), registeredUserDto.isSmoking());
//		
//		RegisteredUser newUser = this.testService.findMatch(freshUser);
//		
//		return new ResponseEntity<>(newUser, HttpStatus.OK);
//	}
}
