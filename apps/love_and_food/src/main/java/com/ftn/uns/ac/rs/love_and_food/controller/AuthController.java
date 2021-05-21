package com.ftn.uns.ac.rs.love_and_food.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserLoginDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserTokenStateDTO;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.security.TokenUtils;
import com.ftn.uns.ac.rs.love_and_food.service.AuthService;
import com.ftn.uns.ac.rs.love_and_food.service.CustomUserDetailsService;
import com.ftn.uns.ac.rs.love_and_food.service.RegisteredUserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService; 

	@Autowired
	private AuthenticationManager authenticationManager;


	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/log-in")
	public ResponseEntity<UserTokenStateDTO> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
			HttpServletResponse response) {

		try {
			boolean verified = true;

			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(), authenticationRequest.getPassword()));

			// Ubaci korisnika u trenutni security kontekst
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Kreiraj token za tog korisnika
			User user = (User) authentication.getPrincipal();

			String email = user.getEmail();
			String jwt = tokenUtils.generateToken(user.getEmail()); // prijavljujemo se na sistem sa email adresom
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, email, verified));
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
//	@PostMapping( value = "/register")
//	public ResponseEntity<RegisteredUser> register(@RequestBody UserDTO userDTO) {
//
//		RegisteredUser user = new RegisteredUser(userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(), userDTO.getSurname(),
//				userDTO.getDateOfBirth(), userDTO.getIncome(), userDTO.getGender(), userDTO.getSexualOrientation(),
//				userDTO.getEducation(), userDTO.getReligion(), userDTO.getChildren(), userDTO.getDesiredRelationship(),
//				userDTO.getLocation(), userDTO.isAlchocol(), userDTO.isSmoking());
//		
//		RegisteredUser createdUser = this.authService.register(user);
//		
//		return new ResponseEntity<>(createdUser, HttpStatus.OK);
//	}

}
