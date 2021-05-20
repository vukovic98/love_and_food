package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	public Admin findByEmail(String email);
}
