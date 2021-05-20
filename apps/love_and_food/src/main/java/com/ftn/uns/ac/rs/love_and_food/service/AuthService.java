package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.PersonalityAnswer;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private AuthorityService authorityService;	
	
	@Autowired
	private KieStatefulSessionService sessionService;
	
	public RegisteredUser register(RegisteredUser user, List<PersonalityAnswer> testAnswers) {
		User existingUser = (User) userRepository.findByEmail(user.getEmail());
		if(existingUser == null) {
			//ovde treba da se hashira lozinka i da se dodaju authorities i onda da se sacuva u bazu
			//enkripcija lozinke
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			//postavljanje authorities
			user.setAuthorities(new ArrayList<>(authorityService.findByName("ROLE_USER")));
			
			// ubacivanje u sesiju i odredjivanje licnosti korisnika
			// kreiramo sesiju za svaku registraciju jer nam nije neophodno da cuvamo sve odgovore za svakog korisnika
			KieSession session = sessionService.getRulesSession();
			session.insert(user);
			for (PersonalityAnswer personalityAnswer : testAnswers) {
				session.insert(personalityAnswer);
			}
			session.getAgenda().getAgendaGroup("personality-test").setFocus();
			session.fireAllRules();
			sessionService.releaseRulesSession();
			
			return registeredUserRepository.save(user);
		}
		
		return user;
	}

}
