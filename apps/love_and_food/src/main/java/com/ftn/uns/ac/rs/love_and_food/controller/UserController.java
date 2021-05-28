package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.UserService;

@RestController
@RequestMapping(path = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private final UserMapper userMapper = new UserMapper();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		
		List<User> users = userService.findAll();
		
		return new ResponseEntity<List<UserDTO>>( userMapper.toDTOList(users), HttpStatus.OK);

	}

}
