package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.PoslovnaInformatika.model.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Integer> {

}
