package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.dto.RestaurantConfigDTO;

@Repository
public interface RestaurantConfigRepository extends JpaRepository<RestaurantConfigDTO, Long> {

	@Query(value = "SELECT * FROM love_and_food.restaurant_config ORDER BY date DESC LIMIT 1", nativeQuery = true)
	public RestaurantConfigDTO findLastOne();
	
}
