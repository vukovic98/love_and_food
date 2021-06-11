package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.DatePlace;
import com.ftn.uns.ac.rs.love_and_food.repository.DatePlaceRepository;

@Service
public class DatePlaceService {

	@Autowired
	private DatePlaceRepository datePlaceRepository;
	
	public ArrayList<DatePlace> findAllByUser(long id) {
		return this.datePlaceRepository.findAllByUser(id);
	}
}
