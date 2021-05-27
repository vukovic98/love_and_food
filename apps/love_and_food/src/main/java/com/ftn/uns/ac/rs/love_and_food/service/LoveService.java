package com.ftn.uns.ac.rs.love_and_food.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.event.MateRatingEvent;
import com.ftn.uns.ac.rs.love_and_food.exceptions.NonExistingIdException;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.MatchRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class LoveService {

	@Autowired
	private KieStatefulSessionService kieService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	public User findMatch(String email) {
		// dobavljanje ulogovanog korisnika
		User user = userRepository.findByEmail(email);
		// kreiranje zahteva vezanog za tog korisnika
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		kieService.releaseRulesSession();
		KieSession session = kieService.getRulesSession();
		session.setGlobal("loggedInUserId", user.getId());
		
		session.insert(user);
		session.insert(partnerReq);
		
		session.getAgenda().getAgendaGroup("partner-requirements").setFocus();
		session.fireAllRules();
		
		List<User> allUsersExceptLoggedIn = userRepository.findAllByIdNot(user.getId());
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
		
		if( soulmate != null ) {
			Match match = new Match();
			match.setInitiator(user);
			match.setSoulmate(soulmate);
			match.setMatchDate(LocalDate.now());
			
			matchRepository.save(match);
			
			return soulmate;
		}
		
		return null;
	}
	
	public Match rateDate(Long matchId, int rating) throws NonExistingIdException {
		Match match = matchRepository.findById(matchId).orElse(null);
		if ( match != null) {
			match.setRating(rating);
			MateRatingEvent event = new MateRatingEvent(new Date(), match);
			KieSession session = kieService.getEventsSession();
			session.insert(event);
			match = matchRepository.save(match);
			List<Match> allMatches = matchRepository.findAll();
			for (Match match1 : allMatches) {
				session.insert(match1);
			}
			session.getAgenda().getAgendaGroup("user-rating-event").setFocus();
			session.fireAllRules();
			return match;
		}
		
		throw new NonExistingIdException("Match");
	}
	
	public List<User> reportLiars() {
		kieService.releaseRulesSession();
		KieSession session = kieService.getRulesSession();
		
		List<User> liars = new ArrayList<User>();
		
		session.setGlobal("liars", liars);
		
		List<User> allUsers = userRepository.findAll();
		for (User user1 : allUsers) {
			session.insert(user1);
		}
		List<Match> allMatches = matchRepository.findAll();
		for (Match match : allMatches) {
			session.insert(match);
		}
		
		session.getAgenda().getAgendaGroup("liars-report").setFocus();
		session.fireAllRules();
		
		return liars;
	}
	
	public List<UserRatingDTO> reportMVPs() {
		kieService.releaseRulesSession();
		KieSession session = kieService.getRulesSession();
		
		List<UserRatingDTO> mvps = new ArrayList<UserRatingDTO>();
		
		session.setGlobal("mvps", mvps);
		
		List<User> allUsers = userRepository.findAll();
		for (User user1 : allUsers) {
			session.insert(user1);
		}
		List<Match> allMatches = matchRepository.findAll();
		for (Match match : allMatches) {
			session.insert(match);
		}
		
		session.getAgenda().getAgendaGroup("MVPs").setFocus();
		session.fireAllRules();
		
		return mvps;
	}
	
}
