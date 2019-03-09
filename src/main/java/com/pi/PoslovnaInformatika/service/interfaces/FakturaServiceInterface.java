package com.pi.PoslovnaInformatika.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pi.PoslovnaInformatika.dto.FakturaDTO;
import com.pi.PoslovnaInformatika.model.Faktura;

public interface FakturaServiceInterface {

	public Faktura getOne(Integer id);
	
	public Page<Faktura> findAll(Pageable pageRequest);

	public Faktura save(Faktura faktura);

	public List<Faktura> save(List<Faktura> fakture);

	public void delete(Integer id);
	
	public void delete(List<Integer> ids);

	public Faktura edit(FakturaDTO editedFakturaDTO, Integer id);
	
	public void softDelete(Integer id);
}
