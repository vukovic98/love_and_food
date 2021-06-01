package com.ftn.uns.ac.rs.love_and_food.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.event.FindMatchEvent;
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
    private KieSession kieSession;
	
	@Autowired
	@Qualifier(value = "eventsSession")
	private KieSession eventsSession;
	
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
		
		this.kieSession.setGlobal("loggedInUserId", user.getId());
		
		this.kieSession.insert(partnerReq);
		
		this.kieSession.getAgenda().getAgendaGroup("partner-requirements").setFocus();
		this.kieSession.fireAllRules();
		
		this.kieSession.getAgenda().getAgendaGroup("partner-age").setFocus();
		this.kieSession.fireAllRules();
		this.kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		this.kieSession.fireAllRules();
		
		FindMatchEvent findMatchEvent = new FindMatchEvent(new Date(), user);
		this.eventsSession.insert(findMatchEvent);
		
		User soulmate = (User) this.kieSession.getGlobal("soulmate");
		
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
		
		List<User> liars = new ArrayList<User>();
		
		this.kieSession.setGlobal("liars", liars);
		
		this.kieSession.getAgenda().getAgendaGroup("liars-report").setFocus();
		this.kieSession.fireAllRules();
		
		return liars;
	}
	
	public List<UserRatingDTO> reportMVPs() {
		
		List<UserRatingDTO> mvps = new ArrayList<UserRatingDTO>();
		
		this.kieSession.setGlobal("mvps", mvps);
		
		this.kieSession.getAgenda().getAgendaGroup("MVPs").setFocus();
		this.kieSession.fireAllRules();
		
		return mvps;
	}
	
}
