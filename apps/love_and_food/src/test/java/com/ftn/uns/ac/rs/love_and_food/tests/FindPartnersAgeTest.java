package com.ftn.uns.ac.rs.love_and_food.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Age;

public class FindPartnersAgeTest {
	
	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	
	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		kieSession.getAgenda().getAgendaGroup("partner-age").setFocus();
	}
	
	@Test
	public void SetLowestAge() throws ParseException {
		Date dateOfBirth = format.parse("2003/01/30");
		
		User user = new User();
		user.setDateOfBirth(dateOfBirth);
		
		kieSession.insert(user);
		
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(1, firedRules);
		assertEquals(Age.AGE_18_20, user.getAgeGroup());
	}
	
	@Test
	public void SetHighestAge() throws ParseException {
		Date dateOfBirth = format.parse("1951/01/30");
		
		User user = new User();
		user.setDateOfBirth(dateOfBirth);
		
		kieSession.insert(user);
		
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(1, firedRules);
		assertEquals(Age.AGE_65_PLUS, user.getAgeGroup());
	}
	
	@Test
	public void SetAge() throws ParseException {
		Date dateOfBirth = format.parse("1994/01/30");
		
		User user = new User();
		user.setDateOfBirth(dateOfBirth);
		
		kieSession.insert(user);
		
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(1, firedRules);
		assertEquals(Age.AGE_25_30, user.getAgeGroup());
	}
	

}
