package com.ftn.uns.ac.rs.love_and_food.service;

import java.time.LocalDate;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.exceptions.NonExistingIdException;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.MatchRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;

@Service
public class LoveService {

	@Autowired
	private KieStatefulSessionService kieService;
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	public User findMatch(String email) {
		// dobavljanje ulogovanog korisnika
		User user = registeredUserRepository.findByEmail(email);
		// kreiranje zahteva vezanog za tog korisnika
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		kieService.releaseRulesSession();
		KieSession session = kieService.getRulesSession();
		session.setGlobal("loggedInUserId", user.getId());
		
		session.insert(user);
		session.insert(partnerReq);
		
		session.getAgenda().getAgendaGroup("partner-requirements").setFocus();
		session.fireAllRules();
		
		List<User> allUsersExceptLoggedIn = registeredUserRepository.findAllByIdNot(user.getId());
		for (User user1 : allUsersExceptLoggedIn) {
			session.insert(user1);
		}
		List<Match> allMatches = matchRepository.findAll();
		for (Match match : allMatches) {
			session.insert(match);
		}
		session.getAgenda().getAgendaGroup("partner-age").setFocus();
		session.fireAllRules();
		session.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		session.fireAllRules();
		
		User soulmate = (User) session.getGlobal("soulmate");

		Match match = new Match();
		match.setInitiator(user);
		match.setSoulmate(soulmate);
		match.setMatchDate(LocalDate.now());
		
		matchRepository.save(match);
		
		return soulmate;
	}
	
	public Match rateDate(Long matchId, int rating) throws NonExistingIdException {
		Match match = matchRepository.findById(matchId).orElse(null);
		if ( match != null) {
			match.setRating(rating);
			return matchRepository.save(match);
		}
		
		throw new NonExistingIdException("Match");
	}
	
}
