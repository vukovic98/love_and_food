package com.ftn.uns.ac.rs.love_and_food.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Age;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class PartnerRequirementsTest {
	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private User user;
	
	@Before
	public void setUp() throws ParseException {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		kieSession.getAgenda().getAgendaGroup("partner-requirements").setFocus();
		
		// priprema usera
		user = new User();
		user.setId(1L);
		user.setChildren(Children.WANT_CHILDREN);
		user.setDesiredRelationship(DesiredRelationship.LONG_TERM);
		Date dateOfBirth = format.parse("1989/05/25");
		user.setDateOfBirth(dateOfBirth);
		user.setGender(Gender.MALE);
		user.setSexualOrientation(SexualOrientation.HETEROSEXUAL);
	}
	
	@Test
	public void SetChildren() {
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		kieSession.insert(user);
		kieSession.insert(partnerReq);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		assertEquals(Children.WANT_CHILDREN, partnerReq.getChildren());
	}
	
	@Test
	public void SetDesiredRelationship() {
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		kieSession.insert(user);
		kieSession.insert(partnerReq);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		assertEquals(DesiredRelationship.LONG_TERM, partnerReq.getDesiredRelationship());
	}
	
	@Test
	public void SetAgeCompatibility() {
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		kieSession.insert(user);
		kieSession.insert(partnerReq);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		assertEquals(Age.AGE_30_40, partnerReq.getIdealAge());
		assertEquals(Age.AGE_25_30, partnerReq.getSuitableAge().get(0));
		assertEquals(Age.AGE_40_50, partnerReq.getSuitableAge().get(1));
		assertEquals(Age.AGE_30_40, partnerReq.getSuitableAge().get(2));
	}
	
	@Test
	public void SetPrefferedGender() {
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		kieSession.insert(user);
		kieSession.insert(partnerReq);
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		assertEquals(Gender.FEMALE, partnerReq.getPrefferedGender());
	}
}
