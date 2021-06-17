package com.ftn.uns.ac.rs.love_and_food.tests.love;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.uns.ac.rs.love_and_food.dto.UserDTO;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.PersonalityAnswer;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Children;
import com.ftn.uns.ac.rs.love_and_food.model.enums.DesiredRelationship;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Education;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Gender;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Income;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Location;
import com.ftn.uns.ac.rs.love_and_food.model.enums.QuestionGroup;
import com.ftn.uns.ac.rs.love_and_food.model.enums.Religion;
import com.ftn.uns.ac.rs.love_and_food.model.enums.SexualOrientation;

public class PersonalityTestTest {
	
	private KieSession kieSession;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
	private final UserMapper userMapper = new UserMapper();
	
	@Before
	public void setUp() {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.ftn.uns.ac.rs", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
		kieSession = kContainer.newKieSession("loveAndFoodSession");
		kieSession.getAgenda().getAgendaGroup("personality-test").setFocus();
	}
	
	@Test
	public void PersonalityTestWithMoreZeroAnswers() throws ParseException {
		ArrayList<PersonalityAnswer> testAnswers = new ArrayList<PersonalityAnswer>() {{
			add(new PersonalityAnswer(QuestionGroup.EI, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 0, 1L));
		}};
		
		Date dateOfBirth = format.parse("1998/10/10");
		
		UserDTO userDTO = new UserDTO("Klara", "Markovic", "klara.markovic@gmail.com", "pass", dateOfBirth, Gender.FEMALE, 
				SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Income.INCOME_0_500, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, true);
		
		User user = userMapper.toEntity(userDTO);
		user.setId(1L);
		
		this.kieSession.insert(user);
		for (PersonalityAnswer personalityAnswer : testAnswers) {
			this.kieSession.insert(personalityAnswer);
		}
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		assertEquals(user.getPersonalityTraits(), "JTSE");
	}
	
	@Test
	public void PersonalityTestWithMoreOneAnswers() throws ParseException {
		ArrayList<PersonalityAnswer> testAnswers = new ArrayList<PersonalityAnswer>() {{
			add(new PersonalityAnswer(QuestionGroup.EI, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.EI, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.SN, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.TF, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 0, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 1, 1L));
			add(new PersonalityAnswer(QuestionGroup.JP, 0, 1L));
		}};
		
		Date dateOfBirth = format.parse("1998/10/10");
		
		UserDTO userDTO = new UserDTO("Klara", "Markovic", "klara.markovic@gmail.com", "pass", dateOfBirth, Gender.FEMALE, 
				SexualOrientation.HETEROSEXUAL, Education.HIGH_SCHOOL, Income.INCOME_0_500, Religion.NOT_IMPORTANT, 
				Children.DOESNT_WANT_CHILDREN, DesiredRelationship.SHORT_TERM, Location.GRBAVICA, true, true);
		
		User user = userMapper.toEntity(userDTO);
		user.setId(1L);
		
		this.kieSession.insert(user);
		for (PersonalityAnswer personalityAnswer : testAnswers) {
			this.kieSession.insert(personalityAnswer);
		}
		
		int firedRules = kieSession.fireAllRules();
		assertEquals(4, firedRules);
		assertEquals(user.getPersonalityTraits(), "INFP");
	}

}
