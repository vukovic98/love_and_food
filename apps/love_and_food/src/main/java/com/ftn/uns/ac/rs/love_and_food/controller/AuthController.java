package com.ftn.uns.ac.rs.love_and_food.controller;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserLoginDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserTokenStateDTO;
import com.ftn.uns.ac.rs.love_and_food.event.FailedLoginEvent;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Alarm;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.AlarmType;
import com.ftn.uns.ac.rs.love_and_food.repository.AlarmRepository;
import com.ftn.uns.ac.rs.love_and_food.security.TokenUtils;
import com.ftn.uns.ac.rs.love_and_food.service.AuthService;
import com.ftn.uns.ac.rs.love_and_food.service.CustomUserDetailsService;
import com.ftn.uns.ac.rs.love_and_food.service.KieStatefulSessionService;
import com.ftn.uns.ac.rs.love_and_food.service.RegisteredUserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private RegisteredUserService registeredUserService;
	
	@Autowired
    private KieStatefulSessionService kieSessionService;
	
	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private CustomUserDetailsService userDetailsService; 

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private AlarmRepository alarmRepository;
	
	private final UserMapper userMapper = new UserMapper();

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
			RegisteredUser registeredUser = (RegisteredUser) authentication.getPrincipal();

			String email = registeredUser.getEmail();
			String jwt = tokenUtils.generateToken(registeredUser.getEmail());
			int expiresIn = tokenUtils.getExpiredIn();

			// Vrati token kao odgovor na uspesnu autentifikaciju
			return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn, email, verified));
		} catch (Exception e) {
			RegisteredUser user = registeredUserService.findByEmail(authenticationRequest.getEmail());
			if (user != null) {
				FailedLoginEvent event = new FailedLoginEvent(new Date(), user);
				KieSession kieSession = kieSessionService.getEventsSession();
				kieSession.getAgenda().getAgendaGroup("failed-login-attempt").setFocus();
				kieSession.insert(event);
				int fired = kieSession.fireAllRules();
				if (fired == 1) {
					user.setEnabled(false);
					registeredUserService.save(user);
					
					//create alarm
					this.alarmRepository.save(new Alarm(AlarmType.FAILED_LOGIN, "More than 5 unsuccessful login attempts in 2 minute from " + user.getEmail(), new Date()));
					
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}
			}
			
			if(!user.isEnabled())
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 

	}
	
	@PostMapping( value = "/register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {

		User user = userMapper.toEntity(userDTO);
		
		try {
			User createdUser = this.authService.register(user, userDTO.getTestAnswers());
			return new ResponseEntity<>(userMapper.toDTO(createdUser), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@GetMapping( value = "/enable-account/{email}")
	public ResponseEntity<String> enableAccount(@PathVariable("email") String email) {

		RegisteredUser u = this.registeredUserService.findByEmail(email);
		
		try {
			u.setEnabled(true);
			this.registeredUserService.save(u);
			
			String content = "You have successfully enabled your account. \nGo to the login page now! \n\n http://localhost:4200/auth/login";
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.TEXT_HTML);
			
			return new ResponseEntity<>(content, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
}
