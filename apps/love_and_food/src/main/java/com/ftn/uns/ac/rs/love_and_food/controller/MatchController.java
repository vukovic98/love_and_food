package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

@RestController
@RequestMapping(path = "/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@Autowired
	private RegisteredUserService userService;

	private final MatchMapper matchMapper = new MatchMapper();
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping(value = "/{matchId}")
	public ResponseEntity<MatchDTO> findMatch(@PathVariable("matchId") Long matchId) {

		Match match = matchService.findById(matchId);

		return new ResponseEntity<MatchDTO>(matchMapper.toDTO(match), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping()
	public ResponseEntity<List<MatchDTO>> findAllForUser() {
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<Match> matches = matchService.findAllForUser(user.getEmail());

		return new ResponseEntity<List<MatchDTO>>(matchMapper.toDTOList(matches), HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/by-page/{pageNum}")
	public ResponseEntity<Page<MatchDTO>> findAll(@PathVariable("pageNum") int pageNum) {

		Page<MatchDTO> matches = matchService.findAll(pageNum);

		return new ResponseEntity<Page<MatchDTO>>(matches, HttpStatus.OK);

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
