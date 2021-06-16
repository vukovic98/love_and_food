package com.ftn.uns.ac.rs.love_and_food.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;

@Service
public class RegisteredUserService {
	
	@Autowired
	private RegisteredUserRepository registeredUserRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public RegisteredUser findByEmail(String email) {
		return this.registeredUserRepository.findByEmail(email);
	}
	
	public RegisteredUser save(RegisteredUser registeredUser) {
		this.sendMail(registeredUser);
		return this.registeredUserRepository.save(registeredUser);
	}

	@Async
	private void sendMail(RegisteredUser registeredUser) {
		try {
			MimeMessage msg = this.javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);

			helper.setTo(registeredUser.getEmail());
			helper.setSubject("Love&Food : Verification Mail");

			helper.setText(createMailBody(registeredUser.getEmail()), true);
			this.javaMailSender.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public String createMailBody(String mail) {

		StringBuffer sb = new StringBuffer();
		
		sb.append("<code>Hello, <br><br>");
		sb.append("We are sorry for the inconvenience. We detected some suspicious activities from your account.");
		sb.append("You tried to login with wrong password more than 5 times in the period of 2 minutes.<br>");
		sb.append("If this was you, please click on the following link in order to enable your account.<br><br>");
		sb.append("<h2>http://localhost:8080/auth/enable-account/" + mail + "</h2><br><br>");
		sb.append("Sincerely,<br> Love&Food Team</code>");
		
		return sb.toString();
	}

}
