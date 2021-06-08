package com.ftn.uns.ac.rs.love_and_food.tests.food;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
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

import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Ambient;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Cuisine;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Music;
import com.ftn.uns.ac.rs.love_and_food.model.enums.PriceRange;

public class RisingRestaurantsTest {
	private KieSession kieSession;

	@Before
	public void setUp() throws ParseException {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");

		Set<Cuisine> cuisine = new HashSet<>();
		cuisine.add(Cuisine.FAST_FOOD);
		cuisine.add(Cuisine.ITALIAN);

		User u = new User();
		u.setId(1L);

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

		this.kieSession.insert(r1);
		this.kieSession.insert(r2);

		kieSession.getAgenda().getAgendaGroup("rising-rating").setFocus();
	}
	
	@Test
	public void getDecliningRestaurants() {
		List<Restaurant> risingRestaurants = new ArrayList<>();

		this.kieSession.setGlobal("risingRestaurants", risingRestaurants);
		
		this.kieSession.fireAllRules();
		
		assertEquals(1, risingRestaurants.size());
		assertEquals(1L, risingRestaurants.get(0).getRestaurant_id());
	}
}
