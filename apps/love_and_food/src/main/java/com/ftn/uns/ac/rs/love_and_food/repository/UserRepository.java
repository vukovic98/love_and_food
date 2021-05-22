package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;

@Repository
public interface UserRepository extends JpaRepository<RegisteredUser, Long>{

	public RegisteredUser findByEmail(String email);
	
	
}
