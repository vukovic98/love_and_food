package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long>{
	
	RegisteredUser findByEmail(String email);
}
