package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pi.PoslovnaInformatika.model.Faktura;
import com.pi.PoslovnaInformatika.repository.FakturaRepository;
import com.pi.PoslovnaInformatika.service.interfaces.FakturaServiceInterface;

@Service
public class FakturaService implements FakturaServiceInterface {

	@Autowired
	FakturaRepository fakturaRepository;

	@Override
	public Faktura getOne(Integer id) {
		return fakturaRepository.getOne(id);
	}

	@Override
	public Page<Faktura> findAll(Pageable pageRequest) {
		
		return fakturaRepository.findAll(pageRequest);
	}

	@Override
	public Faktura save(Faktura faktura) {
		return fakturaRepository.save(faktura);
	}

	@Override
	public List<Faktura> save(List<Faktura> fakture) {
		return fakturaRepository.saveAll(fakture);
	}

	@Override
	public void delete(Integer id) {
		fakturaRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		fakturaRepository.delete((Faktura) ids);
	}
	

	

}
