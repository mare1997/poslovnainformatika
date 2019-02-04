package com.pi.PoslovnaInformatika.service;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Faktura;

public interface FakturaService {

	public Faktura findOne(Long id);
	
	public List<Faktura> findAll();

	public Faktura save(Faktura faktura);

	public List<Faktura> save(List<Faktura> fakture);

	public Faktura delete(Long id);
	
	public void delete(List<Long> ids);
}
