package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

	@Query(value = "SELECT * FROM grade WHERE restaurant_id = ?1 AND user_id = ?2", nativeQuery = true)
	public Grade hasGraded(long restaurantID, long userID);

}
