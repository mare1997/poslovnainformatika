package com.pi.PoslovnaInformatika.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		// TODO Auto-generated method stub
		return fakturaRepository.getOne(id);
	}

	@Override
	public List<Faktura> findAll() {
		// TODO Auto-generated method stub
		return fakturaRepository.findAll();
	}

	@Override
	public Faktura save(Faktura faktura) {
		// TODO Auto-generated method stub
		return fakturaRepository.save(faktura);
	}

	@Override
	public List<Faktura> save(List<Faktura> fakture) {
		// TODO Auto-generated method stub
		return fakturaRepository.saveAll(fakture);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		fakturaRepository.deleteById(id);
	}

	@Override
	public void delete(List<Integer> ids) {
		// TODO Auto-generated method stub
		fakturaRepository.delete((Faktura) ids);
	}
	

	

}
