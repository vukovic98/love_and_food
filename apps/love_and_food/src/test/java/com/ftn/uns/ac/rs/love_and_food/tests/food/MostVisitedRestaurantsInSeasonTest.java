package com.ftn.uns.ac.rs.love_and_food.tests.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
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

import com.ftn.uns.ac.rs.love_and_food.model.DatePlace;
import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

public class MostVisitedRestaurantsInSeasonTest {
	private KieSession kieSession;

	@Before
	public void setUp() throws ParseException {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");

		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void getMostVisitedFallRestaurants() {
		
		Set<Cuisine> cuisine = new HashSet<>();
		cuisine.add(Cuisine.FAST_FOOD);
		cuisine.add(Cuisine.ITALIAN);

		User u = new User();
		u.setId(1L);
		
		User u1 = new User();
		u1.setId(2L);

		Restaurant r1 = new Restaurant(1L, "Velvet", Location.GRBAVICA, LocalTime.parse("07:00"),
				LocalTime.parse("22:00"), null, Ambient.ROMANTIC, Music.CLASSIC, cuisine, PriceRange.AFFORDABLE, true,
				true, true, false, true, true, true);

		ArrayList<Grade> grades = new ArrayList<>();
		grades.add(new Grade(1L, 5, 5, 5, 5, 5, 5, r1, u, new Date()));

		r1.setGrades(grades);

		Restaurant r2 = new Restaurant(2L, "Petrus", Location.STARI_GRAD, LocalTime.parse("07:00"),
				LocalTime.parse("22:00"), null, Ambient.LUXORY, Music.CLASSIC, cuisine, PriceRange.EXPENSIVE, true,
				true, true, false, true, true, true);

		grades = new ArrayList<>();
		grades.add(new Grade(2L, 2, 2, 2, 2, 2, 2, r2, u, new Date()));

		r2.setGrades(grades);
		
		List<Restaurant> mostVisitedRestaurants = new ArrayList<>();
		
		DatePlace d1 = new DatePlace(1L, r1, u, u1, new Date(2020, 10, 10, 12, 0));
		DatePlace d2 = new DatePlace(2L, r1, u, u1, new Date(2020, 10, 10, 12, 0));
		DatePlace d3 = new DatePlace(3L, r1, u, u1, new Date(2020, 10, 10, 12, 0));
		DatePlace d4 = new DatePlace(4L, r1, u, u1, new Date(2020, 10, 10, 12, 0));
		DatePlace d5 = new DatePlace(5L, r1, u, u1, new Date(2020, 10, 10, 12, 0));
		DatePlace d6 = new DatePlace(6L, r1, u, u1, new Date(2020, 10, 10, 12, 0));
		
		List<DatePlace> dates = new ArrayList<>();
		dates.add(d1);
		dates.add(d2);
		dates.add(d3);
		dates.add(d4);
		dates.add(d5);
		dates.add(d6);

		this.kieSession.setGlobal("mostVisitedRestaurants", mostVisitedRestaurants);
		this.kieSession.setGlobal("dates", dates);
		
		LocalDate start_fall = LocalDate.of(2020, 9, 23);
		LocalDate stop_fall = LocalDate.of(2020, 12, 21);

		LocalDate start_winter = LocalDate.of(2020, 12, 21);
		LocalDate stop_winter = LocalDate.of(2020, 3, 21);

		LocalDate start_summer = LocalDate.of(2020, 6, 21);
		LocalDate stop_summer = LocalDate.of(2020, 9, 23);

		LocalDate start_spring = LocalDate.of(2020, 3, 21);
		LocalDate stop_spring = LocalDate.of(2020, 6, 21);

		this.kieSession.setGlobal("start_fall", start_fall);
		this.kieSession.setGlobal("stop_fall", stop_fall);

		this.kieSession.setGlobal("start_winter", start_winter);
		this.kieSession.setGlobal("stop_winter", stop_winter);

		this.kieSession.setGlobal("start_summer", start_summer);
		this.kieSession.setGlobal("stop_summer", stop_summer);

		this.kieSession.setGlobal("start_spring", start_spring);
		this.kieSession.setGlobal("stop_spring", stop_spring);

		this.kieSession.insert(r1);
		this.kieSession.insert(r2);
		
		kieSession.getAgenda().getAgendaGroup("most-visited-fall").setFocus();
		this.kieSession.fireAllRules();
		
		assertEquals(0, mostVisitedRestaurants.size());
//		assertEquals(1L, mostVisitedRestaurants.get(0).getRestaurant_id());
	}
}
