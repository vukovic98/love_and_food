package com.ftn.uns.ac.rs.love_and_food.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.GradeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantConfigDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantEntryDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantFilterDTO;
import com.ftn.uns.ac.rs.love_and_food.event.RestaurantRatingEvent;
import com.ftn.uns.ac.rs.love_and_food.mapper.RestaurantMapper;
import com.ftn.uns.ac.rs.love_and_food.model.DatePlace;
import com.ftn.uns.ac.rs.love_and_food.model.Grade;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;
import com.ftn.uns.ac.rs.love_and_food.model.RestaurantRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.repository.DatePlaceRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.GradeRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.RestaurantConfigRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KieSession kieSession;

	@Autowired
	private KieStatefulSessionService kieService;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private DatePlaceRepository dateRepository;

	@Autowired
	private RestaurantMapper restaurantMapper;
	
	@Autowired
	private RestaurantConfigRepository restaurantConfigRepository;

	public Restaurant findById(long id) {
		return this.restaurantRepository.findById(id).orElse(null);
	}
	
	public Page<RestaurantDTO> findPaginated(ArrayList<RestaurantDTO> restaurants, Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<RestaurantDTO> list;

		if (restaurants.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, restaurants.size());
			list = restaurants.subList(startItem, toIndex);
		}

		Page<RestaurantDTO> restaurantPage = new PageImpl<RestaurantDTO>(list, PageRequest.of(currentPage, pageSize),
				restaurants.size());

		return restaurantPage;
	}

	@SuppressWarnings("serial")
	public HashMap<Location, ArrayList<Location>> distanceMap = new HashMap<Location, ArrayList<Location>>() {
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

	public List<RestaurantDTO> findAll() {
		QueryResults results = this.kieSession.getQueryResults("getAllRestaurants");
		ArrayList<Restaurant> restaurants = new ArrayList<>();

		for (QueryResultsRow row : results) {
			Restaurant restaurant = (Restaurant) row.get("$restaurant");
			restaurants.add(restaurant);
		}

		return this.restaurantMapper.toDTOList(restaurants);
	}

	public Restaurant addRestaurant(Restaurant r) {
		ArrayList<Restaurant> found = this.restaurantRepository.findByName(r.getName());

		if (found.isEmpty()) {
			return this.restaurantRepository.save(r);
		} else {
			return null;
		}
	}

	public Restaurant findRestaurant(RestaurantEntryDTO dto, Match match) {

		this.kieSession.setGlobal("loggedInUserId", match.getInitiator().getId());

		this.kieSession.insert(dto);

		RestaurantRequirements req = new RestaurantRequirements();

		req.setUserId(match.getInitiator().getId());

		this.kieSession.insert(req);

		this.kieSession.getAgenda().getAgendaGroup("restaurant-requirements").setFocus();
		this.kieSession.fireAllRules();

		this.kieSession.insert(this.distanceMap);

		this.kieSession.getAgenda().getAgendaGroup("perfect-restaurant").setFocus();
		this.kieSession.fireAllRules();

		this.kieSession.getAgenda().getAgendaGroup("calculating-score").setFocus();
		this.kieSession.fireAllRules();

		Restaurant perfect = (Restaurant) this.kieSession.getGlobal("selectedRestaurant");

		// TODO COMMENT FOR TESTING

		DatePlace d = new DatePlace();

		d.setDate(dto.getDateTime());
		d.setRestaurant(perfect);
		d.setInitiator(match.getInitiator());
		d.setSoulmate(match.getSoulmate());

		this.dateRepository.save(d);

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
				g.setDate(new java.util.Date());

				Grade saved = this.gradeRepository.save(g);

				r.getGrades().add(saved);

				this.restaurantRepository.save(r);

				RestaurantRatingEvent event = new RestaurantRatingEvent(new Date(), saved, "");

				KieSession session = this.kieService.getEventsSession();
				session.insert(event);

				List<Grade> grades = this.gradeRepository.findAll();

				for (Grade gr : grades)
					session.insert(gr);

				session.getAgenda().getAgendaGroup("restaurant-rating-event").setFocus();
				session.fireAllRules();

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

	public ArrayList<RestaurantDTO> bestGradedReport() {

		List<Restaurant> bestGraded = new ArrayList<>();

		this.kieSession.setGlobal("bestGraded", bestGraded);

		this.kieSession.getAgenda().getAgendaGroup("best-graded-report").setFocus();
		this.kieSession.fireAllRules();

		return (ArrayList<RestaurantDTO>) this.restaurantMapper.toDTOList(bestGraded);
	}

	public ArrayList<RestaurantDTO> decliningRestaurantsReport() {

		List<Restaurant> decliningRestaurants = new ArrayList<>();

		this.kieSession.setGlobal("decliningRestaurants", decliningRestaurants);

		this.kieSession.getAgenda().getAgendaGroup("declining-rating").setFocus();
		this.kieSession.fireAllRules();

		return (ArrayList<RestaurantDTO>) this.restaurantMapper.toDTOList(decliningRestaurants);
	}

	public ArrayList<RestaurantDTO> risingRestaurantsReport() {

		List<Restaurant> risingRestaurants = new ArrayList<>();

		this.kieSession.setGlobal("risingRestaurants", risingRestaurants);

		this.kieSession.getAgenda().getAgendaGroup("rising-rating").setFocus();
		this.kieSession.fireAllRules();

		return (ArrayList<RestaurantDTO>) this.restaurantMapper.toDTOList(risingRestaurants);
	}
	
	public Grade hasGraded(long restaurantID, long userID) {
		return this.gradeRepository.hasGraded(restaurantID, userID);
	}

	public ArrayList<RestaurantDTO> mostVisitedRestaurantsReport(String season) {

		List<Restaurant> mostVisitedRestaurants = new ArrayList<>();
		List<DatePlace> dates = this.dateRepository.findAll();

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

		if (season.equalsIgnoreCase("FALL")) {
			this.kieSession.getAgenda().getAgendaGroup("most-visited-fall").setFocus();
			this.kieSession.fireAllRules();
		}

		if (season.equalsIgnoreCase("WINTER")) {
			this.kieSession.getAgenda().getAgendaGroup("most-visited-winter").setFocus();
			this.kieSession.fireAllRules();
		}

		if (season.equalsIgnoreCase("SUMMER")) {
			this.kieSession.getAgenda().getAgendaGroup("most-visited-summer").setFocus();
			this.kieSession.fireAllRules();
		}

		if (season.equalsIgnoreCase("SPRING")) {
			this.kieSession.getAgenda().getAgendaGroup("most-visited-spring").setFocus();
			this.kieSession.fireAllRules();
		}
		return (ArrayList<RestaurantDTO>) this.restaurantMapper.toDTOList(mostVisitedRestaurants);
	}

	public ArrayList<RestaurantDTO> filterRestaurants(RestaurantFilterDTO dto) {
		QueryResults results = null;

		if (dto.getName() != null) {
//			NAME
			if (dto.getLocation() != null) {
				if (dto.getPrice() != null) {
					if (dto.getCuisine() != null) {
//						ALL
						results = this.kieSession.getQueryResults("getRestaurantsByAll", dto.getName(),
								dto.getLocation(), dto.getPrice(), dto.getCuisine());
					} else {
//						CUISINE == NULL
//						NAME, LOCATION, PRICE
						results = this.kieSession.getQueryResults("getRestaurantsByNameAndLocationAndPrice",
								dto.getName(), dto.getLocation(), dto.getPrice());
					}
				} else {
//					PRICE == NULL
					if (dto.getCuisine() != null) {
//					NAME, LOCATION, CUISINE
						results = this.kieSession.getQueryResults("getRestaurantsByNameAndLocationAndCuisine",
								dto.getName(), dto.getLocation(), dto.getCuisine());
					} else {
//						CUISINE == NULL
//						NAME, LOCATION
						results = this.kieSession.getQueryResults("getRestaurantsByNameAndLocation", dto.getName(),
								dto.getLocation());
					}
				}
			} else {
//				LOCATION == NULL
				if (dto.getPrice() != null) {
					if (dto.getCuisine() != null) {
//						NAME, PRICE, CUISINE
						results = this.kieSession.getQueryResults("getRestaurantsByNameAndPriceAndCuisine",
								dto.getName(), dto.getPrice(), dto.getCuisine());
					} else {
//						CUISINE == NULL
//						NAME, PRICE
						results = this.kieSession.getQueryResults("getRestaurantsByNameAndPrice", dto.getName(),
								dto.getPrice());
					}
				} else {
//					PRICE == NULL
					if (dto.getCuisine() != null) {
//					NAME, CUISINE
						results = this.kieSession.getQueryResults("getRestaurantsByNameAndCuisine", dto.getName(),
								dto.getCuisine());
					} else {
//						CUISINE == NULL
//						NAME
						results = this.kieSession.getQueryResults("getRestaurantsByName", dto.getName());
					}
				}
			}
		} else {
//			NAME = NULL
			if (dto.getLocation() != null) {
				if (dto.getPrice() != null) {
					if (dto.getCuisine() != null) {
//						LOCATION, CUISINE, PRICE
						results = this.kieSession.getQueryResults("getRestaurantsByLocationAndPriceAndCuisine",
								dto.getLocation(), dto.getPrice(), dto.getCuisine());
					} else {
//						LOCATION, PRICE
						results = this.kieSession.getQueryResults("getRestaurantsByLocationAndPrice", dto.getLocation(),
								dto.getPrice());
					}
				} else {
//					PRICE == NULL
					if (dto.getCuisine() != null) {
//						LOCATION, CUISINE
						results = this.kieSession.getQueryResults("getRestaurantsByLocationAndCuisine",
								dto.getLocation(), dto.getCuisine());
					} else {
//						LOCATION
						results = this.kieSession.getQueryResults("getRestaurantsByLocation", dto.getLocation());
					}
				}
			} else {
//				LOCATION == NULL, NAME == NULL
				if (dto.getPrice() != null) {
					if (dto.getCuisine() != null) {
//						CUISINE, PRICE
						results = this.kieSession.getQueryResults("getRestaurantsByPriceAndCuisine", dto.getPrice(),
								dto.getCuisine());
					} else {
//						PRICE
						results = this.kieSession.getQueryResults("getRestaurantsByPrice", dto.getPrice());
					}
				} else {
//					PRICE == NULL
					if (dto.getCuisine() != null) {
//						CUISINE
						results = this.kieSession.getQueryResults("getRestaurantsByCuisine", dto.getCuisine());
					} else {
//						NONE
						results = this.kieSession.getQueryResults("getAllRestaurants");
					}
				}
			}
		}

		if (results != null) {
			ArrayList<Restaurant> restaurants = new ArrayList<>();

			for (QueryResultsRow row : results) {
				Restaurant restaurant = (Restaurant) row.get("$restaurant");
				restaurants.add(restaurant);
			}

			return (ArrayList<RestaurantDTO>) this.restaurantMapper.toDTOList(restaurants);
		} else
			return new ArrayList<>();
	}

	public boolean createRestaurantConfiguration(RestaurantConfigDTO configDTO) {
		try {
			InputStream template = new FileInputStream(
					"..\\drools-spring-kjar\\src\\main\\resources\\rules\\templates\\restaurantConfiguration.drt");

			// Compile template to generate new rules
			List<RestaurantConfigDTO> arguments = new ArrayList<>();
			arguments.add(configDTO);
			ObjectDataCompiler compiler = new ObjectDataCompiler();
			String drl = compiler.compile(arguments, template);

			// Save rule to drl file
			FileOutputStream drlFile = new FileOutputStream(
					new File("..\\drools-spring-kjar\\src\\main\\resources\\rules\\perfect-restaurant.drl"));
			drlFile.write(drl.getBytes());
			drlFile.close();

			// Update Rules project
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
			request.setGoals(Arrays.asList("clean", "install"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M2_HOME")));
			invoker.execute(request);
			
			this.restaurantConfigRepository.save(configDTO);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
