package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.GradeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.RestaurantMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.MatchService;
import com.ftn.uns.ac.rs.love_and_food.service.RegisteredUserService;
import com.ftn.uns.ac.rs.love_and_food.service.RestaurantService;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private RegisteredUserService registeredUserService;

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private RestaurantMapper restaurantMapper;

	@PostMapping()
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant r) {
		Restaurant saved = this.restaurantService.addRestaurant(r);

		if (saved != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/find-restaurant")
	public ResponseEntity<RestaurantDTO> findRestaurant(@RequestBody RestaurantEntryDTO dto) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		User user = this.registeredUserService.findByEmail(username);

		if (user != null) {
			Match match = this.matchService.findByInitiator(user.getId());

			if (match != null) {

				Restaurant r = this.restaurantService.findRestaurant(dto, match);

				if (r != null)
					return new ResponseEntity<>(this.restaurantMapper.toDTO(r), HttpStatus.OK);
				else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/grade-restaurant")
	public ResponseEntity<HttpStatus> gradeRestaurant(@RequestBody GradeDTO dto) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		User user = this.registeredUserService.findByEmail(username);

		if (user != null) {
			boolean ok = this.restaurantService.gradeRestaurant(dto, user);

			if (ok)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/best-graded")
	public ResponseEntity<ArrayList<RestaurantDTO>> bestGradedReport() {
		ArrayList<RestaurantDTO> dtos = this.restaurantService.bestGradedReport();
		
		if(!dtos.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/declining-restaurant")
	public ResponseEntity<ArrayList<RestaurantDTO>> decliningRestaurantsReport() {
		ArrayList<RestaurantDTO> dtos = this.restaurantService.decliningRestaurantsReport();
		
		if(!dtos.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/rising-restaurant")
	public ResponseEntity<ArrayList<RestaurantDTO>> risingRestaurantsReport() {
		ArrayList<RestaurantDTO> dtos = this.restaurantService.risingRestaurantsReport();
		
		if(!dtos.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/most-visited/{season}")
	public ResponseEntity<ArrayList<RestaurantDTO>> mostVisitedRestaurantsReport(@PathVariable("season") String season) {
		ArrayList<RestaurantDTO> dtos = this.restaurantService.mostVisitedRestaurantsReport(season);
		
		if(!dtos.isEmpty())
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
