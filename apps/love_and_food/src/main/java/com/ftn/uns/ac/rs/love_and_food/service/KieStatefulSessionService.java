package com.ftn.uns.ac.rs.love_and_food.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KieStatefulSessionService {

	@Autowired
	private KieContainer kieContainer;
    private KieSession rulesSession;

    public KieContainer getKieContainer() {
        return kieContainer;
    }

    public KieSession getRulesSession() {
    	if (this.rulesSession == null) {
        	rulesSession = kieContainer.newKieSession("loveAndFoodSession");	
    	}
        return rulesSession;
    }

    public void setRulesSession(KieSession kieSession) {
        this.rulesSession = kieSession;
    }
    
    public void releaseRulesSession(){
        this.rulesSession.dispose();
        this.rulesSession = null;
    }

}
