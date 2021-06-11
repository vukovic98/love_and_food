package com.ftn.uns.ac.rs.love_and_food.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.love_and_food.dto.DatePlaceDTO;
import com.ftn.uns.ac.rs.love_and_food.model.DatePlace;

@Component
public class DatePlaceMapper implements MapperInterface<DatePlace, DatePlaceDTO> {

	@Autowired
	private RestaurantMapper restaurantMapper;
	
	@Override
	public DatePlace toEntity(DatePlaceDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatePlaceDTO toDTO(DatePlace d) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");

		DatePlaceDTO dto = new DatePlaceDTO(this.restaurantMapper.toDTO(d.getRestaurant()), sdf.format(d.getDate()), d.getInitiator().getUsername(),
				d.getSoulmate().getUsername());

		return dto;
	}

	@Override
	public List<DatePlace> toEntityList(List<DatePlaceDTO> dtoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DatePlaceDTO> toDTOList(List<DatePlace> entityList) {
		List<DatePlaceDTO> dtos = new ArrayList<>();

		for (DatePlace d : entityList) {
			dtos.add(this.toDTO(d));
		}

		return dtos;
	}

}
