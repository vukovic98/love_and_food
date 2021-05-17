package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;

@Service
public class TestService {

	@Autowired
	private KieContainer kieContainer;
	
	public RegisteredUser getUser(RegisteredUser registeredUser) {
		KieSession kieSession = kieContainer.newKieSession();
		kieSession.insert(registeredUser);
		kieSession.fireAllRules();
		kieSession.dispose();
		return registeredUser;
	}

}
