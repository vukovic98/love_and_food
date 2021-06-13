package com.ftn.uns.ac.rs.love_and_food.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	public ArrayList<Restaurant> findByName(String name);
	
}
