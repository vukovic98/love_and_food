package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;

@Service
public class LoveService {

	@Autowired
	private KieStatefulSessionService kieService;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	public PartnerRequirements findMatch(String email) {
		// dobavljanje ulogovanog korisnika
		RegisteredUser user = registeredUserRepository.findByEmail(email);
		// kreiranje zahteva vezanog za tog korisnika
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		KieSession session = kieService.getRulesSession();
		
		session.insert(user);
		session.insert(partnerReq);
		
		session.getAgenda().getAgendaGroup("partner-requirements").setFocus();
		session.fireAllRules();
		
		List<RegisteredUser> allUsersExceptLoggedIn = registeredUserRepository.findAllByIdNot(user.getId());
		for (RegisteredUser registeredUser : allUsersExceptLoggedIn) {
			session.insert(registeredUser);
		}
		session.setGlobal("loggedInUserId", user.getId());
		session.getAgenda().getAgendaGroup("soulmate").setFocus();
		session.fireAllRules();
		return partnerReq;
	}
	
}
