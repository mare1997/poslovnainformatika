package com.pi.PoslovnaInformatika.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pi.PoslovnaInformatika.model.User;
import com.pi.PoslovnaInformatika.model.UserTokenState;
import com.pi.PoslovnaInformatika.security.JwtAuthenticationRequest;
import com.pi.PoslovnaInformatika.security.TokenHelper;
import com.pi.PoslovnaInformatika.service.CustomUserDetailsService;



@RestController
@RequestMapping(value = "api/auth")
@CrossOrigin("*")
public class AuthenticationController {

	 @Autowired
	    TokenHelper tokenHelper;

	    @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private CustomUserDetailsService userDetailsService;


	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken(
	            @RequestBody JwtAuthenticationRequest authenticationRequest,
	            HttpServletResponse response
	    ) throws AuthenticationException, IOException {
	    	System.out.println(authenticationRequest.getUsername()+" " + authenticationRequest.getPassword());
	        // Izvrsavanje security dela
	        final Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        authenticationRequest.getUsername(),
	                        authenticationRequest.getPassword()
	                )
	        );

	        // Ubaci username + password u kontext
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        // Kreiraj token
	        User user = (User)authentication.getPrincipal();
	        String jws = tokenHelper.generateToken( user.getUsername());

	        // Vrati token kao odgovor na uspesno autentifikaciju
	        return ResponseEntity.ok(new UserTokenState(jws));
	    }

	    
}
