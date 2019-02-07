package com.pi.PoslovnaInformatika.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.pi.PoslovnaInformatika.model.User;
import com.pi.PoslovnaInformatika.repository.UserRepository;








@Service
public class CustomUserDetailsService implements UserDetailsService {

	protected final Log LOGGER = LogFactory.getLog(getClass());
	
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	//FUnkcija koja nalazi User-a iz baze pomocu username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userrepository.getByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
	}
	//Funkcija pomocu koje korisnik menja svoju lozinku
	 public void changePassword(String oldPassword, String newPassword) {
		 Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
	     String username = currentUser.getName();
	     if (authenticationManager != null) {
	            LOGGER.debug("Re-authenticating user '"+ username + "' for password change request.");
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
	        } else {
	            LOGGER.debug("No authentication manager set. Can't change Password!");
	            return;
	        }
	     LOGGER.debug("Changing password for user '"+ username + "'");
	     User user = (User) loadUserByUsername(username);
	     
	     //pre nego sto u bazu upisemo novu lozinku, potrebno ju je hesirati ne zelimo da u bazi cuvamo lozinke u plain text formatu
	     user.setPassword(passwordencoder.encode(newPassword));
	     userrepository.save(user);
	     
	 }
	

}
