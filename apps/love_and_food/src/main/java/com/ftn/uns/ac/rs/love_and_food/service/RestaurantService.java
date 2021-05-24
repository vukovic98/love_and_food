package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;
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
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
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
	
	@SuppressWarnings("serial")
	public Map<Location, ArrayList<Location>> distanceMap = new HashMap<Location, ArrayList<Location>>() {{
		put(Location.BANATIC, new ArrayList<Location>(){
            {
                add(Location.DETELINARA);
                add(Location.ROTKVARIJA);
                add(Location.SAJMISTE);
                add(Location.BANATIC);
            }
        });
		put(Location.DETELINARA, new ArrayList<Location>(){
            {
                add(Location.BANATIC);
                add(Location.SAJMISTE);
                add(Location.NOVO_NASELJE);
                add(Location.DETELINARA);
            }
        });
		put(Location.GRBAVICA, new ArrayList<Location>(){
            {
                add(Location.STARI_GRAD);
                add(Location.LIMAN);
                add(Location.SAJMISTE);
                add(Location.GRBAVICA);
            }
        });
		put(Location.LIMAN, new ArrayList<Location>(){
            {
                add(Location.GRBAVICA);
                add(Location.STARI_GRAD);
                add(Location.TELEP);
                add(Location.LIMAN);
            }
        });
		put(Location.NOVO_NASELJE, new ArrayList<Location>(){
            {
                add(Location.DETELINARA);
                add(Location.TELEP);
                add(Location.NOVO_NASELJE);
            }
        });
		put(Location.PODBARA, new ArrayList<Location>(){
            {
                add(Location.STARI_GRAD);
                add(Location.ROTKVARIJA);
                add(Location.PODBARA);
            }
        });
		put(Location.ROTKVARIJA, new ArrayList<Location>(){
            {
                add(Location.STARI_GRAD);
                add(Location.SAJMISTE);
                add(Location.ROTKVARIJA);
            }
        });
		put(Location.SAJMISTE, new ArrayList<Location>(){
            {
                add(Location.DETELINARA);
                add(Location.GRBAVICA);
                add(Location.SAJMISTE);
                add(Location.BANATIC);
                add(Location.SAJMISTE);
            }
        });
		put(Location.STARI_GRAD, new ArrayList<Location>(){
            {
                add(Location.PODBARA);
                add(Location.ROTKVARIJA);
                add(Location.LIMAN);
                add(Location.GRBAVICA);
                add(Location.STARI_GRAD);
            }
        });
		put(Location.TELEP, new ArrayList<Location>(){
            {
                add(Location.LIMAN);
                add(Location.NOVO_NASELJE);
                add(Location.TELEP);
            }
        });
	}};

	public Restaurant addRestaurant(Restaurant r) {
		return this.restaurantRepository.save(r);
	}

	public Restaurant findRestaurant(RestaurantEntryDTO dto, Match match) {
		
		this.sessionService.releaseRulesSession();
		KieSession session = sessionService.getRulesSession();
		
		session.setGlobal("loggedInUserId", match.getInitiator().getId());


		session.insert(dto);
		session.insert(match);
		
		RestaurantRequirements req = new RestaurantRequirements();
		
		req.setUserId(match.getInitiator().getId());

		session.insert(req);

		session.getAgenda().getAgendaGroup("restaurant-requirements").setFocus();
		session.fireAllRules();

		List<Restaurant> allRestaurants = this.restaurantRepository.findAll();
		
		for (Restaurant r : allRestaurants) {
			session.insert(r);
		}

		session.insert(match.getInitiator());
		session.insert(this.distanceMap);
				
		session.getAgenda().getAgendaGroup("perfect-restaurant").setFocus();
		session.fireAllRules();
				
		session.getAgenda().getAgendaGroup("calculating-score").setFocus();
		session.fireAllRules();

		Restaurant perfect = (Restaurant) session.getGlobal("selectedRestaurant");
		System.out.println(perfect);
		return perfect;
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

				if (saved != null)
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
