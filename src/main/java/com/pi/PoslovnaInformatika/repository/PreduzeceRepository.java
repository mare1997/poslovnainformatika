package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.PoslovnaInformatika.model.Preduzece;


public interface PreduzeceRepository extends JpaRepository<Preduzece, Integer> {
	Preduzece getByName(String name);	
}
