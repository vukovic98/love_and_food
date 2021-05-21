package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;

@Service
public class RestaurantService {

	@Autowired
	private KieContainer kieContainer;

	

	public Restaurant findRestaurant(RestaurantEntryDTO dto) {
		
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(dto);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return new Restaurant();
	}

}
