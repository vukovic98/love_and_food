package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.GradeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.RestaurantRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.GradeRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KieStatefulSessionService sessionService;
	
	@Autowired
	private GradeRepository gradeRepository;

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

	public boolean gradeRestaurant(GradeDTO dto, User user) {
		try {
		Restaurant r = this.restaurantRepository.findById(dto.getRestaurantID()).orElse(null);

		if (r != null) {
			Grade g = new Grade();
			
			g.setUser(user);
			g.setService_grade(dto.getService());
			g.setRestaurant(r);
			g.setOverall_grade(dto.getOverall());
			g.setLocation_grade(dto.getLocation());
			g.setHospitability_grade(dto.getHospitability());
			g.setAtmosphere_grade(dto.getAtmosphere());
			g.setAmbient_grade(dto.getAmbient());
			
			Grade saved = this.gradeRepository.save(g);
			
			if(saved != null)
				return true;
			else
				return false;
		} else
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
