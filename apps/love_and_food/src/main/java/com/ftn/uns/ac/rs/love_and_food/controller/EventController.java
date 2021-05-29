package com.ftn.uns.ac.rs.love_and_food.controller;

import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.service.EventService;

@RestController
@RequestMapping(path = "/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/suspiciousActivity")
	public ResponseEntity<QueryResults> findAll() {
		
		QueryResults results = eventService.findAllSuspiciousActivity();
		
		return new ResponseEntity<QueryResults>( results, HttpStatus.OK);

	}

}
