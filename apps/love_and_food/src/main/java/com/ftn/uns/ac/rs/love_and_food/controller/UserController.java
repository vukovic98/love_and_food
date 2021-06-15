package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.UserMVPDTO;
import com.ftn.uns.ac.rs.love_and_food.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<Page<UserMVPDTO>> findAll(@PathVariable("pageNum") int pageNum) {
		
		Page<UserMVPDTO> users= userService.findAll(pageNum);
		
		return new ResponseEntity<Page<UserMVPDTO>>( users, HttpStatus.OK);
	}

}
