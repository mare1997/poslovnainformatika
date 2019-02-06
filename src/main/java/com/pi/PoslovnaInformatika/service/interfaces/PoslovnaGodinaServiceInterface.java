package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.PoslovnaInformatika.model.PoslovnaGodina;



public interface PoslovnaGodinaServiceInterface extends JpaRepository<PoslovnaGodina, Integer> {
	List<PoslovnaGodina> getAll();
	PoslovnaGodina getOne(Integer PoslovnaGodina);
	PoslovnaGodina save(PoslovnaGodina PoslovnaGodina);
	void remove(Integer id);
}
