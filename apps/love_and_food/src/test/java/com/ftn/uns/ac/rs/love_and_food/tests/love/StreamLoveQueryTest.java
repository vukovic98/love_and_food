package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.ftn.uns.ac.rs.love_and_food.event.FailedLoginEvent;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Age;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Income;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class StreamLoveQueryTest {
	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("eventsSession");
		
		Date dateOfBirth = format.parse("1998/04/05");
		User user1 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user1.setId(1L);
		user1.setAgeGroup(Age.AGE_20_25);
		user1.setPersonalityTraits("ENTJ");
		
		dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		FailedLoginEvent fle1 = new FailedLoginEvent(new Date(), user1);
		FailedLoginEvent fle2 = new FailedLoginEvent(new Date(), user2);
		
		kieSession.insert(fle1);
		kieSession.insert(fle2);

	}
	
	@Test
	public void GetAllFailedLoginEvents() {
		QueryResults results = kieSession.getQueryResults( "getAllFailedLoginEvents" );
		
		List<FailedLoginEvent> result = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
			FailedLoginEvent event = ( FailedLoginEvent ) row.get( "$failedLoginEvent" );
			result.add(event);
		}

		assertEquals(2, result.size());
	}
}
