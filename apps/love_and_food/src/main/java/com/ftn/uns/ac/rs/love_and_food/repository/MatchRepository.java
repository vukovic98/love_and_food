package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
