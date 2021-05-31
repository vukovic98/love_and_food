package com.ftn.uns.ac.rs.love_and_food.tests;

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
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import com.ftn.uns.ac.rs.love_and_food.model.Match;
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

public class LoveQueryTest {

	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		
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
		user2.setPersonalityTraits("INTJ");
		
		kieSession.insert(user1);
		kieSession.insert(user2);
		
		Match match1 = new Match();
		match1.setId(1L);
        match1.setInitiator(user1);
        match1.setSoulmate(user2);
        match1.setMatchDate(LocalDate.parse("2018-05-17"));
        Match match2 = new Match();
		match2.setId(2L);
        match2.setInitiator(user1);
        match2.setSoulmate(user2);
        match2.setMatchDate(LocalDate.parse("2018-05-18"));
		kieSession.insert(match1);
		kieSession.insert(match2);
	}
	
	@Test
	public void GetAllUsersQuery() {
		QueryResults results = kieSession.getQueryResults( "getAllUsers" );
		
		List<User> users = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    User user = ( User ) row.get( "$user" );
		    users.add(user);
		}
		
		assertEquals(2, users.size());
	}
	
	@Test
	public void GetAllMatchesQuery() {
		QueryResults results = kieSession.getQueryResults( "getAllMatches" );
		
		List<Match> matches = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
			Match match = ( Match ) row.get( "$match" );
		    matches.add(match);
		}
		
		assertEquals(2, matches.size());
	}
	
	@Test
	public void GetAllUsersByPersonality() {
		QueryResults results = kieSession.getQueryResults( "getAllUsersByPersonality", "e");
		
		List<User> users = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    User user = ( User ) row.get( "$users" );
		    users.add(user);
		}
		
		assertEquals(1, users.size());
	}
}
