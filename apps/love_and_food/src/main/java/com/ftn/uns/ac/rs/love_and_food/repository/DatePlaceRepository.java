package com.ftn.uns.ac.rs.love_and_food.repository;


import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.DatePlace;

@Repository
public interface DatePlaceRepository extends JpaRepository<DatePlace, Long> {
	
	@Query(value = "SELECT * FROM date_place where initiator_id = ?1 or soulmate_id = ?1", nativeQuery = true)
	public ArrayList<DatePlace> findAllByUser(long id);
	
}
