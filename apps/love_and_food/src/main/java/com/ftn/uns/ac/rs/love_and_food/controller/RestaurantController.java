package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantConfigDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantFilterDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.GradeMapper;
import com.ftn.uns.ac.rs.love_and_food.mapper.RestaurantMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.MatchService;
import com.ftn.uns.ac.rs.love_and_food.service.RestaurantService;
import com.ftn.uns.ac.rs.love_and_food.service.UserService;
import com.ftn.uns.ac.rs.love_and_food.util.PageImplMapper;
import com.ftn.uns.ac.rs.love_and_food.util.PageImplementation;

@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private UserService userService;

	@Autowired
	private MatchService matchService;

	@Autowired
	private RestaurantMapper restaurantMapper;

	@Autowired
	private GradeMapper gradeMapper;

	@GetMapping("/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> findAll(@PathVariable("pageNum") int pageNum) {
		ArrayList<RestaurantDTO> found = (ArrayList<RestaurantDTO>) this.restaurantService.findAll();

		Pageable pageRequest = PageRequest.of(pageNum, 6);

		Page<RestaurantDTO> page = this.restaurantService.findPaginated(found, pageRequest);

		PageImplementation<RestaurantDTO> pageImpl = new PageImplementation<>();
		PageImplMapper<RestaurantDTO> mapper = new PageImplMapper<>();

		pageImpl = mapper.toPageImpl(page);

		if (!found.isEmpty())
			return new ResponseEntity<>(pageImpl, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RestaurantDTO> findById(@PathVariable("id") long id) {
		Restaurant r = this.restaurantService.findById(id);

		if (r != null)
			return new ResponseEntity<>(this.restaurantMapper.toDTO(r), HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping(path = "/configure-restaurant-points")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ArrayList<RestaurantDTO>> filterByHours(@RequestBody RestaurantConfigDTO dto) {

		boolean ok = this.restaurantService.createRestaurantConfiguration(dto);

		if (ok) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/filter/by-page/{pageNum}")
	public ResponseEntity<PageImplementation<RestaurantDTO>> filterRestaurants(@PathVariable("pageNum") int pageNum,
			@RequestBody RestaurantFilterDTO dto) {
		ArrayList<RestaurantDTO> found = (ArrayList<RestaurantDTO>) this.restaurantService.filterRestaurants(dto);

		Pageable pageRequest = PageRequest.of(pageNum, 6);

		Page<RestaurantDTO> page = this.restaurantService.findPaginated(found, pageRequest);

		PageImplementation<RestaurantDTO> pageImpl = new PageImplementation<>();
		PageImplMapper<RestaurantDTO> mapper = new PageImplMapper<>();

		pageImpl = mapper.toPageImpl(page);

		if (!found.isEmpty())
			return new ResponseEntity<>(pageImpl, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping()
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant r) {
		try {
			Restaurant saved = this.restaurantService.addRestaurant(r);

			if (saved != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(path = "/find-restaurant")
	public ResponseEntity<RestaurantDTO> findRestaurant(@RequestBody RestaurantEntryDTO dto) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		User user = this.userService.findByEmail(username);

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

		User user = this.userService.findByEmail(username);

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
		ArrayList<RestaurantDTO> dtos = (ArrayList<RestaurantDTO>) this.restaurantService.bestGradedReport();

		if (!dtos.isEmpty()) {
			if (dtos.size() > 10)
				dtos = (ArrayList<RestaurantDTO>) dtos.subList(0, 10);

			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/declining-restaurant")
	public ResponseEntity<ArrayList<RestaurantDTO>> decliningRestaurantsReport() {
		ArrayList<RestaurantDTO> dtos = (ArrayList<RestaurantDTO>) this.restaurantService.decliningRestaurantsReport();

		if (!dtos.isEmpty()) {
			if (dtos.size() > 10)
				dtos = (ArrayList<RestaurantDTO>) dtos.subList(0, 10);
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/rising-restaurant")
	public ResponseEntity<ArrayList<RestaurantDTO>> risingRestaurantsReport() {
		ArrayList<RestaurantDTO> dtos = (ArrayList<RestaurantDTO>) this.restaurantService.risingRestaurantsReport();

		if (!dtos.isEmpty()) {
			if (dtos.size() > 10)
				dtos = (ArrayList<RestaurantDTO>) dtos.subList(0, 10);
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(path = "/report/most-visited/{season}")
	public ResponseEntity<ArrayList<RestaurantDTO>> mostVisitedRestaurantsReport(
			@PathVariable("season") String season) {
		ArrayList<RestaurantDTO> dtos = (ArrayList<RestaurantDTO>) this.restaurantService
				.mostVisitedRestaurantsReport(season);

		if (!dtos.isEmpty()) {
			if (dtos.size() > 10)
				dtos = (ArrayList<RestaurantDTO>) dtos.subList(0, 10);
			return new ResponseEntity<>(dtos, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(path = "/has-graded/{restaurant_id}")
	public ResponseEntity<GradeDTO> hasGradedRestaurant(@PathVariable("restaurant_id") long restaurantID) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();

		User user = this.userService.findByEmail(username);

		if (user != null) {

			Grade grade = this.restaurantService.hasGraded(restaurantID, user.getId());

			if (grade != null)
				return new ResponseEntity<>(this.gradeMapper.toDTO(grade), HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
