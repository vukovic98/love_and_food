package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.User;

public class LoveReportMVPsTest {

	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private User user1;
	private User user2;
	List<UserRatingDTO> mvps;
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		mvps = new ArrayList<>();
		kieSession.setGlobal("mvps", mvps);
		kieSession.getAgenda().getAgendaGroup("MVPs").setFocus();
		
		user1 = new User();
        user1.setId(1L);
        Date dateOfBirth = format.parse("2003/01/30");
        user1.setDateOfBirth(dateOfBirth);
        kieSession.insert(user1);
        user2 = new User();
        user2.setId(2L);
        user2.setDateOfBirth(dateOfBirth);
        kieSession.insert(user2);
	}
	
	@Test
	public void GetTwoMvps() throws ParseException {
        Match match1 = new Match(1L, user2, user1, LocalDate.parse("2020-12-10"), 3);
        Match match2 = new Match(2L, user2, user1, LocalDate.parse("2020-12-10"), 4);
        Match match3 = new Match(3L, user1, user2, LocalDate.parse("2020-12-10"), 5);
        Match match4 = new Match(4L, user1, user2, LocalDate.parse("2020-12-10"), 5);
        kieSession.insert(match1);
        kieSession.insert(match2);
        kieSession.insert(match3);
        kieSession.insert(match4);
        
		int firedRules = kieSession.fireAllRules();
		mvps = (List<UserRatingDTO>) kieSession.getGlobal("mvps");
		
		assertEquals(2, firedRules);
		assertEquals(2, mvps.size());
		assertEquals(user2.getId(), mvps.get(0).getUser().getId());
		assertEquals(user1.getId(), mvps.get(1).getUser().getId());
		assertEquals(5.0, mvps.get(0).getAverageRating(), 0.1);
		assertEquals(3.5, mvps.get(1).getAverageRating(), 0.1);
	}
	
	@Test
	public void GetOneMvp() throws ParseException {
        Match match1 = new Match(1L, user1, user2, LocalDate.parse("2020-12-10"), 2);
        Match match2 = new Match(2L, user1, user2, LocalDate.parse("2020-12-10"), 4);
        Match match3 = new Match(3L, user2, user1, LocalDate.parse("2020-12-10"), 5);
        Match match4 = new Match(4L, user2, user1, LocalDate.parse("2020-12-10"), 5);
        kieSession.insert(match1);
        kieSession.insert(match2);
        kieSession.insert(match3);
        kieSession.insert(match4);
        
		int firedRules = kieSession.fireAllRules();
		mvps = (List<UserRatingDTO>) kieSession.getGlobal("mvps");
		
		assertEquals(1, firedRules);
		assertEquals(1, mvps.size());
		assertEquals(user1.getId(), mvps.get(0).getUser().getId());
		assertEquals(5.0, mvps.get(0).getAverageRating(), 0.1);
	}
	
	@Test
	public void GetNoMvp() throws ParseException {
        Match match1 = new Match(1L, user1, user2, LocalDate.parse("2020-12-10"), 2);
        Match match2 = new Match(2L, user1, user2, LocalDate.parse("2020-12-10"), 4);
        Match match3 = new Match(3L, user2, user1, LocalDate.parse("2020-12-10"), 1);
        Match match4 = new Match(4L, user2, user1, LocalDate.parse("2020-12-10"), 3);
        kieSession.insert(match1);
        kieSession.insert(match2);
        kieSession.insert(match3);
        kieSession.insert(match4);
        
		int firedRules = kieSession.fireAllRules();
		mvps = (List<UserRatingDTO>) kieSession.getGlobal("mvps");
		
		assertEquals(0, firedRules);
		assertEquals(0, mvps.size());
	}
}
