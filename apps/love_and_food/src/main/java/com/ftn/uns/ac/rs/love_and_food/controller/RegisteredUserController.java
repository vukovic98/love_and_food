package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.RegisteredUserDTO;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.service.RegisteredUserService;

@RestController
@RequestMapping(path = "/register")
public class RegisteredUserController {
	
	@Autowired
	private RegisteredUserService registeredUserService;
	
	@PostMapping
	public ResponseEntity<PartnerRequirements> register(@RequestBody RegisteredUserDTO registeredUserDto) {

		RegisteredUser freshUser = new RegisteredUser(registeredUserDto.getName(), registeredUserDto.getSurname(), registeredUserDto.getEmail(),
				registeredUserDto.getPassword(), registeredUserDto.getDateOfBirth(),  registeredUserDto.getIncome(), registeredUserDto.getGender(),
				registeredUserDto.getSexualOrientation(), registeredUserDto.getEducation(), registeredUserDto.getReligion(), registeredUserDto.getChildren(),
				registeredUserDto.getDesiredRelationship(), registeredUserDto.getLocation(), registeredUserDto.isAlchocol(), registeredUserDto.isSmoking());
		
		PartnerRequirements partnerRequirements = this.registeredUserService.findMatch(freshUser);
		
		return new ResponseEntity<>(partnerRequirements, HttpStatus.OK);
	}

}
