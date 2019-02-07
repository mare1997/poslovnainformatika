package com.pi.PoslovnaInformatika.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Authority;
import com.pi.PoslovnaInformatika.repository.AuthorityRepository;
import com.pi.PoslovnaInformatika.service.interfaces.AuthorityServiceInterface;




@Service
public class AuthoritySercive implements AuthorityServiceInterface {

	@Autowired
	AuthorityRepository authorityRepository;
	
	@Override
	public Authority getByName(String name) {
		
		return authorityRepository.getByName(name);
	}

}
