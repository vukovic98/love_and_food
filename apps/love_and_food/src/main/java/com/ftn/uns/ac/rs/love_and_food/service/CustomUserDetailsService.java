package com.ftn.uns.ac.rs.love_and_food.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ftn.uns.ac.rs.love_and_food.model.RegisteredUser;
import com.ftn.uns.ac.rs.love_and_food.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	// Funkcija koja na osnovu username-a iz baze vraca objekat User-a
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// ako se ne radi nasledjivanje, paziti gde sve treba da se proveri email
		RegisteredUser registeredUser = userRepository.findByEmail(email);
		
		if (registeredUser == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email)); 
		} else {
			return registeredUser;
		}
	}

	// Funkcija pomocu koje korisnik menja svoju lozinku
	public void changePassword(String oldPassword, String newPassword) {

		// Ocitavamo trenutno ulogovanog korisnika
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String username = ((RegisteredUser) currentUser.getPrincipal()).getEmail();

		if (authenticationManager != null) {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
		} else {
			return;
		}
		RegisteredUser registeredUser = (RegisteredUser) loadUserByUsername(username);
        System.out.println(registeredUser.getEmail());
		// pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati
		// ne zelimo da u bazi cuvamo lozinke u plain text formatu
		registeredUser.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(registeredUser);
	}

}