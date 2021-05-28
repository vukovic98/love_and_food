package com.ftn.uns.ac.rs.love_and_food.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.RegisteredUserRepository;

@Component
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	RegisteredUserRepository registeredUserRepository;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {

		RegisteredUser registeredUser = registeredUserRepository.findByEmail(username);

		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		

		if (enc.matches((String)authentication.getCredentials(), registeredUser.getPassword()))
			return registeredUser;
		else
			throw new BadCredentialsException("Password incorrect");
			
	}

}
