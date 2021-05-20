package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.Date;

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
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;
import com.ftn.uns.ac.rs.love_and_food.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {

	@Autowired
	private TestService testService;

	@PostMapping
	public ResponseEntity<User> register(@RequestBody UserDTO registeredUserDto) {

		User freshUser = new User(registeredUserDto.getName(), registeredUserDto.getSurname(), registeredUserDto.getEmail(),
				registeredUserDto.getPassword(), registeredUserDto.getDateOfBirth(),  registeredUserDto.getIncome(), registeredUserDto.getGender(),
				registeredUserDto.getSexualOrientation(), registeredUserDto.getEducation(), registeredUserDto.getReligion(), registeredUserDto.getChildren(),
				registeredUserDto.getDesiredRelationship(), registeredUserDto.getLocation(), registeredUserDto.isAlchocol(), registeredUserDto.isSmoking());
		
		User newUser = this.testService.findMatch(freshUser);
		
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
}
