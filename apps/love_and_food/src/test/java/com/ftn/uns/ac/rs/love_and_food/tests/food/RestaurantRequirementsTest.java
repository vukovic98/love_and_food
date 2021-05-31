package com.ftn.uns.ac.rs.love_and_food.tests.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.RestaurantRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Age;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Income;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class RestaurantRequirementsTest {

	private KieSession kieSession;
	
	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		kieSession.getAgenda().getAgendaGroup("restaurant-requirements").setFocus();
		
		Date dateOfBirth = this.formatter.parse("1998/04/05");
		User user1 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user1.setId(1L);
		user1.setAgeGroup(Age.AGE_20_25);
		user1.setPersonalityTraits("ENTJ");
		
		dateOfBirth = this.formatter.parse("1998/04/05");
		User user2 = new User("milica@gmail.com", "pass", "Milica", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.FEMALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		Match match1 = new Match();
		match1.setId(1L);
        match1.setInitiator(user1);
        match1.setSoulmate(user2);
        match1.setMatchDate(LocalDate.parse("2021-05-31"));
		
		this.kieSession.insert(match1);
	}
	
	@Test
	public void setInputData() throws ParseException {		
		Date dateTime = this.formatter.parse("2021/06/02");
		RestaurantEntryDTO dto = new RestaurantEntryDTO(Music.POP, Cuisine.ITALIAN, true, true, dateTime);
		this.kieSession.insert(dto);
		
		RestaurantRequirements req = new RestaurantRequirements();
		this.kieSession.insert(req);	
		
		this.kieSession.fireAllRules();

		assertEquals(req.getMusic(), dto.getMusic());
		assertEquals(req.getCuisine(), dto.getCuisine());
		assertEquals(req.isGarden(), dto.isHasGarden());
		assertEquals(req.isOnFoot(), dto.isOnFoot());
	}
}
