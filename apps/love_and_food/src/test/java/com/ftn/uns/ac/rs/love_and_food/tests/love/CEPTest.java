package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.event.FailedLoginEvent;
import com.ftn.uns.ac.rs.love_and_food.event.MateRatingEvent;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.model.User;

public class CEPTest {
	
	private KieSession kieSession;
	
	@Before
	public void setUp() {
		KieServices ks = KieServices.Factory.get();
    	KieContainer kc = ks.newKieClasspathContainer();
        kieSession = kc.newKieSession("eventsSession");
	}
	
	@Test
	public void failedLoginTest() {
		kieSession.getAgenda().getAgendaGroup("failed-login-attempt").setFocus();
		
		RegisteredUser user = new RegisteredUser();
		user.setId(1L);
		user.setEmail("email@gmail.com");
		
		int firedRules;
		
		for (int i = 0; i < 6; i++) {
			kieSession.insert(new FailedLoginEvent(new Date(), user));
		}
		
		firedRules = kieSession.fireAllRules();
		assertEquals(1, firedRules);
	}
	
	@Test
	public void badUserRating() {
		User petar = new User();
		petar.setId(1L);
		User marko = new User();
		marko.setId(2L);
		User klara = new User();
		klara.setId(3L);
		User badUser = new User();
		badUser.setId(4L);
		
		Match match1 = new Match(1L, petar, badUser,LocalDate.now(), 3);
		Match match2 = new Match(2L, marko, badUser,LocalDate.now(), 1);
		Match match3 = new Match(3L, klara, badUser,LocalDate.now(), 2);
		
		kieSession.insert(match1);
		kieSession.insert(match2);
		kieSession.insert(match3);
		
		MateRatingEvent mateRatingEvent = new MateRatingEvent(new Date(), match3);
		this.kieSession.insert(mateRatingEvent);
		
		this.kieSession.getAgenda().getAgendaGroup("user-rating-event").setFocus();
		int firedRules = this.kieSession.fireAllRules();
		
		assertEquals(1, firedRules);
		assertEquals(true, mateRatingEvent.isHappened());
	}
	
	@Test
	public void goodUserRating() {
		User petar = new User();
		petar.setId(1L);
		User marko = new User();
		marko.setId(2L);
		User klara = new User();
		klara.setId(3L);
		User marina = new User();
		marina.setId(4L);
		User aleks = new User();
		aleks.setId(5L);
		User goodUser = new User();
		goodUser.setId(6L);
		
		Match match1 = new Match(1L, petar, goodUser,LocalDate.now(), 5);
		Match match2 = new Match(2L, marko, goodUser,LocalDate.now(), 4);
		Match match3 = new Match(3L, klara, goodUser,LocalDate.now(), 5);
		Match match4 = new Match(4L, marina, goodUser,LocalDate.now(), 4);
		Match match5 = new Match(5L, aleks, goodUser,LocalDate.now(), 5);
		
		kieSession.insert(match1);
		kieSession.insert(match2);
		kieSession.insert(match3);
		kieSession.insert(match4);
		kieSession.insert(match5);
		
		MateRatingEvent mateRatingEvent = new MateRatingEvent(new Date(), match5);
		this.kieSession.insert(mateRatingEvent);
		
		this.kieSession.getAgenda().getAgendaGroup("user-rating-event").setFocus();
		int firedRules = this.kieSession.fireAllRules();
		
		assertEquals(1, firedRules);
		assertEquals(true, mateRatingEvent.isHappened());
	}

}
