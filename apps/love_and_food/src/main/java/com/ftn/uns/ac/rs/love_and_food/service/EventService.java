package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	@Autowired
    private KieStatefulSessionService sessionService;
	
	public QueryResults findAllSuspiciousActivity() {
		KieSession kieSession = sessionService.getEventsSession();
		QueryResults results = kieSession.getQueryResults( "getAllSuspiciousEvents" );
		
		return results;
	}

}
