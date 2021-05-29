package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.DateRangeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.MatchDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.MatchMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.service.MatchService;

@RestController
@RequestMapping(path = "/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	private final MatchMapper matchMapper = new MatchMapper();
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public ResponseEntity<List<MatchDTO>> findAll() {
		
		List<Match> matches = matchService.findAll();
		
		return new ResponseEntity<List<MatchDTO>>( matchMapper.toDTOList(matches), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/find-range")
	public ResponseEntity<List<MatchDTO>> findInRange(@RequestBody DateRangeDTO dateRangeDTO) {
		
		List<Match> matches = matchService.findInRange(dateRangeDTO);
		
		return new ResponseEntity<List<MatchDTO>>( matchMapper.toDTOList(matches), HttpStatus.OK);
	}
}
