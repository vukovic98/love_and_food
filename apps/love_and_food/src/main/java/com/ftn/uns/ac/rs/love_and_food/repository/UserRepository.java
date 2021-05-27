package com.ftn.uns.ac.rs.love_and_food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
	List<User> findAllByIdNot(Long id);
}
