package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.PoslovnaInformatika.model.Mesto;

public interface MestoRepository extends JpaRepository<Mesto, Integer>{

}
