package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.DateRangeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.MatchDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.MatchMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.MatchService;
import com.ftn.uns.ac.rs.love_and_food.service.RegisteredUserService;
import com.ftn.uns.ac.rs.love_and_food.service.UserService;

@RestController
@RequestMapping(path = "/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private RegisteredUserService userService;

	private final MatchMapper matchMapper = new MatchMapper();

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<MatchDTO>> findAll() {

		List<Match> matches = matchService.findAll();

		return new ResponseEntity<List<MatchDTO>>(matchMapper.toDTOList(matches), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/find-range")
	public ResponseEntity<List<MatchDTO>> findInRange(@RequestBody DateRangeDTO dateRangeDTO) {

		List<Match> matches = matchService.findInRange(dateRangeDTO);

		return new ResponseEntity<List<MatchDTO>>(matchMapper.toDTOList(matches), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ROLE_USER,ROLE_ADMIN')")
	@GetMapping(path = "/has-a-match")
	public ResponseEntity<HttpStatus> userHasAMatch() {		
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();

		RegisteredUser user = this.userService.findByEmail(email);
		
		if(user != null) {
			boolean has = this.matchService.hasAMatch(user.getId());
			
			if(has) 
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
