package com.pi.PoslovnaInformatika.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.PoslovnaInformatika.model.Authority;





public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	Authority getByName(String name);
}
