package com.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.model.Faktura;

public interface FakturaRepository extends JpaRepository<Faktura, Long> {

}
