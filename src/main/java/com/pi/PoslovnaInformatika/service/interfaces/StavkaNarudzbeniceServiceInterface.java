package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;

public interface StavkaNarudzbeniceServiceInterface {

	public StavkaNarudzbenice findOne(Long id);
	
	public List<StavkaNarudzbenice> findAll();

	public StavkaNarudzbenice save(StavkaNarudzbenice stavkaNarudzbenice);

	public List<StavkaNarudzbenice> save(List<StavkaNarudzbenice> stavke);

	public StavkaNarudzbenice delete(Long id);
	
	public void delete(List<Long> ids);
}
