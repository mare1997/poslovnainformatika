package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.PoslovnaInformatika.model.User;



public interface UserRepository extends JpaRepository<User,Integer>  {

	User getByUsername(String username);
	User getByUsernameAndPassword(String username,String password);
	
}
