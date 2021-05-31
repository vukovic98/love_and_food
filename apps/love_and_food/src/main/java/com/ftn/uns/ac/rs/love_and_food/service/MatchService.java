package com.ftn.uns.ac.rs.love_and_food.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.drools.template.ObjectDataCompiler;


import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.DateRangeDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.repository.UserMatchRepository;

@Service
public class MatchService {

	@Autowired
	private UserMatchRepository matchRepository;
	
	@Autowired
    private KieSession kieSession;
	
	public Match findByInitiator(Long id) {
		return this.matchRepository.findByInitiator(id);
	}
	
	public List<Match> findAll() {
		QueryResults results = kieSession.getQueryResults( "getAllMatches" );
		List<Match> matches = new ArrayList<>();
		
		for ( QueryResultsRow row : results ) {
		    Match match = ( Match ) row.get( "$match" );
		    matches.add(match);
		}
		
		return matches;
	}
	
	public List<Match> findInRange(DateRangeDTO dateRangeDTO) {
		try {
			InputStream template = new FileInputStream(
					"..\\drools-spring-kjar\\src\\main\\resources\\rules\\templates\\matchesByTimeInterval.drt");

			// Compile template to generate new rules
			List<DateRangeDTO> arguments = new ArrayList<>();
			arguments.add(dateRangeDTO);
			ObjectDataCompiler compiler = new ObjectDataCompiler();
			String drl = compiler.compile(arguments, template);

			// Save rule to drl file
			FileOutputStream drlFile = new FileOutputStream(new File(
					"..\\drools-spring-kjar\\src\\main\\resources\\rules\\matchesByTimeInterval.drl"));
			drlFile.write(drl.getBytes());
			drlFile.close();

			// Update Rules project
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
			request.setGoals(Arrays.asList("clean", "install"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M2_HOME")));
			invoker.execute(request);
			
			// Fire new rule
			List<Match> matches = new ArrayList<Match>();
			kieSession.setGlobal("resultMatches", matches);
			kieSession.getAgenda().getAgendaGroup("match-interval").setFocus();
			kieSession.fireAllRules();
			return matches;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
