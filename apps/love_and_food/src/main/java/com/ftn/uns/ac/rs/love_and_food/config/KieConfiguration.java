package com.ftn.uns.ac.rs.love_and_food.config;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.MatchRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.RestaurantRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Configuration
public class KieConfiguration {
	
	@Autowired
	private KieSessionHolder kieSessionHolder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	@Bean(name = "eventsSession")
	public KieSession eventsSession() {
		KieSession kieSession = this.kieContainer().newKieSession("eventsSession");
		System.out.println("Creating new events kie session");
		return kieSession;
	}
	
	@Bean
	@SessionScope
	public KieSession kieSession() {
		KieSession kieSession = this.kieContainer().newKieSession("loveAndFoodSession");
		System.out.println("Creating new kie session");
		List<User> allUsers = userRepository.findAll();
		for (User user : allUsers) {
			kieSession.insert(user);
		}
		List<Match> allMatches = matchRepository.findAll();
		for (Match match : allMatches) {
			kieSession.insert(match);
		}
		List<Restaurant> allRestaurants = restaurantRepository.findAll();
		for (Restaurant r : allRestaurants) {
			kieSession.insert(r);
		}
		this.kieSessionHolder.add(kieSession);
		return kieSession;
	}
}
