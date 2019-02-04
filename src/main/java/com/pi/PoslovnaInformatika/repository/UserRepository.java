package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.PoslovnaInformatika.model.User;



public interface UserRepository extends JpaRepository<User,Integer>  {

	User getByUsername(String username);
	User getByUsernameAndPassword(String username,String password);
	
}
