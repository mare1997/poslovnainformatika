package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.StavkaNarudzbenice;

public interface StavkaNarudzbeniceServiceInterface {

	public StavkaNarudzbenice getOne(Integer id);
	
	public List<StavkaNarudzbenice> findAll();

	public StavkaNarudzbenice save(StavkaNarudzbenice stavkaNarudzbenice);

	public List<StavkaNarudzbenice> save(List<StavkaNarudzbenice> stavke);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);
}
