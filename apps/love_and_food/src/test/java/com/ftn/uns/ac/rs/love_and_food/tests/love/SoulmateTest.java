package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
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

public class SoulmateTest {

	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private User klara;
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");

		Date dateOfBirth = format.parse("1998/10/10");
		klara = new User("klara.markovic@gmail.com", "pass", "Klara", "Markovic", dateOfBirth, Income.INCOME_0_500,
				Gender.FEMALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, true);
		klara.setId(1L);
		klara.setPersonalityTraits("INFP");
		PartnerRequirements partnerReq = new PartnerRequirements(klara.getId());
		partnerReq.setChildren(Children.DOESNT_WANT_CHILDREN);
		partnerReq.setDesiredRelationship(DesiredRelationship.SHORT_TERM);
		partnerReq.setIdealAge(Age.AGE_20_25);
		partnerReq.setPrefferedGender(Gender.MALE);
		List<Age> suitableAge = Arrays.asList(Age.AGE_18_20, Age.AGE_25_30, Age.AGE_20_25);
		partnerReq.setSuitableAge(suitableAge);
        kieSession.insert(klara);
        kieSession.insert(partnerReq);
        kieSession.setGlobal("loggedInUserId", klara.getId());
	}
	
	@Test
	public void GetSoulmatePositive() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		kieSession.insert(user2);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(11, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(user2.getId(), soulmate.getId());
	}
	
	@Test
	public void GetSoulmateWithPastExpiriencePositive() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User petar = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		petar.setId(2L);
		petar.setAgeGroup(Age.AGE_20_25);
		petar.setPersonalityTraits("ENTJ");
		
		kieSession.insert(petar);
		
		User milovan = new User("milovan@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.PHD, Religion.IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		milovan.setId(3L);
		milovan.setAgeGroup(Age.AGE_20_25);
		milovan.setPersonalityTraits("ENTJ");
		
		kieSession.insert(milovan);
		
		Match match1 = new Match();
		match1.setId(1L);
        match1.setInitiator(klara);
        match1.setSoulmate(petar);
        match1.setMatchDate(LocalDate.parse("2021-05-17"));
        match1.setRating(5);
        
        kieSession.insert(match1);
        
		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		
		//assertEquals(11, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(petar.getId(), soulmate.getId());
	}
	
	@Test
	public void GetSoulmatePositiveMoreOptions() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		dateOfBirth = format.parse("1998/04/05");
		User user3 = new User("marko@gmail.com", "pass", "Marko", "Markovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, false);
		user3.setId(3L);
		user3.setAgeGroup(Age.AGE_20_25);
		user3.setPersonalityTraits("ENTJ");

		kieSession.insert(user2);
		kieSession.insert(user3);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(21, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(user3.getId(), soulmate.getId());
	}
	
	@Test
	public void GetSoulmatePositiveMoreOptionsMultipleTimes() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		dateOfBirth = format.parse("1998/04/05");
		User user3 = new User("marko@gmail.com", "pass", "Marko", "Markovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, false);
		user3.setId(3L);
		user3.setAgeGroup(Age.AGE_20_25);
		user3.setPersonalityTraits("ENTJ");

		kieSession.insert(user2);
		kieSession.insert(user3);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		
		assertEquals(21, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(user3.getId(), soulmate.getId());
	}
	
	@Test
	public void GetSoulmateIncompatibleAge() throws ParseException {
		Date dateOfBirth = format.parse("1965/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_50_65);
		user2.setPersonalityTraits("ENTJ");
		
		kieSession.insert(user2);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(null, soulmate);
	}
	
	@Test
	public void GetSoulmateIncompatibleGender() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.FEMALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		kieSession.insert(user2);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(null, soulmate);
	}
	
	@Test
	public void GetSoulmateIncompatibleChildren() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.HAS_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		kieSession.insert(user2);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(null, soulmate);
	}
	
	@Test
	public void GetSoulmateIncompatibleDesiredRelationship() throws ParseException {
		Date dateOfBirth = format.parse("1998/04/05");
		User user2 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.MARRIAGE, Location.GRBAVICA, false, false);
		user2.setId(2L);
		user2.setAgeGroup(Age.AGE_20_25);
		user2.setPersonalityTraits("ENTJ");
		
		kieSession.insert(user2);

		kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		int firedRules = kieSession.fireAllRules();
		assertEquals(0, firedRules);
		
		User soulmate = (User) kieSession.getGlobal("soulmate");
		assertEquals(null, soulmate);
	}
	
}
