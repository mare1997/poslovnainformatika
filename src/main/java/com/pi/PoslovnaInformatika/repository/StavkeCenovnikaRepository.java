package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pi.PoslovnaInformatika.model.StavkaCenovnika;


public interface StavkeCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer>{

}
