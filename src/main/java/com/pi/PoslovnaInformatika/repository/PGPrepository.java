package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.pi.PoslovnaInformatika.model.PoslovnaGodinaPreduzeca;

public interface PGPrepository extends JpaRepository<PoslovnaGodinaPreduzeca, Integer> {

}
