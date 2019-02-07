package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Narudzbenica;

public interface NarudzbenicaServiceInterface {

	public Narudzbenica getOne(Integer id);
	
	public List<Narudzbenica> findAll();

	public Narudzbenica save(Narudzbenica narudzbenica);

	public List<Narudzbenica> save(List<Narudzbenica> narudzbenice);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);
	
}
