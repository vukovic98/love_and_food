package com.ftn.uns.ac.rs.love_and_food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.RestaurantRequirements;
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

	@PostMapping()
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant r) {
		Restaurant saved = this.restaurantService.addRestaurant(r);

		if (saved != null)
			return new ResponseEntity<>(HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PostMapping(path = "/find-restaurant")
	public ResponseEntity<RestaurantRequirements> findRestaurant(@RequestBody RestaurantEntryDTO dto) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		RegisteredUser user = this.registeredUserService.findByEmail(username);

		if (user != null) {

			Match match = this.matchService.findByInitiator(user.getId());

			if (match != null) {

				RestaurantRequirements r = this.restaurantService.findRestaurant(dto, match);

				if (r != null)
					return new ResponseEntity<>(r, HttpStatus.OK);
				else
					return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
