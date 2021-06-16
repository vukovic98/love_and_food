package com.ftn.uns.ac.rs.love_and_food.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.CoupleDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.event.MateRatingEvent;
import com.ftn.uns.ac.rs.love_and_food.exceptions.NonExistingIdException;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Alarm;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.AlarmType;
import com.ftn.uns.ac.rs.love_and_food.repository.AlarmRepository;
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
	private UserRepository userRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private AlarmRepository alarmRepository;
	
	private UserMapper userMapper = new UserMapper();
	
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
	
	public Match rateDate(int matchId, int rating) throws NonExistingIdException {
		Match match = matchRepository.findById((long) matchId).orElse(null);
		if ( match != null) {
			match.setRating(rating);
			MateRatingEvent event = new MateRatingEvent(new Date(), match);
			this.eventsSession.insert(event);
			match = matchRepository.save(match);
			this.eventsSession.insert(match);
			
			this.eventsSession.getAgenda().getAgendaGroup("user-rating-event").setFocus();
			this.eventsSession.fireAllRules();
			
			if(event.isHappened() && event.getMessage().contains("WARNING")) {
				this.alarmRepository.save(new Alarm(AlarmType.BAD_RATING_USER_ALARM, event.getMessage(), new Date()));
			} else if(event.isHappened() && event.getMessage().contains("INFO") ) {
				this.alarmRepository.save(new Alarm(AlarmType.GOOD_RATING_USER_ALARM, event.getMessage(), new Date()));
			}
			return match;
		}
		
		throw new NonExistingIdException("Match");
	}
	
	public List<User> reportLiars() {
		
		Set<User> liars = new HashSet<User>();
		
		this.kieSession.setGlobal("liars", liars);
		
		this.kieSession.getAgenda().getAgendaGroup("liars-report").setFocus();
		this.kieSession.fireAllRules();
		
		List<User> listLiars = new ArrayList<>(liars);
		
		return listLiars;
	}
	
	public List<UserRatingDTO> reportMVPs() {
		
		List<UserRatingDTO> mvps = new ArrayList<UserRatingDTO>();
		
		this.kieSession.setGlobal("mvps", mvps);
		
		this.kieSession.getAgenda().getAgendaGroup("MVPs").setFocus();
		this.kieSession.fireAllRules();
		
		return mvps;
	}
	
	public Set<CoupleDTO> reportCouples(int matchedTimes) {
		QueryResults results = kieSession.getQueryResults( "getAllUsersWhoMatchedAtLeast", matchedTimes);
		
		List<User> users1 = new ArrayList<>();
		List<User> users2 = new ArrayList<>();
		
		Set<CoupleDTO> couples = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
			users1 = (List<User>) row.get( "$users1" );
			users2 = (List<User>) row.get( "$users2" );
		}
		for (int i = 0; i < users1.size(); i++) {
			User user1 = users1.get(i);
			User user2 = users2.get(i);
			CoupleDTO couple = new CoupleDTO(userMapper.toDTO(user1), userMapper.toDTO(user2));
			couples.add(couple);
		}
		
		return couples;
	}
	
}
