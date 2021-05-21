package com.ftn.uns.ac.rs.love_and_food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.repository.UserMatchRepository;

@Service
public class MatchService {

	@Autowired
	private UserMatchRepository matchRepository;
	
	public Match findByInitiator(Long id) {
		return this.matchRepository.findByInitiator(id);
	}
	
}
