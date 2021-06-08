package com.ftn.uns.ac.rs.love_and_food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ftn.uns.ac.rs.love_and_food.model.Match;

@Repository
public interface UserMatchRepository extends JpaRepository<Match, Long> {

	@Query(value = "SELECT * FROM user_match WHERE initiator_id = ?1 ORDER BY match_date DESC LIMIT 1", nativeQuery = true)
	public Match findByInitiator(Long id);
	
	@Query(value = "SELECT count(*) FROM user_match where initiator_id = ?1 or soulmate_id = ?1", nativeQuery = true)
	public int hasAMatch(long id);

}
