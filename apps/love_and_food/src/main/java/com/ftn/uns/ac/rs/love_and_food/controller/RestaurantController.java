package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {
	
	@GetMapping()
	public ResponseEntity<String> test() {
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		
		
		return new ResponseEntity<>(username, HttpStatus.OK);
	}

	@PostMapping(path = "/find-restaurant")
	public ResponseEntity<Restaurant> findRestaurant(@RequestBody RestaurantEntryDTO dto) {
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
