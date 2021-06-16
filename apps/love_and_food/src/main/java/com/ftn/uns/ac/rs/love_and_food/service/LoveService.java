package com.ftn.uns.ac.rs.love_and_food.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.dto.ContactSoulmateDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.CoupleDTO;
import com.ftn.uns.ac.rs.love_and_food.dto.UserRatingDTO;
import com.ftn.uns.ac.rs.love_and_food.event.MateRatingEvent;
import com.ftn.uns.ac.rs.love_and_food.exceptions.NonExistingIdException;
import com.ftn.uns.ac.rs.love_and_food.mapper.UserMapper;
import com.ftn.uns.ac.rs.love_and_food.model.Alarm;
import com.ftn.uns.ac.rs.love_and_food.model.Match;
import com.ftn.uns.ac.rs.love_and_food.model.PartnerRequirements;
import com.ftn.uns.ac.rs.love_and_food.model.SoulmateConfig;
import com.ftn.uns.ac.rs.love_and_food.model.User;
import com.ftn.uns.ac.rs.love_and_food.model.enums.AlarmType;
import com.ftn.uns.ac.rs.love_and_food.repository.AlarmRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.MatchRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.SoulmateConfigRepository;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class LoveService {

	@Autowired
    private KieSession kieSession;
	
	@Autowired
	@Qualifier(value = "eventsSession")
	private KieSession eventsSession;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MatchRepository matchRepository;
	
	@Autowired
	private AlarmRepository alarmRepository;
	
	@Autowired
	private SoulmateConfigRepository soulmateConfigRepository;
	private JavaMailSender javaMailSender;
	
	private UserMapper userMapper = new UserMapper();
	
	public User findMatch(String email) {
		// dobavljanje ulogovanog korisnika
		User user = userRepository.findByEmail(email);
		
		//izmena soulmate config
		SoulmateConfig soulmateConfig = soulmateConfigRepository.findByUserId(user.getId());
		
		this.createSoulmateConfiguration(soulmateConfig);
		
		// kreiranje zahteva vezanog za tog korisnika
		PartnerRequirements partnerReq = new PartnerRequirements(user.getId());
		
		this.kieSession.setGlobal("loggedInUserId", user.getId());
		
		this.kieSession.insert(partnerReq);
		
		this.kieSession.getAgenda().getAgendaGroup("partner-requirements").setFocus();
		this.kieSession.fireAllRules();
		
		this.kieSession.getAgenda().getAgendaGroup("partner-age").setFocus();
		this.kieSession.fireAllRules();
		this.kieSession.getAgenda().getAgendaGroup("prepare-soulmate").setFocus();
		this.kieSession.fireAllRules();
		
		User soulmate = (User) this.kieSession.getGlobal("soulmate");
		
		if( soulmate != null ) {
			Match match = new Match();
			match.setInitiator(user);
			match.setSoulmate(soulmate);
			match.setMatchDate(LocalDate.now());
			
			matchRepository.save(match);
			
			return soulmate;
		}
		
		return null;
	}
	
	private boolean createSoulmateConfiguration(SoulmateConfig soulmateConfig) {
		try {
			InputStream template = new FileInputStream(
					"..\\drools-spring-kjar\\src\\main\\resources\\rules\\templates\\soulmateConfiguration.drt");

			// Compile template to generate new rules
			List<SoulmateConfig> arguments = new ArrayList<>();
			arguments.add(soulmateConfig);
			ObjectDataCompiler compiler = new ObjectDataCompiler();
			String drl = compiler.compile(arguments, template);

			// Save rule to drl file
			FileOutputStream drlFile = new FileOutputStream(
					new File("..\\drools-spring-kjar\\src\\main\\resources\\rules\\soulmate.drl"));
			drlFile.write(drl.getBytes());
			drlFile.close();

			// Update Rules project
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("../drools-spring-kjar/pom.xml"));
			request.setGoals(Arrays.asList("clean", "install"));

			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("M2_HOME")));
			invoker.execute(request);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Match rateDate(int matchId, int rating) throws NonExistingIdException {
		Match match = matchRepository.findById((long) matchId).orElse(null);
		if ( match != null) {
			match.setRating(rating);
			MateRatingEvent event = new MateRatingEvent(new Date(), match);
			this.eventsSession.insert(event);
			match = matchRepository.save(match);
			this.eventsSession.insert(match);
			
			this.eventsSession.getAgenda().getAgendaGroup("user-rating-event").setFocus();
			this.eventsSession.fireAllRules();
			
			if(event.isHappened() && event.getMessage().contains("WARNING")) {
				this.alarmRepository.save(new Alarm(AlarmType.BAD_RATING_USER_ALARM, event.getMessage(), new Date()));
			} else if(event.isHappened() && event.getMessage().contains("INFO") ) {
				this.alarmRepository.save(new Alarm(AlarmType.GOOD_RATING_USER_ALARM, event.getMessage(), new Date()));
			}
			return match;
		}
		
		throw new NonExistingIdException("Match");
	}
	
	public List<User> reportLiars() {
		
		Set<User> liars = new HashSet<User>();
		
		this.kieSession.setGlobal("liars", liars);
		
		this.kieSession.getAgenda().getAgendaGroup("liars-report").setFocus();
		this.kieSession.fireAllRules();
		
		List<User> listLiars = new ArrayList<>(liars);
		
		return listLiars;
	}
	
	public List<UserRatingDTO> reportMVPs() {
		
		List<UserRatingDTO> mvps = new ArrayList<UserRatingDTO>();
		
		this.kieSession.setGlobal("mvps", mvps);
		
		this.kieSession.getAgenda().getAgendaGroup("MVPs").setFocus();
		this.kieSession.fireAllRules();
		
		return mvps;
	}
	
	public Set<CoupleDTO> reportCouples(int matchedTimes) {
		QueryResults results = kieSession.getQueryResults( "getAllUsersWhoMatchedAtLeast", matchedTimes);
		
		List<User> users1 = new ArrayList<>();
		List<User> users2 = new ArrayList<>();
		
		Set<CoupleDTO> couples = new HashSet<>();
		
		for ( QueryResultsRow row : results ) {
			users1 = (List<User>) row.get( "$users1" );
			users2 = (List<User>) row.get( "$users2" );
		}
		for (int i = 0; i < users1.size(); i++) {
			User user1 = users1.get(i);
			User user2 = users2.get(i);
			CoupleDTO couple = new CoupleDTO(userMapper.toDTO(user1), userMapper.toDTO(user2));
			couples.add(couple);
		}
		
		return couples;
	}

	@Async
	public void contactSoulmate(ContactSoulmateDTO dto, String initiatorEmail) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(dto.getSoulmateEmail());
			helper.setSubject("Love&Food :  Good news!");
			helper.setText(createMailBody(dto.getMessage(), initiatorEmail), true);
			this.javaMailSender.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String createMailBody(String message, String initiatorEmail) {

		StringBuffer sb = new StringBuffer();
		
		sb.append("<code>Hello, <br><br>");
		sb.append("We are the team from Love&Food, and we are contacting you for a wonderfull reason.");
		sb.append("Our website found you a soulmate!<br>");
		sb.append("But wait! It get's even better! Your soulmate decided to contact you, so you can consider us as your middleman! :)<br>");
		sb.append("This is what your perfect date said: <br><br>");

		sb.append("<h3> " + message + " </h3><br>");
		sb.append("If you want to respond, here's his email: " + initiatorEmail + " !<br><br>");
		sb.append("Sincerely,<br> Love&Food Team</code>");
		
		return sb.toString();
	}
	
}
