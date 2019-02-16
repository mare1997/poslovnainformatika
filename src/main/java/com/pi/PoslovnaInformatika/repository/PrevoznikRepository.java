package com.pi.PoslovnaInformatika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.PoslovnaInformatika.model.Prevoznik;


public interface PrevoznikRepository extends JpaRepository<Prevoznik, Integer> {
	
}
