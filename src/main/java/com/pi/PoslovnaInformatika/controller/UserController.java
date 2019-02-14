package com.pi.PoslovnaInformatika.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pi.PoslovnaInformatika.dto.UserDTO;
import com.pi.PoslovnaInformatika.model.Authority;

import com.pi.PoslovnaInformatika.model.User;
import com.pi.PoslovnaInformatika.service.interfaces.AuthorityServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.PreduzeceServiceInterface;
import com.pi.PoslovnaInformatika.service.interfaces.UserServiceInterface;


@RestController
@RequestMapping(value = "api/users")
public class UserController {


	@Autowired
	private UserServiceInterface userServiceIterface;
	
	@Autowired
	private AuthorityServiceInterface asi; 
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private PreduzeceServiceInterface psi;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
    	User user= userServiceIterface.getOne(id);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
    	List<User> users = userServiceIterface.getAll();
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			userDTO.add(new UserDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "get/{username}")
	public ResponseEntity<UserDTO> getUserbyUsername(@PathVariable("username") String username){
    	
		User user= userServiceIterface.getByUsername(username);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> addUser(@Validated @RequestBody UserDTO userDto,Errors errors){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		User username=userServiceIterface.getByUsername(userDto.getUsername());
		if(username != null) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
		User user= new User();
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setUsername(userDto.getUsername());
		user.setPassword(passwordencoder.encode(userDto.getPassword()));
		user.setPreduzece(psi.getOne(userDto.getPreduzece().getId()));
		Authority a = asi.getByName(userDto.getAutority());
		
		Set<Authority> aa= new HashSet<>();
		aa.add(a);
		user.setUser_authorities(aa);
		
		
		userServiceIterface.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.CREATED);
	}
	@PutMapping(value = "/update/{id}" ,consumes = "application/json" )
	public ResponseEntity<?> updateUser(@Validated @RequestBody UserDTO userDto,Errors errors,@PathVariable("id")int id){
		if(errors.hasErrors()) {
			return new ResponseEntity<String>(errors.getAllErrors().toString(),HttpStatus.BAD_REQUEST);
		}
		User user=userServiceIterface.getOne(id);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		
		user.setPassword(passwordencoder.encode(userDto.getPassword()));
		user.setPreduzece(psi.getOne(userDto.getPreduzece().getId()));
		Authority a = asi.getByName(userDto.getAutority());
		
		Set<Authority> aa= new HashSet<>();
		aa.add(a);
		user.setUser_authorities(aa);
		
		
		userServiceIterface.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
		
		User user= userServiceIterface.getOne(id);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		user.setObrisano(true);
		userServiceIterface.save(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
