package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;

@Service
public class RegisteredUserService {

	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private RegisteredUserRepository userRepository;
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public PartnerRequirements findMatch(RegisteredUser registeredUser) {
		KieSession kieSession = kieContainer.newKieSession();
		//radice baza
		registeredUser.setId(1L);
		kieSession.insert(registeredUser);
		PartnerRequirements partnerRequirements = new PartnerRequirements(registeredUser.getId());
		kieSession.insert(partnerRequirements);
		kieSession.fireAllRules();
		kieSession.dispose();
		return partnerRequirements;
	}
}
