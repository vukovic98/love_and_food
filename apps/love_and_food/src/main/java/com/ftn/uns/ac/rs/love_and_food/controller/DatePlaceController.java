package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.DatePlaceDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.DatePlaceMapper;
import com.ftn.uns.ac.rs.love_and_food.model.DatePlace;
import com.ftn.uns.ac.rs.love_and_food.service.DatePlaceService;

@RestController
@RequestMapping(path = "/date-place")
public class DatePlaceController {

	@Autowired
	private DatePlaceService datePlaceService;
	
	@Autowired
	private DatePlaceMapper datePlaceMapper;
	
	@GetMapping(path = "/{userID}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ArrayList<DatePlaceDTO>> getAllByUser(@PathVariable("userID") long userId) {
		ArrayList<DatePlace> list = this.datePlaceService.findAllByUser(userId);
		
		if(!list.isEmpty())
			return new ResponseEntity<>((ArrayList<DatePlaceDTO>) this.datePlaceMapper.toDTOList(list), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
