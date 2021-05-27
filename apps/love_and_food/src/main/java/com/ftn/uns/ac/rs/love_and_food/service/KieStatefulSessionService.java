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
    private KieSession eventsSession;

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
    	if (this.rulesSession != null) {
            this.rulesSession.dispose();
            this.rulesSession = null;
    	}
    }
    public KieSession getEventsSession() {
        if(eventsSession == null){
    		eventsSession = kieContainer.newKieSession("eventsSession");
        }
        return eventsSession;
    }

    public void setEventsSession(KieSession kieSession) {
        this.eventsSession = kieSession;
    }

}
