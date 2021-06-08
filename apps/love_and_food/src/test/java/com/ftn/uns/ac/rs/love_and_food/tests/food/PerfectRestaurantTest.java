package com.ftn.uns.ac.rs.love_and_food.tests.food;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.RestaurantRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Age;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Income;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class PerfectRestaurantTest {
	private KieSession kieSession;

	private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		
		@SuppressWarnings("serial")
		HashMap<Location, ArrayList<Location>> distanceMap = new HashMap<Location, ArrayList<Location>>() {
			{
				put(Location.BANATIC, new ArrayList<Location>() {
					{
						add(Location.DETELINARA);
						add(Location.ROTKVARIJA);
						add(Location.SAJMISTE);
						add(Location.BANATIC);
					}
				});
				put(Location.DETELINARA, new ArrayList<Location>() {
					{
						add(Location.BANATIC);
						add(Location.SAJMISTE);
						add(Location.NOVO_NASELJE);
						add(Location.DETELINARA);
					}
				});
				put(Location.GRBAVICA, new ArrayList<Location>() {
					{
						add(Location.STARI_GRAD);
						add(Location.LIMAN);
						add(Location.SAJMISTE);
						add(Location.GRBAVICA);
					}
				});
				put(Location.LIMAN, new ArrayList<Location>() {
					{
						add(Location.GRBAVICA);
						add(Location.STARI_GRAD);
						add(Location.TELEP);
						add(Location.LIMAN);
					}
				});
				put(Location.NOVO_NASELJE, new ArrayList<Location>() {
					{
						add(Location.DETELINARA);
						add(Location.TELEP);
						add(Location.NOVO_NASELJE);
					}
				});
				put(Location.PODBARA, new ArrayList<Location>() {
					{
						add(Location.STARI_GRAD);
						add(Location.ROTKVARIJA);
						add(Location.PODBARA);
					}
				});
				put(Location.ROTKVARIJA, new ArrayList<Location>() {
					{
						add(Location.STARI_GRAD);
						add(Location.SAJMISTE);
						add(Location.ROTKVARIJA);
					}
				});
				put(Location.SAJMISTE, new ArrayList<Location>() {
					{
						add(Location.DETELINARA);
						add(Location.GRBAVICA);
						add(Location.SAJMISTE);
						add(Location.BANATIC);
						add(Location.SAJMISTE);
					}
				});
				put(Location.STARI_GRAD, new ArrayList<Location>() {
					{
						add(Location.PODBARA);
						add(Location.ROTKVARIJA);
						add(Location.LIMAN);
						add(Location.GRBAVICA);
						add(Location.STARI_GRAD);
					}
				});
				put(Location.TELEP, new ArrayList<Location>() {
					{
						add(Location.LIMAN);
						add(Location.NOVO_NASELJE);
						add(Location.TELEP);
					}
				});
			}
		};

		Match match = this.getRomanticMatch();
		
		this.kieSession.setGlobal("loggedInUserId", match.getInitiator().getId());
		
		Date dateOfBirth = this.formatter.parse("1998/04/05");
		User user1 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT,
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, false);
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
		
		this.kieSession.insert(user1);
		this.kieSession.insert(user2);
		
		this.kieSession.insert(match);
		
		Set<Cuisine> cuisine = new HashSet<>();
		cuisine.add(Cuisine.FAST_FOOD);
		cuisine.add(Cuisine.ITALIAN);
		
		Restaurant r1 = new Restaurant(1L, "Velvet", Location.GRBAVICA, LocalTime.parse("07:00"),
				LocalTime.parse("22:00"), null, Ambient.ROMANTIC, Music.CLASSIC,
				cuisine, PriceRange.AFFORDABLE, true, true, true, false, true, true, true);
		
		ArrayList<Grade> grades = new ArrayList<>();
		grades.add(new Grade(1L, 5, 5, 5, 5, 5, 5, r1, match.getInitiator(), new Date()));
		
		r1.setGrades(grades);
		
		Restaurant r2 = new Restaurant(2L, "Petrus", Location.STARI_GRAD, LocalTime.parse("07:00"),
				LocalTime.parse("22:00"), null, Ambient.LUXORY, Music.CLASSIC,
				cuisine, PriceRange.EXPENSIVE, true, true, true, false, true, true, true);
		
		grades = new ArrayList<>();
		grades.add(new Grade(2L, 2, 2, 2, 2, 2, 2, r2, match.getInitiator(), new Date()));
		
		r2.setGrades(grades);
		
		this.kieSession.insert(r1);
		this.kieSession.insert(r2);

		@SuppressWarnings("deprecation")
		Date dateTime = new Date(2021, 6, 2, 12, 0, 0);
		RestaurantEntryDTO dto = new RestaurantEntryDTO(Music.POP, Cuisine.ITALIAN, false, true, dateTime);
		this.kieSession.insert(dto);

		RestaurantRequirements req = new RestaurantRequirements();
		req.setUserId(match.getInitiator().getId());
		
		this.kieSession.insert(req);
		
		this.kieSession.getAgenda().getAgendaGroup("restaurant-requirements").setFocus();
		this.kieSession.fireAllRules();
		
		this.kieSession.insert(distanceMap);
	}
	
	@Test
	public void findRestaurant() {
		this.kieSession.getAgenda().getAgendaGroup("perfect-restaurant").setFocus();
		this.kieSession.fireAllRules();
		
		this.kieSession.getAgenda().getAgendaGroup("calculating-score").setFocus();
		this.kieSession.fireAllRules();
		
		Restaurant perfect = (Restaurant) this.kieSession.getGlobal("selectedRestaurant");
		
		assertNotNull(perfect);
		assertEquals(perfect.getRestaurant_id(), 1L);
		assertTrue(perfect.getName().equalsIgnoreCase("Velvet"));
	}
	
	public Match getRomanticMatch() throws ParseException {
		Date dateOfBirth = this.formatter.parse("1998/04/05");
		User user1 = new User("petar@gmail.com", "pass", "Petar", "Petrovic", dateOfBirth, Income.INCOME_500_1000,
				Gender.MALE, SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Religion.NOT_IMPORTANT,
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, false);
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

		return match1;
	}
}
