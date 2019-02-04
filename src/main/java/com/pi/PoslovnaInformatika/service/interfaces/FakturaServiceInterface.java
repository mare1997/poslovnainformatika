package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.Faktura;

public interface FakturaServiceInterface {

	public Faktura findOne(Long id);
	
	public List<Faktura> findAll();

	public Faktura save(Faktura faktura);

	public List<Faktura> save(List<Faktura> fakture);

	public Faktura delete(Long id);
	
	public void delete(List<Long> ids);
}
