package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.exceptions.ExistingFieldValueException;
import com.ftn.uns.ac.rs.love_and_food.model.PersonalityAnswer;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.SoulmateConfig;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.SoulmateConfigRepository;
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
	private SoulmateConfigRepository soulmateConfigRepository;

	@Autowired
    private KieSession kieSession;

	public User register(User user, List<PersonalityAnswer> testAnswers) throws Exception {
		RegisteredUser existingUser = (RegisteredUser) registeredUserRepository.findByEmail(user.getEmail());
		if (existingUser == null) {
			//aktiviranje naloga
			user.setEnabled(true);
			//enkripcija lozinke
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			user.setPassword(encoder.encode(user.getPassword()));
			// postavljanje authorities
			user.setAuthorities(new ArrayList<>(authorityService.findByName("ROLE_USER")));
			// cuvanje u bazi zbog id-a
			user = userRepository.save(user);
			
			// ubacivanje u sesiju i odredjivanje licnosti korisnika
			// kreiramo sesiju za svaku registraciju jer nam nije neophodno da cuvamo sve
			// odgovore za svakog korisnika
			this.kieSession.insert(user);
			for (PersonalityAnswer personalityAnswer : testAnswers) {
				personalityAnswer.setUserId(user.getId());
				this.kieSession.insert(personalityAnswer);
			}
			this.kieSession.getAgenda().getAgendaGroup("personality-test").setFocus();
			this.kieSession.fireAllRules();

			user =  userRepository.save(user);
			
			SoulmateConfig soulmateConfig = new SoulmateConfig(user.getId());
			this.soulmateConfigRepository.save(soulmateConfig);
			
			return user;
		}

		throw new ExistingFieldValueException("User", "email");
	}

}
