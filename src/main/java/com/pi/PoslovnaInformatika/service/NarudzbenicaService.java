package com.pi.PoslovnaInformatika.service;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Narudzbenica;

public interface NarudzbenicaService {

	public Narudzbenica findOne(Long id);
	
	public List<Narudzbenica> findAll();

	public Narudzbenica save(Narudzbenica narudzbenica);

	public List<Narudzbenica> save(List<Narudzbenica> narudzbenice);

	public Narudzbenica delete(Long id);
	
	public void delete(List<Long> ids);
	
}
