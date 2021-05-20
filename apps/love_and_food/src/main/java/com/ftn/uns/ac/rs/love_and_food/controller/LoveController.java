package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.service.LoveService;

@RestController
@RequestMapping(path = "/love")
public class LoveController {
	
	@Autowired
	private LoveService loveService;
	
	//@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping( value = "/find-match")
	public ResponseEntity<String> findMatch() {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = "email";
		
		loveService.findMatch(email);
		
		return ResponseEntity.ok("ok");

	}
}
