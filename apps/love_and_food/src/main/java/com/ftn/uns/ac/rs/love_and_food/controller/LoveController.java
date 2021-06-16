package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.CoupleDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.SoulmateConfigDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserMVPDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.SoulmateConfig;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.service.LoveService;
import com.ftn.uns.ac.rs.love_and_food.service.SoulmateConfigService;

@RestController
@RequestMapping(path = "/love")
public class LoveController {
	
	@Autowired
	private LoveService loveService;
	
	@Autowired
	private SoulmateConfigService soulmateConfigService;
	
	private final UserMapper userMapper = new UserMapper();
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping( value = "/find-match")
	public ResponseEntity<UserDTO> findMatch() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		
		User soulmate = loveService.findMatch(email);
		
		return new ResponseEntity<UserDTO>( userMapper.toDTO(soulmate), HttpStatus.OK);

	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping( value = "/rate-date/{matchId}/{rating}")
	public ResponseEntity<Void> rateDate(@PathVariable("matchId") int matchId,
			@PathVariable("rating") int rating) {
		try {
			loveService.rateDate(matchId, rating);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping( value = "/get-configuration")
	public ResponseEntity<SoulmateConfigDTO> getSoulmateConfiguration() {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		SoulmateConfig soulmateConfig = this.soulmateConfigService.findConfiguration(email);
		SoulmateConfigDTO soulmateConfigDTO = new SoulmateConfigDTO(soulmateConfig.getSmokingPoints(), soulmateConfig.getAlcoholPoints(), soulmateConfig.getEducationPoints(),
				soulmateConfig.getTraitPoints(), soulmateConfig.getIncomePoints(), soulmateConfig.getReligionPoints());
		return new ResponseEntity<SoulmateConfigDTO>(soulmateConfigDTO, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@PutMapping( value = "/update-configuration")
	public ResponseEntity<Void> updateSoulmateConfiguration(@RequestBody SoulmateConfigDTO soulmateConfigDTO) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getName();
		this.soulmateConfigService.updateConfiguration(email, soulmateConfigDTO);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/*REPORTS*/
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "/report/liars")
	public ResponseEntity<List<UserDTO>> reportLiars() {
		List<User> liars = loveService.reportLiars();
		return new ResponseEntity<List<UserDTO>>(userMapper.toDTOList(liars), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "/report/mvps")
	public ResponseEntity<List<UserMVPDTO>> reportMVPs() {
		List<UserRatingDTO> userRatings = loveService.reportMVPs();
		return new ResponseEntity<List<UserMVPDTO>>(userMapper.toUserMVPDTOlist(userRatings), 
				HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping( value = "/report/couples/{matchedTimes}")
	public ResponseEntity<Set<CoupleDTO>> reportCouples(@PathVariable("matchedTimes") int matchedTimes) {
		Set<CoupleDTO> couples = loveService.reportCouples(matchedTimes);
		return new ResponseEntity<Set<CoupleDTO>>(couples, HttpStatus.OK);
	}

}
