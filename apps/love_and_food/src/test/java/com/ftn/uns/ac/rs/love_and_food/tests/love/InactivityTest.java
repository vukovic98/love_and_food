package com.ftn.uns.ac.rs.love_and_food.tests.love;

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

public class InactivityTest {

	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private User user1;
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("eventsSession");
		kieSession.getAgenda().getAgendaGroup("user-activity-event").setFocus();
		
		user1 = new User();
        user1.setId(1L);
        Date dateOfBirth = format.parse("2003/01/30");
        user1.setDateOfBirth(dateOfBirth);
        kieSession.insert(user1);
	}
	
	@Test
	public void GetInactiveUser() throws ParseException, InterruptedException {
		
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(1, firedRules);
	}
	
}
