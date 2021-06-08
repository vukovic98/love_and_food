package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private User petar;
	private User marko;

	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		
		Date dateOfBirth = format.parse("1998/04/05");
		petar = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		petar.setId(1L);
		petar.setAgeGroup(Age.AGE_20_25);
		petar.setPersonalityTraits("ENTJ");
		
		dateOfBirth = format.parse("1998/04/05");
		marko = new User("marko@gmail.com", "pass", "Marko", "Markovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		marko.setId(2L);
		marko.setAgeGroup(Age.AGE_20_25);
		marko.setPersonalityTraits("INTJ");
		
		kieSession.insert(petar);
		kieSession.insert(marko);
		
		Match match1 = new Match();
		match1.setId(1L);
        match1.setInitiator(petar);
        match1.setSoulmate(marko);
        match1.setMatchDate(LocalDate.parse("2018-05-17"));
        match1.setRating(5);
        Match match2 = new Match();
		match2.setId(2L);
        match2.setInitiator(marko);
        match2.setSoulmate(petar);
        match2.setMatchDate(LocalDate.parse("2018-05-18"));
        match2.setRating(5);
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
	
	@Test
	public void GetAllUsersWithRatingAbove() {
        Match match = new Match();
		match.setId(3L);
        match.setInitiator(petar);
        match.setSoulmate(marko);
        match.setMatchDate(LocalDate.parse("2018-05-18"));
        match.setRating(1);
		kieSession.insert(match);
        
		QueryResults results = kieSession.getQueryResults( "getAllUsersWithRatingAbove", 3);
		
		Set<User> users = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
			users = (Set<User>) row.get( "$users" );
		}
		
		assertEquals(1, users.size());
		assertEquals(petar.getId(), users.iterator().next().getId());
	}
	
	@Test
	public void GetAllUsersWithRatingBelow() {
        Match match = new Match();
		match.setId(3L);
        match.setInitiator(petar);
        match.setSoulmate(marko);
        match.setMatchDate(LocalDate.parse("2018-05-18"));
        match.setRating(1);
		kieSession.insert(match);
        
		QueryResults results = kieSession.getQueryResults( "getAllUsersWithRatingBelow", 4);
		
		Set<User> users = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
			users = (Set<User>) row.get( "$users" );
		}
		
		assertEquals(1, users.size());
		assertEquals(marko.getId(), users.iterator().next().getId());
	}
	
	@Test
	public void GetAllUsersWithRatingInRange() {
        Match match = new Match();
		match.setId(3L);
        match.setInitiator(petar);
        match.setSoulmate(marko);
        match.setMatchDate(LocalDate.parse("2018-05-18"));
        match.setRating(1);
		kieSession.insert(match);
        
		QueryResults results = kieSession.getQueryResults( "getAllUsersWithRatingInRange", 2, 4);
		
		Set<User> users = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
			users = (Set<User>) row.get( "$users" );
		}
		
		assertEquals(1, users.size());
		assertEquals(marko.getId(), users.iterator().next().getId());
	}
	
	@Test
	public void GetAllUsersWhoMatchedAtLeast() {
		QueryResults results = kieSession.getQueryResults( "getAllUsersWhoMatchedAtLeast", 2);
		
		Set<User> users = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
			users = (Set<User>) row.get( "$users" );
		}
		
		assertEquals(2, users.size());
	}
}
