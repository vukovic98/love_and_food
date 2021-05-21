package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.RestaurantRequirements;
import com.ftn.uns.ac.rs.love_and_food.repository.RestaurantRepository;

@Service
public class RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KieStatefulSessionService sessionService;

	public Restaurant addRestaurant(Restaurant r) {
		return this.restaurantRepository.save(r);
	}

	public RestaurantRequirements findRestaurant(RestaurantEntryDTO dto, Match match) {
		KieSession session = sessionService.getRulesSession();
		
		session.insert(dto);
		session.insert(match);
		
		RestaurantRequirements req = new RestaurantRequirements();
		
		session.insert(req);
		
		session.getAgenda().getAgendaGroup("restaurant-requirements").setFocus();
		session.fireAllRules();
		sessionService.releaseRulesSession();
				
		return req;
	}

}
