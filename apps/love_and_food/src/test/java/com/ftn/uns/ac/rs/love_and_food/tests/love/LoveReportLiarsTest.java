package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.User;

public class LoveReportLiarsTest {

	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private User user1;
	private User user2;
	private Set<User> liars;
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		liars = new HashSet<>();
		kieSession.setGlobal("liars", liars);
		kieSession.getAgenda().getAgendaGroup("liars-report").setFocus();
		
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
	public void GetLiarsReportPositive() throws ParseException {
        Match match1 = new Match(1L, user1, user2, LocalDate.parse("2020-12-10"), 1);
        Match match2 = new Match(2L, user2, user1, LocalDate.parse("2020-12-20"), 5);
        kieSession.insert(match1);
        kieSession.insert(match2);
        
		int firedRules = kieSession.fireAllRules();
		liars = (Set<User>) kieSession.getGlobal("liars");
		
		assertEquals(1, firedRules);
		assertEquals(1, liars.size());
		for (Iterator<User> it = liars.iterator(); it.hasNext(); ) {
	        User u = it.next();
	        assertEquals(user2.getId(), u.getId());
	    }
	}
	
	@Test
	public void GetLiarsReportNegative() throws ParseException {
		Match match1 = new Match(1L, user1, user2, LocalDate.parse("2020-12-10"), 3);
        Match match2 = new Match(2L, user2, user1, LocalDate.parse("2020-12-20"), 3);
        kieSession.insert(match1);
        kieSession.insert(match2);
        
		int firedRules = kieSession.fireAllRules();
		liars = (Set<User>) kieSession.getGlobal("liars");
		
		assertEquals(1, firedRules);
		assertEquals(0, liars.size());
	}
}
