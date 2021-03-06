package com.ftn.uns.ac.rs.love_and_food.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.love_and_food.dto.GradeDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;

@Component
public class RestaurantMapper implements MapperInterface<Restaurant, RestaurantDTO> {

	@Autowired
	private GradeMapper gradeMapper;
	
	@Override
	public Restaurant toEntity(RestaurantDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantDTO toDTO(Restaurant r) {
		RestaurantDTO dto = new RestaurantDTO(r.getRestaurant_id(), r.getName(), r.getLocation(),
				r.getStartingHours(), r.getEndingHours(), r.getAmbient(), r.getMusic(), r.getCuisine(),
				r.getPriceRange(), r.isGarden(), r.isWifi(), r.isTv(), r.isLiveMusic(), r.isAlcohol(),
				r.isParking(), r.isSmokingArea(), (ArrayList<GradeDTO>) this.gradeMapper.toDTOList(r.getGrades()), r.getImage());
				
		return dto;
	}

	@Override
	public List<Restaurant> toEntityList(List<RestaurantDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RestaurantDTO> toDTOList(List<Restaurant> entityList) {
		List<RestaurantDTO> dtos = new ArrayList<>();
		
		for(Restaurant r : entityList) {
			dtos.add(this.toDTO(r));
		}
		
		return dtos;
	}

}
