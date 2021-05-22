package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.LoveService;

@RestController
@RequestMapping(path = "/love")
public class LoveController {
	
	@Autowired
	private LoveService loveService;
	
	private final UserMapper userMapper = new UserMapper();
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping( value = "/find-match")
	public ResponseEntity<UserDTO> findMatch() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		
		User soulmate = loveService.findMatch(email);
		
		return new ResponseEntity<UserDTO>( userMapper.toDTO(soulmate), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping( value = "/rate-date/{matchId}/{rating}")
	public ResponseEntity<Void> rateDate(@PathVariable("matchId") Long matchId,
			@PathVariable("rating") int rating) {
		try {
			loveService.rateDate(matchId, rating);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
