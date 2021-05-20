package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	
	Authority findByName(String name);
	
}
