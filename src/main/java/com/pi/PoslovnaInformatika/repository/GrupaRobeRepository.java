package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.PoslovnaInformatika.model.GrupaRobe;


public interface GrupaRobeRepository extends JpaRepository<GrupaRobe , Integer> {
	GrupaRobe getByName(String name); 
}
