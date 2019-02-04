package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.PoslovnaInformatika.model.Faktura;

public interface FakturaRepository extends JpaRepository<Faktura, Long> {

}
