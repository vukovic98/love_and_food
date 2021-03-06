package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.SoulmateConfig;

@Repository
public interface SoulmateConfigRepository extends JpaRepository<SoulmateConfig, Long> {
	
	SoulmateConfig findByUserId(Long userId);

}
