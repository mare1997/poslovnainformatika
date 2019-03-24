package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.StavkaFakture;

public interface StavkaFaktureServiceInterface {


	public StavkaFakture getOne(Integer id);
	
	public List<StavkaFakture> findAll();
	
	public List<StavkaFakture> findByFakturaId(Integer idFakture);
	
	public List<StavkaFakture> findByFakturaIdAndActive(Integer idFakture);

	public StavkaFakture save(StavkaFakture stavkaFakture);

	public List<StavkaFakture> save(List<StavkaFakture> stavke);
	
	public void delete(StavkaFakture stavkaFakture);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);
}
