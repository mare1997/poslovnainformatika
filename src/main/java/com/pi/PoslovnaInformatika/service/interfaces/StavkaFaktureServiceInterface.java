package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import com.pi.PoslovnaInformatika.model.StavkaFakture;

public interface StavkaFaktureServiceInterface {


	public StavkaFakture findOne(Long id);
	
	public List<StavkaFakture> findAll();

	public StavkaFakture save(StavkaFakture stavkaFakture);

	public List<StavkaFakture> save(List<StavkaFakture> stavke);

	public StavkaFakture delete(Long id);
	
	public void delete(List<Long> ids);
}
