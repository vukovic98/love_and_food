package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.model.User;

@RestController
@RequestMapping(path = "/love")
public class LoveController {
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping( value = "/find-match")
	public ResponseEntity<User> findMatch() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		
		return ResponseEntity.ok(user);

	}
}
