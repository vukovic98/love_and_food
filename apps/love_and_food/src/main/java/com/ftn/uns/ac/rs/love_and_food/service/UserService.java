package com.ftn.uns.ac.rs.love_and_food.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private KieSession kieSession;
	
	public User findByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
	
	public List<User> findAll() {
		QueryResults results = kieSession.getQueryResults( "getAllUsers" );
		List<User> users = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    User user = ( User ) row.get( "$user" );
		    users.add(user);
		}
		
		return users;
	}
}
