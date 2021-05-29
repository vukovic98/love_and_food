package com.ftn.uns.ac.rs.love_and_food.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.ObjectDataCompiler;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.dto.DateRangeDTO;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.User;

public class MatchesByTimeIntervalTemplateTest {

	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private List<Match> matches;
	private User user1;
	private User user2;
	
	@Before
	public void setUp() throws IOException, MavenInvocationException, ParseException {
		InputStream template = new FileInputStream(
				"../drools-spring-kjar/src/main/resources/rules/templates/matchesByTimeInterval.drt");
		List<DateRangeDTO> arguments = new ArrayList<DateRangeDTO>();
		LocalDate startDate = LocalDate.of(2020, 1, 1);
		LocalDate endDate = LocalDate.of(2020, 12, 31);
		arguments.add(new DateRangeDTO(startDate, endDate));
		
		ObjectDataCompiler compiler = new ObjectDataCompiler();
		String drl = compiler.compile(arguments, template);
		
		FileOutputStream drlFile = new FileOutputStream(new File(
				"..\\drools-spring-kjar\\src\\main\\resources\\rules\\matchesByTimeInterval.drl"));
		drlFile.write(drl.getBytes());
		drlFile.close();
		
		InvocationRequest request = new DefaultInvocationRequest();
		request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
		request.setGoals(Arrays.asList("clean", "install"));

		Invoker invoker = new DefaultInvoker();
		invoker.setMavenHome(new File(System.getenv("M2_HOME")));
		invoker.execute(request);
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
        
        matches = new ArrayList<Match>();
        kieSession.setGlobal("resultMatches", matches);
        kieSession.getAgenda().getAgendaGroup("match-interval").setFocus();
        
        user1 = new User();
        user1.setId(1L);
        Date dateOfBirth = format.parse("2003/01/30");
        user1.setDateOfBirth(dateOfBirth);
        kieSession.insert(user1);
        user2 = new User();
        user2.setId(2L);
        user2.setDateOfBirth(dateOfBirth);
        kieSession.insert(user2);
	}

	@Test
	public void MatchByTimeIntervalTestExistingMatches() throws ParseException {
		Match match1 = new Match();
		match1.setId(1L);
        match1.setInitiator(user1);
        match1.setSoulmate(user2);
        match1.setMatchDate(LocalDate.parse("2020-05-17"));
        
        kieSession.insert(match1);
        
        int firedRules = kieSession.fireAllRules();
        matches = (List<Match>) kieSession.getGlobal("resultMatches");

        assertEquals(1, firedRules);
        assertEquals(1, matches.size());
        assertEquals(match1.getId(), matches.get(0).getId());
	}
	
	@Test
	public void MatchByTimeIntervalTestNoExistingMatches() throws ParseException {
		Match match1 = new Match();
		match1.setId(1L);
        match1.setInitiator(user1);
        match1.setSoulmate(user2);
        match1.setMatchDate(LocalDate.parse("2018-05-17"));
        
        kieSession.insert(match1);
        
        int firedRules = kieSession.fireAllRules();
        matches = (List<Match>) kieSession.getGlobal("resultMatches");

        assertEquals(1, firedRules);
        assertEquals(0, matches.size());
	}
}
